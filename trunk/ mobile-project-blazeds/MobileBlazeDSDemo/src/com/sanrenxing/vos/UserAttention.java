package com.sanrenxing.vos;

public class UserAttention {
	
	private int attentionId;
	private int userId;
	private int productId;//淘宝中为item_id
	private int currentLowestPrice;
	private int attentionPrice;
	
	
	public int getAttentionId() {
		return attentionId;
	}
	public void setAttentionId(int attentionId) {
		this.attentionId = attentionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
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
	
	
}
