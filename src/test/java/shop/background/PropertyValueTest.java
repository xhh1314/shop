package shop.background;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shop.bean.Product;
import shop.bean.Property;
import shop.bean.PropertyValue;
import shop.service.PropertyValueService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class PropertyValueTest {
	
	@Autowired
	private PropertyValueService propertyValueService;
	/*@Test
	public void addTest(){
		Product product=new Product();
		product.setUuid("1a60447e-32b0-40c9-846f-0462f53961dd");//商品--金典牛奶
		Property property1=new Property();
		property1.setUuid("646d87ae-3a41-4e40-bbc5-97a1870f50e8");//品牌
		PropertyValue propertyValue1=new PropertyValue();
		propertyValue1.setProduct(product);
		propertyValue1.setProperty(property1);
		propertyValue1.setValue("伊利");
		//创建第二个对象
		Property property2=new Property();
		property2.setUuid("89363a57-435e-4f0d-8548-ab20cfba75a4");//生产日期
		PropertyValue propertyValue2=new PropertyValue();
		propertyValue2.setProduct(product);
		propertyValue2.setProperty(property2);
		propertyValue2.setValue("2017-05-10");
		//构造一个集合
		List<PropertyValue> list=new ArrayList<PropertyValue>();
		list.add(propertyValue1);
		list.add(propertyValue2);
		//把集合插入数据库
		propertyValueService.add(list);
		
	}*/
	@Test
	public void getPropertysTest(){
		List<Property> propertys=propertyValueService.getPropertyByProductUUID("1a60447e-32b0-40c9-846f-0462f53961dd");
		System.out.println(propertys);
		
	}
	
	@Test
	public void insertTest1(){
		PropertyValue p1=new PropertyValue();
		PropertyValue p2=new PropertyValue();
		PropertyValue p3=new PropertyValue();
		PropertyValue p4=new PropertyValue();
		p1.setValue("伊利");
		p1.setPd_uuid("1a60447e-32b0-40c9-846f-0462f53961dd");
		p1.setPp_uuid("646d87ae-3a41-4e40-bbc5-97a1870f50e8");
		p2.setValue("2017-05-13");
		p2.setPd_uuid("1a60447e-32b0-40c9-846f-0462f53961dd");
		p2.setPp_uuid("89363a57-435e-4f0d-8548-ab20cfba75a4");
		p3.setValue("内蒙古市");
		p3.setPd_uuid("1a60447e-32b0-40c9-846f-0462f53961dd");
		p3.setPp_uuid("d9a8e270-82ba-4717-b6f8-eb7ba32433c9");
		p4.setValue("牛奶");
		p4.setPd_uuid("1a60447e-32b0-40c9-846f-0462f53961dd");
		p4.setPp_uuid("e919df60-eb4d-4a30-a5c6-69d4431a782a");
		List<PropertyValue> lists=new ArrayList<PropertyValue>();
		lists.add(p1);
		lists.add(p3);
		lists.add(p4);
		
		//把集合插入数据库
		propertyValueService.add(lists);	
	}

}
