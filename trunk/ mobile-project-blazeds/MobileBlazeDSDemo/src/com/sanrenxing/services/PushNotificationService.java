package com.sanrenxing.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import com.sanrenxing.model.ModelData;
import com.sanrenxing.vos.BackyardProduct;
import com.sanrenxing.vos.BackyardProductDetail;
import com.sanrenxing.vos.UserAttention;

public class PushNotificationService extends BaseService {
	
	private ModelData _model = ModelData.getInstance();
	
	private JobDetail pushNotificationJobDetail;
	
	
	public JobDetail getPushNotificationJobDetail() {
		return pushNotificationJobDetail;
	}

	public void setPushNotificationJobDetail(JobDetail pushNotificationJobDetail) {
		this.pushNotificationJobDetail = pushNotificationJobDetail;
	}

	public void queryEarlyToPush() {
		try {
			if(_model.getPushScheduler()!=null) return; //如果当前有在计划内的任务，则跳过此方法
			
			ModelData _model = ModelData.getInstance();
			
			_model.setEarlyActivityDate(
					this.getBackyardProductDetailDao().selectEarlyActivity());
			
			if(_model.getEarlyActivityDate()==null) return;
			
			long curTime = System.currentTimeMillis();
			long earlyTime = _model.getEarlyActivityDate().getTime();
			
			if(earlyTime<curTime+60000
				&& earlyTime>curTime-300000
					) {
				pushProductByDate(_model.getEarlyActivityDate());
			} else if(earlyTime>curTime+60000) {
				//将在未来一段时间内执行
				pushNotificationJobDetail.setName("PushNotificationJobDetail"+new Date(earlyTime));
				
				SchedulerFactory schedulerFactory = new StdSchedulerFactory();  
				Scheduler pushNotificationScheduler = schedulerFactory.getScheduler();
			
				// 实例化SimpleTrigger  
				SimpleTrigger pushNotificationTrigger = new SimpleTrigger();
				// 这些值的设置也可以从外面传入，这里采用默放值  
				pushNotificationTrigger.setJobName(pushNotificationJobDetail.getName());  
				pushNotificationTrigger.setJobGroup(Scheduler.DEFAULT_GROUP);
				pushNotificationTrigger.setStartTime(new Date(earlyTime));
				// 设置名称  
				pushNotificationTrigger.setName("PushNotificationTriger"+new Date(earlyTime));
			
				pushNotificationScheduler.scheduleJob(pushNotificationJobDetail, pushNotificationTrigger);
				
				_model.setPushScheduler(pushNotificationScheduler);
				_model.setPushJobDetail(pushNotificationJobDetail);
				_model.setPushSimpleTrigger(pushNotificationTrigger);
				
//				pushNotificationScheduler.start();
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void trigerToPush() {
		Date currentTrigerDate = _model.getPushSimpleTrigger().getStartTime();
		
		_model.setPushScheduler(null);
		pushProductByDate(currentTrigerDate);
	}
	
	public void addProductPush(BackyardProduct product) {
		
		long pushStartTime = product.getProductDetail().get(0).getActivityStartDate().getTime();
		long pushEndTime = product.getProductDetail().get(0).getActivityEndDate().getTime();
		long curTime = System.currentTimeMillis();
		try {
			if(pushStartTime<curTime+60000
					&& pushEndTime>curTime
						) {
				pushProductByDate(new Date(pushStartTime));
			} else if(pushStartTime>curTime+60000) {
				if(_model.getEarlyActivityDate()==null||pushStartTime<_model.getEarlyActivityDate().getTime()) { //如果新增加的商品推送时间小于当前保存的最早时间，则执行按新的最早时间推送
					_model.setEarlyActivityDate(new Date(pushStartTime));
					long earlyTime = _model.getEarlyActivityDate().getTime();
					
					if(_model.getPushScheduler()!=null) {
						_model.getPushScheduler().deleteJob(_model.getPushSimpleTrigger().getJobName(), _model.getPushSimpleTrigger().getGroup());
					}
					
					pushNotificationJobDetail.setName("PushNotificationJobDetail"+new Date(earlyTime));
					
					SchedulerFactory schedulerFactory = new StdSchedulerFactory();  
					Scheduler pushNotificationScheduler = schedulerFactory.getScheduler();
				
					// 实例化SimpleTrigger  
					SimpleTrigger pushNotificationTrigger = new SimpleTrigger();
					// 这些值的设置也可以从外面传入，这里采用默放值  
					pushNotificationTrigger.setJobName(pushNotificationJobDetail.getName());  
					pushNotificationTrigger.setJobGroup(Scheduler.DEFAULT_GROUP);
					pushNotificationTrigger.setStartTime(new Date(earlyTime));
					// 设置名称  
					pushNotificationTrigger.setName("PushNotificationTriger"+new Date(earlyTime));
				
					pushNotificationScheduler.scheduleJob(pushNotificationJobDetail, pushNotificationTrigger);
					
					_model.setPushScheduler(pushNotificationScheduler);
					_model.setPushJobDetail(pushNotificationJobDetail);
					_model.setPushSimpleTrigger(pushNotificationTrigger);
				}
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void pushProductByDate(Date pushDate) {
		//查询在指定时间范围内，立即执行
		List<BackyardProductDetail> list = 
				this.getBackyardProductDetailDao().selectProductDetailByDate(pushDate);
		
		int productLength = list.size();
		for(int i=0;i<productLength;i++) {
			PushNotificationUtil service = new PushNotificationUtil();
			BackyardProductDetail productDetail = list.get(i);
			BackyardProduct product = this.getBackyardProductDao().selectProductById(productDetail.getProductId()).get(0);
			List<BackyardProductDetail> pl = new ArrayList<BackyardProductDetail>();
			pl.add(list.get(i));
			List<UserAttention> userList = this.getUserAttentionDao().selectListUserAttentionByProductId(productDetail.getProductId());
			int length = userList.size();
			for(int j=0;j<length;j++) {
				this.getUserAttentionDao().updateUnreadStatusByAttentionId(userList.get(j).getAttentionId());
				userList.get(j).setAttentionStatusUnreadCount(
						this.getUserAttentionDao().selectUnreadStatusCountByUserDeviceId(
								userList.get(j).getUserDeviceId()));
			}
			product.setProductDetail(pl);
			
			service.pushProduct(product, userList);
			
			this.getBackyardProductDetailDao().updateProductDetailPushStatus(productDetail.getPushProductDetailId());
		}
		queryEarlyToPush();
		
		System.out.println(list);
	}
}
