package tmall.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tmall.bean.Order;
import tmall.bean.OrderItem;
import tmall.bean.User;
import tmall.service.OrderItemService;
import tmall.service.OrderService;
import tmall.service.ProductService;
import tmall.service.SubmitOrderService;

public class SubmitOrderAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//判断订单的状态
		String type=request.getParameter("type");
		
		switch (type) {
		case "submitOrder":
			submitOrder(request,response);
			break;
		case "paySuccess":
			paySuccess(request,response);
			break;
		case "orderBuyCar":
			orderBuyCar(request,response);
			break;
		case "delivery":
			delivery(request,response);
			break;
		case "myorderSubmit":
			myorderSubmit(request,response);
			break;
		case "deliverGoods":
			deliverGoods(request,response);
			break;
		case "confirm":
			confirm(request,response);
			break;
		default:
			break;
		}
	}
	//提交订单
	public void submitOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SubmitOrderService service=new SubmitOrderService();
		HttpSession session=request.getSession();
		OrderItem orderitem = new OrderItem();
		OrderItemService oiservice=new OrderItemService();
		//获取发送过来的参数
		String totalPrice=request.getParameter("totalPrice");
		String address=request.getParameter("address");
		String post=request.getParameter("post");
		String revname=request.getParameter("revname");
		String phonenum=request.getParameter("phonenum");
		//获取订单里面的产品id
		String pid=request.getParameter("pid");
		//获取当前用户
		User user = (User)request.getSession().getAttribute("user");
		//获取提交订单时间,插入数据库
		Date createDate=new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//订单编号字符串
		String orderCode=sdf.format(createDate)+new Random().nextInt(9999);
		
		Order order = new Order();
		order.setAddress(address);
		order.setCreateDate(createDate);
		order.setPost(post);
		order.setReceiver(revname);
		order.setUserMessage(user.getName());
		order.setUser(user);
		order.setMobile(phonenum);
		order.setStatus(Order.PENDPAY);
		order.setOrderCode(orderCode);
		//向数据库中插入信息
		service.insert(order);
		
		orderitem.setProduct(new ProductService().queryEachOne(Integer.parseInt(pid)));
		orderitem.setOrder(order);
		orderitem.setUser(user);
		
		//更新订单项信息
		oiservice.updateoid(orderitem);
		//发送总价格的属性
		request.setAttribute("totalPrice", totalPrice);
		session.setAttribute("order", order);
		request.setAttribute("pid", pid);
		//转到订单支付页面
		request.getRequestDispatcher("./payment.jsp").forward(request, response);
	}
	
	public void paySuccess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		SubmitOrderService soservice=new SubmitOrderService();
		OrderItemService oiservice=new OrderItemService();
		HttpSession session=request.getSession();
		OrderItem orderitem = new OrderItem();
		String totalPrice=request.getParameter("totalPrice");
		String pid=request.getParameter("pid");
//		Gson gson=new Gson();
//		//解析json字符串
//		Order order=gson.fromJson(orderInfo,Order.class);
		User user = (User)session.getAttribute("user");
		//拿到订单信息
		Order order = (Order)session.getAttribute("order");
		//支付日期
		order.setStatus(Order.DELIVER);
		order.setUser(user);
		Date payDate=new Date();
		order.setPayDate(payDate);
		//支付之后三天左右到货
		long arrTime=payDate.getTime()+3*24*60*60*1000;
		Date arrDate=new Date();
		arrDate.setTime(arrTime);
		request.setAttribute("arrDate", arrDate);
		//更新订单状态
		soservice.update(order);
		//覆盖掉原来的session
		session.setAttribute("order", order);
		//发送总订单价格过去
		request.setAttribute("totalPrice", totalPrice);
		//请求转发
		request.getRequestDispatcher("./paysuccess.jsp").forward(request, response);
	}
	//从购物车页面跳转过来
	public void orderBuyCar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//发送过来多个订单项
		String []oiids=request.getParameterValues("oiid");
		
		List<OrderItem> ois=new ArrayList<OrderItem>();
		
//		System.out.println(Arrays.toString(oiids));
		
		float totalPrice=0;
		//遍历每一个订单项id
		for(String oiide:oiids){
			
			int oiid = Integer.parseInt(oiide);
			
			OrderItem oi=OrderItemService.get(oiid);
			//总价格
			totalPrice += oi.getProduct().getPromotePrice()*oi.getNumber();
			ois.add(oi);
		}
		request.getSession().setAttribute("ois", ois);
		request.setAttribute("totalPrice", totalPrice);
		request.getRequestDispatcher("./submitorderbycar.jsp").forward(request, response);
	}
	//立即发货更新数据库
	public void delivery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid=request.getParameter("oid");
		
		OrderService service=new OrderService();
		service.updateById(Integer.parseInt(oid), Order.CONFIRM);
	}
	//从我的订单页面提交支付订单信息
	public void myorderSubmit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String oiid=request.getParameter("oiid");
		
//		OrderItemService service=new OrderItemService();
		
		OrderService service=new OrderService();
		OrderItem orderitem=OrderItemService.get(Integer.parseInt(oiid));
		
		Order order=orderitem.getOrder();
		
//		order.setStatus(Order.DELIVER);
		
		service.updateById(order.getId(), Order.DELIVER);
		double totalPrice = orderitem.getNumber()*orderitem.getProduct().getPromotePrice();
		request.setAttribute("totalPrice", totalPrice);
		request.getSession().setAttribute("order", order);
		request.setAttribute("pid", orderitem.getProduct().getId());
		//请求转发
		request.getRequestDispatcher("./payment.jsp").forward(request, response);
	}
	//催卖家发货
	public void deliverGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String oiid=request.getParameter("oiid");
		//静态方法拿到当前订单项
		OrderItem orderitem=OrderItemService.get(Integer.parseInt(oiid));
		OrderService service=new OrderService();
		Order order=orderitem.getOrder();
		
		service.updateById(order.getId(), Order.CONFIRM);
		
		PrintWriter pw=response.getWriter();
		
		String returnData="卖家已经秒发货";
		
		pw.write(returnData);
	}
	//确认订单信息
	public void confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String oiid=request.getParameter("oiid");
		//静态方法拿到当前订单项
		OrderItem orderitem=OrderItemService.get(Integer.parseInt(oiid));
		OrderService service=new OrderService();
		Order order=orderitem.getOrder();
		service.updateById(order.getId(), Order.COMMENT);
	}
}
