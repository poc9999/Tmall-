package tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tmall.bean.Category;
import tmall.util.DataResource;

public class AllHomePageDataDAO {
	//拿到所有分类	
	public List<Category>cate_list(){
		 Connection conn=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 Category cate=null;
		 List<Category>cate_list=new ArrayList<Category>();
		 try {
			conn=DataResource.getConnection();
			
			String sql="select * from category";
			
			ps=conn.prepareStatement(sql);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				
				cate=new Category();
				
				cate.setId(rs.getInt("id"));
				cate.setName(rs.getString("name"));
				//根据产品分类id拿到所有的产品列表
				cate.setProd_list(new ProductDAO().get(rs.getInt("id")));
				
				cate_list.add(cate);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		 return cate_list;
	}
	
}
