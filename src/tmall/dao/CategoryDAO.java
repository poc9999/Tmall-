package tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import tmall.bean.Category;
import tmall.util.DataResource;
import tmall.util.UniversalDAO;

public class CategoryDAO {
	//newһ������DAO
	private UniversalDAO udao=new UniversalDAO();
	//���մ������Ķ���
	private Object obj;
	/* �������Ĺ�����������
	 * new��ʱ����
	 * ��Ҫ�жϴ�������obj�Ƿ�Ϊ��
	 */
	public CategoryDAO(Object obj){
		this.obj=obj;
	}
	//�޲εĹ�����
	public CategoryDAO(){}
	
	//��ȡ�ܷ�����
	public int getTotal(){
			return udao.getTotal(this.obj.getClass());
	}
	//����һ��booleanֵ,�ж��Ƿ����ɹ�
	public boolean add(){
			return udao.insert(this.obj);
		
	}
	//���²���
	public boolean update(){
		
		return udao.update(obj);
	}
	//�����ض�ID ɾ��
	public boolean delete(int id){
		return udao.deleteById(obj.getClass(), id);
	}
	//�����ض�ID��ѯһ������
	public Category get(int id){
		
		 Connection conn=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 Category cate=null;
		 
		 try {
			conn=DataResource.getConnection();
			
			String sql="select * from category where id=?";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				
				cate=new Category();
				
				cate.setId(id);
				cate.setName(rs.getString("name"));
//				cate.setProd_list(new ProductDAO().get(id));
//				cate.setProd_list(new ProductDAO().get(id));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		 
		 return cate;
		
	}
	//�õ����еĲ�Ʒ����
	public List<Category>getAll_List(){
		
		@SuppressWarnings("unchecked")
		List<Category> cate_list=(List<Category>)udao.getList(obj.getClass());
		
		return cate_list;
	}
	//�õ���ҳ��List
	public List<Category>getPage_List(int start,int count){
		@SuppressWarnings("unchecked")
		List<Category> cate_list=udao.getList(obj.getClass(), start, count);
		
		return cate_list;
	}
	
	public void insert(String cname){
		 Connection conn=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		
		 try {
			conn=DataResource.getConnection();
			
			String sql="insert into category values(null,?)";
			
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, cname);
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
	}
	
	
}
