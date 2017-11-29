package tmall.bean;

import java.util.Date;
import java.util.List;

public class Order {
	//待付款
	public static final String PENDPAY="1";
	//待发货
	public static final String DELIVER="2";
	//待确认
	public static final String CONFIRM="3";
	//待评论
	public static final String COMMENT="4";
	//已完成
	public static final String FINISH="5";
	//已删除
	public static final String DELETE="6";
    //订单序号
	private int id;
	//订单编号
	private String orderCode;
	//收货地址
	private String address;
	//收货邮编
	private String post;
	//收货人信息
	private String receiver;
	//电话
	private String mobile;
	//用户信息
	private String userMessage;
	//创建日期
	private Date createDate;
	//支付日期
	private Date payDate;
	//发送日期
	private Date deliveryDate;
	//查收日期
	private Date confirmDate;
	//关联用户
	private User user;
	//订单状态
	private String status;
	//订单总金额
	private float totalPrice;
	//订单总数量
	private int totalNum;
	//一个订单对应多个产品
	private Product product;
	
	public String getStatusDesc(){
		String desc="未知";
		
	switch (status) {
		
		case PENDPAY:
			desc="待付款";
			break;
		case DELIVER:
			desc="待发货";
			break;
		case CONFIRM:
			desc="待确认";
			break;
		case COMMENT:
			desc="待评价";
			break;
		case FINISH:
			desc="已完成";
			break;
		case DELETE:
			desc="已删除";
			break;
		default:
			desc="未知";
			break;
		}
		return desc;
	}
	
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Date getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
