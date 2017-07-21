package shop.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import shop.bean.OrderItem;
import shop.bean.Orders;
import shop.dao.OrderItemDao;
import shop.dao.OrdersDao;
import shop.exception.MyException;
import shop.service.OrderService;
import shop.util.GetUUID;
import shop.util.OrderCode;

@Service("orderService")
@Scope("prototype")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private OrdersDao ordersDao;

	@Override
	public int insertOrderItem(OrderItem orderItem) throws MyException {
		if (orderItem == null) {
			throw new MyException("空指针异常！订单明细不能为空！");
		}
		if (orderItem.getProduct() == null || orderItem.getUser() == null)
			throw new MyException("添加订单明细异常！所属产品或用户不能为空！");
		int number = 0;
		
		Integer orderNumber=orderItemDao.selectItemNumberByUserAndProduct(orderItem.getUser().getUuid(), orderItem.getProduct().getUuid());
		if(orderNumber==null)
			orderNumber=0;
		if(orderNumber!=0)//查询这个产品是否已经有添加到订单明细中，如果在订单明细表中已经有记录，则更新数量
		{
			orderItemDao.updateNumber(orderItem.getUser().getUuid(), orderItem.getProduct().getUuid(),orderItem.getNumber());
			return orderItem.getNumber();
		}
		else{//查询这个产品是否已经有添加到订单明细中，如果在订单明细表中没有产品记录，则直接新增一条记录
		orderItemDao.addOrderItem(orderItem);
		number = orderItemDao.selectItemNumberByUser(orderItem.getUser().getUuid());
		return number;
		}

	}

	@Override
	public List<OrderItem> selectOrderItemsByUser(String userUUID) {
		// TODO Auto-generated method stub
		return orderItemDao.selectOrderItemsByUser(userUUID);

	}

	@Override
	public void insertOrder(Orders orders) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Orders> selectOdersByUser(String userUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectOrderItemNuber(String userUUID) {
		// TODO Auto-generated method stub
		Integer number = orderItemDao.selectItemNumberByUser(userUUID);
		if (number == null)
			number = 0;
		// Integer类型自动拆箱，这里必须做一下非空判断，如果mybatis查询出来为0就会报空指针异常
		return number;
	}

	@Override
	public void deleteItem(String id) throws MyException {
		// TODO Auto-generated method stub
		if (id == null)
			throw new MyException("删除订单失败！订单号不能为空");
		orderItemDao.deleteItem(id);

	}

	/* (non-Javadoc)
	 * 
	 * @see shop.service.OrderService#findOrderItemsByIds(java.lang.String[])
	 */
	@Override
	public List<OrderItem> selectOrderItemsByIds(String[] oids) {
		// TODO Auto-generated method stub
		if(oids.length==0)
			return null;
		return orderItemDao.selectItemsByIds(oids);
	}

	@Override
	public void updateOrderItemOid(String[] oids, Orders order) {
		// TODO Auto-generated method stub
		String ordersUUID=GetUUID.getUuid();
		order.setUuid(ordersUUID);//设置uuid
		//初始化创建订单时间
		Calendar calendar=Calendar.getInstance();
		Date date=new Date();
		Timestamp time=new Timestamp(date.getTime());
		order.setCreateDate(time);
		//生成一个订单号
		OrderCode oc=new OrderCode();
		String ordercode=oc.getOrderCode(calendar, order.getUser().getUuid());
		order.setOrdercode(ordercode);
		ordersDao.insertOrder(order);
		orderItemDao.updateOrderId(oids, ordersUUID);
		
	}

	@Override
	@Transactional(isolation=Isolation.READ_UNCOMMITTED)
	public List<OrderItem> selectOrderItemByProductAndUser(String pid, String uid) throws MyException {
		// TODO Auto-generated method stub
		if(pid==null || uid==null)
		{
			throw new MyException("查询失败！产品uuid 或者 用户uuid不能为空！");
		}
		return orderItemDao.selectItemByProcuctIdAndUserId(pid, uid);
	}

}
