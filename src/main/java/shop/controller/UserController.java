package shop.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import redis.clients.jedis.Jedis;
import shop.bean.User;
import shop.redis.LoginCookie;
import shop.service.OrderService;
import shop.service.UserService;
import shop.util.PropertyUtil;
import shop.util.ResponseWrite;

@Controller
@RequestMapping(value="user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	
	
	/**
	 * 用户是否启用redis
	 */
	private static String isRedis=PropertyUtil.getProperty("isRedis");
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String registerBefor(ModelMap model){
		model.addAttribute("user",new User());
		return "fore/register";
	}
	//注册控制器
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(@ModelAttribute User user, ModelMap model){
		String flag=null;
		userService.register(user);
		return "redirect:/user/login";
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginBefor(ModelMap model,HttpServletRequest request){
		//User user=new User();
		model.addAttribute("user",new User());
		//这里为了解决权限拦截ajax请求时，登录成功后返回到原网页的问题
		String previousUri=request.getParameter("previousUri");
		if(previousUri!=null)
		model.addAttribute("previousUri",previousUri);
		return "fore/login";
	}
	//登录控制器
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@ModelAttribute User user,HttpServletRequest request,ModelMap model,HttpServletResponse response) throws UnsupportedEncodingException{
		if(userService.verificationUser(user))//验证user用户名密码是否匹配
		{
			//verificationUser方法写错了，应该直接返回个User对象的，难得再去改了，这里再查一次User，不能直接使用前台传过来的User对象
			User user_new=userService.selectByEmail(user.getEmail());
			HttpSession session=request.getSession();
			session.setAttribute("user", user_new);
			loginInial(session);//初始化页面
			if(isRedis.equals("yes"))
			{//启用redis才执行该操作
			Jedis conn=new Jedis("localhost");
			Cookie cookie=LoginCookie.jedisLogin(conn, request, user_new);//redis中存储用户cookie
			response.addCookie(cookie);
			}
			model.addAttribute("message","");
			String previousUri=request.getParameter("previousUri");//previousUri是登录页面隐藏表单
			//如果是从其他链接跳转到登录界面的，则登录成功之后返回登录之前的页面
			if(previousUri==null || previousUri.equals(""))
			return "redirect:/fore/index";
			else{
				//截取掉前缀，如/shop/forePermission/addOderItem 截取后为/forePermission/addOderItem
				return "redirect:"+previousUri.substring(request.getContextPath().length());
			}
		}
		else{
			model.addAttribute("user",new User());
			model.addAttribute("message","用户名或者密码错误！");
			return "fore/login";
			
		}
		
		
	}
	
	/**
	 *登录时页面初始化，把一些需要在页面展示出来的信息，出库查询出来，添加到session中 
	 */
	private void loginInial(HttpSession session){
		
		int cartNumber=orderService.selectOrderItemNuber(((User)session.getAttribute("user")).getUuid());
		session.setAttribute("cartNumber", cartNumber);
	}
	
	/**
	 * 前台通过ajax传入email，后台判定该email是否可以注册
	 * 因为email包含特殊字符@，直接传入字符串后端出现乱码，所以前台把email转换成json格式，传入后台
	 * 使用Gson解析传入的json数据
	 * @param email
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/isexist",produces="application/json;charset=utf-8")
	public String detection(@RequestBody String email,HttpServletResponse response,HttpServletRequest request){
		JsonParser jsonParser= new JsonParser();
		JsonElement element =jsonParser.parse(email);
		JsonObject jsonObj =element.getAsJsonObject();
		email =jsonObj.get("email").toString();
		//System.out.println("执行成功！打印出:"+email);
		//Gson取出的值带有“”号，导致传入数据库的参数多了个“”,所以这里把双引号截取掉
		email=email.substring(email.indexOf("\"")+1, email.lastIndexOf("\""));
		User user =userService.selectByEmail(email);
		if(user==null)
		{
			try {
				//传回非0，表示用户不存在
				ResponseWrite.write(response, "1");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				//传回0表示用户存在
				ResponseWrite.write(response, "0");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
	return null;
	}
	
	/**
	 * 前台ajax访问后台，看用户是否登录
	 */
	@RequestMapping(value="/userExist")
	public void userExist(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		if(session.getAttribute("user")!=null)
		{
			try {
				ResponseWrite.write(response, "yes");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				ResponseWrite.write(response, "no");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	/**
	 * 注销用户
	 * @return
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request){
		HttpSession session=request.getSession();
		if(session.getAttribute("user")!=null)
			session.removeAttribute("user");
		return "redirect:/user/login";
	}

}
