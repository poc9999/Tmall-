package tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tmall.bean.Product;
import tmall.bean.ProductImage;
import tmall.util.DataResource;

public class ProductImageDAO {
	//图片的两种状态
	public static final String type_single="type_single";
	public static final String type_detail="type_detail";
	//查询所有图片信息
	public List<ProductImage>list(Product p,String type){
		return list(p,type,0,Short.MAX_VALUE);
	}
	//查询一组图片信息
	public List<ProductImage>list(Product p,String type,int start,int count){
		 Connection conn=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 List<ProductImage> pi_list=new ArrayList<ProductImage>();
		 
		 try {
			conn=DataResource.getConnection();
			String sql="select * from productimage where pid=? and type=? order by id desc limit ?,?";
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, p.getId());
			ps.setString(2, type);
			ps.setInt(3, start);
			ps.setInt(4, count);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				ProductImage pi=new ProductImage();
				pi.setId(rs.getInt("id"));
				pi.setType(rs.getString("type"));
				pi.setProduct(p);
				pi_list.add(pi);
			}
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		return pi_list;
	}
	
	
}
