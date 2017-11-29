package tmall.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * 万能DAO
 * @author poc999
 *
 */
public class UniversalDAO {
	// 根据类的Class查询数据库里面的额所有条目详细信息
	public ArrayList getList(Class cl) {
		return getList(cl, 0, Short.MAX_VALUE);
	}

	// 分页查询
	public ArrayList getList(Class cl, int start, int count) {
		ArrayList list = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		// 由于数据库中的表名对应的是实体类的类名，所以可以通过传入的类得到表名cl.getSimpleName()
		String sql = "select * from " + cl.getSimpleName()
				+ " order by id desc limit " + start + " , " + count;
		// 获取类对象的所有公共属性
		Field[] fi = cl.getFields();
		try {
			conn = DataResource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Object object = cl.newInstance();// 实例化类对象
				for (Field ff : fi) {
					// 打开控制访问权限 ,不然private修饰的变量无法访问
					ff.setAccessible(true);
					if(ff.getName().contains("Date")){
						ff.set(object, DateUtil.t2d((Timestamp)rs.getObject(ff.getName())));
					}
					// 将指定对象变量上此 Field 对象表示的字段设置为指定的新值。
					ff.set(object, rs.getObject(ff.getName()));
					// System.out.println(ff.getName());
				}
				list.add(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataResource.closeAll(conn, ps, rs);
		}
		return list;
	}
	
	// 根据表的主键查询表的单个对象
	public Object selectById(Class cl, int id) {
		Object object = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		// 获取该类的所有属性
		Field[] f = cl.getFields();

		String sql = "select * from " + cl.getSimpleName() + " where "
				+ f[0].getName() + " = " + id;
		try {
			conn = DataResource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				object = cl.newInstance();

				for (Field ff : f) {
					ff.setAccessible(true);
					ff.set(object, rs.getObject(ff.getName()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataResource.closeAll(conn, ps, rs);
		}
		return object;
	}

	// 插入数据 返回值为boolean 判断是否插入成功
	public boolean insert(Object obj) {

		boolean flag = false;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = null;
		Class cl = obj.getClass();
		// 拿到所有的属性
		Field[] field = cl.getFields();

		StringBuffer sb = new StringBuffer();

		sb.append("insert into ");

		sb.append(cl.getSimpleName());

		sb.append(" ( ");

		for (int i = 0; i < field.length; i++) {

			sb.append(field[i].getName());
			// 这里可以使用两种方法实现
			if (i != field.length - 1) {
				sb.append(" , ");
			}
		}
		// id自增，直接赋值为null
		sb.append(" ) values ( null , ");

		for (int i = 1; i < field.length; i++) {
			sb.append("?");
			if (i != field.length - 1) {
				sb.append(" , ");
			}
		}
		sb.append(")");

		System.out.println(sb.toString());
		try {
			conn = DataResource.getConnection();
			ps = conn.prepareStatement(sb.toString());

			for (int i = 1; i < field.length; i++) {
				field[i].setAccessible(true);
				// 返回指定对象上面此字段的值
				ps.setObject(i, field[i].get(obj));
			}

			int num = ps.executeUpdate();
			// 获取主键
//			rs = ps.getGeneratedKeys();
			// 把主键发送到Obj对象里面去
			if (rs.next()) {
				field[0].set(obj, rs.getInt("id"));
			}
			if (num > 0) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataResource.closeAll(conn, ps, rs);
		}
		return flag;
	}

	// 执行更新操作
	public boolean update(Object obj) {

		boolean flag = false;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = null;

		Class cl = obj.getClass();

		Field[] fl = cl.getFields();

		StringBuffer sb = new StringBuffer();

		sb.append("update ");

		sb.append(cl.getSimpleName());

		sb.append(" set ");

		for (int i = 1; i < fl.length; i++) {

			sb.append(fl[i].getName());
			sb.append("=? ");
			if (i != fl.length - 1) {
				sb.append(",");
			}
		}

		sb.append(" where ");
		sb.append(fl[0].getName());
		sb.append("=?");

		try {
			conn = DataResource.getConnection();
			ps = conn.prepareStatement(sb.toString());

			for (int i = 1; i < fl.length; i++) {
				// 打开操作权限
				fl[i].setAccessible(true);
				ps.setObject(i, fl[i].get(obj));
			}
			fl[0].setAccessible(true);
			ps.setObject(fl.length, fl[0].get(obj));

			int num = ps.executeUpdate();
			if (num > 0) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DataResource.closeAll(conn, ps, rs);
		}
		return flag;
	}

	// 根据主键删除
	public boolean deleteById(Class cl, int id) {

		boolean flag = false;
		PreparedStatement ps = null;
		Connection conn = null;
		Field[] fl = cl.getFields();
		String sql = "delete from " + cl.getSimpleName() + " where "
				+ fl[0].getName() + "=?";

		try {
			conn = DataResource.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setObject(1, id);

			int num = ps.executeUpdate();

			if (num > 0) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DataResource.closeAll(conn, ps, null);
		}
		return flag;
	}

	 //根据指定条件查找
//	 public ArrayList getList(Class cl,String name,Object value){
//		 
//		 
//	
//	 }

	// 查询总记录数
	public int getTotal(Class cl) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		int totalNum = 0;

		try {
			conn = DataResource.getConnection();
			String sql = "select count(*) from " + cl.getSimpleName();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				totalNum = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DataResource.closeAll(conn, ps, rs);
		}
		return totalNum;
	}
}
