package tmall.bean;

import java.util.List;

import java.util.*;
import java.util.Date;
//产品类，有个产品一个分类，一个分类有多个产品，一个产品具有多个图片
//产品与分类具有多对一的关系，产品和图片具有一对多的关系
public class Product {
	//产品id
	public int id;
	//产品名称
	public String name;
	//产品小标题
	public String subTitle;
	//原始价格
	public float orignalPrice;
	//优惠价格
	public float promotePrice;
	//库存量
	public int stock;
	//创建日期
	public Date createDate;
	//产品分类
	private Category category;
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", subTitle="
				+ subTitle + ", orignalPrice=" + orignalPrice
				+ ", promotePrice=" + promotePrice + ", stock=" + stock
				+ ", createDate=" + createDate + "]";
	}
	//显示产品的第一张图片
	private ProductImage firstProductImage;
	
	//显示介绍图片集合
	private List<ProductImage>productSingleImages;
	
	//显示所有详情图片
	private List<ProductImage>productDetailImage;
	//显示累计评价
	private int reviewCount;
	//显示产品销量
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
