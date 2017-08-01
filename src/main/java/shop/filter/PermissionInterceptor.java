package shop.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import redis.clients.jedis.Jedis;
import shop.redis.LoginCookie;
import shop.util.PropertyUtil;
import shop.util.ResponseWrite;

/**
 * @author lh
 *权限拦截器，拦截需要登录才能访问的资源，权限验证之后跳转到访问之前的页面
 *如果是ajax请求，则直接给前端返回消息“isAjax”，需要在前端判断进行跳转
 */
public class PermissionInterceptor implements HandlerInterceptor {
	
	/**
	 * 用户是否启用redis
	 */
	private static String isRedis=PropertyUtil.getProperty("isRedis");
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String uri=request.getRequestURI();
		String path=request.getContextPath();
		String uri1=uri.substring(path.length()+1);
		//System.out.println("截取path后的uri："+uri1);
		//根据路径判定该资源是否需要进行权限验证,这里是设置forePermission Controller下的方法都要拦截
		if(uri1.startsWith("forePermission")){
		HttpSession session=request.getSession();
		if(session.getAttribute("user")!=null)
			return true;
		else{
			if(isRedis.equals("yes")){//判断用户是否启用redis数据库
				Jedis conn=new Jedis("localhost");
				if(LoginCookie.checkUser(conn, request))
					return true;
			}
			
			//这里对请求进行判断，看看是否是ajax请求，如果是ajax请求，则直接在拦截器这里给前端返回一个消息，然后再前端ajax函数里进行判断跳转到登录界面
			String XRequested=request.getHeader("X-Requested-With");
			if(XRequested!=null && XRequested.equals("XMLHttpRequest"))
			{
				ResponseWrite.write(response, "isAjax");
				return false;
			}
			else{
			request.setAttribute("previousUri", uri);
			//ModelAndView mav=new ModelAndView("user/login");
			request.getRequestDispatcher("/user/login").forward(request, response);
			return false;
			}
			}
		}
		else{//页面不需要权限，通过
			return true;
		}
					
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

	
	

}
