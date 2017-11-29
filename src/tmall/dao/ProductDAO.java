package tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tmall.bean.Category;
import tmall.bean.Product;
import tmall.bean.ProductImage;
import tmall.bean.Property;
import tmall.bean.PropertyValue;
import tmall.util.DataResource;
import tmall.util.DateUtil;
import tmall.util.UniversalDAO;

public class ProductDAO {
		//new一个万能DAO
		private UniversalDAO udao=new UniversalDAO();
		
		public int getTotal(){
			return udao.getTotal(Product.class);
		}
		
		@SuppressWarnings("unchecked")
		public List<Product> getPage_List(int start,int count){
			
			return udao.getList(Product.class,start,count);	
		}
		//根据一个分类ID拿到一组产品
		public List<Product> get(int cid){
			 Connection conn=null;
			 PreparedStatement ps=null;
			 ResultSet rs=null;
			 Product product=null;
			 List<Product>prod_list=new ArrayList<Product>();
			 
			 try {
				conn=DataResource.getConnection();
				
				String sql="select * from product where cid=?";
				
				ps=conn.prepareStatement(sql);
				
				ps.setInt(1, cid);
				
				rs=ps.executeQuery();
				
				while(rs.next()){
					product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setSubTitle(rs.getString("subTitle"));
					product.setOrignalPrice(rs.getFloat("orignalPrice"));
					product.setPromotePrice(rs.getFloat("promotePrice"));
					product.setStock(rs.getInt("stock"));
					product.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
					setSingleImage(product);
					setTitleImage(product);
					setFirstImage(product);
					prod_list.add(product);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DataResource.closeAll(conn, ps, rs);
			}
			return prod_list;
		}
		
		//拿到产品的第一张图片
		public void setFirstImage(Product p){
			
			if(p.getProductSingleImages()!=null){
				p.setFirstProductImage(p.getProductSingleImages().get(0));
			}	
		}
		//拿到产品的所有小图片
		public void setSingleImage(Product p){
			
			List<ProductImage>pi_list=new ProductImageDAO().list(p, ProductImageDAO.type_single);
			
			p.setProductSingleImages(pi_list);
		}
		
		//拿到产品的所有详细图片
		public void setTitleImage(Product p){
			List<ProductImage>pi_list=new ProductImageDAO().list(p,ProductImageDAO.type_detail);
			
			p.setProductDetailImage(pi_list);
		}
		public void setFirstProductImage(Product p){
			List<ProductImage> pis=new ProductImageDAO().list(p, ProductImageDAO.type_single);
		    p.setFirstProductImage(pis.get(0));
		}
		
		public Product queryEachOne(int id){
			 Connection conn=null;
			 PreparedStatement ps=null;
			 ResultSet rs=null;
			 Product product=null;
			 
			 try {
				conn=DataResource.getConnection();
				
				String sql="select * from product where id=?";
				
				ps=conn.prepareStatement(sql);
				
				ps.setInt(1, id);
				
				rs=ps.executeQuery();
				while(rs.next()){
					product=new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setSubTitle(rs.getString("subTitle"));
					product.setOrignalPrice(rs.getFloat("orignalPrice"));
					product.setPromotePrice(rs.getFloat("promotePrice"));
					product.setStock(rs.getInt("stock"));
					product.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
					setSingleImage(product);
					setTitleImage(product);
					setFirstImage(product);
				}
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return product;
		}
//		//根据分类id拿到产品的所有属性
//		public List<Property> queryProperty(int cid){
//			
//			 List<Property> prop_list=new ArrayList<Property>();
//			 Connection conn=null;
//			 PreparedStatement ps=null;
//			 ResultSet rs=null;
//			 
//			 Property prop=null;
//			 
//			 try {
//				conn=DataResource.getConnection();
//				
//				String sql="select * from property where cid=?";
//				
//				ps=conn.prepareStatement(sql);
//				
//				ps.setInt(1, cid);
//				
//				rs=ps.executeQuery();
//				
//				while(rs.next()){
//					
//					prop=new Property();
//					
//					prop.setId(rs.getInt("id"));
//					prop.setName(rs.getString("name"));
//					
////					prop.set
//					prop_list.add(prop);
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}finally{
//				DataResource.closeAll(conn, ps, rs);
//			}
//			return prop_list;
//		}
		
		
		public Product queryProduct(int id){
			
			 Connection conn=null;
			 PreparedStatement ps=null;
			 ResultSet rs=null;
			 
			 Product prod=null;
			 
			 try {
				conn=DataResource.getConnection();
				
				String sql="select * from product where id=?";
				
				ps=conn.prepareStatement(sql);
				
				ps.setInt(1, id);
				
				rs=ps.executeQuery();
				
				while(rs.next()){
					
					prod=new Product();
					
					String name = rs.getString("name");
	                String subTitle = rs.getString("subTitle");
	                float orignalPrice = rs.getFloat("orignalPrice");
	                float promotePrice = rs.getFloat("promotePrice");
	                int stock = rs.getInt("stock");
	                int cid = rs.getInt("cid");
	                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
	               
	                prod.setName(name);
	                prod.setSubTitle(subTitle);
	                prod.setOrignalPrice(orignalPrice);
	                prod.setPromotePrice(promotePrice);
	                prod.setStock(stock);
	                Category category = new CategoryDAO().get(cid);
	                prod.setCategory(category);
	                prod.setCreateDate(createDate);
	                prod.setId(id);
	                setFirstProductImage(prod);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DataResource.closeAll(conn, ps, rs);
			}
			return prod;
		}
		
		public List<PropertyValue> queryPropValue(int id){
			 Connection conn=null;
			 PreparedStatement ps=null;
			 ResultSet rs=null;
			 PropertyValue pv=null;
			 List<PropertyValue> prop_value_list = new ArrayList<>();
		
			 try {
				conn=DataResource.getConnection();
				
				
				String sql="select * from propertyvalue where pid=?";
				
				ps=conn.prepareStatement(sql);
				
				ps.setInt(1, id);
				
				rs=ps.executeQuery();
				PropertyDAO pd = new PropertyDAO();
				while(rs.next()){
					
					pv=new PropertyValue();
					
					pv.setId(rs.getInt("id"));
					
					pv.setValue(rs.getString("value"));
					
					pv.setProduct(this.queryProduct(id));
					
					pv.setProperty(pd.queryProperty(rs.getInt("ptid")));

					prop_value_list.add(pv);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				DataResource.closeAll(conn, ps, rs);
			}
			return prop_value_list;
		}
		
		//根据关键词查询产品
		public List<Product> queryKeyProd(String key){
		 	 Connection conn=null;
			 PreparedStatement ps=null;
			 ResultSet rs=null;
			 Product prod=null;
			 List<Product> prod_list=new ArrayList<Product>();
			 
			 try {
				conn=DataResource.getConnection();
				
				String sql="select * from product where name like ?";
				
				ps=conn.prepareStatement(sql);
				
				ps.setString(1, "%"+key+"%");
				
				rs=ps.executeQuery();
				
				while(rs.next()){
					prod=new Product();
					String name = rs.getString("name");
	                String subTitle = rs.getString("subTitle");
	                float orignalPrice = rs.getFloat("orignalPrice");
	                float promotePrice = rs.getFloat("promotePrice");
	                int stock = rs.getInt("stock");
	                int cid = rs.getInt("cid");
	                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
	               
	                prod.setName(name);
	                prod.setSubTitle(subTitle);
	                prod.setOrignalPrice(orignalPrice);
	                prod.setPromotePrice(promotePrice);
	                prod.setStock(stock);
	                Category category = new CategoryDAO().get(cid);
	                prod.setCategory(category);
	                prod.setCreateDate(createDate);
	                prod.setId(rs.getInt("id"));
	                setFirstProductImage(prod);
	                prod_list.add(prod);
				}
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return prod_list; 
		}
}
