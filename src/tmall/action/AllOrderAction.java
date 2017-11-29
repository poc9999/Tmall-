package tmall.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tmall.bean.Order;
import tmall.bean.OrderItem;
import tmall.service.OrderItemService;
import tmall.service.OrderService;
import tmall.util.Page;

public class AllOrderAction extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Page<OrderItem> page = null;
		String type=request.getParameter("type");
		OrderItemService service=new OrderItemService();
		
		HttpSession session=request.getSession();
		//第一次请求
		if(null==type){
			if(session.getAttribute("page")==null){
				page=new Page<OrderItem>(6);
				session.setAttribute("page", page);
			}else{
				page=(Page<OrderItem>)session.getAttribute("page");
			}
			service.queryFirstList(page);
		}else{
			page = (Page<OrderItem>) session.getAttribute("page");
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
		request.getRequestDispatcher("./allorder.jsp").forward(request, response);
	}

}
