package tmall.bean;

//属性类，一个分类具有多条属性
public class Property {
	//id
	public int id;
	//属性名称
	public String name;
	//一个分类具有多个属性
	private Category category;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
