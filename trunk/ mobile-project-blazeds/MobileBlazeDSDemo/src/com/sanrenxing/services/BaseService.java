package com.sanrenxing.services;

import com.sanrenxing.dao.IUserAttentionDao;
import com.sanrenxing.dao.IUserDao;
import com.sanrenxing.vos.User;
import com.sanrenxing.vos.UserAttention;

public class BaseService {
	
	private IUserAttentionDao<UserAttention> userAttentionDao;
	
	private IUserDao<User> userDao;

	public IUserAttentionDao<UserAttention> getUserAttentionDao() {
		return userAttentionDao;
	}

	public void setUserAttentionDao(IUserAttentionDao<UserAttention> userAttentionDao) {
		this.userAttentionDao = userAttentionDao;
	}

	public IUserDao<User> getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao<User> userDao) {
		this.userDao = userDao;
	}
}
