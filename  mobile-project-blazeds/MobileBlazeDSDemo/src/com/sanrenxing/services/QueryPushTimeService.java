package com.sanrenxing.services;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QueryPushTimeService extends BaseService {

	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("??");
		this.getBackyardProductDetailDao();
	}
}
