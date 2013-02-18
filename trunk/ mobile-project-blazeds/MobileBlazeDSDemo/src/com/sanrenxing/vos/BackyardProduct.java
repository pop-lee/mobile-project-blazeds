package com.sanrenxing.vos;

import java.util.Date;
import java.util.List;

public class BackyardProduct {
	private String productId;
	private String productName;
	private String productOriginalPrice;
	private Date shelvesDate;
	private int pushCount;
	private List<BackyardProductDetail> productDetail;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductOriginalPrice() {
		return productOriginalPrice;
	}
	public void setProductOriginalPrice(String productOriginalPrice) {
		this.productOriginalPrice = productOriginalPrice;
	}
	public Date getShelvesDate() {
		return shelvesDate;
	}
	public void setShelvesDate(Date shelvesDate) {
		this.shelvesDate = shelvesDate;
	}
	public List<BackyardProductDetail> getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(List<BackyardProductDetail> productDetail) {
		this.productDetail = productDetail;
	}
	public int getPushCount() {
		return pushCount;
	}
	public void setPushCount(int pushCount) {
		this.pushCount = pushCount;
	}
	
	

}
