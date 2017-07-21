package shop.service;

import java.util.List;

import shop.bean.Category;
import shop.dao.CategoryDao;
import shop.exception.MyException;

public interface CategoryService {
	public boolean insert(Category category) throws MyException, Exception;
	public List<Category> findAll();
	public Category findById(String uuid);
	public boolean update(Category category);
	public boolean delete(Category category);

}
