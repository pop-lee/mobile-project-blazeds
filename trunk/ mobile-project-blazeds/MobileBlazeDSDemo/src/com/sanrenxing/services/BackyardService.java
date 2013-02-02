package com.sanrenxing.services;

import org.springframework.stereotype.Service;

import com.sanrenxing.enums.ResultCodeEnum;
import com.sanrenxing.utils.ResultObject;
import com.sanrenxing.vos.BackyardUser;

@Service
public class BackyardService extends BaseService {

	public ResultObject insertUser(BackyardUser user)
	{
		ResultObject ro = new ResultObject();
		ro.setResultData(ResultCodeEnum.SUCCESS_CODE);
		return ro;
	}
	
	public ResultObject selectPushProductById(String productId) {
		
		ResultObject ro = new ResultObject();
		ro.setResultData(ResultCodeEnum.SUCCESS_CODE);
		return ro;
	}
}
