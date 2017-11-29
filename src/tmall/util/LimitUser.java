package tmall.util;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tmall.bean.User;

public class LimitUser implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
			System.out.println("Ïú»Ùfifler");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filter) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse respone=(HttpServletResponse)res;
		request.setCharacterEncoding("utf-8");
//		String path=request.getServletPath();
		User user=(User) request.getSession().getAttribute("user");
		
		if(user==null){
			respone.sendRedirect("./tmall.jsp");
		}else{
			filter.doFilter(request, respone);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
			System.out.println("Éú³Éfilter");
	}

}
