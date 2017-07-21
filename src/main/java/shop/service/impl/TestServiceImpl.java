package shop.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.bean.Category;
import shop.dao.CategoryDao;
import shop.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private CategoryDao categoryDao;

	
	@Test
	public String returnInfo() {
		// TODO Auto-generated method stub
		return "test success!";
	}
	
	public void insert(Category category){
		
		categoryDao.insertCategory(category);
	}

}
