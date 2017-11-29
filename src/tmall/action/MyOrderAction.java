package tmall.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tmall.bean.Order;
import tmall.bean.OrderItem;
import tmall.bean.User;
import tmall.service.OrderItemService;

public class MyOrderAction extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//创建service服务层
		OrderItemService service=new OrderItemService();
		//获取用户的订单信息
		User user=(User)request.getSession().getAttribute("user");
		//获取订单页面
		if(null==user){
			request.getRequestDispatcher("./userlogin.jsp").forward(request, response);
		}else{
			List<OrderItem> order_list=service.queryOrderItem(user.getId());
			request.setAttribute("order_list", order_list);
			request.getRequestDispatcher("./myorder.jsp").forward(request, response);
		}
		
	}

}
