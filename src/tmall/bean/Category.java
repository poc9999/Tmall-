package tmall.bean;

import java.util.List;

import tmall.bean.Product;

//��Ʒ���� ��   �����ж�����Ʒ, һ����Ʒ�����ж��С��
public class Category {
	//һ����Ʒ�����Ӧ���ֲ�Ʒ
	public int id;
	public String name;

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	//һ�������ж����Ʒ
	private List<Product> prod_list;
	//һ������������һ������(ǰ̨ҳ��)
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
