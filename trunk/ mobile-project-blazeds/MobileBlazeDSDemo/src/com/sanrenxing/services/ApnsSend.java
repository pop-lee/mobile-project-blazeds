package com.sanrenxing.services;

import java.util.ArrayList;
import java.util.List;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ApnsSend {
	    private Log    log    = LogFactory.getLog(ApnsSend.class);
	    public static void main(String[] args) throws Exception
	    {
	        String deviceToken = "c60b33344dc727bff137bbddb0216fcce9579c48de4873845d094f0e47134275";//iphone手机获取的token
	        String alert = "我的push测试";//push的内容
	        int badge = 100;//图标小红圈的数值
	        String sound = "default";//铃音
	        sendTiangouAPNS(deviceToken, alert, badge, sound, "10001");
	    }
	    public static void sendTiangouAPNS(String deviceToken, String message, int badge, String sound, String ab)
	    {
	        List<String> tokens = new ArrayList<String>();
	        tokens.add(deviceToken);
	        String certificatePath = "";
	        if (ab.equals("10001"))
	        {
	            certificatePath = "";//Constant.tiangouCommerceAPNS;//导出的证书
	        }
	        else if (ab.equals("10002"))
	        {
	            certificatePath = "";//Constant.tianGouCustomerAPNS;//导出的证书
	        }
	        String certificatePassword = "123456";//此处注意导出的证书密码不能为空因为空密码会报错
	        new ApnsSend().sendpush(tokens, message, badge, sound, certificatePath, certificatePassword, true);
	    }
	    /**
	     * apple的推送方法
	     * @param tokens iphone手机获取的token
	     * @param message 推送消息的内容
	     * @param count 应用图标上小红圈上的数值
	     * @param sound 声音
	     * @param ab 系统
	     * @param certificatePath 证书路径
	     * @param certificatePassword 证书密码
	     * @param sendCount 单发还是群发 true：单发 false：群发
	     */
	    private void sendpush(List<String> tokens, String message, int badge, String sound, String certificatePath, String certificatePassword, boolean sendCount)
	    {
	        try
	        {
	            PushNotificationPayload payLoad = new PushNotificationPayload();
	            payLoad.addAlert(message); // 消息内容
	            payLoad.addBadge(badge); // iphone应用图标上小红圈上的数值
	            if (!"".equals(sound))
	            {
	                payLoad.addSound(sound);//铃音
	            }
	            PushNotificationManager pushManager = new PushNotificationManager();
	            //true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
	            pushManager.initializeConnection(new AppleNotificationServerBasicImpl(certificatePath, certificatePassword, false));
	            List<PushedNotification> notifications = new ArrayList<PushedNotification>();
	            // 发送push消息
	            if (sendCount)
	            {
	                log.debug("--------------------------apple 推送 单-------");
	                Device device = new BasicDevice();
	                device.setToken(tokens.get(0));
	                PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
	                notifications.add(notification);
	            }
	            else
	            {
	                log.debug("--------------------------apple 推送 群-------");
	                List<Device> device = new ArrayList<Device>();
	                for (String token : tokens)
	                {
	                    device.add(new BasicDevice(token));
	                }
	                notifications = pushManager.sendNotifications(payLoad, device);
	            }
	            List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
	            List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
	            int failed = failedNotifications.size();
	            int successful = successfulNotifications.size();
	            System.out.println(failed + "    " + successful);
	            // pushManager.stopConnection();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
}
