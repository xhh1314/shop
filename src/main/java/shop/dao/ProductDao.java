package shop.dao;

import java.util.List;

import shop.bean.Product;
import shop.bean.wrap.ProductPropertyValue;

public interface ProductDao {
	public void insert(Product product);
	public Product selectById(String uuid);
	public List<Product> findAll();
	public List<Product> selectBySubdivide(String uuid);
	
	/**
	 * @param uuid
	 * @return根据产品UUID查询出该产品对应的所有属性
	 */
	public List<ProductPropertyValue> findProductPropertyValue(String uuid);
	/**根据产品种类或者产品名称搜索产品
	 * @param kyes
	 * @return
	 */
	public List<Product> selectByKeys(String keys);

}
