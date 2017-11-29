package tmall.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tmall.bean.OrderItem;
import tmall.bean.Product;
import tmall.bean.User;
import tmall.service.OrderItemService;
import tmall.service.ProductService;

public class JoinBuyCarAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String type=request.getParameter("type");
		
		switch (type) {
		case "join":
			join(request,response);
			break;
		case "view":
			view(request,response);
			break;
		case "delete":
			delete(request,response);
		default:
			break;
		}
		
		
	}
	//加入购物车信息
	public void join(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取产品的id和购买的数量
		String buynum=request.getParameter("buynum");
		String pid=request.getParameter("pid");

		
		int num=Integer.parseInt(buynum);
		//拿到总数量的session并加上添加到购物车的新产品的件数
		int totalNum=(int) request.getSession().getAttribute("totalNum");
		request.getSession().setAttribute("totalNum", totalNum+num);
		
		OrderItem orderitem=new OrderItem();
		ProductService pservice=new ProductService();
		OrderItemService oiservice=new OrderItemService();
		
		int productId=Integer.parseInt(pid);
		User user = (User)request.getSession().getAttribute("user");
		//根据产品id查询到具体的产品
		Product product=pservice.queryEachOne(productId);
		//根据用户去拿到所有的订单项
		List<OrderItem>oi_list=oiservice.ListByUser(user.getId());
		//把查询到的信息放到orderitem里面
		orderitem.setNumber(num);
		orderitem.setProduct(product);
		orderitem.setUser(user);
		//使用一个变量来控制循环
		boolean flag=false;
		//遍历所有的订单项
		for(OrderItem oi:oi_list){
			//遍历oi_list集合,如果有产品的id和发送过来的id一样就进行更新操作
			if(oi.getProduct().getId()==productId){
				oiservice.update(orderitem);
				flag=true;
				break;
			}
		}
		//如果发送过来的id在集合里面不存在,就创建一条新的记录
		if(!flag){
			oiservice.insert(orderitem);
		}
	}
	//查看购物车页面
	public void view(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		//如果用户信息为空,跳转到登录页面
		if(null==user){
			request.getRequestDispatcher("./userlogin.jsp").forward(request, response);
		}else{
			OrderItemService oiservice = new OrderItemService();
			
			List<OrderItem>oi_list=oiservice.ListByUser(user.getId());
			
			request.setAttribute("oi_list", oi_list);
			
//			System.out.println(oi_list.size());
			
			request.getRequestDispatcher("./buycar.jsp").forward(request, response);
		}
	}
	
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id=request.getParameter("id");
		String pid=request.getParameter("pid");
		
		OrderItemService oiservice = new OrderItemService();
		int totalNum=(int)request.getSession().getAttribute("totalNum");
		int deleteNum=oiservice.getNumber(Integer.parseInt(pid));
		request.getSession().setAttribute("totalNum", totalNum-deleteNum);
		oiservice.delete(Integer.parseInt(id));
		//根据产品id去删除产品订单项
//		oiservice.delete(Integer.parseInt(pid),user.getId());
	}
			
}
