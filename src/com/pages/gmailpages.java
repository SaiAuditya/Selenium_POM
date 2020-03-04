package com.pages;
//import org.testng.ITestResult;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.start.start;
import com.utilities.reference;

public class gmailpages extends pagefactory {

	reference ref = null;

	@FindBy(xpath = "//*[@id='identifierId']")
	public WebElement emailName;

	@FindBy(xpath = "//*[@id='identifierNext']")
	public WebElement usernext;

	@FindBy(xpath = "//*[@id='passwordNext']")
	public WebElement passnext;

	public gmailpages(WebDriver driver) {
		super(driver);
		AjaxElementLocatorFactory agaxFactory = new AjaxElementLocatorFactory(driver, 100);
		PageFactory.initElements(agaxFactory, this);
		ref = new reference(driver);
	}

	public gmailpages set_text_username(String text) {
		ExtTest.getTest().log(LogStatus.PASS, "Wrting thread safe synchronized log");
		emailName.sendKeys(text);
		ExtTest.getTest().log(LogStatus.PASS, "Entered the search tex " + text);
		return this;
	}

	public gmailpages set_text_password(String text) {
		ExtTest.getTest().log(LogStatus.PASS, "Wrting thread safe synchronized log");

		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		// element.click();
		element.sendKeys(text);
		ExtTest.getTest().log(LogStatus.PASS, "Entered the search tex " + text);
		return this;
	}

	public gmailpages user_next_click() throws InterruptedException {
		Thread.sleep(1000);
		ExtTest.getTest().log(LogStatus.PASS, "Clicked on Next");
		usernext.click();
		return this;
	}

	public gmailpages pass_next_click() throws InterruptedException {
		Thread.sleep(1000);
		ExtTest.getTest().log(LogStatus.PASS, "Clicked on Next");
		passnext.click();
		return this;
	}

	public gmailpages click_on_compose() {
		ExtTest.getTest().log(LogStatus.PASS, "Wrting thread safe synchronized log");

		WebDriverWait wait = new WebDriverWait(driver, 70);
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Compose')]")));
		element.click();
		ExtTest.getTest().log(LogStatus.PASS, "Clicked on compose");
		return this;
	}

	//
	// *[@id=":9n"]

	public gmailpages enter_to_and_subject(String receipent, String subject) throws InterruptedException {
		ExtTest.getTest().log(LogStatus.PASS, "Wrting thread safe synchronized log");

		WebDriverWait wait = new WebDriverWait(driver, 50);
		WebElement elementTo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("to")));
		WebElement elementSubject = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("subjectbox")));
		elementTo.click();
		elementTo.sendKeys(receipent, Keys.TAB);
		elementSubject.click();
		elementSubject.sendKeys(subject, Keys.TAB);
		ExtTest.getTest().log(LogStatus.PASS, "entered the reciepients");
		driver.findElement(By.cssSelector(".T-I.J-J5-Ji.aoO")).click();
		Thread.sleep(8000);

		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=':2x']/tbody/tr"));
		int sz = rows.size();
		for (int i = 1; i < sz; i++) {
			String text = driver.findElement(By.xpath("//*[@id=':2x']/tbody/tr[" + i + "]/td[6]/div/div/div/span/span"))
					.getText();
			System.out.println(text);
			if (text.contains("Test Email")) {
				//driver.findElement(By.xpath("//*[@id=':2x']/tbody/tr[" + i + "]/td[2]")).click();
				driver.findElement(By.xpath("//*[@id=':2x']/tbody/tr[" + i + "]/td[6]/div/div/div/span/span")).click();
				
				break;
			}
		}
        Thread.sleep(2000);
		Actions build = new Actions(driver);
		build.moveToElement(driver.findElement(By.cssSelector(".hA.T-I-J3"))).click().build().perform();
		driver.findElement(By.cssSelector(".hA.T-I-J3")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.cssSelector(".hA.T-I-J3")).sendKeys(Keys.ENTER);
		/*driver.findElement(By.xpath("//*[@title='Delete']")).click();
		driver.findElement(By.xpath("//div[@gh='s']/*[@role='button']")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='ms']")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("Filters and Blocked Addresses")).click();
		Thread.sleep(4000);
		// driver.findElement(By.xpath("//td[contains(.,'auditya')]")).sendKeys(Keys.TAB,Keys.ENTER);

		List<WebElement> alldata = driver.findElements(By.xpath("//tr[@role='listitem']"));

		//// tr[@role='listitem'][7]/td[2]
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		for (int i = 1; i <= alldata.size(); i++) {
			try {
				System.out.println(driver.findElement(By.xpath("//tr[@role='listitem'][" + i + "]/td[2]")).getText());
				if (driver.findElement(By.xpath("//tr[@role='listitem'][" + i + "]/td[2]")).getText()
						.contains("auditya")) {
					driver.findElement(By.xpath("//tr[@role='listitem'][" + i + "]/td[1]/input")).click();
					break;
				}
			} catch (Exception e) {

			}
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[contains(.,'Unblock')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("yes")).click();

		driver.findElement(By.cssSelector(".gb_D.gb_Oa.gb_i")).click();
		driver.findElement(By.linkText("Sign out")).click();
		//log.

		driver.switchTo().alert().accept();*/

		return this;

	}



}
