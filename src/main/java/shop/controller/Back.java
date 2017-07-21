package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//后台首页的链接首先访问该controller,然后再转发到对应的jsp中（因为view视图放在了web-inf下，无法直接使用链接访问到）
@Controller
@RequestMapping("/back")
public class Back {
	
	@RequestMapping(value="/back",method=RequestMethod.GET)
	public String toMain(){
		
		return "back/main";
	}
	@RequestMapping(value="/addCategory",method=RequestMethod.GET)
	public String addCategory(){
		return "back/addCategory";
	} 
	@RequestMapping(value="/showCategory",method=RequestMethod.GET)
	public String showCategory(){
		return "back/categoryView";
	}
	@RequestMapping(value="/addProperty",method=RequestMethod.GET)
	public String addProperty(){
		return "back/addProperty";
	} 
	@RequestMapping(value="/showProperty",method=RequestMethod.GET)
	public String showProperty(){
		return "back/propertyView";
	}
	
	@RequestMapping(value="/addProertyValue",method=RequestMethod.GET)
	public String addProertyValue(){
		
		return "back/propertyValueView";
	}
	
	

}
