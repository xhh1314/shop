package shop.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import shop.bean.wrap.ProductPropertyValue;
import shop.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class RedisTest01 {
	@Autowired
	private ProductService productService;
	
	@SuppressWarnings("resource")
	@Test
	public void test01(){
		Jedis jedis=new Jedis("localhost");
		/*System.out.println(jedis.ping());
		jedis.set("name", "lihao");
		jedis.set("age","11");
		System.out.println("name:"+jedis.get("name"));
		System.out.println("age:"+jedis.get("age"));*/
		List<ProductPropertyValue> ppv=productService.selectProductPropertyValue("005e2984-644d-4d1b-9878-f36cb52541e6");
		Map<String,String> map=new HashMap<String,String>();
		for(ProductPropertyValue p:ppv){
			map.put(p.getProductName(), p.getPropertyValue());
		}
		jedis.hmset("005e2984-644d-4d1b-9878-f36cb52541e6",map);
		Map<String, String> properties=jedis.hgetAll("005e2984-644d-4d1b-9878-f36cb52541e6");
		for(Entry<String, String> key:properties.entrySet()){
			
			System.out.println("{"+key.getKey()+":"+key.getValue()+"}");
			
		}
		
		
		
	}
	
	@Test
	public void test02(){
		
		String[] ckv={"user:x0101x","password:a0000a","ip:http://10.0.9.23"};
		String userid=ckv[0].substring(ckv[0].indexOf(":")+1);
		String password=ckv[2].substring(ckv[2].indexOf(":")+1);
		//String ip=ckv[3].substring(ckv[3].indexOf(":")+1);
	     userid="81cd4b2f-f7f3-4240-b813-72c7909f67d2";
		Jedis conn=new Jedis("localhost");
		String redisIP=conn.hget(userid, "ip");//从redis中取出对应user存储的ip信息，如果相等，则cookie有效，
		String redisName=conn.hget(userid, "name");
		String redisPassword=conn.hget(userid, "password");
		
		System.out.println();
	}
	
	

}
