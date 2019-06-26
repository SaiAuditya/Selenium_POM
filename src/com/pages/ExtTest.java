package com.pages;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtTest {
	/*
	 * This class is to create thread safe test for parallel execution.
	 */
	public static ThreadLocal<ExtentTest>  extentTestThreadSafe = new ThreadLocal<ExtentTest>();

	//getting the test
	public static synchronized ExtentTest getTest()
	{
		return extentTestThreadSafe.get();
	}
	
	//setting the test
	public static void setTest(ExtentTest tst) {
		extentTestThreadSafe.set(tst);	
	}
	
}



