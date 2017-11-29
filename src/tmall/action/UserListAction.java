package tmall.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tmall.bean.Category;
import tmall.bean.User;
import tmall.service.UserService;
import tmall.util.Page;

public class UserListAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	
	}


	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		request.setCharacterEncoding("UTF-8");
		
		String crud=request.getParameter("crud");
		//处理增删改查
		switch (crud) {
		case "select":
			select(request,response);
			break;
		case "delete":
			break;
		case "update":
			break;
		case "insert":
			break;
		default:
			break;
		}	
	}
	
	
	public void select(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	
				//服务层
				UserService service=new UserService();
				//分页类型
				String type=request.getParameter("type");
				
				Page<User>page=null;
				HttpSession session=request.getSession();
				//是否第一次查询所有的分类信息
				if(type==null){
					if(session.getAttribute("page")==null){
						page=new Page<User>(6);
						session.setAttribute("page", page);
					}else{
						page=(Page<User>)session.getAttribute("page");
					}
					service.queryFirstList(page);
				}else{
					page=(Page<User>)session.getAttribute("page");
					if("first".equals(type)){
						page.setCurrentPage(1);
					}else if("prev".equals(type)){
						page.setCurrentPage(page.getCurrentPage()-1);
					}else if("next".equals(type)){
						page.setCurrentPage(page.getCurrentPage()+1);
					}else if("last".equals(type)){
						page.setCurrentPage(page.getTotalPage());
					}
					service.queryAllList(page);
					session.setAttribute("page", page);
				}
				//请求转发
				request.getRequestDispatcher("./listUser.jsp").forward(request, response);
	}

}
