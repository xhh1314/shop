package shop.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import shop.bean.Category;
import shop.bean.Property;
import shop.bean.Subdivide;
import shop.dao.PropertyDao;
import shop.exception.MyException;
import shop.service.CategoryService;
import shop.service.PropertyService;
import shop.service.SubdivideService;
import shop.util.GetUUID;

@Service("propertyService")
@Scope("singleton")
public class PropertyServiceImpl implements PropertyService {
	
	@Autowired
	private PropertyDao propertyDao;
	@Autowired
	private SubdivideService subdivideService;

	
	public boolean insert(Property property) throws MyException {
		// TODO Auto-generated method stub
		boolean flag=false;
		if(property.getName()==null)
		{
			throw new MyException("属性名称不能为空！");
		}
		//String category_uuid=(String) request.getAttribute("category_uuid");
		
		Subdivide subdivide=subdivideService.findById(property.getSubdivide().getUuid());
		if(subdivide!=null){
		property.setSubdivide(subdivide);
		property.setUuid(GetUUID.getUuid());
		propertyDao.insert(property);
		flag=true;
		}
		return flag;
	}


	public Property findById(String uuid) {
		// TODO Auto-generated method stub
		
		return propertyDao.selectById(uuid);
	}


	public List<Property> findAll() {
		// TODO Auto-generated method stub
		return propertyDao.findAll();
	}


	@Override
	public List<Property> findBySubdivide(Subdivide subdivide) {
		// TODO Auto-generated method stub
		return propertyDao.findBySubdivide(subdivide.getUuid());
	}

}
