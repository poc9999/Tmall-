package tmall.service;

import java.util.List;

import tmall.bean.User;
import tmall.dao.UserDAO;
import tmall.util.Page;

public class UserService {

	private UserDAO dao=new UserDAO(new User());
	
	private UserDAO ndao=new UserDAO();
	//后面的分页查询
	public void queryAllList(Page<User> page){
		
		int start=(page.getCurrentPage()==0?0:page.getCurrentPage()-1)*page.getSize();
		
//		System.out.println(page.getCurrentPage()+"=========");
		
		List<User> user_list=dao.getPage_List(start, page.getSize());
		
		page.setData(user_list);
		
		
	}
	//第一页的分页查询
	public void queryFirstList(Page<User> page){
		//拿到偏移量
		int start=(page.getCurrentPage()-1)*page.getSize();
		//拿到总条数
		int totalNum=dao.getTotal();
		//传回总条数
		page.setTotalNum(totalNum);
		
		page.setCurrentPage(1);
		
		List<User> user_list=dao.getPage_List(start, page.getSize());
		
		page.setData(user_list);
	}
	//登录查询
	public User queryLogin(String username,String password,String type){
		
		return dao.queryLogin(username, password, type);
		
	}
	//判断用户名是否存在
	public boolean isExist(String username){
		
		User user=dao.queryUsername(username);
		
		return user!=null;
		
	}
	//插入用户信息
	public void insertUser(String username,String password){
		
		dao.insertUser(username,password);
	}
	
	
}
