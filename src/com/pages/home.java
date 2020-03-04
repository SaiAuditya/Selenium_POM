package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.start.start;
import com.utilities.reference;

public class home extends pagefactory {

	//public ExtentTest test;
	public reference ref = null;

	@FindBy(id = "interaction_table_header")
	public WebElement infoText;
	
	@FindBy(name="q")
	WebElement searchText;
	
	@FindBy(name="btnK")
	WebElement button_search;

	@FindBy(linkText = "Click here to continue")
	public WebElement continue_link;

	@FindBy(css = "#emailAddress")
	public WebElement email_text;

	@FindBy(css = ".LoginBtn")
	public WebElement logon;

	@FindBy(css = "#input_2")
	public WebElement password_element;
	

	public home(WebDriver driver) {
		super(driver);
		AjaxElementLocatorFactory agaxFactory = new AjaxElementLocatorFactory(
				driver, 120);
		PageFactory.initElements(agaxFactory, this);
		ref = new reference(driver);
		
	}
	
	public home set_text(String text)
	{
		ExtTest.getTest().log(LogStatus.PASS, "Wrting thread safe synchronized log");
		searchText.sendKeys(text);
		ExtTest.getTest().log(LogStatus.PASS, "Entered the search tex "+text);
		return this;
	}
	
	public home click_search()
	{
		button_search.submit();
		ExtTest.getTest().log(LogStatus.PASS, "Clicked on submit button");
		return this;
	}

	public home click_continue() throws InterruptedException {
		try {
			String info = infoText.getText();
			if (info.contains("The account information you've provided cannot be located in our records.")) {
				ExtTest.getTest().log(LogStatus.INFO,
						"The account information you've provided cannot be located in our records.");
				ExtTest.getTest().log(LogStatus.INFO, "Clicking here");

				continue_link.click();
				ExtTest.getTest().log(LogStatus.PASS, "Clicked on continue link");

			}
		} catch (Exception e) {

		}
		return this;
	}

	public home enter_username_and_click_logon(String username) {

		email_text.sendKeys(username);
		ExtTest.getTest().log(LogStatus.PASS, "Entered username " + username);
		logon.click();
		ExtTest.getTest().log(LogStatus.PASS, "Clicked button logon");
		String path = ref.getScreenshot();
		ExtTest.getTest().log(LogStatus.PASS,
				"adding screen capure " + ExtTest.getTest().addScreenCapture(path));
		return this;
	}

	public home enter_password_and_click_logon(String password) {

		password_element.sendKeys(password);
		ExtTest.getTest().log(LogStatus.PASS, "Entered password ");
		logon.click();
		ExtTest.getTest().log(LogStatus.PASS, "Clicked button logon");
		return this;
	}
	
	public home verify_page_title(String titleText)
	{
		if (driver.getTitle().contains(titleText))
			ExtTest.getTest().log(LogStatus.PASS, "site opened successfully"
					+ ExtTest.getTest().addScreenCapture(ref.getScreenshot()));
		else
			ExtTest.getTest().log(LogStatus.FAIL, "site is down or internet may causing the issue"
					+ ExtTest.getTest().addScreenCapture(ref.getScreenshot()));
			
	  	return this;
	}

	
	
		
}
