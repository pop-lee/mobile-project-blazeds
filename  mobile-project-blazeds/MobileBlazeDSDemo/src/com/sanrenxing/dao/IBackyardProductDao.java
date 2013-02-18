package com.sanrenxing.dao;

import java.util.List;
import java.util.Map;

public interface IBackyardProductDao<T> {
	public List<T> selectProductByPage(Map<String,Integer> paramMap);
	
	public List<T> selectAllProduct();
	
	public List<T> selectProductById(String productId);
	
	public void insertBackyardProduct(T product);
	
	public void updateBackyardProduct(T product);
	
	public void updatePushCountById(T product);
}
