package shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import shop.bean.Product;
import shop.service.CategoryService;
import shop.service.ProductService;
import shop.service.SubdivideService;

/**
 * @author lh
 *
 */
@Controller
@RequestMapping(value="/product")
public class ProductController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired SubdivideService subdivideService;
	
	@RequestMapping(value="/productView")
	public String productView(ModelMap model){
		List<Product> products=productService.findAll();
		model.addAttribute("products",products);
		return "back/productView";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addProductBefor(ModelMap model){
		model.addAttribute("subdivides",subdivideService.findAll());
		model.addAttribute("product",new Product());
		return "back/addProduct";
	}
	
	
	/**
	 * @param product
	 * @param model
	 * @param request
	 * @param file
	 * 这个控制器只适合上传一个multipartFile对象
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addProduct(@ModelAttribute Product product,  ModelMap model,HttpServletRequest request, @RequestParam("image") MultipartFile file){
		String flag=null;
		
		try {
			if(productService.insert(product,file,request)){
				flag="forward:/product/productView";
			}
			else{
				flag="back/addProduct";
				model.addAttribute("product",product);
				model.addAttribute("message","添加产品失败!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			flag="back/addProduct";
			model.addAttribute("product",product);
			model.addAttribute("message","添加产品失败!");
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * @param image
	 * @return
	 * 单独上传多个图片控制器，需要使用@RequestParam 注解修饰，MVC才能识别多个file对象，并将其转换成数组
	 */
	@RequestMapping(value="/upload")
	public String upload(@RequestParam MultipartFile[] image){
		
		return null;
		
	}
	

}
