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

	@FindBy(partialLinkText = "LinkedIn: Log In or Sign Up")
	public WebElement myblogslink;
	
	public search_page(WebDriver driver) {
		super(driver);
		AjaxElementLocatorFactory agaxFactory = new AjaxElementLocatorFactory(
				driver, 120);
		PageFactory.initElements(agaxFactory, this);
		ref = new reference(driver);
		
	}

	/*
	 * Opens the site and returns the search page object for subsequent operations on the page.
	 */
	public search_page open_site(String siteLinkText)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.ignoring(NoSuchElementException.class);
		WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(siteLinkText)));
		try
		{
			link.click();
			ExtTest.getTest().log(LogStatus.PASS, "clicked on site successfully");
					
		}catch(Exception e)
		{
			ExtTest.getTest().log(LogStatus.FAIL, "Site link not found"
					+ ExtTest.getTest().addScreenCapture(ref.getScreenshot()));
		}
		
		return this;
	}
}