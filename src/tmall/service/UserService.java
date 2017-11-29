package tmall.service;

import java.util.List;

import tmall.bean.User;
import tmall.dao.UserDAO;
import tmall.util.Page;

public class UserService {

	private UserDAO dao=new UserDAO(new User());
	
	private UserDAO ndao=new UserDAO();
	//����ķ�ҳ��ѯ
	public void queryAllList(Page<User> page){
		
		int start=(page.getCurrentPage()==0?0:page.getCurrentPage()-1)*page.getSize();
		
//		System.out.println(page.getCurrentPage()+"=========");
		
		List<User> user_list=dao.getPage_List(start, page.getSize());
		
		page.setData(user_list);
		
		
	}
	//��һҳ�ķ�ҳ��ѯ
	public void queryFirstList(Page<User> page){
		//�õ�ƫ����
		int start=(page.getCurrentPage()-1)*page.getSize();
		//�õ�������
		int totalNum=dao.getTotal();
		//����������
		page.setTotalNum(totalNum);
		
		page.setCurrentPage(1);
		
		List<User> user_list=dao.getPage_List(start, page.getSize());
		
		page.setData(user_list);
	}
	//��¼��ѯ
	public User queryLogin(String username,String password,String type){
		
		return dao.queryLogin(username, password, type);
		
	}
	//�ж��û����Ƿ����
	public boolean isExist(String username){
		
		User user=dao.queryUsername(username);
		
		return user!=null;
		
	}
	//�����û���Ϣ
	public void insertUser(String username,String password){
		
		dao.insertUser(username,password);
	}
	
	
}
