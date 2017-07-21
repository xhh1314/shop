package shop.background;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import shop.bean.User;
import shop.service.UserService;
import shop.util.GetUUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class UserTest {
	@Autowired
	private UserService userService;
	
	
	@Test
	public void insertTest(){
	User user=new User();
	user.setEmail("90ddd");
	user.setUuid(GetUUID.getUuid());
	user.setName("lihao");
		userService.register(user);
	}
	@Test
	public void JsonTest(){
		
		
		//String email="{\"email\":\"90344@qq.com\"}";
		String email="{\"email\":\"903440799\"}";
		
		JsonParser jsonParser= new JsonParser();

		JsonElement element =jsonParser.parse(email);

		JsonObject jsonObj =element.getAsJsonObject();

		email =jsonObj.get("email").toString();
		email=email.substring(email.indexOf("\"")+1, email.lastIndexOf("\""));
		System.out.println("执行成功！打印出:"+email);
		
	}
	@Test
	public void findByEmail(){
		User user=userService.selectByEmail("903440799@qq.com");
		System.out.println(user);
		
	}

}
