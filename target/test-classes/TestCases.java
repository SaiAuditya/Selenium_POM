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
	public void beforeTest() {
		System.out.println("Calling before test");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("Calling after test");
	}

	@BeforeGroups("Sanity")
	public void beforeGroups() {
		System.out.println("Calling before groups");
	}

	@AfterGroups("Sanity")
	public void afterGroups() {
		System.out.println("Calling after groups");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Calling before class");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Calling after class");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Calling before method");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Calling after method");
	}

	/*
	@Test(enabled = true, groups = { "Sanity" })
	public void Google_Search_Test() {
		try {

			// gets the method name
			String SheetName = Thread.currentThread().getStackTrace()[1].getMethodName();

			// sets the xldriver to point to sheet where variables are stored
			//start.xldriver.SetExcelSheet(SheetPath, SheetName);

			// starts this test method as extent test
			
            int rows=2;
			// iterates for number of rows presents in the xl.
			
			for (int i = 1; i <= rows; i++) {
				ExtentTest test = extent.startTest(SheetName+"_iteration_"+i);
				
			
				//getting the value from test data excel sheet
			
				
				//starting the test using the POM and returning the page objects creating the test flow
				st = new start();
				st.set_excelDriver(SheetPath, SheetName);
				rows=st.get_ExcelDriver().get_used_rows();
				st.set_Logger(test);
				String textToSearch =st.get_ExcelDriver().getExcelData("TextToSearch", i);
			
				st
				.launch_browser()
				.goTo_Home()
				.set_text(textToSearch)
				.click_search()
				.goTo_searchPage()
				.open_site(textToSearch).goTo_Home()
				.verify_page_title(textToSearch).tearDown();
				extent.flush();
			}

		} catch (Exception e) {
			ExtTest.getTest().log(LogStatus.FAIL, "unexpected error " + e.getStackTrace().toString());
			e.printStackTrace();
			
		} finally {
			extent.flush();
		}
	}
	
	@Test(enabled = true, groups = { "Sanity" })
	public void Google_Search_Test_2() {
		try {

			// gets the method name
			String SheetName = Thread.currentThread().getStackTrace()[1].getMethodName();

			// sets the xldriver to point to sheet where variables are stored
			
			// starts this test method as extent test
			//ExtentTest test = extent.startTest(SheetName);

			// iterates for number of rows presents in the xl.
			int rows = 2;
			for (int i = 1; i <= rows; i++) {
				
				ExtentTest test = extent.startTest(SheetName+"_iteration_"+i);
				//getting the value from test data excel sheet
				
				
				//starting the test using the POM and returning the page objects creating the test flow
				st = new start();
				st.set_excelDriver(SheetPath, SheetName);
				rows=st.get_ExcelDriver().get_used_rows();
				String textToSearch = st.get_ExcelDriver().getExcelData("TextToSearch", i);
				st.set_Logger(test);
				//calls the test methods
				st
				.launch_browser()
				.goTo_Home()
				.set_text(textToSearch)
				.click_search()
				.goTo_searchPage()
				.open_site(textToSearch).goTo_Home()
				.verify_page_title(textToSearch).tearDown();
				extent.flush();
			}

		} catch (Exception e) {
			ExtTest.getTest().log(LogStatus.FAIL, "unexpected error " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			extent.flush();
		}
	}

	@AfterClass
	public void flush_test() {
		extent.flush();
	}*/
	
	/*@Test(enabled = true, groups = { "Sanity" })
	public void Gmail_Test_1() {
		try {

			// gets the method name
			String SheetName = Thread.currentThread().getStackTrace()[1].getMethodName();

			// sets the xldriver to point to sheet where variables are stored
			//start.xldriver.SetExcelSheet(SheetPath, SheetName);

			// starts this test method as extent test
			
            int rows=2;
			// iterates for number of rows presents in the xl.
			
			for (int i = 1; i <= rows; i++) {
				ExtentTest test = extent.startTest(SheetName+"_iteration_"+i);
				
			
				//getting the value from test data excel sheet
			
				
				//starting the test using the POM and returning the page objects creating the test flow
				st = new start();
				st.set_excelDriver(SheetPath, SheetName);
				rows=st.get_ExcelDriver().get_used_rows();
				st.set_Logger(test);
				String username =st.get_ExcelDriver().getExcelData("Username", i);
				String pass = st.get_ExcelDriver().getExcelData("password", i);
			
				st
				.launch_browser()
				.goTo_gmailpage().set_text_username(username).user_next_click().set_text_password(pass).pass_next_click().click_on_compose().enter_to_and_subject(username, "Test Email");
			
				extent.flush();
			}

		} catch (Exception e) {
			ExtTest.getTest().log(LogStatus.FAIL, "unexpected error " + e.getStackTrace().toString());
			e.printStackTrace();
			
		} finally {
			extent.flush();
		}
	}
	
	/*
	@Test(enabled = true, groups = { "Sanity" })
	public void Gmail_Test_2() {
		try {

			// gets the method name
			String SheetName = Thread.currentThread().getStackTrace()[1].getMethodName();

			// sets the xldriver to point to sheet where variables are stored
			//start.xldriver.SetExcelSheet(SheetPath, SheetName);

			// starts this test method as extent test
			
            int rows=2;
			// iterates for number of rows presents in the xl.
			
			for (int i = 1; i <= rows; i++) {
				ExtentTest test = extent.startTest(SheetName+"_iteration_"+i);
				
			
				//getting the value from test data excel sheet
			
				
				//starting the test using the POM and returning the page objects creating the test flow
				st = new start();
				st.set_excelDriver(SheetPath, SheetName);
				rows=st.get_ExcelDriver().get_used_rows();
				st.set_Logger(test);
				String username =st.get_ExcelDriver().getExcelData("Username", i);
				String pass = st.get_ExcelDriver().getExcelData("password", i);
			
				st
				.launch_browser()
				.goTo_gmailpage().set_text_username(username).user_next_click().set_text_password(pass).pass_next_click().click_on_compose().enter_to_and_subject(username, "Test Email");
			
				extent.flush();
			}

		} catch (Exception e) {
			ExtTest.getTest().log(LogStatus.FAIL, "unexpected error " + e.getStackTrace().toString());
			e.printStackTrace();
			
		} finally {
			extent.flush();
		}
	}*/
	
	@Test
	public void test1()
	{
		System.out.println("test1");
	}
	
	@Test
	public void test2()
	{
		System.out.println("test2");
	}
	
}
