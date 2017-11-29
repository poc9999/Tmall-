package tmall.bean;

/*
 * ������ʵ���࣬�ڲ�Ʒҳ�棬����������� �����Ǽ��빺�ﳵ�ͻ����һ��OrderItem����
 */
public class OrderItem {

	private int number;
	
	private Product product;
	
	private Order order;
	
	private User user;
	
	private int id;

	
	@Override
	public String toString() {
		return "OrderItem [number=" + number + ", product=" + product
				+ ", order=" + order + ", user=" + user + ", id=" + id + "]";
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
