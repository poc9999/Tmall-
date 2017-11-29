package tmall.bean;

import java.util.List;

import tmall.bean.Product;

//商品分类 类   里面有多种商品, 一行商品里面有多个小类
public class Category {
	//一个商品分类对应多种产品
	public int id;
	public String name;

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	//一个分类有多个商品
	private List<Product> prod_list;
	//一个分类里面有一个集合(前台页面)
	private List<List<Product>>productByRow;
	
	public List<Product> getProd_list() {
		return prod_list;
	}
	public void setProd_list(List<Product> prod_list) {
		this.prod_list = prod_list;
	}
	public List<List<Product>> getProductByRow() {
		return productByRow;
	}
	public void setProductByRow(List<List<Product>> productByRow) {
		this.productByRow = productByRow;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
