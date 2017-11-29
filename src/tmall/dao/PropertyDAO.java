package tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tmall.bean.Category;
import tmall.bean.Property;
import tmall.util.DataResource;
import tmall.util.UniversalDAO;

public class PropertyDAO {

//	UniversalDAO udao=new UniversalDAO();
	
	public List<Property>queryCategory(String cid){
		Property prop=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Property> prop_list=new ArrayList<Property>();
		try {
			conn=DataResource.getConnection();
			
			String sql="select * from property where cid=?";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, Integer.parseInt(cid));
			
			rs=ps.executeQuery();
			while(rs.next()){
				prop=new Property();
				prop.setId(rs.getInt("id"));
				Category cate=new CategoryDAO(new Category()).get(Integer.parseInt(cid));
				prop.setCategory(cate);
				prop.setName(rs.getString("name"));
				prop_list.add(prop);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop_list;
	}
	
	public Property queryProperty(int id){
		
		Property prop=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			conn = DataResource.getConnection();
			
			String sql="select * from property where id=?";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				
				prop=new Property();
				
				int cid=rs.getInt("cid");
				prop.setId(rs.getInt("id"));
				prop.setName(rs.getString("name"));
				prop.setCategory(new CategoryDAO().get(cid));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		
		return prop;
		
	}
}
