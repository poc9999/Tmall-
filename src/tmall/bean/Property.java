package tmall.bean;

//�����࣬һ��������ж�������
public class Property {
	//id
	public int id;
	//��������
	public String name;
	//һ��������ж������
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
