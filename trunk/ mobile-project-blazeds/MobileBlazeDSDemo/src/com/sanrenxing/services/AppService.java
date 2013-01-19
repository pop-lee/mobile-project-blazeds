package com.sanrenxing.services;

import org.springframework.stereotype.Service;

import com.sanrenxing.enums.ResultCodeEnum;
import com.sanrenxing.utils.ResultObject;
import com.sanrenxing.vos.User;

@Service
public class AppService extends BaseService {

	/**
	 * 用户安装应用后，注册保存设备Token
	 * @param userId 用户设备的Token唯一编号
	 * @return 返回ResultObject
	 */
	public ResultObject addRegistUser(String userId) {
		User user = new User();
		user.setUserDeviceId(userId);
		this.getUserDao().insertUser(user);
		
		ResultObject ro = new ResultObject();
		ro.setResultData(ResultCodeEnum.SUCCESS_CODE);
		return ro;
	}
	
	/**
	 * 用户添加关注商品的服务接口
	 * @param userId 用户的设备Token唯一编号
	 * @param proeuctId 用户关注的商品唯一编号
	 * @return 返回ResultObject
	 */
	public ResultObject addUserAttention(String userId,String productId) {
		ResultObject ro = new ResultObject();
		ro.setResultData(ResultCodeEnum.SUCCESS_CODE);
		return ro;
	}
}
