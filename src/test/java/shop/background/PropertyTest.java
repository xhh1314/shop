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
import shop.bean.Property;
import shop.exception.MyException;
import shop.service.CategoryService;
import shop.service.PropertyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class PropertyTest {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PropertyService propertyService;
	@Test
	public void insertTest(){
		ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");
		CategoryService cs=(CategoryService)ctx.getBean("categoryService");
		PropertyService ps=(PropertyService)ctx.getBean("propertyService");
		Category category=cs.findById("28a83670-d8f8-469e-8317-3d365b1c2a66");//查找一个服装类
		Property property=new Property();
		property.setName("适宜人群");
		//property.setCategory(category);
		try {
			ps.insert(property);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void selectTest(){
		//ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");
		//CategoryService cs=(CategoryService)ctx.getBean("categoryService");
		//PropertyService ps=(PropertyService)ctx.getBean("propertyService");
		Property property=propertyService.findById("24d0f1a0-08d2-4b10-9c14-dab8f929a4a1");
		List<Property> pros=propertyService.findAll();
		System.out.println(property);
	}

}
