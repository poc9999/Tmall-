package tmall.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tmall.bean.OrderItem;
import tmall.bean.Product;
import tmall.bean.PropertyValue;
import tmall.bean.User;
import tmall.service.OrderItemService;
import tmall.service.ProductService;

public class AllUserListAction extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String user_type=request.getParameter("user_type");
		
		switch (user_type) {
		case "eachOneProd":
			queryeachOneProd(request,response);
			break;
		case "searchKeyProd":
			searchKeyProd(request,response);
			break;
		default:
			break;
		}
	}
	public void queryeachOneProd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		获取产品的id
		String id=request.getParameter("id");
		//分类id
//		String cid=request.getParameter("cid");
		//产品服务层
		ProductService pservice=new ProductService();
		//订单项逻辑处理层
		OrderItemService oiservice=new OrderItemService();
		//新建一个订单项
		OrderItem orderitem=new OrderItem();
		//判断跳转页面的参数
		String order=request.getParameter("order");
		//插入到orderitem数据库,此时并没有生成订单
//		oservice.insert(orderitem);
		Product product=pservice.queryEachOne(Integer.parseInt(id));
		
		List<PropertyValue> prop_value_list = pservice.queryPropValue(Integer.parseInt(id));
		
		request.setAttribute("product", product);
		request.setAttribute("prop_value_list", prop_value_list);
		//跳转到产品详情页面
		if("no".equals(order)){
			//发送产品详细信息和属性信息
			request.getRequestDispatcher("./product.jsp").forward(request, response);
		//跳转到提交订单页面
		}else if("ok".equals(order)){
			//把购买数量的字符串转换成数字
			String buynum=request.getParameter("buynum");
			//通过user对象去拿对应的订单项
			User user=(User) request.getSession().getAttribute("user");
			//使用Integer转换成数字
			int pid=Integer.parseInt(id);
			int num=Integer.parseInt(buynum);
			//通过用户的id拿到所有订单的详细信息
//			List<OrderItem>oi_list=oiservice.ListByUser(user.getId());
			
			Product p=pservice.queryEachOne(pid);
			orderitem.setNumber(num);
			orderitem.setProduct(p);
			
			orderitem.setUser(user);
			//一点击立即购买插入一条订单项信息
			oiservice.insert(orderitem);
			
			request.setAttribute("buynum", num);
			request.getRequestDispatcher("./submitorder.jsp").forward(request, response);
		}
	}
	
	public void searchKeyProd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String key=request.getParameter("keyword");
		
		ProductService service=new ProductService();
		
		List<Product>prod_list=service.queryKeyProd(key);
		
		System.out.println(prod_list);
		
		request.setAttribute("keyword", key);
		
		request.setAttribute("prod_list", prod_list);
		
		request.getRequestDispatcher("./keyproduct.jsp").forward(request, response);
	}	
}
