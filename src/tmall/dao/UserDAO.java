package tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import tmall.bean.User;
import tmall.util.DataResource;
import tmall.util.UniversalDAO;

public class UserDAO {

	private UniversalDAO udao=new UniversalDAO();
	private Object obj;
	//�вεĹ�����
	public UserDAO(Object obj){
		
		this.obj=obj;
	}
	//�޲εĹ�����
	public UserDAO(){}
	//�õ���������
	public List<User>getAll_List(){
		
		@SuppressWarnings("unchecked")
		List<User> user_list=(List<User>)udao.getList(obj.getClass());
		
		return user_list;
	}
	//���ط�ҳ����
	public List<User>getPage_List(int start,int count){
		@SuppressWarnings("unchecked")
		List<User> user_list=udao.getList(obj.getClass(), start, count);
		return user_list;
	}
	//��ȡ��ҳ����
	public int getTotal(){
		
		return udao.getTotal(this.obj.getClass());
	}
	//�����û���¼��ѯ
	public User queryLogin(String username,String password,String type){
		 User user=null;
		 Connection conn=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		try {
			conn=DataResource.getConnection();
			
			String sql="select * from user where name=? and password=? and type=?";
			
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, type);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setType("type");
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		return user;
	}
	
	public User queryUsername(String username){
		 User user=null;
		 Connection conn=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 
		 try {
			conn=DataResource.getConnection();
			
			String sql="select * from user where name=? and type='user'";
			
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, username);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setType(rs.getString("type"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		 return user;
	}
	//�����û���Ϣ
	public void insertUser(String username,String password){	
		 User user=null;
		 Connection conn=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 
		 try {
			conn=DataResource.getConnection();
			
			String sql="insert into user values(null,?,?,?)";
			
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, "user");
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		 
	}
}
