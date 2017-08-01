package shop.background;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shop.bean.Category;
import shop.controller.CategoryController;
import shop.dao.CategoryDao;
import shop.exception.MyException;
import shop.service.CategoryService;
import shop.service.TestService;
import shop.service.impl.CategoryServiceImpl;
import shop.util.GetUUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mvc.xml")
public class CategoryTest {
	
	@Autowired
	private CategoryService categoryService;
	
	@SuppressWarnings("resource")
	@Test
	public void insertTest() throws MyException, Exception{
		ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");
		CategoryService cs=(CategoryService)ctx.getBean("categoryService");
		Category cg=new Category();
		cg.setUuid(GetUUID.getUuid());
		cg.setName("电器");
		cg.setDescription("家用电器");
		cs.insert(cg);

		
	}
	@Test
	public void testService(){
		ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");
		TestService ts=(TestService)ctx.getBean("testServiceImpl");
		Category cg=new Category();
		cg.setUuid(GetUUID.getUuid());
		cg.setName("电器");
		cg.setDescription("家用电器");
		ts.insert(cg);
	}
	
	@Test
	public void CategoryDaoTest(){
		ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");
		CategoryDao cd=(CategoryDao)ctx.getBean("categoryDao");
		Category cg=new Category();
		cg.setUuid(GetUUID.getUuid());
		cg.setName("电器");
		cg.setDescription("家用电器");
		cd.insertCategory(cg);
		//System.out.println(ts.returnInfo());
	}
	@Test
	public void CategoryControllerTest(){
		@SuppressWarnings("resource")
		ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");
		CategoryController cc=(CategoryController)ctx.getBean("categoryController");
		Category cg=new Category();
		cg.setUuid(GetUUID.getUuid());
		//cg.setName("电器");
		cg.setDescription("家用电器");
		try {
			cc.getCategoryService().insert(cg);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("step1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("step2");
		}
		
	}
	@Test//测试查询所有的种类是否成功
	public void categoryFindAllTest(){
		//ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");
		//CategoryService cs=(CategoryService)ctx.getBean("categoryService");
		List<Category> cts=categoryService.findAll();
		System.out.println(cts);
		
	}
	

}
