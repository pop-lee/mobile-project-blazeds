package com.sanrenxing.vos;

public class UserAttention {
	
	private int attentionId;
	private String userDeviceId;
	private String productId;//淘宝中为item_id
	private int currentLowestPrice;
	private int attentionPrice;
	private int attentionStatus;
	
	private int attentionStatusUnreadCount;
	
	public int getAttentionId() {
		return attentionId;
	}
	public void setAttentionId(int attentionId) {
		this.attentionId = attentionId;
	}
	
	public String getUserDeviceId() {
		return userDeviceId;
	}
	public void setUserDeviceId(String userDeviceId) {
		this.userDeviceId = userDeviceId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getCurrentLowestPrice() {
		return currentLowestPrice;
	}
	public void setCurrentLowestPrice(int currentLowestPrice) {
		this.currentLowestPrice = currentLowestPrice;
	}
	public int getAttentionPrice() {
		return attentionPrice;
	}
	public void setAttentionPrice(int attentionPrice) {
		this.attentionPrice = attentionPrice;
	}
	public int getAttentionStatus() {
		return attentionStatus;
	}
	public void setAttentionStatus(int attentionStatus) {
		this.attentionStatus = attentionStatus;
	}
	public int getAttentionStatusUnreadCount() {
		return attentionStatusUnreadCount;
	}
	public void setAttentionStatusUnreadCount(int attentionStatusUnreadCount) {
		this.attentionStatusUnreadCount = attentionStatusUnreadCount;
	}
	
}
