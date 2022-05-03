package testRunner;

import com.cucumber.listener.ExtentCucumberFormatter;
import com.google.common.collect.ImmutableMap;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import stepDefinition.Steps;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions
(features = { "Feature" },
glue = { "stepDefinition" },
tags = { "@QAQA" },
//@qatest
//@QAQA
//@qawispp
//@bumpyard_wispp
plugin = { "pretty",
		"html:target/Cucumber-htmlreport", "json:target/cucumber-report.json",
		"com.cucumber.listener.ExtentCucumberFormatter:target/ExtentReport.html" }, dryRun = false, monochrome = true)

public class Web_Runner {

	static Properties prop = new Properties();
	public static WebDriver driver;

	@BeforeClass

	public static void init() {

		String BrowserName = null;

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream input = loader.getResourceAsStream("config/Configuration.properties");
		try {
			prop.load(input);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String AppToken = prop.getProperty("MacAPKFile");
		String Chrome_Driver = prop.getProperty("Chrome_Driver");
		String FireFoxDriver=prop.getProperty("FireFoxDriver");
		String EdgeDriver=prop.getProperty("EdgeDriver");
		String Android_Chrome=prop.getProperty("Android_Chrome");
		
		
		BrowserName = "Chrome";

		if (BrowserName == "Android_App") {

			// File ApplicationToTest =new
			// File("C:\\Spring\\WorkSpace\\Appuim_Cucumber\\Appium_Cucumber\\ApplicationToTest\\app-handz-release.apk");

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
			capabilities.setCapability(MobileCapabilityType.APP, AppToken);
			// capabilities.setCapability(MobileCapabilityType.APP,
			// ApplicationToTest.getAbsolutePath());
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.app12handz");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
					"com.camilyo.mobile.activities.MainActivity");

			try {
				driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Steps.defaulturl = "Mobile";
			Steps.driver = driver;
		}
   
   else if (BrowserName == "Android_Chrome") {
	
	   DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "And_11");
		//capabilities.setCapability(MobileCapabilityType.APP, Android_Chrome);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		// capabilities.setCapability("w3c", false);
		capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", true));
		capabilities.setCapability("chromedriverExecutable", Android_Chrome);
		
		
		try {
			driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Steps.defaulturl = "Mobile";
		Steps.driver = driver;
	}
		
	else if (BrowserName == "IOS") {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.2");
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11");

			try {
				driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Steps.defaulturl = "Mobile";
			Steps.driver = driver;
		}

		else if (BrowserName == "Chrome") {
			System.setProperty("webdriver.chrome.driver", Chrome_Driver);
			// System.setProperty("webdriver.chrome.driver",
			// "C:/chromedriver/chromedriver");
			
			//ChromeOptions options = new ChromeOptions();
			//options.addArguments("-incognito");
			//options.addArguments("--disable-popup-blocking");
			//ChromeDriver driver = new ChromeDriver(options);
			
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			
			
			//options.addArguments("C:\\Spring\\Workspace_1\\com.Auto.io\\ChromeData");
			//options.setExperimentalOption("excludeSwitches", "enable-automation");
			//options.setExperimentalOption("useAutomationExtension", false);

			//WebDriver driver = ChromeDriver(Chrome_Driver, options);
			
			//options.addArguments("C:\\Spring\\Workspace_1\\com.Auto.io\\ChromeData");
			//WebDriver driver = ChromeDriver("C:\\Spring\\Workspace_1\\com.Auto.io\\ChromeData\\whatsautomatico\\driver\\chromedriver",
            //        options);
					
			WebDriver driver = new ChromeDriver(options);            //Chrome_Driver

			//WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			driver.manage().window().maximize();
			//Steps.defaulturl = "Web";
			Steps.driver = driver;

		} else if (BrowserName == "FireFox") {

			System.setProperty("webdriver.gecko.driver", FireFoxDriver);
			FirefoxProfile profile = new FirefoxProfile();
			WebDriver driver = new FirefoxDriver();

			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("firefox_profile", profile);

			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

			driver.manage().window().maximize();

			//Steps.defaulturl = "Web";
			Steps.driver = driver;

		}

		else if (BrowserName == "Edge") {

			System.setProperty("webdriver.edge.driver",EdgeDriver);
			WebDriver driver = new EdgeDriver();

			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			driver.manage().window().maximize();

			driver.navigate().refresh();

			//Steps.defaulturl = "Web";
			Steps.driver = driver;

		}
		
	}

	private static WebDriver ChromeDriver(String string, ChromeOptions chromeOptions) {
		// TODO Auto-generated method stub
		return null;
	}

	@AfterClass
	public static void after() {

		//if (Steps.defaulturl.equals("Mobile")) {

			// Steps.driver.close();
			Steps.driver.quit();
		//} else {
			// Steps.driver.close();
			//Steps.driver.quit();
		//}
		// Steps.driver.close();

	}

}