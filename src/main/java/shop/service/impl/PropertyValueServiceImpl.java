package shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import shop.bean.Property;
import shop.bean.PropertyValue;
import shop.dao.PropertyDao;
import shop.dao.PropertyValueDao;
import shop.service.ProductService;
import shop.service.PropertyValueService;

@Service("propertyValueService")
@Scope("prototype")
public class PropertyValueServiceImpl implements PropertyValueService {

	@Autowired
	private PropertyValueDao propertyValueDao;
	@Autowired
	private PropertyDao propertyDao;
	@Autowired
	private ProductService productService;
	@Override
	public boolean add(List<PropertyValue> propertyValues) {
		// TODO Auto-generated method stub
		boolean flag=false;
		propertyValueDao.insert(propertyValues);
		flag=true;
		
		return flag;
	}

	@Override
	public boolean update(PropertyValue proertyValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(PropertyValue proertyValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Property> getPropertyByProductUUID(String uuid) {
		// TODO Auto-generated method stub
		
		return propertyDao.findBySubdivide(productService.selectById(uuid).getSubdivide().getUuid());
		
	}

}
