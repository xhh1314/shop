package shop.background;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shop.bean.OrderItem;
import shop.bean.Product;
import shop.bean.User;
import shop.service.CategoryService;
import shop.service.OrderService;
import shop.util.OrderCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class OrderItemTest {
	
	@Autowired
	private OrderService orderService;
	
	@Test
	public void addOrderItemTest(){
		ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");
		OrderService cs=(OrderService)ctx.getBean("orderService");
		Product product=new Product();
		product.setUuid("005e2984-644d-4d1b-9878-f36cb52541e6");
		User user=new User();
		user.setUuid("81cd4b2f-f7f3-4240-b813-72c7909f67d2");
		OrderItem item=new OrderItem();
		item.setNumber(100);
		item.setProduct(product);
		item.setUser(user);
		//cs.addOrderItem(item);
		
	}
	@Test
	public void findOrderItemByUser(){
		
		List<OrderItem> items=orderService.selectOrderItemsByUser("81cd4b2f-f7f3-4240-b813-72c7909f67d2");
		System.out.println(items);
	}
	
	@Test
	public void produceOrderCode(){
		OrderCode or=new OrderCode();
		System.out.println(or.getOrderCode(Calendar.getInstance(), "1234CD"));
	}
}
