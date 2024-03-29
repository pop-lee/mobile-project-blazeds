package com.sanrenxing.dao;

import java.util.Date;
import java.util.List;


public interface IBackyardProductDetailDao<T> {
	public void insertBackyardProductDetail(T product);
	
	public int selectDetailCountById(String productId);
	
	public List<T> selectProductDetailById(String productId);
	
	public Date selectEarlyActivity();
	
	public List<T> selectProductDetailByDate(Date pushDate);
	
	public void updateProductDetailPushStatus(int pushProductDetailId);
}
