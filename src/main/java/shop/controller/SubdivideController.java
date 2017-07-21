package shop.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.bean.Subdivide;
import shop.exception.MyException;
import shop.service.CategoryService;
import shop.service.SubdivideService;

@Controller
@RequestMapping(value="/subdivide")
public class SubdivideController {
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SubdivideService subdivideService;
	public SubdivideService getCategoryService() {
		return subdivideService;
	}
	@Autowired
	public void setSubdivideService(SubdivideService categoryService) {
		this.subdivideService = categoryService;
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String beforInsert(Model model){
		model.addAttribute("subdivide",new Subdivide());
		model.addAttribute("categorys",categoryService.findAll());
		return "back/addSubdivide";
	}


	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute Subdivide subdivide,ModelMap mp){
		String info=null;//定义一个变量存储视图，添加成功或者失败时返回到什么界面！
		try{
		subdivideService.insert(subdivide);
		info="forward:/subdivide/subdivideView";
		}
		catch(MyException e){
			e.printStackTrace();
			mp.addAttribute("message", "商品种类不能为空");	
			mp.addAttribute(subdivide);
			info="back/addSubdivide";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			info="back/subdivideView";
			mp.addAttribute("message", "系统出现异常");	
		}
			return info;
		
	}
	@RequestMapping(value="/subdivideView")
	public String subdivideView(ModelMap mp){
		
		List<Subdivide> subdivides=subdivideService.findAll();
		if(subdivides==null)//如果查询出来的结果是空的，人为的添加一个空的category对象，防止前台报错
		{
			subdivides=new LinkedList<Subdivide>();
			subdivides.add(new Subdivide());
		}
		mp.addAttribute("subdivides",subdivides);
		return "back/subdivideView";
	}

}
