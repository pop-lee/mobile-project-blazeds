package com.sanrenxing.vos;

import java.util.Date;

public class BackyardProductDetail {
	private int pushProductDetailId;
	private int pushProductId;
	private String productId;
	private int saleType;
	private float saleTypeValue;
	private Date activityStartDate;
	private Date activityEndDate;
	// -1为推送 1以推送
	private int pushStatus;
	private int activityPublisher;
	
	public int getPushProductDetailId() {
		return pushProductDetailId;
	}
	public void setPushProductDetailId(int pushProductDetailId) {
		this.pushProductDetailId = pushProductDetailId;
	}
	public int getPushProductId() {
		return pushProductId;
	}
	public void setPushProductId(int pushProductId) {
		this.pushProductId = pushProductId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getSaleType() {
		return saleType;
	}
	public void setSaleType(int saleType) {
		this.saleType = saleType;
	}
	public float getSaleTypeValue() {
		return saleTypeValue;
	}
	public void setSaleTypeValue(float saleTypeValue) {
		this.saleTypeValue = saleTypeValue;
	}
	public Date getActivityStartDate() {
		return activityStartDate;
	}
	public void setActivityStartDate(Date activityStartDate) {
		this.activityStartDate = activityStartDate;
	}
	public Date getActivityEndDate() {
		return activityEndDate;
	}
	public void setActivityEndDate(Date activityEndDate) {
		this.activityEndDate = activityEndDate;
	}
	public int getPushStatus() {
		return pushStatus;
	}
	public void setPushStatus(int pushStatus) {
		this.pushStatus = pushStatus;
	}
	public int getActivityPublisher() {
		return activityPublisher;
	}
	public void setActivityPublisher(int activityPublisher) {
		this.activityPublisher = activityPublisher;
	}
	
}
