package com.sanrenxing.dao;

import java.util.List;

public interface IBackyardUserDao<T> {
	public int insertBackyardUser(T user);
	
	public List<T> selectUserByName(String userName);
	
	public List<T> selectUser(T user);
}
