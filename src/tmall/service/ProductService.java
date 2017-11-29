package tmall.service;

import java.util.List;

import tmall.bean.Product;
import tmall.bean.Property;
import tmall.bean.PropertyValue;
import tmall.dao.ProductDAO;
import tmall.util.Page;

public class ProductService {

	private ProductDAO dao=new ProductDAO();
	
	public void queryFirstList(Page<Product>page){
		
		int start=(page.getCurrentPage()-1)*page.getSize();
		
		int totalNum=dao.getTotal();
		
		page.setTotalNum(totalNum);
		page.setCurrentPage(1);
		
		List<Product> prod_list=dao.getPage_List(start,page.getSize());
		
		page.setData(prod_list);
	}
	
	public void queryAllList(Page<Product>page){
		
		int start=(page.getCurrentPage()-1)*page.getSize();
		
		List<Product> prod_list=dao.getPage_List(start,page.getSize());
		
		page.setData(prod_list);
	}
	
	public Product queryEachOne(int id){
		
		
		return dao.queryEachOne(id);
	}
		
	public List<PropertyValue> queryPropValue(int id){
		
		return dao.queryPropValue(id);
	}
	
	public List<Product>queryKeyProd(String key){
		
		
		return dao.queryKeyProd(key);
	}
	
}
