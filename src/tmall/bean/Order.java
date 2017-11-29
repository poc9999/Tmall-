package tmall.bean;

import java.util.Date;
import java.util.List;

public class Order {
	//������
	public static final String PENDPAY="1";
	//������
	public static final String DELIVER="2";
	//��ȷ��
	public static final String CONFIRM="3";
	//������
	public static final String COMMENT="4";
	//�����
	public static final String FINISH="5";
	//��ɾ��
	public static final String DELETE="6";
    //�������
	private int id;
	//�������
	private String orderCode;
	//�ջ���ַ
	private String address;
	//�ջ��ʱ�
	private String post;
	//�ջ�����Ϣ
	private String receiver;
	//�绰
	private String mobile;
	//�û���Ϣ
	private String userMessage;
	//��������
	private Date createDate;
	//֧������
	private Date payDate;
	//��������
	private Date deliveryDate;
	//��������
	private Date confirmDate;
	//�����û�
	private User user;
	//����״̬
	private String status;
	//�����ܽ��
	private float totalPrice;
	//����������
	private int totalNum;
	//һ��������Ӧ�����Ʒ
	private Product product;
	
	public String getStatusDesc(){
		String desc="δ֪";
		
	switch (status) {
		
		case PENDPAY:
			desc="������";
			break;
		case DELIVER:
			desc="������";
			break;
		case CONFIRM:
			desc="��ȷ��";
			break;
		case COMMENT:
			desc="������";
			break;
		case FINISH:
			desc="�����";
			break;
		case DELETE:
			desc="��ɾ��";
			break;
		default:
			desc="δ֪";
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
