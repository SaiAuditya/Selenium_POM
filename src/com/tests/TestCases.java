package com.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.pages.ExtReport;
import com.pages.ExtTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.start.start;

public class TestCases {
	public start st = null;
	public static String SheetPath = "./data/TestData.xlsx";
	public ExtentReports extent = ExtReport.getReport();

	@BeforeSuite
	public void beforesuite() {

		System.out.println("Calling before suite");
	}

	@AfterSuite
	public void aftersuite() {
	
		System.out.println("Calling after suite");
	}
	
	@BeforeTest
	public void beforeTest()
	{
		System.out.println("Calling before test");
	}
	
	@AfterTest
	public void afterTest()
	{
		System.out.println("Calling after test");
	}
	@BeforeGroups("Sanity")
	public void beforeGroups(){
		System.out.println("Calling before groups");
	}
	
	@AfterGroups("Sanity")
	public void afterGroups()
	{
		System.out.println("Calling after groups");
	}
	@BeforeClass
	public void beforeClass()
	{
		System.out.println("Calling before class");
	}
	@AfterClass
	public void afterClass()
	{
		System.out.println("Calling after class");
	}
	@BeforeMethod
	public void beforeMethod()
	{
		System.out.println("Calling before method");
	}
	@AfterMethod
	public void afterMethod()
	{
		System.out.println("Calling after method");
	}

	
	@Test(enabled = false, groups = {"Sanity"})
	public void login_Test_Case_01() {
		try {
			String SheetName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println(SheetName);
			start.xldriver.SetExcelSheet(SheetPath, SheetName);
			ExtentTest test = extent.startTest(SheetName);
			int rows = start.xldriver.get_used_rows();
			for (int i = 1; i <= rows; i++) {

				st = new start();
				st.set_Logger(test);
				String username = start.xldriver.getExcelData("username", i);
				System.out.println("username " + username);

				st.launch_browser().goTo_Home().set_text("Parallel testing").click_search();
			}

		} catch (Exception e) {
			ExtTest.getTest().log(LogStatus.FAIL, "unexpected error " + e.getStackTrace().toString());
			e.printStackTrace();

		} finally {
			extent.flush();
		}
	}

	@Test(enabled = false)
	public void login_Test_Case_02() {
		try {
			String SheetName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("Test2");
			start.xldriver.SetExcelSheet(SheetPath, "login_Test_Case_01");
			// start.set_Logger(SheetName);
			ExtentTest test = extent.startTest(SheetName);

			int rows = start.xldriver.get_used_rows();

			for (int i = 1; i <= rows; i++) {

				st = new start();
				st.set_Logger(test);
				String username = start.xldriver.getExcelData("username", i);
				String password = start.xldriver.getExcelData("password", i);
				System.out.println("Password " + password);
				System.out.println("Password " + username);
				st.launch_browser().goTo_Home().set_text("thread safe programme").click_search();

			}

		} catch (Exception e) {
			ExtTest.getTest().log(LogStatus.FAIL, "unexpected error " + e.getStackTrace().toString());
			e.printStackTrace();

		} finally {
			extent.flush();
		}
	}

	@Test(enabled = true, groups = {"Sanity"})
	public void login_Test_Case_03() {
		try {
			String SheetName = Thread.currentThread().getStackTrace()[1].getMethodName();

			start.xldriver.SetExcelSheet(SheetPath, "login_Test_Case_01");
			
			ExtentTest test = extent.startTest(SheetName);
			int rows = start.xldriver.get_used_rows();

			for (int i = 1; i <= rows; i++) {

				st = new start();
				st.set_Logger(test);
				
				String username = start.xldriver.getExcelData("username", i);
				String password = start.xldriver.getExcelData("password", i);
				
				st.launch_browser().goTo_Home().set_text("ConsolidatedChaos").click_search().goTo_searchPage()
						.opensite();

			}

		} catch (Exception e) {
			ExtTest.getTest().log(LogStatus.FAIL, "unexpected error " + e.getStackTrace().toString());
			e.printStackTrace();
		} finally {
			extent.flush();
		}
	}

	@AfterClass
	public void flush_test() {
		extent.flush();
	}
}
