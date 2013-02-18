package com.sanrenxing.services;

import com.sanrenxing.dao.IBackyardProductDao;
import com.sanrenxing.dao.IBackyardProductDetailDao;
import com.sanrenxing.dao.IBackyardUserDao;
import com.sanrenxing.dao.IUserAttentionDao;
import com.sanrenxing.dao.IUserDao;
import com.sanrenxing.vos.BackyardProduct;
import com.sanrenxing.vos.BackyardProductDetail;
import com.sanrenxing.vos.BackyardUser;
import com.sanrenxing.vos.User;
import com.sanrenxing.vos.UserAttention;

public class BaseService {
	
	private IUserAttentionDao<UserAttention> userAttentionDao;
	
	private IUserDao<User> userDao;
	
	private IBackyardUserDao<BackyardUser> backyardUserDao;
	
	private IBackyardProductDao<BackyardProduct> backyardProductDao;
	
	private IBackyardProductDetailDao<BackyardProductDetail> backyardProductDetailDao;

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

	public IBackyardUserDao<BackyardUser> getBackyardUserDao() {
		return backyardUserDao;
	}

	public void setBackyardUserDao(IBackyardUserDao<BackyardUser> backyardUserDao) {
		this.backyardUserDao = backyardUserDao;
	}

	public IBackyardProductDao<BackyardProduct> getBackyardProductDao() {
		return backyardProductDao;
	}

	public void setBackyardProductDao(
			IBackyardProductDao<BackyardProduct> backyardProductDao) {
		this.backyardProductDao = backyardProductDao;
	}

	public IBackyardProductDetailDao<BackyardProductDetail> getBackyardProductDetailDao() {
		return backyardProductDetailDao;
	}

	public void setBackyardProductDetailDao(
			IBackyardProductDetailDao<BackyardProductDetail> backyardProductDetailDao) {
		this.backyardProductDetailDao = backyardProductDetailDao;
	}
	
}
