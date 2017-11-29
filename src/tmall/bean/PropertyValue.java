package tmall.bean;

//属性值类，与产品是多对一的关系，与属性是多对一的关系
//产品与属性是一个多对多的关系，所以拆分成三个表
public class PropertyValue {
	
	private int id;
	private String value;
	
	private Product product;
	
	private Property property;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	
}
