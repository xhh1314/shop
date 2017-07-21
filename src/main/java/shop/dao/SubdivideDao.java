package shop.dao;

import java.util.List;

import shop.bean.Subdivide;

public interface SubdivideDao {
	
	public Subdivide selectById(String uuid);
	public void insertSubdivide(Subdivide subdivide);
	public void updateSubdivide(Subdivide subdivide);
	public void deleteSubdivide(String uuid);
	public List<Subdivide> findAll();

}
