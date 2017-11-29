package tmall.bean;

import java.util.Date;

//评价类，一个用户可以有多条评价，一件商品也可以有多条评价
//所以评价类与用户和商品都是多对一的关系
public class Review {
	private int id;
	private String content;
	private Date createDate;
	private User user;
	private Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
