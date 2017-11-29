package tmall.service;

import java.util.List;

import tmall.bean.Property;
import tmall.dao.PropertyDAO;

public class PropertyService {

	PropertyDAO dao=new PropertyDAO();
	public List<Property>queryCategory(String cid){
		
		if(cid!=null&cid.length()!=0){
			
			return dao.queryCategory(cid);
		}
		return null;
	}
}
