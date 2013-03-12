package com.sanrenxing.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sanrenxing.model.ModelData;
import com.sanrenxing.vos.ActivityVO;
import com.sanrenxing.vos.BackyardProduct;
import com.sanrenxing.vos.UserAttention;

import javapns.Push;
import javapns.notification.AppleNotificationServer;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PayloadPerDevice;
import javapns.notification.PushNotificationPayload;
import javapns.notification.transmission.NotificationProgressListener;
import javapns.notification.transmission.NotificationThread;
import javapns.notification.transmission.NotificationThreads;
import javapns.notification.transmission.PushQueue;

@Service
public class PushNotificationUtil {
	
	public void pushNotification1() {
		String keystore = "E:/downloads/devTools/IOS/aps_development_20130120.p12";//"D:/XXXXXXXX/XXX.p12";//证书路径和证书名
		String password = "2013";//"XXXXXXXX"; // 证书密码
		String token = "F380883BE1563E6A5A8ED27CEC2623E75F4C1113010206DDFF1BF2EB5D172CAE";//"XXXXXX XXXXXX XXXXXX XXXXXX XXXXXX XXXXXX XXXXXX XXXXXX";// 手机唯一标识
		boolean production = false; // 设置true为正式服务地址，false为开发者地址
		int threadThreads = 10; // 线程数
		try {
			// 建立与Apple服务器连接
			AppleNotificationServer server = new AppleNotificationServerBasicImpl(keystore, password, production);
			List<PayloadPerDevice> list = new ArrayList<PayloadPerDevice>();
			PushNotificationPayload payload = new PushNotificationPayload();
			payload.addAlert("推送内容");
			payload.addSound("default");// 声音
//			payload.addBadge(1);//图标小红圈的数值
			payload.addCustomDictionary("url","www.baidu.com");// 添加字典 
			PayloadPerDevice pay = new PayloadPerDevice(payload,token);// 将要推送的消息和手机唯一标识绑定
			list.add(pay);

			NotificationThreads work = new NotificationThreads(server,list,threadThreads);// 
			work.setListener(DEBUGGING_PROGRESS_LISTENER);// 对线程的监听，一定要加上这个监听
			work.start(); // 启动线程
			work.waitForAllThreads();// 等待所有线程启动完成

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pushNotification() {
		String keystore = "E:/downloads/devTools/IOS/aps_development_20130120.p12";//"D:/XXXXXXXX/XXX.p12";//证书路径和证书名
		String password = "2013";//"XXXXXXXX"; // 证书密码
		String token = "F380883BE1563E6A5A8ED27CEC2623E75F4C1113010206DDFF1BF2EB5D172CAE";//"XXXXXX XXXXXX XXXXXX XXXXXX XXXXXX XXXXXX XXXXXX XXXXXX";// 手机唯一标识
		boolean production = false; // 设置true为正式服务地址，false为开发者地址
		int threadThreads = 10; // 线程数
		try {
			// 建立与Apple服务器连接
			AppleNotificationServer server = new AppleNotificationServerBasicImpl(keystore, password, production);
			List<PayloadPerDevice> list = new ArrayList<PayloadPerDevice>();
			PushNotificationPayload payload = new PushNotificationPayload();
			payload.addAlert("推送内容");
			payload.addSound("default");// 声音
//			payload.addBadge(1);//图标小红圈的数值
			payload.addCustomDictionary("url","www.baidu.com");// 添加字典 
			PayloadPerDevice pay = new PayloadPerDevice(payload,token);// 将要推送的消息和手机唯一标识绑定
			list.add(pay);

			NotificationThreads work = new NotificationThreads(server,list,threadThreads);// 
			work.setListener(DEBUGGING_PROGRESS_LISTENER);// 对线程的监听，一定要加上这个监听
			work.start(); // 启动线程
			work.waitForAllThreads();// 等待所有线程启动完成

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pushActivity(ActivityVO activity) {
		
	}
	
	public void pushProduct(BackyardProduct product,List<UserAttention> userList) {
		ModelData model = ModelData.getInstance();
		String keystore = model.getProperties().getProperty("pns.key");//"E:/downloads/devTools/IOS/aps_development_20130120.p12";//"D:/XXXXXXXX/XXX.p12";//证书路径和证书名
		String password = model.getProperties().getProperty("pns.pass");//"2013";//"XXXXXXXX"; // 证书密码
		boolean production = Integer.parseInt(model.getProperties().getProperty("pns.production"))==0?false:true; // 设置true为正式服务地址，false为开发者地址
		try {
//			AppleNotificationServer server = new AppleNotificationServerBasicImpl(keystore, password, production);
//			List<PayloadPerDevice> list = new ArrayList<PayloadPerDevice>();
			
			int length = userList.size();
			
			PushNotificationPayload payload = new PushNotificationPayload();
			payload.addAlert("推送内容");
			payload.addSound("default");// 声音
			//			payload.addBadge(1);//图标小红圈的数值
			payload.addCustomDictionary("url","www.baidu.com");// 添加字典 
			
//			List<String> devices = new ArrayList<String>();
			PushQueue pq = Push.queue(keystore, password, production, length);
			for(int i=0;i<length;i++) {
//				devices.add(userList.get(i).getUserDeviceId());
				pq.add(payload, userList.get(i).getUserDeviceId());
			}
			pq.start();
//			Push.payload(payload,keystore,password,production,length,devices);
//			int threadThreads = length; // 线程数
//			NotificationThreads work = new NotificationThreads(server,list,threadThreads);// 
//			work.setListener(DEBUGGING_PROGRESS_LISTENER);// 对线程的监听，一定要加上这个监听
//			work.start(); // 启动线程
//			work.waitForAllThreads();// 等待所有线程启动完成
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 线程监听
	public static final NotificationProgressListener DEBUGGING_PROGRESS_LISTENER = new NotificationProgressListener() {
			public void eventThreadStarted(NotificationThread notificationThread) {
				System.out.println("   [EVENT]: thread #" + notificationThread.getThreadNumber() + " started with " + " devices beginning at message id #" + notificationThread.getFirstMessageIdentifier());
			}
			public void eventThreadFinished(NotificationThread thread) {
				System.out.println("   [EVENT]: thread #" + thread.getThreadNumber() + " finished: pushed messages #" + thread.getFirstMessageIdentifier() + " to " + thread.getLastMessageIdentifier() + " toward "+ " devices");
			}
			public void eventConnectionRestarted(NotificationThread thread) {
				System.out.println("   [EVENT]: connection restarted in thread #" + thread.getThreadNumber() + " because it reached " + thread.getMaxNotificationsPerConnection() + " notifications per connection");
			}
			public void eventAllThreadsStarted(NotificationThreads notificationThreads) {
				System.out.println("   [EVENT]: all threads started: " + notificationThreads.getThreads().size());
			}
			public void eventAllThreadsFinished(NotificationThreads notificationThreads) {
				System.out.println("   [EVENT]: all threads finished: " + notificationThreads.getThreads().size());
			}
			public void eventCriticalException(NotificationThread notificationThread, Exception exception) {
				System.out.println("   [EVENT]: critical exception occurred: " + exception);
			}
		 };
}