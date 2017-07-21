package shop.redis;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;
import shop.bean.User;

public class LoginCookie {
	
	/**
	 *调用login方法的时候，调用这个方法，把用户登录信息存放到hash中,key为user_uuid
	 *
	 * @param conn
	 * @param request
	 * @param resposne
	 * @param user
	 * @return new Cookie("user", cookieValue) 
	 * @throws UnsupportedEncodingException 
	 */
	public static Cookie jedisLogin(Jedis conn,HttpServletRequest request,User user) throws UnsupportedEncodingException{
		String ip=getIpAddress(request);
		HashMap<byte[],byte[]> userInfo=new HashMap<byte[],byte[]>();
		userInfo.put("uuid".getBytes(),user.getUuid().getBytes("utf-8"));
		userInfo.put("name".getBytes("utf-8"),user.getName().getBytes("utf-8"));
		userInfo.put("password".getBytes("utf-8"), user.getPassword().getBytes("utf-8"));
		userInfo.put("ip".getBytes("utf-8"),ip.getBytes("utf-8"));
		conn.hmset(user.getUuid().getBytes("utf-8"),userInfo);
		//conn.hmset(user.getUuid(),userInfo);
		String cookieValue="uuid:"+user.getUuid()+"&name:"+user.getName()+"&password:"+user.getPassword()+"&ip:"+ip;
		Cookie cookie=new Cookie("user", cookieValue);
		cookie.setPath(request.getContextPath());
		return cookie;
	}
	
	/**验证用户是否存在，查询redis数据库，根据user_id查询出来的user密码 ip匹配的话 与cookie带过来的匹配则，返回true
	 * 验证成功将User加入session
	 * @param conn
	 * @param request
	 * @return
	 */
	public static boolean checkUser(Jedis conn,HttpServletRequest request){
		boolean flag=false;
		Cookie[] cookies=request.getCookies();
		Cookie ck=null;
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("user")){
				ck=cookie;
				break;
			}	
		}
		if (ck==null)
			return false;
		String[] ckv=ck.getValue().split("&");
		String userid=ckv[0].substring(ckv[0].indexOf(":")+1);
		//String username=ckv[1].substring(ckv[1].indexOf(":")+1);
		String password=ckv[2].substring(ckv[2].indexOf(":")+1);
		String ip=ckv[3].substring(ckv[3].indexOf(":")+1);
		String redisIP=conn.hget(userid, "ip");//从redis中取出对应user存储的ip信息，如果相等，则cookie有效，
		String redisName=conn.hget(userid, "name");
		String redisPassword=conn.hget(userid, "password");
		if(ip.equals(redisIP) && password.equals(redisPassword))
		{
			User user=new User();
			user.setUuid(userid);
			user.setName(redisName);
			user.setPassword(password);
			request.getSession().setAttribute("user", user);
			flag=true;	
			
		}
			
		return flag;
	}
	
	
	
	
	 /** 
	   * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 
	   * 
	   * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 
	   * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
	   * 
	   * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 
	   * 192.168.1.100 
	   * 
	   * 用户真实IP为： 192.168.1.110 
	   * 
	   * @param request 
	   * @return 
	   */
	public static String getIpAddress(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	  } 
	    
	


}
