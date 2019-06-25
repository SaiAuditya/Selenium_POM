package com.pages;

import java.io.File;
import java.util.Calendar;
import java.util.TimeZone;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtReport {

	public static Calendar cal = Calendar.getInstance(TimeZone
			.getTimeZone("GMT"));
	public static long time = cal.getTimeInMillis();
    
    public synchronized static ExtentReports getReport() {
    	   		
    	ExtentReports Report = ( new ExtentReports(System.getProperty("user.dir")
    				+ "/test-output/ExtentReport_" + time + ".html", true));
    	Report.addSystemInfo("Host Name", "TestMachine")
    				.addSystemInfo("Environment", "Automation Testing")
    				.addSystemInfo("User Name", "eppTester");

    	Report.loadConfig(new File(System.getProperty("user.dir")
    				+ "\\extent-config.xml"));	
    		 return Report;  		 

    	}
         
    	
    }
	
