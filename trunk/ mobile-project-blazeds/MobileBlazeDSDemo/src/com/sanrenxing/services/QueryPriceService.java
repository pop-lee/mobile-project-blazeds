package com.sanrenxing.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sanrenxing.vos.UserAttention;

@Service
public class QueryPriceService extends BaseService {

	public void queryPrice() {
		List<UserAttention> list = this.getUserAttentionDao().selectListUserAttention();
		System.out.println(list.size());
		System.out.println(Calendar.getInstance().toString());
//		System.out.println("queryPrice Start");
	}
	
	public void test() {
		System.out.println("this is test for flex+spring");
	}
}
