package tmall.bean;

/*
 * 订单项实体类，在产品页面，点击立即购买 或者是加入购物车就会产生一条OrderItem对象
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
