package com.start;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.pages.ExtTest;
import com.pages.pagefactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.utilities.ExcelManager;

public class start {

	public static String browser = "", IEDriverPath = "", ChromeDriverPath = "", AppUrl = "", FirefoxDriverPath = "";

	public WebDriver driver = null;
	public ExcelManager xldriver = null;
	public static String url;
	public static ThreadLocal<ExcelManager> tlxldriver = new ThreadLocal<ExcelManager>();

	public ThreadLocal<ExtentTest> logging = new ThreadLocal<>();

	public static Properties props = null;
	public ThreadLocal<RemoteWebDriver> remotedriver = null;

	public static Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
	public static long time = cal.getTimeInMillis();
	protected ThreadLocal<WebDriver> wbdriver = new ThreadLocal<WebDriver>();

	@SuppressWarnings("finally")
	public pagefactory launch_browser() {

		props = getPropertiesValues("./data/config.properties");
		browser = props.getProperty("Browser");
		try {
			if (browser.equalsIgnoreCase("IE")) {
				IEDriverPath = props.getProperty("IEDriver");

				InternetExplorerOptions options = new InternetExplorerOptions();
				options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				options.setCapability("ignoreZoomSetting", true);
				options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				options.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
				options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, true);
				options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
				options.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, false);
				System.setProperty("webdriver.ie.driver", IEDriverPath);
					
				wbdriver.set(new InternetExplorerDriver(options));
				driver = wbdriver.get();

				driver.manage().window().maximize();
				driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
				System.out.println("IE Browser launched");

				driver.get(props.getProperty("URL"));
			} else if (browser.equalsIgnoreCase("FF")) {
				FirefoxDriverPath = System.getProperty("user.dir") + props.getProperty("GeckoDriverWindows");
				System.setProperty("webdriver.gecko.driver", FirefoxDriverPath);
				FirefoxOptions capabilities = new FirefoxOptions();
				capabilities.setCapability("platform", "WINDOWS");

				driver = new FirefoxDriver(capabilities);
				driver.manage().window().maximize();
				driver.get(props.getProperty("URL"));
				System.out.println("FireFox Driver Is Launched.");
				wbdriver.set(driver);
				wbdriver.get().manage().window().maximize();
				wbdriver.get().manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
				wbdriver.get().get(props.getProperty("URL"));
				
				


			} else if (browser.equalsIgnoreCase("Chrome")) {
				System.out.println("In Chrome Browser");
				ChromeDriverPath = System.getProperty("user.dir") + props.getProperty("ChromeDriverWindows");
				//System.setProperty("webdriver.chrome.driver", value)

				System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
				System.setProperty("webdriver.chrome.silentOutput", "true");
				ChromeOptions options = new ChromeOptions();
				//options.setExperimentalOption("useAutomationExtension", false);

				String Type = props.getProperty("Type");

				if (Type.equals("Local")) {

					wbdriver.set(new ChromeDriver(options));
					wbdriver.get().manage().window().maximize();
					wbdriver.get().manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
					wbdriver.get().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					wbdriver.get().get(props.getProperty("URL"));

				} else {
					String IPAddress = props.getProperty("IPAddress");
					remotedriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options));
					remotedriver.set(new RemoteWebDriver(new URL(IPAddress), options));
					driver = remotedriver.get();
					driver.manage().window().maximize();
					driver.get(props.getProperty("URL"));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			return new pagefactory(wbdriver.get());
		}

	}

	public WebDriver getDriver() {
		return wbdriver.get();
	}

	public synchronized void set_Logger(ExtentTest tst) {
		ExtTest.setTest(tst);
	}
	
	public void set_excelDriver(String SheetPath,String sheetName) throws IOException
	{
		ExcelManager ex = new ExcelManager();
		ex.SetExcelSheet(SheetPath, sheetName);
		//if(tlxldriver.get()!= null)
		//{
		tlxldriver.set(ex);	
		//}
	}
	
	public ExcelManager get_ExcelDriver()
	{
		return tlxldriver.get();
	}

	public Properties getPropertiesValues(String filePath) {
		Properties getPropertiesValues = null;
		FileInputStream file = null;
		try {
			getPropertiesValues = new Properties();
			getPropertiesValues.load(new FileInputStream(filePath));
		} catch (Exception e) {

		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					System.out.println("Exception thrown  :" + e.getMessage());
				}
			}
		}
		return getPropertiesValues;
	}
}
