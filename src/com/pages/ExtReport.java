package com.pages;

import java.io.File;
import java.util.Calendar;
import java.util.TimeZone;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtReport {

	public static Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
	public static long time = cal.getTimeInMillis();

	// Fetches the extent report.
	public synchronized static ExtentReports getReport() {

		ExtentReports Report = (new ExtentReports(
				System.getProperty("user.dir") + "/test-output/ExtentReport_" + time + ".html", true));
		Report.addSystemInfo("Host Name", "TestMachine").addSystemInfo("Environment", "Automation Testing")
				.addSystemInfo("User Name", "eppTester");

		//Loading the extent configuration file
		Report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		return Report;

	}
}
