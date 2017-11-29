package tmall.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import tmall.bean.Category;
import tmall.service.AllHomePageDataService;

public class InitDataAction extends HttpServlet {
	/**
	 * tomcat一启动就拿到所有数据
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		
		ServletContext application = this.getServletContext();
		
		AllHomePageDataService service = new AllHomePageDataService();
		
		List<Category>cate_list = service.cate_list();
		
		application.setAttribute("cate_list", cate_list);
		//服务器一启动就清空所有的session
		
//		this.getServletContext().getSessionCookieConfig();
		
//		ComboPooledDataSource ds=new ComboPooledDataSource();
	}

}
