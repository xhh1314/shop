package shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shop.bean.OrderItem;

/**
 * @author lh
 *
 */
public interface OrderItemDao {
	
	public void addOrderItem(OrderItem orderItem);
	public List<OrderItem> selectOrderItemsByUser(String userUUID);
	/**
	 * @param userUUID
	 * @return
	 * 查询出该用户购物车物品数量
	 */
	public Integer selectItemNumberByUser(String userUUID);
	public void deleteItem(String id);
	/**根据用户ID和产品ID，查询该表中是否存在关于这个产品的订单
	 * @param uid
	 * @param pid
	 * @return
	 */
	public Integer selectItemNumberByUserAndProduct(@Param("uid")String uid,@Param("pid")String pid);
	
	/**
	 * 更新一个订单项产品的数量
	 * @param uid
	 * @param pid
	 * @param number
	 */
	public void updateNumber(@Param("uid")String uid,@Param("pid")String pid,@Param("number") int number);
	
	public void updateOrderId(@Param("oids")String[] oids,@Param("orderId")String orderId);
	
	/**
	 * 根据订单id[]集合，查询出对应的订单项
	 * @param oids
	 */
	public List<OrderItem> selectItemsByIds(String[] oids); 
	
	/**根据用用户ID和产品ID找出这个订单明细信息
	 * @param pid
	 * @param uid
	 * @return
	 */
	public List<OrderItem> selectItemByProcuctIdAndUserId(String pid,String uid);
	
}
