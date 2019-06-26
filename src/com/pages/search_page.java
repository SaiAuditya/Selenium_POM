package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;
import com.utilities.reference;

public class search_page extends pagefactory {

	reference ref = null;

	@FindBy(partialLinkText = "consolidatedchaos")
	public WebElement myblogslink;
	
	public search_page(WebDriver driver) {
		super(driver);
		AjaxElementLocatorFactory agaxFactory = new AjaxElementLocatorFactory(
				driver, 120);
		PageFactory.initElements(agaxFactory, this);
		ref = new reference(driver);
		
	}

	public search_page opensite()
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("consolidatedchaos")));
		myblogslink.click();
		ExtTest.getTest().log(LogStatus.PASS, "Site opened successfully"
				+ ExtTest.getTest().addScreenCapture(ref.getScreenshot()));
		return this;
	}
}