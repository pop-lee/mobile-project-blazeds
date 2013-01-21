package com.sanrenxing.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao<T> {
	public void insertUser(T user);
}
