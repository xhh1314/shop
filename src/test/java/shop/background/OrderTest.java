package shop.background;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shop.bean.Orders;
import shop.bean.User;
import shop.dao.OrdersDao;
import shop.service.OrderService;
import shop.util.GetUUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class OrderTest {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrdersDao ordersDao;
	
	@Test//测试提交订单操作
	public void insertOrderTest(){
		Orders order=new Orders();
		order.setAddress("北京");
		order.setMobile("15901015121");
		order.setReceiver("lihao");
		User user=new User();
		user.setUuid("12beafbc-23d8-42fd-bc7e-65bb341573dd");
		order.setUser(user);
		String[] oids={"18","19","20"};
		orderService.updateOrderItemOid(oids,order);
		//order.setUuid(GetUUID.getUuid());
		//ordersDao.insertOrder(order);
		
	}

}
