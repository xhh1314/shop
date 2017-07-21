package shop.dao;

import java.util.List;

import shop.bean.Category;

public interface CategoryDao {
	public Category selectById(String uuid);
	public void insertCategory(Category category);
	public void updateCategory(Category category);
	public void deleteCategory(String uuid);
	public List<Category> findAll();

}
