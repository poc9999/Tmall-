package tmall.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tmall.bean.Category;
import tmall.bean.Property;
import tmall.service.CategoryService;
import tmall.service.PropertyService;
import tmall.util.Page;

public class PropertyListAction extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String crud=request.getParameter("crud");
		
		switch (crud) {
		case "select":
			select(request,response);
			break;
		case "delete":
			break;
		case "update":
			
			update(request,response);
			
			break;
		case "insert":
			break;
		default:
			break;
		}	
		
	}
	
	//查询操作
	public void select(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
			PropertyService service=new PropertyService();
		
			String name=request.getParameter("name");
		
			String cid=request.getParameter("cid");
		
			request.setAttribute("name", name);
		
			List<Property> prop_list=service.queryCategory(cid);
		
			request.setAttribute("prop_list", prop_list);
		
			request.getRequestDispatcher("./listProperty.jsp").forward(request, response);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name=request.getParameter("name");
		
		request.setAttribute("name", name);
		
		request.getRequestDispatcher("./editProperty.jsp").forward(request, response);
		
		
	}
	
//	public void select(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
//		Page<Category> page = null;
//		HttpSession session = request.getSession();
//		// 服务层
//		CategoryService service = new CategoryService();
//		// 分页类型
//		String type = request.getParameter("type");
//		
//		if (type == null) {
//			if (session.getAttribute("page") == null) {
//				page = new Page<Category>(6);
//				session.setAttribute("page", page);
//			} else {
//				page = (Page<Category>) session.getAttribute("page");
//			}
//			service.queryFirstList(page);
//		} else {
//			page = (Page<Category>) session.getAttribute("page");
//			if ("first".equals(type)) {
//				page.setCurrentPage(1);
//			} else if ("prev".equals(type)) {
//				page.setCurrentPage(page.getCurrentPage() - 1);
//			} else if ("next".equals(type)) {
//				page.setCurrentPage(page.getCurrentPage() + 1);
//			} else if ("last".equals(type)) {
//				page.setCurrentPage(page.getTotalPage());
//			}
//			service.queryAllList(page);
//			session.setAttribute("page", page);
//		}
//		// 请求转发
//		request.getRequestDispatcher("./listProperty.jsp").forward(request,response);
//
//	}

}
