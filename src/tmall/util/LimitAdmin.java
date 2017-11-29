package tmall.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tmall.bean.User;

public class LimitAdmin implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("销毁管理员过滤器");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filter) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		
		User user=(User)request.getAttribute("admin");
		//如果管理员未登录,那么是无法操作后续页面的
		if(null==user){
			response.sendRedirect("./adminLogin.jsp");
		}else{
			filter.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("生成管理员过滤器");
	}

}
