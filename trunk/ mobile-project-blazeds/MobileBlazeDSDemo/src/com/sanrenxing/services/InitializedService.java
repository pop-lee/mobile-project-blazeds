package com.sanrenxing.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.sanrenxing.model.ModelData;

public class InitializedService implements InitializingBean {

	private JobDetail pushNotificationJobDetail;
	
	public JobDetail getPushNotificationJobDetail() {
		return pushNotificationJobDetail;
	}

	public void setPushNotificationJobDetail(JobDetail pushNotificationJobDetail) {
		this.pushNotificationJobDetail = pushNotificationJobDetail;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		initProperties();
		initPushNotificationService();
	}
	
	private void initProperties() {
		ModelData model = ModelData.getInstance();
		
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(
					"mainConfig.properties");
			model.getProperties().load(inputStream);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initPushNotificationService() {
		try {
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();  
			Scheduler pushNotificationScheduler = schedulerFactory.getScheduler();
		
			// 实例化SimpleTrigger  
			SimpleTrigger initPushNotificationTrigger = new SimpleTrigger();  
			// 这些值的设置也可以从外面传入，这里采用默放值  
//			initPushNotificationTrigger.setJobName("initPushNotificationJobDetail");
//			initPushNotificationTrigger.setJobGroup(Scheduler.DEFAULT_GROUP);
			initPushNotificationTrigger.setStartTime(new Date(System.currentTimeMillis()+6000));
			// 设置名称  
			initPushNotificationTrigger.setName("InitPushTriger");
		
			pushNotificationScheduler.scheduleJob(pushNotificationJobDetail, initPushNotificationTrigger);
			pushNotificationScheduler.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

}
