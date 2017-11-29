package tmall.service;

import java.util.List;

import tmall.bean.Category;
import tmall.dao.AllHomePageDataDAO;

public class AllHomePageDataService {
	
	private AllHomePageDataDAO dao = new AllHomePageDataDAO();
	
	public List<Category>cate_list(){
			
		return dao.cate_list();
	}
}
