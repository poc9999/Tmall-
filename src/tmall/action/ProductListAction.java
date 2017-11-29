package tmall.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tmall.bean.Product;
import tmall.service.ProductService;
import tmall.util.Page;

public class ProductListAction extends HttpServlet {
	//产品服务层
	ProductService service = new ProductService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String crud=request.getParameter("crud");
		
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

	
	public void select(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		String type=request.getParameter("type");
		
		String name=request.getParameter("name");
		
		HttpSession session = request.getSession();
		
		Page <Product>page=null;
		
		if(type==null){
			if(session.getAttribute("page")==null){
				page=new Page<Product>(6);
				session.setAttribute("page", page);
			}else{
				page=(Page<Product>)session.getAttribute("page");
			}
			session.setAttribute("name", name);
			service.queryFirstList(page);
		}else{
			//获取name属性
			name=(String)session.getAttribute("name");
			page = (Page<Product>) session.getAttribute("page");
			if ("first".equals(type)) {
				page.setCurrentPage(1);
			} else if ("prev".equals(type)) {
				page.setCurrentPage(page.getCurrentPage() - 1);
			} else if ("next".equals(type)) {
				page.setCurrentPage(page.getCurrentPage() + 1);
			} else if ("last".equals(type)) {
				page.setCurrentPage(page.getTotalPage());
			}
			session.setAttribute("name", name);
			service.queryAllList(page);
			session.setAttribute("page", page);
			}
			request.getRequestDispatcher("./listProduct.jsp").forward(request, response);
	}
}
