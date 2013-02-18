package com.sanrenxing.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sanrenxing.enums.ResultCodeEnum;
import com.sanrenxing.utils.ResultObject;
import com.sanrenxing.vos.BackyardProduct;
import com.sanrenxing.vos.BackyardProductDetail;
import com.sanrenxing.vos.BackyardUser;

@Service
public class BackyardService extends BaseService {
	
	public ResultObject insertUser(BackyardUser user)
	{
		ResultObject ro = new ResultObject();
		ro.setResultCode(ResultCodeEnum.SUCCESS_CODE);
		return ro;
	}
	
	public ResultObject selectPushProductById(String productId) {
		
		ResultObject ro = new ResultObject();
		ro.setResultCode(ResultCodeEnum.SUCCESS_CODE);
		return ro;
	}
	
	public ResultObject checkUser(String userEmail) {
		this.getBackyardUserDao().selectUserByName(userEmail);
		
		ResultObject ro = new ResultObject();
		ro.setResultCode(ResultCodeEnum.SUCCESS_CODE);
		return ro;
	}
	
	public ResultObject regUser(BackyardUser user) {
		int userId = this.getBackyardUserDao().insertBackyardUser(user);
		
		ResultObject ro = new ResultObject();
		ro.setResultCode(ResultCodeEnum.SUCCESS_CODE);
		ro.setResultData(userId);
		return ro;
	}
	
	public ResultObject login(BackyardUser user) {
		List<BackyardUser> findUser= this.getBackyardUserDao().selectUser(user);
		ResultObject ro = new ResultObject();
		if(findUser.size()>0) {
			ro.setResultCode(ResultCodeEnum.SUCCESS_CODE);
			ro.setResultData(findUser.get(0));
			return ro;
		} else {
			ro.setResultCode(ResultCodeEnum.FAULT_CODE);
			return ro;
		}
			
	}
	
	public ResultObject checkProduct(String productId) {
		List<BackyardProduct> products = this.getBackyardProductDao().selectProductById(productId);
		ResultObject ro = new ResultObject();
		if(products.size()>0) {
			ro.setResultCode(ResultCodeEnum.SUCCESS_CODE);
			return ro;
		} else {
			ro.setResultCode(ResultCodeEnum.FAULT_CODE);
			return ro;
		}
	}
	
	public ResultObject addPushProduct(BackyardProduct product) {
		List<BackyardProduct> products = this.getBackyardProductDao().selectProductById(product.getProductId());
		if(products.size()>0) {
//			this.getBackyardProductDao().updateBackyardProduct(product);
			int len = product.getProductDetail().size();
			for(int i=0;i<len;i++) {
				this.getBackyardProductDetailDao().insertBackyardProductDetail(product.getProductDetail().get(i));
				product.setPushCount(this.getBackyardProductDetailDao().selectDetailCountById(product.getProductId()));
				this.getBackyardProductDao().updatePushCountById(product);
			}
		} else {
			int len = product.getProductDetail().size();
			for(int i=0;i<len;i++) {
				this.getBackyardProductDao().insertBackyardProduct(product);
				this.getBackyardProductDetailDao().insertBackyardProductDetail(product.getProductDetail().get(i));
				product.setPushCount(this.getBackyardProductDetailDao().selectDetailCountById(product.getProductId()));
				this.getBackyardProductDao().updatePushCountById(product);
			}
		}
		
		return selectAllProduct();
	}
	
	public ResultObject selectAllProduct() {
		List<BackyardProduct> products = this.getBackyardProductDao().selectAllProduct();
		
		ResultObject ro = new ResultObject();
		ro.setResultCode(ResultCodeEnum.SUCCESS_CODE);
		ro.setResultData(products);
		return ro;
	}
	
	public ResultObject selectProductByPage(int count,int page) {
		Map<String, Integer> param=new HashMap<String, Integer>();
		param.put("count", count);  
		param.put("column", (page-1)*count);  
		
		List<BackyardProduct> products = this.getBackyardProductDao().selectProductByPage(param);
		
		ResultObject ro = new ResultObject();
		ro.setResultCode(ResultCodeEnum.SUCCESS_CODE);
		ro.setResultData(products);
		return ro;
	}
	
	public ResultObject selectProductDetailById(String productId) {
		List<BackyardProductDetail> productDetails = this.getBackyardProductDetailDao().selectProductDetailById(productId);
		
		ResultObject ro = new ResultObject();
		ro.setResultCode(ResultCodeEnum.SUCCESS_CODE);
		ro.setResultData(productDetails);
		return ro;
	}
	
	public ResultObject addActivity() {
		
		ResultObject ro = new ResultObject();
		ro.setResultCode(ResultCodeEnum.SUCCESS_CODE);
		return ro;
	}
}
