package com.sanrenxing.utils;

public class StringUtil {

	public static String gen(int length) {
		 char[] ss = new char[length];
		 int i=0;
		while(i<length) {
		    int f = (int) (Math.random()*3);
		    if(f==0)  
		     ss[i] = (char) ('A'+Math.random()*26);
		    else if(f==1)  
		     ss[i] = (char) ('a'+Math.random()*26);
		    else 
		     ss[i] = (char) ('0'+Math.random()*10);    
		    i++;
		 }
		String is=new String(ss);
		 return is;
		}
}
