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
plugin = { "pretty",
		"html:target/Cucumber-htmlreport", "json:target/cucumber-report.json",
		"com.cucumber.listener.ExtentCucumberFormatter:target/ExtentReport.html" }, dryRun = false, monochrome = true)

public class Android_Runner {

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
		String Android_Chrome=prop.getProperty("Android_Chrome");
		
		
		
		BrowserName = "Android_Chrome";
		
		

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
		//capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "And_11");
		//capabilities.setCapability(MobileCapabilityType.APP, Android_Chrome);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
		// capabilities.setCapability("w3c", false);
		capabilities.setCapability("VERSION", "83.0");
		//capabilities.setCapability("autoAcceptAlerts", true);
		//capabilities.setCapability("autoGrantPermissions",true);
		capabilities.setCapability("autoDissmissAlerts",true);
				 
		//capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", true));
		capabilities.setCapability("chromedriverExecutable", Android_Chrome);
		
		
		try {capabilities.setCapability("autoGrantPermissions", true);
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