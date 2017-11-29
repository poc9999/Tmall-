package tmall.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tmall.bean.User;
import tmall.service.OrderItemService;
import tmall.service.UserService;
import tmall.util.MD5Util;

/**
 * ��¼����servlet,ʹ�����ַ�ʽ��¼,һ����ͨ��¼��һ��ajax��¼����
 * @author lenovo
 */
public class LoginAction extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
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
			insert(request,response);
			break;
		case "logout":
			logout(request,response);
			break;
		case "checkLogin":
			checkLogin(request,response);
			break;
		case "loginAjax":
			loginAjax(request,response);
			break;
		default:
			break;
		}	
	}
	//ʹ����ͨ����ת���ķ�ʽ���е�¼
	public void select(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		HttpSession session=request.getSession();
		response.setContentType("text/html");
		//�жϹ������������û����ǹ���Ա
		String property=request.getParameter("property");
		UserService service=new UserService();
		OrderItemService oiservice=new OrderItemService();
		User user=null;
		int totalNum=0;
		if("admin".equals(property)){
			user=service.queryLogin(username, password, property);
			
			if(user==null){
				request.setAttribute("error", "�û��������벻��ȷ!");
				request.getRequestDispatcher("./adminLogin.jsp").forward(request, response);
			}else{
				session.setAttribute("admin", user);
				//��̨�������
				request.getRequestDispatcher("./backWelcome.jsp").forward(request, response);
			}
		}else if("user".equals(property)){
			//����ͨ��md5����
			String	MD5password=MD5Util.getMD5(password);
			user=service.queryLogin(username, MD5password, property);
			//û���û���Ϣ����fail,���û���Ϣ����success
			if(user==null){
				request.setAttribute("error", "�û��������벻��ȷ");
				request.getRequestDispatcher("../user_tmall/userlogin.jsp").forward(request, response);
			}else{
				//�õ����ﳵ������Ʒ��
				totalNum=oiservice.getTotalByUser(user);
				
				session.setAttribute("totalNum", totalNum);
				session.setAttribute("user", user);
				request.getRequestDispatcher("../user_tmall/tmall.jsp").forward(request, response);
			}
		}
	}
	
	public void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		HttpSession session = request.getSession();
		UserService service=new UserService();
		boolean flag=service.isExist(username);
		
		if(flag){
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			request.setAttribute("error", "���û����Ѿ�����,���޸��û���");
			request.getRequestDispatcher("../user_tmall/register.jsp").forward(request, response);
		}else{
			//ע�����MD5����
			String MD5password = MD5Util.getMD5(password); 
			service.insertUser(username,MD5password);
			request.getRequestDispatcher("../user_tmall/registersuccess.jsp").forward(request, response);
		}
		
	}
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//ͬʱɾ���û���¼��Ϣ�͹��ﳵ��Ʒ����
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("totalNum");
		request.getRequestDispatcher("../user_tmall/tmall.jsp").forward(request, response);
	
	}
	//ajax��֤�û��Ƿ��¼
	public void checkLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		User user = null;
		
		user = (User) request.getSession().getAttribute("user");
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		@SuppressWarnings("unused")
		String returnData="status";
		if(user!=null){	
			returnData="success";
		}else{
			returnData="fail";
		}
		pw.write(returnData);
		pw.flush();
		pw.close();
	}
	//ʹ��ajax��¼
	public void loginAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String type=request.getParameter("type");
		UserService service=new UserService();
		User user=null;
		//ģ̬���¼,����ת����MD5
		String password1=MD5Util.getMD5(password);
		user=service.queryLogin(username, password1, type);
		PrintWriter pw=response.getWriter();
		int totalNum=0;
		String flag;
		if(null!=user){
			OrderItemService oiservice=new OrderItemService();
			request.getSession().setAttribute("user", user);
			totalNum=oiservice.getTotalByUser(user);
			request.getSession().setAttribute("totalNum", totalNum);
			flag="success";
		}else{
			flag="fail";
		}
		pw.write(flag);
		pw.flush();
		pw.close();
	}

}
