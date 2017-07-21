package shop.service;

import java.util.List;

import shop.bean.Property;
import shop.bean.PropertyValue;

public interface PropertyValueService {
	
	//public boolean add(PropertyValue proertyValue);
	public boolean update(PropertyValue proertyValue);
	public boolean delete(PropertyValue proertyValue );
	boolean add(List<PropertyValue> propertyValues);
	//根据产品的uuid，查找出产品的所有属性,这里应该写错了位置了
	public List<Property> getPropertyByProductUUID(String uuid);

}
