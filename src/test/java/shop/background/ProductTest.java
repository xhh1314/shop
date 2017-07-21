package shop.background;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shop.bean.Category;
import shop.bean.Product;
import shop.bean.wrap.ProductPropertyValue;
import shop.service.ProductService;
import shop.util.GetUUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ProductTest {
	
	@Autowired
	private ProductService productService;
	
	@Test
	public void insertTest(){
		Product p=new Product();
		Category category=new Category();
		category.setUuid("4f13f6f6-2962-4c2d-894d-045f6c080e1e");//这里插入一个食品种类
		//p.setCategory(category);
		p.setName("金典牛奶XXX");
		p.setCreateTime("2017-03-11");
		p.setOriginalPrice(66f);
		p.setPromotePrice(66f);
		p.setStock(200);
		p.setUuid(GetUUID.getUuid());
		//productService.add(p);
	}
	
	@Test
	public void findByIdTest(){
		
		Product product=productService.selectById("1a60447e-32b0-40c9-846f-0462f53961dd");
		System.out.println(product);
	}
	@Test
	public void findBySubdivide(){
		
		List<Product> products=productService.selectBySubdivide("36bd55a6-df93-4c99-b849-ae5dcb5489f3");//羽毛球拍
		System.out.println();
	}
	@Test
	public void findProductPropertys(){
		
		List<ProductPropertyValue> ppvs=productService.selectProductPropertyValue("005e2984-644d-4d1b-9878-f36cb52541e6");
		System.out.println();
		
	}
	@Test
	public void findProductByKeys(){
		
		List<Product> products=productService.selectBykeys("qiu");
		System.out.println(products);
	}

}
