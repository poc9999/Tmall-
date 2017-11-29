package tmall.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * ����DAO
 * @author poc999
 *
 */
public class UniversalDAO {
	// �������Class��ѯ���ݿ�����Ķ�������Ŀ��ϸ��Ϣ
	public ArrayList getList(Class cl) {
		return getList(cl, 0, Short.MAX_VALUE);
	}

	// ��ҳ��ѯ
	public ArrayList getList(Class cl, int start, int count) {
		ArrayList list = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		// �������ݿ��еı�����Ӧ����ʵ��������������Կ���ͨ���������õ�����cl.getSimpleName()
		String sql = "select * from " + cl.getSimpleName()
				+ " order by id desc limit " + start + " , " + count;
		// ��ȡ���������й�������
		Field[] fi = cl.getFields();
		try {
			conn = DataResource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Object object = cl.newInstance();// ʵ���������
				for (Field ff : fi) {
					// �򿪿��Ʒ���Ȩ�� ,��Ȼprivate���εı����޷�����
					ff.setAccessible(true);
					if(ff.getName().contains("Date")){
						ff.set(object, DateUtil.t2d((Timestamp)rs.getObject(ff.getName())));
					}
					// ��ָ����������ϴ� Field �����ʾ���ֶ�����Ϊָ������ֵ��
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
	
	// ���ݱ��������ѯ��ĵ�������
	public Object selectById(Class cl, int id) {
		Object object = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		// ��ȡ�������������
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

	// �������� ����ֵΪboolean �ж��Ƿ����ɹ�
	public boolean insert(Object obj) {

		boolean flag = false;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = null;
		Class cl = obj.getClass();
		// �õ����е�����
		Field[] field = cl.getFields();

		StringBuffer sb = new StringBuffer();

		sb.append("insert into ");

		sb.append(cl.getSimpleName());

		sb.append(" ( ");

		for (int i = 0; i < field.length; i++) {

			sb.append(field[i].getName());
			// �������ʹ�����ַ���ʵ��
			if (i != field.length - 1) {
				sb.append(" , ");
			}
		}
		// id������ֱ�Ӹ�ֵΪnull
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
				// ����ָ������������ֶε�ֵ
				ps.setObject(i, field[i].get(obj));
			}

			int num = ps.executeUpdate();
			// ��ȡ����
//			rs = ps.getGeneratedKeys();
			// ���������͵�Obj��������ȥ
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

	// ִ�и��²���
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
				// �򿪲���Ȩ��
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

	// ��������ɾ��
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

	 //����ָ����������
//	 public ArrayList getList(Class cl,String name,Object value){
//		 
//		 
//	
//	 }

	// ��ѯ�ܼ�¼��
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
