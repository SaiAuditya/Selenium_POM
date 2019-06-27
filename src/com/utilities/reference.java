package com.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.pages.pagefactory;
import com.relevantcodes.extentreports.ExtentTest;

public class reference extends pagefactory {

	public WebDriver driver;
	public ExtentTest logger;
	WebDriverWait wait;

	public reference(WebDriver driver) {
		super(driver);
		AjaxElementLocatorFactory agaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(agaxFactory, this);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);

	}

	/*
	 * This function takes the screenshot and stores in specified location and returns the 
	 * path of the screen shot
	 */

	public String getScreenshot() {

		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy_dd_MMM_HH_mm_ss");
			Date date = new Date();
			String getCurrentDate = dateFormat.format(date);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path = "./screenshots" + "/" + "screen_" + getCurrentDate + ".png";
			//System.out.println("Path : " + path);
			File dest = new File(path);
			FileUtils.copyFile(scrFile, dest);
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			return dest.getAbsolutePath();

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
