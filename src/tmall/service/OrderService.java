package tmall.service;

import java.util.List;

import tmall.bean.Category;
import tmall.bean.Order;
import tmall.dao.OrderDAO;
import tmall.util.Page;

public class OrderService {

	OrderDAO dao=new OrderDAO();
	public List<Order>list(int uid){
		
		return dao.list(uid);
	}
	//��ʾȫ���Ķ���
	public List<Order> pageOrder(int start,int count){
		return dao.pageOrder(start, count);
	}
	//���ض�������
	public int getTotal(){
		return dao.getTotal();
	}
	public Order queryOrder(int oid){
		return dao.queryOrder(oid);
	}
	//��ʼ��ѯ������Ϣ
	public void queryFirstList(Page<Order> page){
		//�õ�����������
		int totalNum=dao.getTotal();
		//����page��������ȥ
		page.setTotalNum(totalNum);
		//�õ�ƫ����
		int start=(page.getCurrentPage()-1)*page.getSize();
		//�տ�ʼ�ǵ�һҳ
		page.setCurrentPage(1);
		
		List<Order> o_list=dao.pageOrder(start, page.getSize());
		
		page.setData(o_list);
	}
	
	//��ҳ��ѯ��Ϣ
	public void queryAllList(Page<Order> page){
		int start=(page.getCurrentPage()==0?0:page.getCurrentPage()-1)*page.getSize();
		
		List<Order> o_list=dao.pageOrder(start, page.getSize());
		
		page.setData(o_list);
	}
	
	public void updateById(int id,String status){
		
		dao.updateById(id, status);
	}
}
