package shop.service;

import java.util.List;

import shop.bean.Property;
import shop.bean.Subdivide;
import shop.exception.MyException;

public interface PropertyService {
	
	public boolean insert(Property property) throws MyException;
	public Property findById(String uuid);
	public List<Property> findAll();
	public List<Property> findBySubdivide(Subdivide subdivide);

}
