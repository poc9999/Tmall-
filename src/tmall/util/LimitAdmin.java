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
		System.out.println("���ٹ���Ա������");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filter) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		
		User user=(User)request.getAttribute("admin");
		//�������Աδ��¼,��ô���޷���������ҳ���
		if(null==user){
			response.sendRedirect("./adminLogin.jsp");
		}else{
			filter.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("���ɹ���Ա������");
	}

}
