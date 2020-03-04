package com.pages;

import org.openqa.selenium.WebDriver;
public class pagefactory {

	WebDriver driver;

	//THis is page factory class contains goto links for each page class.
	//THis is used to navigate between pages. All the pages extends the Pagefactory.
	//All page class objects have access to methods in this class as then extend this.
	//By that means all page class objects have visibility to "goto" methods in this class.
	
	public pagefactory(WebDriver driver) {
		this.driver = (driver);
		
	}

	public home goTo_Home() {
		return new home(driver);
	}

	public landing_page goTo_landing() {
		return new landing_page(driver);
	}
	
	public search_page goTo_searchPage()
	{
		return new search_page(driver);
	}
	
	public gmailpages goTo_gmailpage()
	{
		return new gmailpages(driver);
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
