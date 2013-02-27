package com.sanrenxing.model;

import java.util.Date;
import java.util.Properties;

public class ModelData {

	private static ModelData uniqueInstance = null;
	
	private Date earlyActivityDate;
	
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
    
}
