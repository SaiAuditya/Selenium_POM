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

public class landing_page extends pagefactory {

	//public ExtentTest test;
	reference ref = null;

	@FindBy(linkText = "MY PROFILE")
	public WebElement myprofile;

	@FindBy(linkText = "LOGOUT")
	public WebElement logout;

	public landing_page(WebDriver driver) {
		super(driver);
		AjaxElementLocatorFactory agaxFactory = new AjaxElementLocatorFactory(
				driver, 100);
		PageFactory.initElements(agaxFactory, this);
		//this.test=test;
		ref = new reference(driver);
	}

	public landing_page checkmyprofilelink() {
		if (myprofile.isDisplayed()) {
			ExtTest.getTest().log(
					LogStatus.PASS,
					"Myprofle link is displayed"
							+ ExtTest.getTest().addScreenCapture(ref.getScreenshot()));
		} else {
			ExtTest.getTest().log(LogStatus.FAIL, "Myprofle link is NOT displayed"
					+ ExtTest.getTest().addScreenCapture(ref.getScreenshot()));
		}
		return this;
	}

	public landing_page logout() {
		try {
			logout.click();
			ExtTest.getTest().log(
					LogStatus.PASS,
					"logout link is clicked"
							+ ExtTest.getTest().addScreenCapture(ref.getScreenshot()));
		} catch (Exception e) {
			ExtTest.getTest().log(
					LogStatus.FAIL,
					"logout link is NOT clicked"
							+ ExtTest.getTest().addScreenCapture(ref.getScreenshot()));
		}

		return this;
	}

}
