package shop.service;

import java.util.List;

import shop.bean.OrderItem;
import shop.bean.Orders;
import shop.exception.MyException;

/**
 * @author lh
 *orderItem 和 orders的相关操作都写在这个service了
 */
public interface OrderService {
	
	/**增加订单明细的同时，查询出购物车有多少物品，把这个物品数量更新到HttpSession中
	 * @param orderItem
	 * @return 返回该用户购物车总数量
	 * @throws MyException 
	 */
	public int insertOrderItem(OrderItem orderItem) throws MyException;
	
	public List<OrderItem> selectOrderItemsByUser(String userUUID);
	
	public void insertOrder(Orders orders);
	public List<Orders> selectOdersByUser(String userUUID);
	
	/**
	 * 查询当前用户购物车商品数量
	 * @param userUUID
	 * @return
	 */
	public int selectOrderItemNuber(String userUUID);
	
	public void deleteItem(String id) throws MyException;
	
	public List<OrderItem> selectOrderItemsByIds(String[] oids);

	/**
	 * 当用户提交订单时，在order表中增加一条记录，同时返回order_id,把order_id更新到对应的订单项中
	 * @param oids
	 * @param order
	 */
	public void updateOrderItemOid(String[] oids, Orders order);
	
	/**根据产品ID和用户ID找出这个orderItem对象
	 * @param pid 产品UUID
	 * @param uid 用户UUID
	 * @throws MyException 
	 */
	public List<OrderItem> selectOrderItemByProductAndUser(String pid,String uid) throws MyException;

}
