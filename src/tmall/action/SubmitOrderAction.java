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
		//�ж϶�����״̬
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
	//�ύ����
	public void submitOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SubmitOrderService service=new SubmitOrderService();
		HttpSession session=request.getSession();
		OrderItem orderitem = new OrderItem();
		OrderItemService oiservice=new OrderItemService();
		//��ȡ���͹����Ĳ���
		String totalPrice=request.getParameter("totalPrice");
		String address=request.getParameter("address");
		String post=request.getParameter("post");
		String revname=request.getParameter("revname");
		String phonenum=request.getParameter("phonenum");
		//��ȡ��������Ĳ�Ʒid
		String pid=request.getParameter("pid");
		//��ȡ��ǰ�û�
		User user = (User)request.getSession().getAttribute("user");
		//��ȡ�ύ����ʱ��,�������ݿ�
		Date createDate=new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//��������ַ���
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
		//�����ݿ��в�����Ϣ
		service.insert(order);
		
		orderitem.setProduct(new ProductService().queryEachOne(Integer.parseInt(pid)));
		orderitem.setOrder(order);
		orderitem.setUser(user);
		
		//���¶�������Ϣ
		oiservice.updateoid(orderitem);
		//�����ܼ۸������
		request.setAttribute("totalPrice", totalPrice);
		session.setAttribute("order", order);
		request.setAttribute("pid", pid);
		//ת������֧��ҳ��
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
//		//����json�ַ���
//		Order order=gson.fromJson(orderInfo,Order.class);
		User user = (User)session.getAttribute("user");
		//�õ�������Ϣ
		Order order = (Order)session.getAttribute("order");
		//֧������
		order.setStatus(Order.DELIVER);
		order.setUser(user);
		Date payDate=new Date();
		order.setPayDate(payDate);
		//֧��֮���������ҵ���
		long arrTime=payDate.getTime()+3*24*60*60*1000;
		Date arrDate=new Date();
		arrDate.setTime(arrTime);
		request.setAttribute("arrDate", arrDate);
		//���¶���״̬
		soservice.update(order);
		//���ǵ�ԭ����session
		session.setAttribute("order", order);
		//�����ܶ����۸��ȥ
		request.setAttribute("totalPrice", totalPrice);
		//����ת��
		request.getRequestDispatcher("./paysuccess.jsp").forward(request, response);
	}
	//�ӹ��ﳵҳ����ת����
	public void orderBuyCar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���͹������������
		String []oiids=request.getParameterValues("oiid");
		
		List<OrderItem> ois=new ArrayList<OrderItem>();
		
//		System.out.println(Arrays.toString(oiids));
		
		float totalPrice=0;
		//����ÿһ��������id
		for(String oiide:oiids){
			
			int oiid = Integer.parseInt(oiide);
			
			OrderItem oi=OrderItemService.get(oiid);
			//�ܼ۸�
			totalPrice += oi.getProduct().getPromotePrice()*oi.getNumber();
			ois.add(oi);
		}
		request.getSession().setAttribute("ois", ois);
		request.setAttribute("totalPrice", totalPrice);
		request.getRequestDispatcher("./submitorderbycar.jsp").forward(request, response);
	}
	//���������������ݿ�
	public void delivery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid=request.getParameter("oid");
		
		OrderService service=new OrderService();
		service.updateById(Integer.parseInt(oid), Order.CONFIRM);
	}
	//���ҵĶ���ҳ���ύ֧��������Ϣ
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
		//����ת��
		request.getRequestDispatcher("./payment.jsp").forward(request, response);
	}
	//�����ҷ���
	public void deliverGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String oiid=request.getParameter("oiid");
		//��̬�����õ���ǰ������
		OrderItem orderitem=OrderItemService.get(Integer.parseInt(oiid));
		OrderService service=new OrderService();
		Order order=orderitem.getOrder();
		
		service.updateById(order.getId(), Order.CONFIRM);
		
		PrintWriter pw=response.getWriter();
		
		String returnData="�����Ѿ��뷢��";
		
		pw.write(returnData);
	}
	//ȷ�϶�����Ϣ
	public void confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String oiid=request.getParameter("oiid");
		//��̬�����õ���ǰ������
		OrderItem orderitem=OrderItemService.get(Integer.parseInt(oiid));
		OrderService service=new OrderService();
		Order order=orderitem.getOrder();
		service.updateById(order.getId(), Order.COMMENT);
	}
}
