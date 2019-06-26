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

	public void select_dropdown(String dropdown_css, String texttoselect)

	{
		try {

			WebElement dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(dropdown_css))));
			dropdown.click();
			dropdown.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
			dropdown.sendKeys(texttoselect, " ", Keys.BACK_SPACE);

			String option_to_select = "//span/b[text()='" + texttoselect + "']";

			wait.ignoring(NoSuchElementException.class);

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(option_to_select)));
			List<WebElement> options = driver.findElements(By.xpath(option_to_select));

			for (WebElement option : options) {
				option.click();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void select_button(String button_name) throws InterruptedException {

		wait.ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.button.rules-btn")));
		List<WebElement> buttons = driver.findElements(By.cssSelector("div.button.rules-btn"));
		System.out.println(buttons.size());
		for (WebElement button : buttons) {
			System.out.println(button.getText());
			if (button.getText().trim().equalsIgnoreCase(button_name)) {
				button.click();
			}
		}

	}

	public void select_item_by_css(String selector) {
		WebElement e = driver.findElement(By.cssSelector(selector));
		e.click();
	}

	public void select_filter(String gridname, String coulumn, String filterText) throws InterruptedException {

		WebElement e_grid = driver.findElement(By.cssSelector(gridname));
		List<WebElement> columns = e_grid.findElements(By.tagName("div"));
		for (WebElement e : columns) {
			if (e.getText().equalsIgnoreCase(coulumn)) {
				Actions actions = new Actions(driver);
				actions.moveToElement(e);
				actions.build().perform();

				List<WebElement> dropdowns = driver.findElements(By.className("jqx-icon-arrow-down"));
				System.out.println(dropdowns.size());

				for (WebElement ed : dropdowns) {

					try {
						if (ed.isDisplayed()) {
							ed.click();
							break;
						}
					} catch (Exception exce) {

					}

				}
				WebElement filterTextbox = driver.findElement(By.className("filtertext1moreAccountsGrid"));
				filterTextbox.sendKeys(filterText);
				WebElement fliterbutton = driver.findElement(By.id("filterbuttonmoreAccountsGrid"));
				fliterbutton.click();
				break;
			}
		}
	}

	public void select_row_in_table(String text) throws InterruptedException {

		wait_popupready();

		wait.until(
				ExpectedConditions.invisibilityOfElementWithText(By.className("jqx-fill-state-normal"), "Loading..."));
		WebElement row = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("row0moreAccountsGrid"))));
		row.click();

		wait.ignoring(NoSuchElementException.class);
		wait.until(
				ExpectedConditions.invisibilityOfElementWithText(By.className("jqx-fill-state-normal"), "Loading..."));

	}

	public void select_standard_button(String text) throws InterruptedException {

		System.out.println("Button input text" + text);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		List<WebElement> buttons = wait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button.btn-standard.blue-btn")));

		System.out.println(buttons.size());
		for (WebElement button : buttons) {
			System.out.println(button.getText());
			if (button.getText().trim().equalsIgnoreCase(text)) {
				if (button.isDisplayed()) {
					button.click();
					// button.submit();
					System.out.println("Clicked");
					break;
				}
			}
		}

	}

	public void wait_popupready() {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("jqx-grid-load")));
	}

	public void enter_text_by_id(String id, String text) {
		driver.findElement(By.id(id)).sendKeys(text);
	}

	public void click_calander(String id) throws InterruptedException {

		WebElement cal = driver.findElement(By.id(id)).findElement(By.className("jqx-icon-calendar"));
		cal.click();
	}

	public void select_date(String date) throws InterruptedException {
		Calendar now = Calendar.getInstance(); // Gets the current date and time
		int year = now.get(Calendar.YEAR);
		int year_to_select = year;
		int month_to_select = 0;
		int day_to_select = 0;

		System.out.println(date);
		if (date.contains("today")) {
			if (date.contains(";")) {
				int add_days = Integer.valueOf(date.split(";")[1]);
				System.out.println(add_days);
				year_to_select = year;
				month_to_select = now.get(Calendar.MONTH) + 1;
				day_to_select = now.get(Calendar.DATE) + add_days;

			} else {
				year_to_select = year;
				month_to_select = now.get(Calendar.MONTH) + 1;
				day_to_select = now.get(Calendar.DATE);
			}
		} else {
			year_to_select = Integer.valueOf((date.split("/")[2]));
			month_to_select = Integer.valueOf((date.split("/")[1]));
			day_to_select = Integer.valueOf((date.split("/")[0]));
		}

		Thread.sleep(1000);
		
		int act_year = year;
		List<WebElement> titles = driver.findElements(By.cssSelector("div.jqx-calendar-title-content"));
		{
			for (WebElement title : titles) {
				if (title.isDisplayed()) {
					act_year = Integer.valueOf(title.getText().split(" ")[1]);
					title.click();
					break;
				}
			}
		}

		int number_of_clicks = year_to_select - act_year;

		for (int i = 0; i < number_of_clicks; i++) {

			List<WebElement> arrows = driver
					.findElements(By.cssSelector("div.jqx-calendar-title-navigation.jqx-icon-arrow-right"));
			for (WebElement arrow : arrows) {
				if (arrow.isDisplayed()) {
					arrow.click();
				}
			}

		}

		List<WebElement> month_to_select_list = driver
				.findElements(By.cssSelector("td.jqx-rc-all.jqx-item.jqx-calendar-cell.jqx-calendar-cell-year"));
		int i = 1;
		for (WebElement month : month_to_select_list) {
			if (month.isDisplayed()) {
				if (month_to_select == i) {
					month.click();
					break;
				}
				i += 1;
			}
		}

		List<WebElement> day_to_select_list = driver
				.findElements(By.cssSelector(".jqx-rc-all.jqx-item.jqx-calendar-cell.jqx-calendar-cell-month"));

		for (WebElement day : day_to_select_list) {
			if (day.isDisplayed()) {

				if (!day.getAttribute("class").contains("othermonth")) {

					if (Integer.valueOf(day.getText()) == day_to_select) {
						day.click();
						break;
					}
				}

			}
		}

	}

	public boolean verify_page_title(String title) throws InterruptedException {
		boolean flag = false;
		List<WebElement> title_texts = driver.findElements(By.className("gac-title-style"));
		for (WebElement title_in : title_texts) {
			if (title_in.isDisplayed()) {
				System.out.println(title_in.getText());
				flag = true;
			}
		}
		if (!flag) {

			return false;
		} else {
			return true;
		}
	}

	public void select_breadcrumb(String linkname) throws InterruptedException {

		List<WebElement> bclinks = driver.findElements(By.cssSelector("span.breadcrumb-active.ng-binding.ng-scope"));
		System.out.println("Links " + bclinks.size());

		for (WebElement link : bclinks) {
			System.out.println(link.getText());
			if (link.getText().trim().equalsIgnoreCase(linkname)) {
				link.click();
				System.out.println("Clicked the home link");
				break;
			}
		}
	}

	public void click_item_id(String id) throws InterruptedException {

		WebElement id_item = driver.findElement(By.id(id));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", id_item);
		id_item.click();

	}

	public void select_filter_search(String gridname, String coulumn, String filterText) throws InterruptedException {

		WebElement e_grid = driver.findElement(By.id(gridname));
		List<WebElement> columns = e_grid.findElements(By.tagName("div"));
		for (WebElement e : columns) {
			if (e.getText().equalsIgnoreCase(coulumn)) {
				Actions actions = new Actions(driver);
				actions.moveToElement(e);
				actions.build().perform();
				List<WebElement> dropdowns = e_grid.findElements(By.className("jqx-icon-arrow-down"));
				System.out.println(dropdowns.size());

				for (WebElement ed : dropdowns) {

					try {

						if (ed.isDisplayed()) {
							ed.click();
							break;
						}
					} catch (Exception exce) {
						exce.printStackTrace();
					}

				}
				WebElement filterTextbox = driver.findElement(By.className("filtertext1searchResultsGridsearchWells"));
				filterTextbox.sendKeys(filterText);
				WebElement fliterbutton = driver.findElement(By.id("filterbuttonsearchResultsGridsearchWells"));
				fliterbutton.click();
				break;
			}
		}
	}

	public boolean select_row_in_wells_search_table(String text) throws InterruptedException {
		try {

			wait_popupready();

			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	public String getScreenshot() {

		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy_dd_MMM_HH_mm_ss");
			Date date = new Date();
			String getCurrentDate = dateFormat.format(date);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path = "./screenshots" + "/" + "screen_" + getCurrentDate + ".png";
			System.out.println("Path : " + path);
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
