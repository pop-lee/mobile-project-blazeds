package com.sanrenxing.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IUserAttentionDao<T> {
	public List<T> selectListUserAttention();
	
	public List<T> selectListUserAttentionByProductId(String productId);
	
	public int selectUnreadStatusCountByUserDeviceId(String userDeviceId);
	
	public void updateUnreadStatusByAttentionId(int attentionId);
	
	public void insertUserAttention(T userAttention);
}
