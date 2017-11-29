package tmall.bean;

import java.util.List;

import java.util.*;
import java.util.Date;
//��Ʒ�࣬�и���Ʒһ�����࣬һ�������ж����Ʒ��һ����Ʒ���ж��ͼƬ
//��Ʒ�������ж��һ�Ĺ�ϵ����Ʒ��ͼƬ����һ�Զ�Ĺ�ϵ
public class Product {
	//��Ʒid
	public int id;
	//��Ʒ����
	public String name;
	//��ƷС����
	public String subTitle;
	//ԭʼ�۸�
	public float orignalPrice;
	//�Żݼ۸�
	public float promotePrice;
	//�����
	public int stock;
	//��������
	public Date createDate;
	//��Ʒ����
	private Category category;
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", subTitle="
				+ subTitle + ", orignalPrice=" + orignalPrice
				+ ", promotePrice=" + promotePrice + ", stock=" + stock
				+ ", createDate=" + createDate + "]";
	}
	//��ʾ��Ʒ�ĵ�һ��ͼƬ
	private ProductImage firstProductImage;
	
	//��ʾ����ͼƬ����
	private List<ProductImage>productSingleImages;
	
	//��ʾ��������ͼƬ
	private List<ProductImage>productDetailImage;
	//��ʾ�ۼ�����
	private int reviewCount;
	//��ʾ��Ʒ����
	private int saleCount;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public float getOrignalPrice() {
		return orignalPrice;
	}
	public void setOrignalPrice(float orignalPrice) {
		this.orignalPrice = orignalPrice;
	}
	public float getPromotePrice() {
		return promotePrice;
	}
	public void setPromotePrice(float promotePrice) {
		this.promotePrice = promotePrice;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public ProductImage getFirstProductImage() {
		return firstProductImage;
	}
	public void setFirstProductImage(ProductImage firstProductImage) {
		this.firstProductImage = firstProductImage;
	}
	public List<ProductImage> getProductSingleImages() {
		return productSingleImages;
	}
	public void setProductSingleImages(List<ProductImage> productSingleImages) {
		this.productSingleImages = productSingleImages;
	}
	public List<ProductImage> getProductDetailImage() {
		return productDetailImage;
	}
	public void setProductDetailImage(List<ProductImage> productDetailImage) {
		this.productDetailImage = productDetailImage;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public int getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}
	
}
