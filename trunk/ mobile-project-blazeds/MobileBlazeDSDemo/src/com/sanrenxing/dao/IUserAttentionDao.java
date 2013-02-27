package com.sanrenxing.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IUserAttentionDao<T> {
	public List<T> selectListUserAttention();
	
	public List<T> selectListUserAttentionByProductId(String productId);
}
