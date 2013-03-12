package com.sanrenxing.model;

import java.util.Date;
import java.util.Properties;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;

public class ModelData {

	private static ModelData uniqueInstance = null;
	
	private Date earlyActivityDate;
	
	private Scheduler pushScheduler;
	
	private JobDetail pushJobDetail;
	
	private SimpleTrigger pushSimpleTrigger;
	
	private Properties properties = new Properties();
	 
    private ModelData() {
       // Exists only to defeat instantiation.
    }
 
    public static ModelData getInstance() {
       if (uniqueInstance == null) {
           uniqueInstance = new ModelData();
       }
       return uniqueInstance;
    }

	public Date getEarlyActivityDate() {
		return earlyActivityDate;
	}

	public void setEarlyActivityDate(Date earlyActivityDate) {
		this.earlyActivityDate = earlyActivityDate;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Scheduler getPushScheduler() {
		return pushScheduler;
	}

	public void setPushScheduler(Scheduler pushScheduler) {
		this.pushScheduler = pushScheduler;
	}

	public JobDetail getPushJobDetail() {
		return pushJobDetail;
	}

	public void setPushJobDetail(JobDetail pushJobDetail) {
		this.pushJobDetail = pushJobDetail;
	}

	public SimpleTrigger getPushSimpleTrigger() {
		return pushSimpleTrigger;
	}

	public void setPushSimpleTrigger(SimpleTrigger pushSimpleTrigger) {
		this.pushSimpleTrigger = pushSimpleTrigger;
	}
	
}
