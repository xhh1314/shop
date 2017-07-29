package shop.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import shop.bean.Category;
import shop.bean.Product;
import shop.bean.Subdivide;
import shop.bean.comparator.ProductAscending;
import shop.bean.comparator.ProductDescending;
import shop.bean.wrap.ProductPropertyValue;
import shop.service.CategoryService;
import shop.service.ProductService;
import shop.service.SubdivideService;

@Controller
@RequestMapping(value = "/fore")
public class ForeController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SubdivideService subdivideService;

	// 转发到主页index
	@RequestMapping(value = "/index")
	public String returnMain(ModelMap model) {
		List<Category> categorys = categoryService.findAll();
		model.addAttribute("categorys", categorys);
		// model.addAttribute("subdivide",categorys.);
		return "fore/index";
	}

	// 根据sbudivide_uuid返回所有对应产品的视图
	@RequestMapping(value = "/showProducts/{uuid}")
	public String showProducts(@PathVariable String uuid, ModelMap model) {
		String view = null;
		List<Product> products = null;
		if (uuid != null) {
			products = productService.selectBySubdivide(uuid);
			Subdivide subdivide = subdivideService.findById(uuid);
			model.addAttribute("products", products);
			model.addAttribute("subdivide", subdivide);
		}
		if (products == null)
			model.addAttribute("message", "产品不存在！");
		return "fore/productsView";
	}

	// 根据产品的UUID返回这种产品的详细信息视图
	@RequestMapping(value = "/showProduct/{uuid}")
	public String showProduct(@PathVariable String uuid, ModelMap model) {
		Product product = productService.selectById(uuid);
		List<ProductPropertyValue> ppv = productService.selectProductPropertyValue(uuid);
		String view = null;
		if (product != null) {
			model.addAttribute("product", product);
			model.addAttribute("productPropertyValues", ppv);
			view = "fore/productView";
			if (ppv.isEmpty())
				model.addAttribute("propertyValueInfo", "无属性！");
		} else {
			model.addAttribute("message", "产品不存在！");
			view = "fore/error";
		}
		return view;
	}

	/**
	 * 根据产品名称或者种类查找产品
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/select")
	public String findProducts(HttpServletRequest request, ModelMap model) throws UnsupportedEncodingException {
		String selectInfo = request.getParameter("selectInfo");// 前台传入的要搜索的关键字
		if (selectInfo == null || selectInfo == "") {
			return "forward:/fore/index";
		}
		System.out.println("selectInfo转码之前的值:"+selectInfo);
		selectInfo=URLDecoder.decode(selectInfo,"utf-8");
		System.out.println("selectInfo转码之后的值:"+selectInfo);
		List<Product> products = productService.selectBykeys(selectInfo);
		if (products == null || products.isEmpty()) {
			model.addAttribute("noproducts", "没有找到对应的产品");
			model.addAttribute("keysName", selectInfo);// 把搜索关键字返回，作为页面标题
			return "fore/productsView";
		}
		String sort = request.getParameter("sort");
		String uri = request.getRequestURI();
		if (sort == null) {
			// ##前台通过js修改链接
			uri = uri + "?selectInfo=" + selectInfo;
		} else if (sort.equals("ascending"))// 升序
		{
			Collections.sort(products, ProductAscending.getInstance());
			uri = uri + "?selectInfo=" + selectInfo + "&sort=descending";
		} else if (sort.equals("descending"))// 降序
		{
			Collections.sort(products, ProductDescending.getInstance());
			uri = uri + "?selectInfo=" + selectInfo + "&sort=ascending";
		}
		model.addAttribute("uriPath", uri);// 把本次查询的参数传递到前台，供排序时调用同样的链接
		model.addAttribute("products", products);
		model.addAttribute("keysName", selectInfo);// 把搜索关键字返回，作为页面标题
		return "fore/productsView";
	}

}
