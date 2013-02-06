package com.sanrenxing.dao;

import java.util.List;

public interface IBackyardUserDao<T> {
	public void insertBackyardUser(T user);
	
	public List<T> selectUserByName(String userName);
}
