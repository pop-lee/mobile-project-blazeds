package com.sanrenxing.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.JobDetail;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.springframework.scheduling.quartz.SimpleTriggerBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.sanrenxing.model.ModelData;
import com.sanrenxing.vos.BackyardProduct;
import com.sanrenxing.vos.BackyardProductDetail;
import com.sanrenxing.vos.UserAttention;

public class InitService extends BaseService {

//	@Override
//	public void execute(JobExecutionContext arg0) throws JobExecutionException {
//		// TODO Auto-generated method stub
//		System.out.println(arg0);
//		initProperties();
//		initPushTimeListener();
//	}
	
	public void execute() throws JobExecutionException {
		// TODO Auto-generated method stub
//		initProperties();
//		initPushTimeListener();
	}

	private void initPushTimeListener() {
		try {
			ModelData earlyActivityDate = ModelData.getInstance();
			
			earlyActivityDate.setEarlyActivityDate(
					this.getBackyardProductDetailDao().selectEarlyActivity());
			
			long curTime = System.currentTimeMillis();
			long selTime = earlyActivityDate.getEarlyActivityDate().getTime();
			
			if(selTime<curTime+60000
				&& selTime>curTime-300000
					) {
				//查询在指定时间范围内，立即执行
				List<BackyardProductDetail> list = 
						this.getBackyardProductDetailDao().selectProductDetailByDate(earlyActivityDate.getEarlyActivityDate());
				
				
				int productLength = list.size();
				for(int i=0;i<productLength;i++) {
					PushNotificationUtil service = new PushNotificationUtil();
					BackyardProductDetail productDetail = list.get(i);
					BackyardProduct product = this.getBackyardProductDao().selectProductById(productDetail.getProductId()).get(0);
					List<BackyardProductDetail> pl = new ArrayList<BackyardProductDetail>();
					pl.add(list.get(i));
					List<UserAttention> userList = this.getUserAttentionDao().selectListUserAttentionByProductId(productDetail.getProductId());
					product.setProductDetail(pl);
					
					service.pushProduct(product, userList);
				}
				
				System.out.println(list);
			} else if(selTime>curTime+60000) {
				//将在未来一段时间内执行
				
				
//				JobDetail jobDetail1 = new JobDetail(PUSH_JOB_BY_TIME, PUSH_JOB_GROUP,  
//						SimpleJob.class);
//				SimpleTrigger st = new SimpleTrigger("a", "a",new Date(System.currentTimeMillis()+10000));
//				
//				SchedulerFactory schedulerFactory = new StdSchedulerFactory();  
//				
//				Scheduler scheduler = schedulerFactory.getScheduler();  
//				
//				scheduler.scheduleJob(jobDetail1, st);
//				scheduler.start();
////				scheduler.triggerJob("job1_1", "jGroup1");
				
			}
		} catch(Exception e) {
			System.out.println(e);
		}
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
}