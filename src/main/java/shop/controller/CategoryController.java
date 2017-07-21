package shop.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.bean.Category;
import shop.exception.MyException;
import shop.service.CategoryService;

@Controller
@RequestMapping(value="/category")
public class CategoryController {
	
	
	private CategoryService categoryService;
	public CategoryService getCategoryService() {
		return categoryService;
	}
	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String beforInsert(Model model){
		model.addAttribute(new Category());
		return "back/addCategory";
	}


	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute Category category,ModelMap mp){
		String info=null;//定义一个变量存储视图，添加成功或者失败时返回到什么界面！
		try{
		categoryService.insert(category);
		info="forward:/category/categoryView";
		}
		catch(MyException e){
			e.printStackTrace();
			mp.addAttribute("message", "商品种类不能为空");	
			mp.addAttribute(category);
			info="back/addCategory";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			info="back/categoryView";
			mp.addAttribute("message", "系统出现异常");	
		}
			return info;
		
	}
	@RequestMapping(value="/categoryView")
	public String categoryView(ModelMap mp){
		
		List<Category> categorys=categoryService.findAll();
		if(categorys==null)//如果查询出来的结果是空的，人为的添加一个空的category对象，防止前台报错
		{
			categorys=new LinkedList<Category>();
			categorys.add(new Category());
		}
		mp.addAttribute("categorys",categorys);
		return "back/categoryView";
	}
	/*
	@RequestMapping(value="/categoryView",method=RequestMethod.GET)
	public String categoryViewBefor(ModelMap mp){
		
		Category ct=new Category();
		mp.addAttribute("categorys", ct);
		return "back/categoryView";
		
	}*/
	}


