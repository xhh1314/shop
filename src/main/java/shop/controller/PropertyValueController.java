package shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.bean.Product;
import shop.bean.Property;
import shop.bean.PropertyValue;
import shop.service.ProductService;
import shop.service.PropertyValueService;
import shop.util.ResponseWrite;

@Controller
@RequestMapping(value="/pvc")
public class PropertyValueController {
	@Autowired
	private PropertyValueService propertyValueService;
	@Autowired
	private ProductService productService;
	
	//传入一个产品uuid
	@RequestMapping(value="/addBefor/{pduuid}")
	public String addBefor(@PathVariable String pduuid,ModelMap model){
		List<Property> propertys=propertyValueService.getPropertyByProductUUID(pduuid);
		model.addAttribute("pd_uuid",pduuid);
		model.addAttribute("propertys",propertys);
		return "back/addPropertyValue";
	}
	//produces注解为text/html时,可以接受application/json，格式数据。反之则不行
	@RequestMapping(value="/add",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String add(@RequestBody List<PropertyValue> propertyValues ,ModelMap model,HttpServletResponse response) throws IOException{
		
		String flag=null;
		
		try {
			propertyValueService.add(propertyValues);
			flag="添加成功！";
		} catch (Exception e) {
			// TODO: handle exception
			flag="添加失败！";
			e.printStackTrace();
		}
		//这里返回ajax消息给前端
		ResponseWrite.write(response, flag);
		
		//return "forward:/product/productView"; 这里返回消息给ajax，就不能再转发到其他视图了
		return null;
	}

	
}
