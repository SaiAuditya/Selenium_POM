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

	reference ref = null;

	@FindBy(name = "btnK")
	public WebElement googleSearch;

	@FindBy(name = "btnI")
	public WebElement googleFeelingLucky;

	public landing_page(WebDriver driver) {
		super(driver);
		AjaxElementLocatorFactory agaxFactory = new AjaxElementLocatorFactory(
				driver, 100);
		PageFactory.initElements(agaxFactory, this);
		ref = new reference(driver);
	}


}
