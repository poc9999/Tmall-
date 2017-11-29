package tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tmall.bean.Order;
import tmall.bean.OrderItem;
import tmall.bean.User;
import tmall.util.DataResource;
import tmall.util.DateUtil;

//订单项DAO
public class OrderItemDAO {
	//插入订单项信息
	public void insert(OrderItem orderitem){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			conn=DataResource.getConnection();
			
			String sql="insert into orderitem values(null,?,?,?,?)";
			
			ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, -1);
			ps.setInt(2, orderitem.getUser().getId());
			ps.setInt(3, orderitem.getProduct().getId());
			ps.setInt(4, orderitem.getNumber());
			ps.executeUpdate();
			
			rs=ps.getGeneratedKeys();
			
			while(rs.next()){
				orderitem.setId(rs.getInt(1));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
	}
	//删除订单
	public void delete(int id){
		Connection conn=null;
		PreparedStatement ps=null;
//		ResultSet rs=null;
		try {
			conn=DataResource.getConnection();
			
			String sql="delete from orderitem where id=? and oid=-1";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, null);
		}
	}
	//根据产品拿到多个订单项
	public List<OrderItem> ListByProd(int pid){
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		OrderItem oi=null;
		ProductDAO pdao=new ProductDAO();
		OrderDAO mdao=new OrderDAO();
		List<OrderItem> oi_list=new ArrayList<OrderItem>();
		try {
			conn=DataResource.getConnection();
			
			String sql="select * from orderitem where pid=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pid);
			rs=ps.executeQuery();
			while(rs.next()){
				oi = new OrderItem();
				oi.setId(rs.getInt("id"));
				oi.setProduct(pdao.queryProduct(rs.getInt("pid")));
				oi.setOrder(mdao.queryOrder(rs.getInt("oid")));
				oi.setNumber(rs.getInt("number"));
				oi_list.add(oi);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		return oi_list;
	}
	//根据订单拿到多个订单项
	public List<OrderItem> ListByOrder(int oid){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		OrderItem oi=null;
		List<OrderItem> oi_list=new ArrayList<OrderItem>();
		ProductDAO pdao=new ProductDAO();
		OrderDAO mdao=new OrderDAO();
		try {
			conn=DataResource.getConnection();
			
			String sql="select * from orderitem where oid=?";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, oid);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				oi = new OrderItem();
				oi.setId(rs.getInt("id"));
				oi.setProduct(pdao.queryProduct(rs.getInt("pid")));
				oi.setOrder(mdao.queryOrder(rs.getInt("oid")));
				oi.setNumber(rs.getInt("number"));
				oi_list.add(oi);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		return oi_list;
	}
	
	
	public List<OrderItem>ListByUser(int uid){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		OrderItem oi=null;
		ProductDAO pdao=new ProductDAO();
		OrderDAO mdao=new OrderDAO();
		List<OrderItem> oi_list=new ArrayList<OrderItem>();
//		查询到六条数据
		try {
			conn=DataResource.getConnection();
			
			String sql="select * from orderitem where uid=? and oid=-1 order by id desc limit 0,6";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, uid);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				oi = new OrderItem();
				oi.setId(rs.getInt("id"));
				oi.setNumber(rs.getInt("number"));
				oi.setOrder(mdao.queryOrder(rs.getInt("oid")));
				oi.setProduct(pdao.queryEachOne(rs.getInt("pid")));
				
				oi_list.add(oi);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		return oi_list;
	}
	
	//根据uid和oid去拿购物车一共有多少产品
	public int getTotalByUser(User user){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int totalNum=0;
		
		try {
			conn=DataResource.getConnection();
			
			String sql="select count(number) from orderitem where uid=? and oid=-1";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, user.getId());
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				totalNum = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		
		return totalNum;
	}
	//执行更新操作
	public void update(OrderItem orderitem){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		//更新一件商品的总数
		int num=getNumber(orderitem.getProduct().getId())+orderitem.getNumber();
		try {
			conn=DataResource.getConnection();
			
			String sql="update orderitem set number=? where pid=? and uid=?";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, num);
			ps.setInt(2, orderitem.getProduct().getId());
			ps.setInt(3, orderitem.getUser().getId());
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
	}
	//更新订单项订单id信息
	public void updateoid(OrderItem orderitem){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			conn=DataResource.getConnection();
			
			String sql="update orderitem set oid=? where uid=? and pid=? and oid=-1";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, orderitem.getOrder().getId());
			ps.setInt(2, orderitem.getUser().getId());
			ps.setInt(3, orderitem.getProduct().getId());
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		
		
	}
	//根据产品id获取产品的数量
	public int getNumber(int pid){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int number=0;
		try {
			conn=DataResource.getConnection();
			
			String sql="select number from orderitem where pid=? and oid=-1";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, pid);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				
				number=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		return number;
	}
	
	//根据订单项id找到相应的订单项
	public static OrderItem get(int id){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		OrderItem orderitem=null;
		ProductDAO pdao=new ProductDAO();
		OrderDAO mdao=new OrderDAO();
		try {
			conn=DataResource.getConnection();
			
			String sql="select * from orderitem where id=? and oid!=-1";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				orderitem = new OrderItem();
				orderitem.setId(rs.getInt("id"));
				orderitem.setProduct(pdao.queryProduct(rs.getInt("pid")));
				orderitem.setOrder(mdao.queryOrder(rs.getInt("oid")));
				orderitem.setNumber(rs.getInt("number"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		return orderitem;
	}
	
	public List<OrderItem> queryOrderItem(int uid){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		OrderItem orderitem=null;
		List<OrderItem> oi_list=new ArrayList<OrderItem>();
		ProductDAO pdao=new ProductDAO();
		OrderDAO mdao=new OrderDAO();
		try {
			conn=DataResource.getConnection();
			//查询不是-1且用户名是uid的订单
			String sql="select * from orderitem where uid=? and oid !=-1 order by id desc";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, uid);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				orderitem = new OrderItem();
				orderitem.setId(rs.getInt("id"));
				orderitem.setProduct(pdao.queryProduct(rs.getInt("pid")));
				orderitem.setOrder(mdao.queryOrder(rs.getInt("oid")));
				orderitem.setNumber(rs.getInt("number"));
				oi_list.add(orderitem);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		return oi_list;
	}
	//拿到所有的已经生成的订单
	public List<OrderItem> get(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		OrderItem orderitem=null;
		List<OrderItem> oi_list=new ArrayList<OrderItem>();
		ProductDAO pdao=new ProductDAO();
		OrderDAO mdao=new OrderDAO();
		try {
			conn=DataResource.getConnection();
			//查询不是-1的所有订单
			String sql="select * from orderitem where oid !=-1 order by id desc";
			
			ps=conn.prepareStatement(sql);
			
			rs=ps.executeQuery();
			while(rs.next()){
				orderitem = new OrderItem();
				orderitem.setId(rs.getInt("id"));
				orderitem.setProduct(pdao.queryProduct(rs.getInt("pid")));
				orderitem.setOrder(mdao.queryOrder(rs.getInt("oid")));
				orderitem.setNumber(rs.getInt("number"));
				oi_list.add(orderitem);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		return oi_list;
	}
	
	public int getTotal(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int totalNum=0;
		try {
			conn=DataResource.getConnection();
			
			String sql="select count(*) as total from orderitem where oid!=-1";
			
			ps=conn.prepareStatement(sql);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				totalNum=rs.getInt("total");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		return totalNum;
	}
	public List<OrderItem> allOrderItem(){
		return pageOrderItem(0,Short.MAX_VALUE);
	}
	//拿到分页的订单项
	public List<OrderItem> pageOrderItem(int start,int count){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		OrderItem orderitem=null;
		ProductDAO pdao=new ProductDAO();
		OrderDAO mdao=new OrderDAO();
		List<OrderItem>orderitem_list=new ArrayList<OrderItem>();
		try {
			conn=DataResource.getConnection();
			
			String sql="select * from orderitem where oid!=-1 order by id desc limit ?,?";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				orderitem = new OrderItem();
				orderitem.setId(rs.getInt("id"));
				orderitem.setProduct(pdao.queryProduct(rs.getInt("pid")));
				orderitem.setOrder(mdao.queryOrder(rs.getInt("oid")));
				orderitem.setNumber(rs.getInt("number"));
				orderitem_list.add(orderitem);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		return orderitem_list;
	}
	
}
