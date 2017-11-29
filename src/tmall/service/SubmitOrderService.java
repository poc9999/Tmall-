package tmall.service;

import tmall.bean.Order;
import tmall.dao.SubmitOrderDAO;

public class SubmitOrderService {

	SubmitOrderDAO dao=new SubmitOrderDAO();
	public void insert(Order order){
		
		dao.insert(order);
		
	}
	
	public void update(Order order){
		dao.update(order);
	}
}
