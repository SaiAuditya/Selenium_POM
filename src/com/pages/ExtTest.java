package com.pages;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtTest {
	private static ExtentReports extent = ExtReport.getReport();
	public static ThreadLocal<ExtentTest>  extentTestThreadSafe = new ThreadLocal<ExtentTest>();
	public static synchronized ExtentTest getTest()
	{
		return extentTestThreadSafe.get();
	}
	public static void setTest(ExtentTest tst) {
		
		extentTestThreadSafe.set(tst);
		
	}
	
}



