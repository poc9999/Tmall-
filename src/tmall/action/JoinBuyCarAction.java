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
	//���빺�ﳵ��Ϣ
	public void join(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ��Ʒ��id�͹��������
		String buynum=request.getParameter("buynum");
		String pid=request.getParameter("pid");

		
		int num=Integer.parseInt(buynum);
		//�õ���������session��������ӵ����ﳵ���²�Ʒ�ļ���
		int totalNum=(int) request.getSession().getAttribute("totalNum");
		request.getSession().setAttribute("totalNum", totalNum+num);
		
		OrderItem orderitem=new OrderItem();
		ProductService pservice=new ProductService();
		OrderItemService oiservice=new OrderItemService();
		
		int productId=Integer.parseInt(pid);
		User user = (User)request.getSession().getAttribute("user");
		//���ݲ�Ʒid��ѯ������Ĳ�Ʒ
		Product product=pservice.queryEachOne(productId);
		//�����û�ȥ�õ����еĶ�����
		List<OrderItem>oi_list=oiservice.ListByUser(user.getId());
		//�Ѳ�ѯ������Ϣ�ŵ�orderitem����
		orderitem.setNumber(num);
		orderitem.setProduct(product);
		orderitem.setUser(user);
		//ʹ��һ������������ѭ��
		boolean flag=false;
		//�������еĶ�����
		for(OrderItem oi:oi_list){
			//����oi_list����,����в�Ʒ��id�ͷ��͹�����idһ���ͽ��и��²���
			if(oi.getProduct().getId()==productId){
				oiservice.update(orderitem);
				flag=true;
				break;
			}
		}
		//������͹�����id�ڼ������治����,�ʹ���һ���µļ�¼
		if(!flag){
			oiservice.insert(orderitem);
		}
	}
	//�鿴���ﳵҳ��
	public void view(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		//����û���ϢΪ��,��ת����¼ҳ��
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
		//���ݲ�Ʒidȥɾ����Ʒ������
//		oiservice.delete(Integer.parseInt(pid),user.getId());
	}
			
}
