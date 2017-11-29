package tmall.service;

import java.util.List;

import tmall.bean.Order;
import tmall.bean.OrderItem;
import tmall.bean.User;
import tmall.dao.OrderItemDAO;
import tmall.util.Page;

public class OrderItemService {

	OrderItemDAO dao=new OrderItemDAO();
	
	public List<OrderItem> ListByUser(int uid){
		return dao.ListByUser(uid);
	}
	
	public List<OrderItem> ListByProduct(int pid){
		return dao.ListByProd(pid);
	}
	
	public List<OrderItem> ListByOrder(int oid){
		return dao.ListByOrder(oid);
	}
	
	public void insert(OrderItem oi){
		dao.insert(oi);
	}
	
	public int getTotalByUser(User user){
		
		return dao.getTotalByUser(user);
	}
	
	public void update(OrderItem orderitem){
		dao.update(orderitem);
	}
	
	public void delete(int id){
		dao.delete(id);
	}
	
	public int getNumber(int pid){
		return dao.getNumber(pid);
	}
	
	public static OrderItem get(int id){
		return OrderItemDAO.get(id);
	}
	
	public void updateoid(OrderItem orderitem){
		dao.updateoid(orderitem);
	}
	//根据用户id查询到已经生成订单的订单项
	public List<OrderItem> queryOrderItem(int uid){
		return dao.queryOrderItem(uid);
	}
	
	public List<OrderItem> get(){
		return dao.get();
	}
	
		//初始查询订单信息
		public void queryFirstList(Page<OrderItem> page){
			//拿到订单总条数
			int totalNum=dao.getTotal();
			//塞到page对象里面去
			page.setTotalNum(totalNum);
			//拿到偏移量
			int start=(page.getCurrentPage()-1)*page.getSize();
			//刚开始是第一页
			page.setCurrentPage(1);
			
			List<OrderItem> orderitem_list=dao.pageOrderItem(start, page.getSize());
			
			page.setData(orderitem_list);
		}
		
		//分页查询信息
		public void queryAllList(Page<OrderItem> page){
			
			int start=(page.getCurrentPage()==0?0:page.getCurrentPage()-1)*page.getSize();
			
			List<OrderItem> orderitem_list=dao.pageOrderItem(start, page.getSize());
			
			page.setData(orderitem_list);
			
		}
	
	
}
