package tmall.service;

import java.util.List;

import tmall.bean.Category;
import tmall.dao.CategoryDAO;
import tmall.util.Page;

//分类服务层
public class CategoryService {

	private CategoryDAO dao=new CategoryDAO(new Category());
	//后面的分页查询
	public void queryAllList(Page<Category> page){
		
		int start=(page.getCurrentPage()==0?0:page.getCurrentPage()-1)*page.getSize();
		
		List<Category> cate_list=dao.getPage_List(start, page.getSize());
		
		page.setData(cate_list);
	}
	//显示第一页
	public void queryFirstList(Page<Category> page){
		//拿到偏移量
		int start=(page.getCurrentPage()-1)*page.getSize();
		//拿到总条数
		int totalNum=dao.getTotal();
		//传回总条数
		page.setTotalNum(totalNum);
		
		page.setCurrentPage(1);
		
		List<Category> cate_list=dao.getPage_List(start, page.getSize());
		
		page.setData(cate_list);
	}
	
	
	public void delete(int cid){
		
		dao.delete(cid);
		
	}
	
	public void insert(String cname){
		dao.insert(cname);
	}
}
