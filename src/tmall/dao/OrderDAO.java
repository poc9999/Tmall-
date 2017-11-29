package tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tmall.bean.Order;
import tmall.util.DataResource;
import tmall.util.DateUtil;

public class OrderDAO {
	//根据用户的id查询用户所有的订单
	public List<Order>list(int uid){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Order order=null;
		List<Order> order_list=new ArrayList<Order>();
		try {
			conn=DataResource.getConnection();
			
			String sql="select * from order_ where uid=?";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, uid);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				order = new Order();
				order.setId(rs.getInt("id"));
				order.setOrderCode(rs.getString("orderCode"));
				order.setAddress(rs.getString("address"));
				order.setPost(rs.getString("post"));
				order.setReceiver(rs.getString("receiver"));
				order.setMobile(rs.getString("mobile"));
				order.setUserMessage(rs.getString("userMessage"));
				order.setStatus(rs.getString("status"));
				order.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				order.setPayDate(DateUtil.t2d(rs.getTimestamp("payDate")));
				order.setDeliveryDate(DateUtil.t2d(rs.getTimestamp("deliveryDate")));
				order.setConfirmDate(DateUtil.t2d(rs.getTimestamp("confirmDate")));
				order_list.add(order);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		return order_list;
	}
	//根据一个id主键去查询某个订单
	public Order queryOrder(int id){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Order order=null;
		
		try {
			conn=DataResource.getConnection();
			
			String sql="select * from order_ where id=?";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				order = new Order();
				order.setId(rs.getInt("id"));
				order.setOrderCode(rs.getString("orderCode"));
				order.setAddress(rs.getString("address"));
				order.setPost(rs.getString("post"));
				order.setReceiver(rs.getString("receiver"));
				order.setMobile(rs.getString("mobile"));
				order.setUserMessage(rs.getString("userMessage"));
				order.setStatus(rs.getString("status"));
				order.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				order.setPayDate(DateUtil.t2d(rs.getTimestamp("payDate")));
				order.setDeliveryDate(DateUtil.t2d(rs.getTimestamp("deliveryDate")));
				order.setConfirmDate(DateUtil.t2d(rs.getTimestamp("confirmDate")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		return order;
	}
	
	//通过订单状态去查询订单的信息
	public List<Order> orderByKey(int uid,String status){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Order order=null;
		List<Order>order_list=new ArrayList<Order>();
		
		try {
			conn=DataResource.getConnection();
			
			String sql="select * from where order_ order by id desc where uid=? and status=?";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, uid);
			ps.setString(2, status);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				order=new Order();
				order.setId(rs.getInt("id"));
				order.setOrderCode(rs.getString("orderCode"));
				order.setAddress(rs.getString("address"));
				order.setPost(rs.getString("post"));
				order.setReceiver(rs.getString("receiver"));
				order.setMobile(rs.getString("mobile"));
				order.setUserMessage(rs.getString("userMessage"));
				order.setStatus(rs.getString("status"));
				order.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				order.setPayDate(DateUtil.t2d(rs.getTimestamp("payDate")));
				order.setDeliveryDate(DateUtil.t2d(rs.getTimestamp("deliveryDate")));
				order.setConfirmDate(DateUtil.t2d(rs.getTimestamp("confirmDate")));
				order_list.add(order);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		return order_list;
	}
	
	public List<Order> allOrder(){
		return pageOrder(0,Short.MAX_VALUE);
	}
	//所有的订单
	public List<Order> pageOrder(int start,int count){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Order order=null;
		List<Order>order_list=new ArrayList<Order>();
		
		try {
			conn=DataResource.getConnection();
			
			String sql="select * from order_ order by id desc limit ?,?";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, start);
			ps.setInt(2, count);
			rs=ps.executeQuery();
			
			while(rs.next()){
				order=new Order();
				order.setId(rs.getInt("id"));
				order.setOrderCode(rs.getString("orderCode"));
				order.setAddress(rs.getString("address"));
				order.setPost(rs.getString("post"));
				order.setReceiver(rs.getString("receiver"));
				order.setMobile(rs.getString("mobile"));
				order.setUserMessage(rs.getString("userMessage"));
				order.setStatus(rs.getString("status"));
				order.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				order.setPayDate(DateUtil.t2d(rs.getTimestamp("payDate")));
				order.setDeliveryDate(DateUtil.t2d(rs.getTimestamp("deliveryDate")));
				order.setConfirmDate(DateUtil.t2d(rs.getTimestamp("confirmDate")));
				order_list.add(order);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		return order_list;
	}
	//拿到总订单数
	public int getTotal(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int totalNum=0;
		try {
			conn=DataResource.getConnection();
			
			String sql="select count(*) as total from order_";
			
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
	//根据id去更新订单信息
	public void updateById(int id,String status){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DataResource.getConnection();
			String sql1="update order_ set status=?,deliveryDate=? where id=?";
			String sql2="update order_ set status=?,confirmDate=? where id=?";
			if(Order.CONFIRM.equals(status)){
				ps=conn.prepareStatement(sql1);
				ps.setString(1, status);
				ps.setTimestamp(2, DateUtil.d2t(new Date()));
			}else if(Order.COMMENT.equals(status)){
				ps=conn.prepareStatement(sql2);
				ps.setString(1, status);
				ps.setTimestamp(2, DateUtil.d2t(new Date()));
			}
			ps.setInt(3, id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
	}
}
