package tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tmall.bean.Order;
import tmall.util.DataResource;
import tmall.util.DateUtil;

public class SubmitOrderDAO {
	//插入订单信息
	public void insert(Order order){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn = DataResource.getConnection();
			
			String sql="insert into order_ values(null,?,?,?,?,?,?,?,?,?,?,?,?)";
			//获取不到主键需要带一个参数
			ps=conn.prepareStatement(sql,java.sql.Statement.RETURN_GENERATED_KEYS);
					
			ps.setString(1, order.getOrderCode());
			ps.setString(2, order.getAddress());
			ps.setString(3, order.getPost());
			ps.setString(4, order.getReceiver());
			ps.setString(5, order.getMobile());
			ps.setString(6, order.getUserMessage());
			ps.setTimestamp(7, DateUtil.d2t(order.getCreateDate()));
			ps.setTimestamp(8, DateUtil.d2t(order.getPayDate()));
			ps.setTimestamp(9, DateUtil.d2t(order.getDeliveryDate()));
			ps.setTimestamp(10, DateUtil.d2t(order.getConfirmDate()));
			ps.setInt(11, order.getUser().getId());
			ps.setString(12, order.getStatus());
			
			ps.executeUpdate();
			
			//获取订单主键id
			rs=ps.getGeneratedKeys();
			if(rs.next()){
				order.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		
	}
	//更新订单信息
	public void update(Order order){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DataResource.getConnection();
			
			String sql="update order_ set orderCode=?,address=?,post=?,receiver=?,"
					+ "mobile=?,userMessage=?,createDate=?,payDate=?,deliveryDate=?,"
					+ "confirmDate=?,uid=?,status=? where id=?";
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, order.getOrderCode());
			ps.setString(2, order.getAddress());
			ps.setString(3, order.getPost());
			ps.setString(4, order.getReceiver());
			ps.setString(5, order.getMobile());
			ps.setString(6, order.getUserMessage());
			ps.setTimestamp(7, DateUtil.d2t(order.getCreateDate()));
			ps.setTimestamp(8, DateUtil.d2t(order.getPayDate()));
			ps.setTimestamp(9, DateUtil.d2t(order.getDeliveryDate()));
			ps.setTimestamp(10, DateUtil.d2t(order.getConfirmDate()));
			ps.setInt(11, order.getUser().getId());
			ps.setString(12, order.getStatus());
			ps.setInt(13, order.getId());
			//执行该方法
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		
	}
	
}
