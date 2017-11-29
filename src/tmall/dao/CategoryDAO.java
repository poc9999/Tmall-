package tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import tmall.bean.Category;
import tmall.util.DataResource;
import tmall.util.UniversalDAO;

public class CategoryDAO {
	//new一个万能DAO
	private UniversalDAO udao=new UniversalDAO();
	//接收传过来的对象
	private Object obj;
	/* 带参数的构造器！！！
	 * new的时候检查
	 * 需要判断传过来的obj是否为空
	 */
	public CategoryDAO(Object obj){
		this.obj=obj;
	}
	//无参的构造器
	public CategoryDAO(){}
	
	//获取总分数数
	public int getTotal(){
			return udao.getTotal(this.obj.getClass());
	}
	//返回一个boolean值,判断是否插入成功
	public boolean add(){
			return udao.insert(this.obj);
		
	}
	//更新操作
	public boolean update(){
		
		return udao.update(obj);
	}
	//根据特定ID 删除
	public boolean delete(int id){
		return udao.deleteById(obj.getClass(), id);
	}
	//根据特定ID查询一个分类
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
	//拿到所有的产品集合
	public List<Category>getAll_List(){
		
		@SuppressWarnings("unchecked")
		List<Category> cate_list=(List<Category>)udao.getList(obj.getClass());
		
		return cate_list;
	}
	//拿到分页的List
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
