package tmall.service;

import java.util.List;

import tmall.bean.Category;
import tmall.dao.CategoryDAO;
import tmall.util.Page;

//��������
public class CategoryService {

	private CategoryDAO dao=new CategoryDAO(new Category());
	//����ķ�ҳ��ѯ
	public void queryAllList(Page<Category> page){
		
		int start=(page.getCurrentPage()==0?0:page.getCurrentPage()-1)*page.getSize();
		
		List<Category> cate_list=dao.getPage_List(start, page.getSize());
		
		page.setData(cate_list);
	}
	//��ʾ��һҳ
	public void queryFirstList(Page<Category> page){
		//�õ�ƫ����
		int start=(page.getCurrentPage()-1)*page.getSize();
		//�õ�������
		int totalNum=dao.getTotal();
		//����������
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
