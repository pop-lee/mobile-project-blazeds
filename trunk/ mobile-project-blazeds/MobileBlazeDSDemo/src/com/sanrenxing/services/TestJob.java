package com.sanrenxing.services;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class TestJob implements Job {

	@Autowired
	private PushNotificationService pushNotificationService;
	
	public PushNotificationService getPushNotificationService() {
		return pushNotificationService;
	}



	public void setPushNotificationService(
			PushNotificationService pushNotificationService) {
		this.pushNotificationService = pushNotificationService;
	}



	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("a");
	}

}
