package shop.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.bean.OrderItem;
import shop.bean.Orders;
import shop.bean.Product;
import shop.bean.User;
import shop.exception.MyException;
import shop.service.OrderService;
import shop.util.ResponseWrite;

/**
 * @author lh 前台需要权限验证的访问都到这个控制类
 */
@Controller
@RequestMapping(value = "/forePermission")
public class ForePermissionController {

	@Autowired
	private OrderService orderService;

	/**
	 * 将商品添加到购物车，同时更新购物车中商品总数量
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addCart")
	public void addOderItem(HttpServletRequest request, HttpServletResponse response) {
		String product_uuid = request.getParameter("product_uuid");
		Product product = new Product();
		product.setUuid(product_uuid);
		String number = request.getParameter("number");
		User user = (User) request.getSession().getAttribute("user");
		OrderItem orderItem = new OrderItem();
		orderItem.setNumber(Integer.parseInt(number));
		orderItem.setUser(user);
		orderItem.setProduct(product);
		try {
			int cartNumber = orderService.insertOrderItem(orderItem);
			HttpSession session = request.getSession();
			session.setAttribute("cartNumber", cartNumber);
			// 添加无异常则给前台ajax返回一个success字符
			ResponseWrite.write(response, "success");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ResponseWrite.write(response, "failure");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	/**立即购买操作
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addImmidiateCart")
	public String buyOderItem(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		String product_uuid = request.getParameter("product_uuid");
		Product product = new Product();
		product.setUuid(product_uuid);
		String number = request.getParameter("number");
		User user = (User) request.getSession().getAttribute("user");
		OrderItem orderItem = new OrderItem();
		orderItem.setNumber(Integer.parseInt(number));
		orderItem.setUser(user);
		orderItem.setProduct(product);
		try {
			int cartNumber = orderService.insertOrderItem(orderItem);
			HttpSession session = request.getSession();
			session.setAttribute("cartNumber", cartNumber);
			List<OrderItem> orderItems=orderService.selectOrderItemByProductAndUser(product_uuid,user.getUuid());
			model.addAttribute("orderItems",orderItems);
			model.addAttribute("orders",new Orders());
			return "fore/orders";//无异常则返回到提交订单页面

		} catch (Exception e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
			String error = "<script type=\"text/javascript\">alert(\"购买商品失败，系统出现异常！\");" + "</script>";
			try {
				ResponseWrite.write(response, error);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}

	}

	/**
	 * 展示出购物车里的多个订单
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="showCart")
	public String showCart(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {//没有登录则返回登录界面
			return "redirect:/fore/index";
		} else {
			String uuid = ((User) session.getAttribute("user")).getUuid();
			int number = orderService.selectOrderItemNuber(uuid);
			if (number == 0) {
				model.addAttribute("noOrderItems", "noOrderItems");
			} else {
				List<OrderItem> orderItems = orderService.selectOrderItemsByUser(uuid);
				model.addAttribute("orderItems", orderItems);

			}
			return "fore/orderItems";
		}
	}
	@RequestMapping(value="/deleteItem/{oid}")
	public void deleteItem(@PathVariable String oid,HttpServletResponse response){
		try {
			orderService.deleteItem(oid);
			ResponseWrite.write(response, "success");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			try {
				ResponseWrite.write(response, "fail");//回复ajax删除失败！
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@RequestMapping(value="/showOrder")
	public String showOrder(ModelMap model,@RequestParam("oids") String[] oids){
		model.addAttribute("orders",new Orders());
		List<OrderItem> orderItems=orderService.selectOrderItemsByIds(oids);
		model.addAttribute("orderItems",orderItems);
		return "fore/orders";
	}
	@RequestMapping(value="/addOrder")
	public String submitOrder(@RequestParam("oid") String[] oids ,@ModelAttribute Orders order,HttpServletRequest request){
		if (oids==null)
			return null;
		User user=(User)request.getSession().getAttribute("user");
		order.setUser(user);
		orderService.updateOrderItemOid(oids,order);
		return "fore/pay";
	}

}
