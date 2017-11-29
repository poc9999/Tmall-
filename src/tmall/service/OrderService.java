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
	//显示全部的订单
	public List<Order> pageOrder(int start,int count){
		return dao.pageOrder(start, count);
	}
	//返回订单总数
	public int getTotal(){
		return dao.getTotal();
	}
	public Order queryOrder(int oid){
		return dao.queryOrder(oid);
	}
	//初始查询订单信息
	public void queryFirstList(Page<Order> page){
		//拿到订单总条数
		int totalNum=dao.getTotal();
		//塞到page对象里面去
		page.setTotalNum(totalNum);
		//拿到偏移量
		int start=(page.getCurrentPage()-1)*page.getSize();
		//刚开始是第一页
		page.setCurrentPage(1);
		
		List<Order> o_list=dao.pageOrder(start, page.getSize());
		
		page.setData(o_list);
	}
	
	//分页查询信息
	public void queryAllList(Page<Order> page){
		int start=(page.getCurrentPage()==0?0:page.getCurrentPage()-1)*page.getSize();
		
		List<Order> o_list=dao.pageOrder(start, page.getSize());
		
		page.setData(o_list);
	}
	
	public void updateById(int id,String status){
		
		dao.updateById(id, status);
	}
}
