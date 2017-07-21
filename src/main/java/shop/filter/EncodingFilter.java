package shop.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author lh
 *解决URI有中文字符时，无法访问到系统资源问题
 *例如图片名称有中文时，不加这个过滤器将无法访问到图片
 */
public class EncodingFilter implements Filter {
	
	String encoding=null;
	FilterConfig filterConfig=null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig=filterConfig;
	      this.encoding=filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		HttpServletRequest req = (HttpServletRequest) request;
	       String uri = req.getRequestURI();
	     // System.out.println("uri:"+uri);
	       String ch = URLDecoder.decode(uri, encoding);
	       if(uri.equals(ch)) {
	           chain.doFilter(req, response);
	           return;
	       }
	       ch = ch.substring(req.getContextPath().length());
	     // System.out.println("ch:"+ch);
	       filterConfig.getServletContext().getRequestDispatcher(ch).forward(req, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.encoding=null;
	      this.filterConfig=null;
	}

}
