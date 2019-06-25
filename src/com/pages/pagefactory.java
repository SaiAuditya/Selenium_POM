package com.pages;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

public class pagefactory {

	//protected ThreadLocal<WebDriver> wbdriver = new ThreadLocal<WebDriver>();
	WebDriver driver;

	public pagefactory(WebDriver driver) {
		this.driver = (driver);
		
	}

	public home goTo_Home() {
		return new home(driver);
	}

	public landing_page goTo_landing() {
		return new landing_page(driver);
	}
	
	public void tearDown() {

		try {
			driver.close();
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
