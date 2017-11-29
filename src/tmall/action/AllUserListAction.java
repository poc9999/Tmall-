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
//		��ȡ��Ʒ��id
		String id=request.getParameter("id");
		//����id
//		String cid=request.getParameter("cid");
		//��Ʒ�����
		ProductService pservice=new ProductService();
		//�������߼������
		OrderItemService oiservice=new OrderItemService();
		//�½�һ��������
		OrderItem orderitem=new OrderItem();
		//�ж���תҳ��Ĳ���
		String order=request.getParameter("order");
		//���뵽orderitem���ݿ�,��ʱ��û�����ɶ���
//		oservice.insert(orderitem);
		Product product=pservice.queryEachOne(Integer.parseInt(id));
		
		List<PropertyValue> prop_value_list = pservice.queryPropValue(Integer.parseInt(id));
		
		request.setAttribute("product", product);
		request.setAttribute("prop_value_list", prop_value_list);
		//��ת����Ʒ����ҳ��
		if("no".equals(order)){
			//���Ͳ�Ʒ��ϸ��Ϣ��������Ϣ
			request.getRequestDispatcher("./product.jsp").forward(request, response);
		//��ת���ύ����ҳ��
		}else if("ok".equals(order)){
			//�ѹ����������ַ���ת��������
			String buynum=request.getParameter("buynum");
			//ͨ��user����ȥ�ö�Ӧ�Ķ�����
			User user=(User) request.getSession().getAttribute("user");
			//ʹ��Integerת��������
			int pid=Integer.parseInt(id);
			int num=Integer.parseInt(buynum);
			//ͨ���û���id�õ����ж�������ϸ��Ϣ
//			List<OrderItem>oi_list=oiservice.ListByUser(user.getId());
			
			Product p=pservice.queryEachOne(pid);
			orderitem.setNumber(num);
			orderitem.setProduct(p);
			
			orderitem.setUser(user);
			//һ��������������һ����������Ϣ
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
