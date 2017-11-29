package tmall.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tmall.bean.Category;
import tmall.service.CategoryService;
import tmall.util.Page;

public class CategoryListAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String crud=request.getParameter("crud");
		
		switch (crud) {
		case "select":
			select(request,response);
			break;
		case "delete":
			delete(request,response);
			break;
		case "update":
			
			break;
		case "insert":
			insert(request,response);
			break;
		default:
			
			break;
		}	
	}
	
	public void select(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		Page<Category> page = null;
		HttpSession session = request.getSession();
		// 服务层
		CategoryService service = new CategoryService();
		// 分页类型
		String type = request.getParameter("type");
		
			//管理员第一次请求
			if (type == null) {
				if (session.getAttribute("page") == null) {
					page = new Page<Category>(6);
					session.setAttribute("page", page);
				} else {
					page = (Page<Category>) session.getAttribute("page");
				}
				service.queryFirstList(page);
				//不是第一次请求
			} else {
				page = (Page<Category>) session.getAttribute("page");
				if ("first".equals(type)) {
					page.setCurrentPage(1);
				} else if ("prev".equals(type)) {
					page.setCurrentPage(page.getCurrentPage() - 1);
				} else if ("next".equals(type)) {
					page.setCurrentPage(page.getCurrentPage() + 1);
				} else if ("last".equals(type)) {
					page.setCurrentPage(page.getTotalPage());
				}
				service.queryAllList(page);
				session.setAttribute("page", page);
			}
			request.getRequestDispatcher("./listCategory.jsp").forward(request,response);

	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CategoryService service = new CategoryService();
		
		String cid = request.getParameter("cid");
		
		service.delete(Integer.parseInt(cid));
	}
	
	
	public void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//拿到新增分类的名称
		String cname=request.getParameter("cname");
		
//		System.out.println("============"+cname);
		CategoryService service = new CategoryService();
		
		service.insert(cname);
		
		select(request,response);
	}
		
	}