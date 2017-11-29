package tmall.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DataResource {
	//获得数据库连接池的方法
	public static Connection getConnection()throws Exception{
		
		Connection conn=null;
		
		Context ctx=new InitialContext();
		
		ctx = (Context)ctx.lookup("java:/comp/env");
		
		DataSource source=(DataSource)ctx.lookup("java/webdb1");
		
		conn=source.getConnection();
		
		System.out.println(conn);
		
		return conn;
	}
	//所有连接关闭的方法
	public static void closeAll(Connection conn,PreparedStatement ps,ResultSet rs){
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
