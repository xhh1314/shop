package shop.service;

import java.util.List;

import shop.bean.Category;
import shop.bean.Subdivide;
import shop.exception.MyException;

public interface SubdivideService {
	
	public boolean insert( Subdivide  subdivide) throws MyException, Exception;
	public List< Subdivide> findAll();
	public  Subdivide findById(String uuid);
	public boolean update( Subdivide  subdivide);
	public boolean delete( Subdivide  subdivide);

}
