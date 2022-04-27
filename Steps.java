package stepDefinition;

import static org.junit.Assert.assertTrue;

import io.appium.java_client.android.nativekey.AndroidKey;
//import io.appium.java_client.android.nativekey.KeyEvent;

import java.awt.event.KeyEvent;
import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.concurrent.TimeUnit;
import java.awt.Robot;
import java.awt.RenderingHints.Key;

import java.awt.Window;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.CharMatcher;
import com.mongodb.assertions.Assertions;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
//import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.bys.builder.ByChained;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.HideKeyboardStrategy;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import static java.time.Duration.ofMillis;

public class Steps {

	public static WebDriver driver; // = new ChromeDriver();
	static Properties prop = new Properties();
	public static String defaulturl;
	public static String ExcelFilepath;
	public static String APIUsage;
	public static String otp;

//API  Functions  Example 1

	public static String Token_path;
	public static String Url_path;
	public static String BASE_URL = "https://dhtapp.danhotels.com:443";
	public static String User_name_contact;
	public static String User_Email_contact;
	public static String User_Phone_contact;
	public static String parentWindow;
	public static String JWT_Token;
	public static String reset_token;
	public static String invitation_code;
	public static String user_id;
	public static String error_msg;
	public static String reservationId;
	public static String API_URL;
	public static Response API_STRING;
	public static String ValidRequest1;
	private static String[] Excel_Content = new String[25];

	private Response response;

	@Given("^Generate random contact details$")
	public void Generate_random_contact_details() throws Throwable {

		Random random = new Random();
		int index = random.nextInt(10000);
		String Randnumber = Integer.toString(index);

		User_name_contact = "MobileAuto" + Randnumber;
		User_Email_contact = "MobileAuto@" + Randnumber + ".com";
		User_Phone_contact = Randnumber;

		System.out.println("Contact Details :    " + User_name_contact + "  : " + User_Email_contact + "  : "
				+ User_Phone_contact);

	}

	@Given("^Lunch onboarding with new user for Env \"(.*?)\"$")
	public void Lunch_onboarding_with_new_user_for_Env(String BASE_URL) throws Throwable {
		// String username="dev-fs2/gadisivan";
		// String password="Gadi12345";
		// String BASE_URL="https://onlinemanagementplatform-dev.azurewebsites.net";
		// ClassLoader loader = Thread.currentThread().getContextClassLoader();
		// InputStream input =
		// loader.getResourceAsStream("config/Configuration.properties");
		// prop.load(input);
		// BASE_URL= prop.getProperty("Api_Base_URL");
		// username= prop.getProperty("Api_Auth_username");
		// password= prop.getProperty("Api_Auth_password");
		// input.close();
		// Thread.sleep(1000);
		String Username = "dev-fs2/gadisivan";
		String Password = "Gadi12345";

		String APi_call = "/api/CreateAccount?code=auinVgEElxH/aON/4Pcfw6v9V90qZgVdAVNZgrXf8KfvrJXez3/uvg==";
		String Auth_credantial = "Basic " + Username + "," + Password;

		Random random = new Random();
		int index = random.nextInt(10000);
		String Randnumber = Integer.toString(index);
		String accountName = "TEST_ACCOUNT" + Randnumber;
		String accountEmail = "TEST_ACCOUNT@" + Randnumber + ".com";

		System.out.println("User   " + accountName);
		System.out.println("Email   " + accountEmail);

		String validRequest = "{\n    \"accountName\": \"" + accountName + "\",\n    \"accountEmail\": \""
				+ accountEmail
				+ "\",\n    \"plan\": \"demopackage\",\n    \"trialDays\": 14,\n    \"startupFlow\": \"onboarding\",\n    \"brand\": \"12handz\",\n    \"country\": \"US\",\n    \"timezone\": \"America/New_York\",\n    \"accountPassword\": \"Pass12345\",\n    \"language\": \"en\",\n    \"testAccount\": true,\n    \"utmSource\": \"source2\",\n    \"utmMedium\": \"medium2\",\n    \"utmCampaign\": \"campaign2\",\n    \"utmTerm\": \"hair-salon\",\n    \"utmContent\": \"content2\"\n}";
		System.out.println("validRequest   " + validRequest);

		System.out.println("Auth_credantial   " + Auth_credantial);

		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		// request.header("Authorization", "Bearer" + token).header("Content-Type",
		// "application/json");
		// request.header("Authorization", "Basic
		// ZGV2LWZzMi9nYWRpc2l2YW46R2FkaTEyMzQ1");
		request.header("Authorization", Auth_credantial);
		response = request.body(validRequest).post(APi_call);

		System.out.println(response.statusCode());
		// System.out.println(response.asString());
		// System.out.println(response.getHeaders());
		// System.out.println(response.jsonPath().getString("url"));
		// System.out.println(response.jsonPath().getString("ompid"));

		String Url_link = response.jsonPath().getString("url");
		String ompid_link = response.jsonPath().getString("ompid");
		System.out.println("Url_link :     " + Url_link);
		System.out.println("ompid_link    " + ompid_link);

		Url_path = "";
		Url_path = Url_link;

		driver.get(Url_link);
		driver.navigate().refresh();

	}

	// if the username is new then you get onboarding otherwise login
	@Given("^Genereting login url for Env \"(.*?)\" with the user name \"(.*?)\"$")
	public void Generating_login_url_for_env_with_the_user_name(String BASE_URL, String user_name) throws Throwable {

		// BASE_URL="https://manage.develop.camilyo.net";
		String Username, Password;

		Username = "dev-fs2/gadisivan";
		Password = "Gadi12345";

		String get_req_user = "/api/accounts/" + user_name + "/SignOn";

		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given().auth().preemptive().basic(Username, Password)
				.cookie("sLastLang=en");
		// response = request.get("/api/accounts/Gadi_test/SignOn");
		response = request.get(get_req_user);

		// System.out.println ("Here comes the response" + response.asString ());
		System.out.println(response.statusCode());
		// System.out.println(response.asString());
		// System.out.println(response.getBody().asString());
		// System.out.println(response.statusLine());

		String loginToken = response.path("loginToken");
		String loginURL = response.path("loginURL");
		System.out.println("Your_Token    " + loginToken);
		System.out.println("loginURL    " + loginURL);

		Url_path = "";
		Token_path = "";

		Url_path = loginURL;
		Token_path = loginToken;

		driver.get(loginURL);
		driver.navigate().refresh();
	}

//   	Mobile  Functions
//		Mobile  Functions
//		Mobile  Functions

	@And("^Press the Back button$")
	public void press_the_screen() throws Throwable {

		driver.navigate().back();

	}

	@And("^If the object by ios chain \"(.*?)\" is not exists try this one \"(.*?)\"$")
	public void if_the_object_by_ios_chain_does_not_exits_try_this_one(String input, String input2) throws Throwable {

		String isPresent, allobjects;
		isPresent = "";

		isPresent = driver.findElement(MobileBy.iOSClassChain(input)).getText();
		// System.out.println("the text on isPresent "+isPresent);

		allobjects = driver.findElement(MobileBy.iOSClassChain(input2)).getText();
		// System.out.println("the text on allobjects "+allobjects);

		if (isPresent.length() > allobjects.length() && allobjects.length() != 0) {
			try {
				driver.findElement(MobileBy.iOSClassChain(input2)).click();
				// System.out.println("User Press the first"+input2);

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				driver.findElement(MobileBy.iOSClassChain(input)).click();
				// System.out.println("User Press the Secound"+input);

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}

	@And("^User press ios chain value \"(.*?)\"$")
	public void User_press_ios_chain_value(String input) throws Throwable {

		// WebDriverWait wait = new WebDriverWait(driver, 30);
		// wait.until(ExpectedConditions.elementToBeClickable(MobileBy.iOSClassChain(input))).click();

		driver.findElement(MobileBy.iOSClassChain(input)).click();

	}

	@And("^User press by location value \"(.*?)\" \"(.*?)\"$")
	public void User_press_bylocation_value(int point1, int point2) throws Throwable {

		TouchAction startStop = new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(point1, point2))
				.perform();

	}

	@And("^User press ios chain field \"(.*?)\" with the value \"(.*?)\"$")
	public void User_press_ios_chain_field_with_the_value(String input, String value) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.iOSClassChain(input))).sendKeys(value);
		// Thread.sleep(1000);
		// driver.findElement(MobileBy.iOSClassChain(input)).sendKeys(value);

	}

	@And("^User press ios chain field \"(.*?)\" with the value int \"(.*?)\"$")
	public void User_press_ios_chain_field_with_the_value_int(String input, int value) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.iOSClassChain(input))).sendKeys("" + value);

		System.out.println("User enter the value" + value);
	}

	@And("^User press ios AccessibilityId value \"(.*?)\"$")
	public void User_press_ios_AccessibilityId_value(String input) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId(input))).click();

	}

	@And("^User press ios iOSNsPredicateString value \"(.*?)\"$")
	public void User_press_ios_iOSNsPredicateString_value(String input) throws Throwable {

		// String selector= "type == 'XCUIElementTypeTextField' AND name == 'Email'";
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.iOSNsPredicateString(input))).click();

	}

	@And("^Press any Key \"(.*?)\"$")
	public void press_anyKey(String input) throws Throwable {

		try {
			((RemoteWebDriver) driver).getKeyboard().sendKeys(input);
			// ((IOSDriver) driver).hideKeyboard(HideKeyboardStrategy.PRESS_KEY, input);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@And("^If the button \"(.*?)\" does not exists press the back button$")
	public void if_the_button_does_not_exits_press_the_back_button(String input) throws Throwable {

		if (!driver.findElements(By.xpath(input)).isEmpty()) {
			try {
				driver.findElement(By.xpath(input)).click();
				System.out.println("User Press the first");

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				driver.navigate().back();

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}

	@And("^If the object By xpath \"(.*?)\" is not exists try this one \"(.*?)\"$")
	public void if_the_object_does_not_exits_try_this_one(String input, String input2) throws Throwable {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		try {
			driver.findElement(By.xpath(input)).click();
		} catch (Exception e) {
			System.out.println("User Press the first");
		}

		try {
			driver.findElement(By.xpath(input2)).click();
		} catch (Exception e) {
			System.out.println("User Press the Secound");
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@And("^Press the IOS hide keyboard$")
	public void press_the_ios_hidekeyboard() throws Throwable {

		((IOSDriver<?>) driver).hideKeyboard();

	}

	@And("^Android hide keyboard$")
	public void Android_hidekeyboard() throws Throwable {

		((AndroidDriver<?>) driver).hideKeyboard();
	}

	@And("^Press the IOS key Next$")
	public void press_the_ios_key_next() throws Throwable {

		((IOSDriver) driver).hideKeyboard(HideKeyboardStrategy.PRESS_KEY, "Next");
		// ((IOSDriver<?>)driver).hideKeyboard(HideKeyboardStrategy.PRESS_KEY,"NEXT");
		Thread.sleep(1000);
	}

	// @And("^Press the AndroidKey Digit5$")
	// public void press_the_AndroidKey_digit5() throws Throwable {

	// ((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_5));
	// Thread.sleep(1000);
	// }

	// @And("^Press the AndroidKey Enter$")
	// public void press_the_AndroidKey_enter() throws Throwable {

	// ((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
	// Thread.sleep(1000);
	// }

	@And("^Press the field \"(.*?)\" by xpath and the Value is \"(.*?)\"$")
	public void press_the_field_by_xpath_and_the_value_is(String input1, String value) throws Throwable {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(MobileBy.xpath(input1)).click();
		Thread.sleep(1000);
		driver.findElement(MobileBy.xpath(value)).click();
		Thread.sleep(2000);

	}

	@And("^User press long press by xpath on \"(.*?)\"$")
	public void user_press_long_press_by_xpath_on(String input) throws Throwable {

		// driver.findElement(By.xpath(input)).click();

		TouchActions action = new TouchActions(driver);
		action.longPress(driver.findElement(MobileBy.xpath(input)));
		action.perform();
	}

	@When("^The user see the text by id \"(.*?)\"$")
	public void The_user_see_the_text_by_id(String input) throws Throwable {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id(input)));

			Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	@And("^User press the object by id \"(.*?)\"$")
	public void User_press_the_object_by_id(String input) throws Throwable {

		try {
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			// driver.findElement(MobileBy.id(input)).click();
			driver.findElement(By.id(input)).click();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@And("^User press the object by xpath on Android \"(.*?)\"$")
	public void User_press_the_object_Android(String input) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath(input))).click();

	}

	@And("^User press the object by xpath \"(.*?)\"$")
	public void User_press_the_object(String input) throws Throwable {

		driver.findElement(By.xpath(input)).click();
		// Thread.sleep(4000);
	}

	@And("^User enter the text to field by xpath \"(.*?)\" With the Value \"(.*?)\"$")
	public void user_enter_the_text_to_field_by_xpath_with_the_value(String input1, String value) throws Throwable {

		try {
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.findElement(MobileBy.xpath(input1)).sendKeys(value);
			System.out.println("User enter the text to field:    " + input1 + "    " + value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@And("^User enter the text to field by css \"(.*?)\" With the Value \"(.*?)\"$")
	public void user_enter_the_text_to_field_by_css_with_the_value(String input1, String value) throws Throwable {

		try {
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector(input1)).sendKeys(value);
			System.out.println("User enter the text to field:    " + input1 + "    " + value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@And("^User enter the text to field by id \"(.*?)\" With the Value \"(.*?)\"$")
	public void user_enter_the_text_to_field_by_id_with_the_value(String input1, String value) throws Throwable {

		try {
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.findElement(MobileBy.id(input1)).sendKeys(value);
			System.out.println("User enter the text to field:    " + input1 + "    " + value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@And("^User enter the text to field xpath \"(.*?)\" With the Value from ConfigFile \"(.*?)\"$")
	public void user_enter_the_text_to_field_by_Android_with_the_value_from_ConfigFile(String input1, String value)
			throws Throwable {

		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream input = loader.getResourceAsStream("config/Configuration.properties");
			prop.load(input);

			String varibletofind1 = prop.getProperty(value);
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.findElement(MobileBy.xpath(input1)).sendKeys(varibletofind1);

			System.out.println("User enter the text to field:    " + input1 + "    " + varibletofind1);
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("^User see the object by xpath \"(.*?)\"$")
	public void user_see_the_object_by_xpath(String input) throws Throwable {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath(input)));
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// (From,135, 40)
	@And("^User drag from object by css \"(.*?)\" and by pixel position \"(.*?)\" \"(.*?)\"$")
	public void user_drag_by_css_and_pixel(String input, int Xnumber, int Ynumber) throws Throwable {

		WebElement From = driver.findElement(By.cssSelector(input));
		// Using Action class for drag and drop.
		Actions act = new Actions(driver);
		// Drag and Drop by Offset.
		act.dragAndDropBy(From, Xnumber, Ynumber).build().perform();
	}

	@And("^User drag from object by css \"(.*?)\" to object \"(.*?)\"$")
	public void user_drag_by_css_from_object_to_object(String input, String toinput) throws Throwable {

		// Element which needs to drag.
		WebElement From = driver.findElement(By.xpath(input));
		// Element on which need to drop.
		WebElement To = driver.findElement(By.xpath(toinput));
		// Using Action class for drag and drop.
		Actions act = new Actions(driver);
		// Dragged and dropped.
		act.dragAndDrop(From, To).build().perform();

	}

	// How to select element in multi select box in selenium webdriver
	@And("^User press multyselect option by xpath \"(.*?)\"$")
	public void Scroll_down(String input) throws Throwable {

		WebElement element = driver.findElement(By.xpath(input));

		Select select = new Select(element);
		Actions builder = new Actions(driver);
		builder.keyDown(Keys.CONTROL).click(select.getOptions().get(2))
				// .click(select.getOptions().get(3))
				// .click(select.getOptions().get(4))
				.keyUp(Keys.CONTROL);

		builder.build().perform();
	}

	@And("^scroll down$")
	public void Scroll_down() throws Throwable {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);

	}

	@And("^Scroll down and press the object byxpath \"(.*?)\"$")
	public void Scroll_down_and_press_the_object(String input) throws Throwable {

		WebElement element = driver.findElement(By.xpath(input));

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", element);
		Actions action = new Actions(driver);
		action.click(element).build().perform();

	}

	@And("^Scroll down and press the object bycss \"(.*?)\"$")
	public void Scroll_down_and_press_the_object_bycss(String input) throws Throwable {

		WebElement element = driver.findElement(By.cssSelector(input));

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", element);
		Actions action = new Actions(driver);
		action.click(element).build().perform();
		// driver.findElement(MobileBy.cssSelector(input)).click();
	}

	@And("^Android Scroll to text and click \"(.*?)\"$")
	public void android_Scroll_to_text(String input) throws Throwable {

		try {

			((AndroidDriver<?>) driver).findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
							+ input + "\").instance(0))")
					.click();

			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@And("^Scroll down page$")
	public void Scroll_down_page() throws Throwable {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// JavascriptExecutor jse = (JavascriptExecutor)driver;
		// jse.executeScript("scroll(0, 250);"); //Scroll Down
		// jse.executeScript("scroll(0,-250);"); //Scroll Up
		// js.executeScript("window.scrollBy(0,1000)");
		// js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		// TouchAction action = new TouchAction((PerformsTouchActions) driver);

		// action.press(PointOption.point(32, 1844));
		// action.moveTo(PointOption.point(46, 1231));
		// action.release();
		// action.perform();

	}

	@And("^Android_swipe down$")
	public void Android_swipe_down() throws Throwable {

		TouchAction action = new TouchAction((PerformsTouchActions) driver);
		// action.press(PointOption.point(32, 1844));
		// action.moveTo(PointOption.point(46, 1231));
		// action.release();
		// action.perform();

		action.press(PointOption.point(810, 1725));
		action.moveTo(PointOption.point(810, 200));
		action.release();
		action.perform();

	}

	@And("^User clear the data from textbox Byxpath \"(.*?)\"$")
	public void user_clear_the_data_from_textbox_byxpath(String input) throws Throwable {

		driver.findElement(By.xpath(input)).clear();
		Thread.sleep(1000);
	}

//Mobile  Functions   End
//Mobile  Functions   End
//Mobile  Functions   End
//Mobile  Functions   End

	// Find out which of the first element Icon is it
	@And("^Click the first element and send text and emoji$")
	public void Click_the_first_element_and_send_text_and_emoji() throws Throwable {

		// Forms
		String str1 = "#divExpandedObjectsWrapper div[title=\"Forms\"]";
		// Chat
		// String str2=""
		// Mail
		String str3 = "#divExpandedObjectsWrapper div[title=\"Mail\"]";
		// WhatsUp
		String str4 = "#divExpandedObjectsWrapper div[title=\"WhatsApp\"]";
		// VideoChat
		// String str5=""
		// Agent
		// String str6=""

		// Forms
		String element1 = "";
		try {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			element1 = driver.findElement(By.cssSelector(str1)).getAttribute("title");
		} catch (Exception e) {
			System.out.println("Element Forms Not found");
		}
		String element2 = ""; // Mail
		try {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			element2 = driver.findElement(By.cssSelector(str3)).getAttribute("title");
		} catch (Exception e) {
			System.out.println("Element Mail Not found");
		}
		String element3 = ""; // WhatsUp
		try {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			element3 = driver.findElement(By.cssSelector(str4)).getAttribute("title");
		} catch (Exception e) {
			System.out.println("Element WhatsUp Not found");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// Forms

		if ((element1.equals("Forms"))) {

			System.out.println("Element Forms found");

			System.out.println("Send two tab");
			Actions builder = new Actions(driver);
			Thread.sleep(1000);
			builder.sendKeys(Keys.TAB).build().perform();
			Thread.sleep(1000);
			builder.sendKeys(Keys.TAB).build().perform();
			Thread.sleep(1000);
			// Sending Text
			System.out.println("Sending Text");
			builder.sendKeys("   Hello from Commbox").perform();
			Thread.sleep(1000);
			// System.out.println("press Add");
			// driver.findElement(By.xpath("//*[@class=\"theme_button saveRemark
			// pointer\"][text()[contains(.,\"Add\")]]")).click();
			// Thread.sleep(1000);
			// Sending Emoji
			System.out.println("Send one tabs one enter");
			builder.sendKeys(Keys.TAB).build().perform();
			Thread.sleep(1000);
			builder.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(1000);
			System.out.println("Choose Emoji");
			driver.findElements(By.cssSelector("#by_emoji_picker > span:nth-child(1)")).get(0).click();
			Thread.sleep(2000);
			// System.out.println("press ENTER");
			// builder.sendKeys(Keys.ENTER).build().perform();
			// Thread.sleep(2000);
			System.out.println("press add");
			driver.findElement(By.xpath("//*[@class=\"theme_button saveRemark pointer\"][text()[contains(.,\"Add\")]]"))
					.click();
			Thread.sleep(1000);
			builder.release().perform();
			Thread.sleep(1000);

			// Mail
		} else if ((element2.equals("Mail"))) {
			// } else if (element2 != null){

			System.out.println("Element Mail found");

			Actions builder1 = new Actions(driver);
			Thread.sleep(1000);
			// Sending Text

			builder1.sendKeys("Hello from Commbox").perform();
			System.out.println("Hello from Commbox");

			Thread.sleep(2000);
			// Sending Emoji
			System.out.println("Press emoji button");
			Thread.sleep(2000);
			driver.findElement(
					By.cssSelector(".icon-very-satisfied emoji-picker inline-block ,button[title=\"Emoji\"]")).click();
			Thread.sleep(2000);
			System.out.println("Choose Emoji");
			driver.findElements(By.cssSelector("#by_emoji_picker > span:nth-child(1)")).get(0).click();
			Thread.sleep(2000);
			System.out.println("Press Enter");
			builder1.sendKeys(Keys.ENTER).build().perform();
			builder1.sendKeys("   Sending Emoji").perform();
			Thread.sleep(2000);
			// System.out.println("Press send");
			// driver.findElement(By.xpath("//*[@class=\"sendMessage
			// pointer\"][text()[contains(.,\"Send\")]]")).click();
			// Thread.sleep(2000);
			builder1.release().perform();

			// WhatsUp WhatsApp
		} else if ((element3.equals("WhatsApp"))) {
			// } else if (element3 != null){
			System.out.println("Element WhatsUp found");
			System.out.println("Choosing a template");
			driver.findElement(By.xpath(
					"//*[@class=\"template-replay theme_main_border_color pointer mousetrap theme_secondary_panel_bg\"]"))
					.click();
			Thread.sleep(2000);
			System.out.println("Choosing aaaa as template");
			Thread.sleep(2000);
			Actions builder = new Actions(driver);
			Thread.sleep(2000);
			System.out.println("Press Two DOWN");
			builder.sendKeys(Keys.DOWN).build().perform();
			Thread.sleep(2000);
			builder.sendKeys(Keys.DOWN).build().perform();
			Thread.sleep(2000);
			System.out.println("Press ENTER");
			builder.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);
			System.out.println("Entering values to the template");
			driver.findElement(
					MobileBy.xpath("//*[@id=\"comment-send-wa-template-dialog\"]/div[3]/div[2]/div/div/input"))
					.sendKeys("Testing123");
			Thread.sleep(2000);
			System.out.println("Press Send");
			// driver.findElement(By.xpath("//div[174]/div[2]//div[4]/div[2]/div")).click();
			driver.findElement(By.cssSelector("#comment-send-wa-template-dialog div[title=\"Ctrl + Enter\"]")).click();

			Thread.sleep(2000);

			builder.release().perform();

		} else {

			// VideoChat,Chat,Agent,SMS

			System.out.println("Element VideoChat,Chat,Agent,SMS,found");

			Actions builder = new Actions(driver);
			Thread.sleep(3000);
			// Sending Text
			System.out.println("Sending Text");
			builder.sendKeys("   Hello Customer from Commbox").perform();
			// Thread.sleep(3000);
			// System.out.println("Press Send");
			// driver.findElement(By.xpath("//*[@class=\"sendMessage
			// pointer\"][text()[contains(.,\"Send\")]]")).click();
			Thread.sleep(2000);
			// Sending Emoji
			System.out.println("Sending Emoji");
			System.out.println("Press TAB and ENTER ");
			builder.sendKeys(Keys.TAB).build().perform();
			Thread.sleep(2000);
			builder.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);
			System.out.println("Choose Emoji");
			driver.findElements(By.cssSelector("#by_emoji_picker > span:nth-child(1)")).get(0).click();
			Thread.sleep(2000);
			System.out.println("Press ENTER ");
			builder.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);
			builder.sendKeys("   Sending Emoji").perform();
			Thread.sleep(2000);
			System.out.println("Sending Emoji");
			System.out.println("Press Send ");
			driver.findElement(By.xpath("//*[@class=\"sendMessage pointer\"][text()[contains(.,\"Send\")]]")).click();
			Thread.sleep(2000);
			builder.release().perform();
		}
	}

	// for hidden element and not visible
	@And("^Clicks on an element that is visible or not by xpath \"(.*?)\"$")
	public void Clicks_on_an_element_that_is_visible_or_not_by_xpath(String toclick) throws Throwable {

		WebElement element = driver.findElement(By.xpath(toclick));
		((JavascriptExecutor) driver).executeScript("return arguments[0].click();", element);

	}

	@And("^if the checkbox is not check check it by xpath \"(.*?)\"$")
	public void if_the_checkbox_is_not_check_check_it_by_xpath(String input) throws Throwable {

		if (!driver.findElement(By.xpath(input)).isSelected()) {
			driver.findElement(By.xpath(input)).click();
		}
	}

	@And("^if the checkbox is checked then uncheck it by xpath \"(.*?)\"$")
	public void if_the_checkbox_is_checked_then_uncheck_it_by_xpath(String input) throws Throwable {

		if (driver.findElement(By.xpath(input)).isSelected()) {
			driver.findElement(By.xpath(input)).click();
		}
	}

	@And("^if the Slider is checked \"(.*?)\" then uncheck it by xpath \"(.*?)\"$")
	public void if_the_slider_is_checked_then_uncheck_it_by_xpath(String input1, String input2) throws Throwable {

		if (driver.findElement(By.xpath(input1)).isSelected()) {
			driver.findElement(By.xpath(input2)).click();
		}
	}

	@And("^if the Slider is uncheck \"(.*?)\" then check it by xpath \"(.*?)\"$")
	public void if_the_slider_is_unchecked_then_check_it_by_xpath(String input1, String input2) throws Throwable {

		if (driver.findElement(By.xpath(input1)).isSelected()) {
			driver.findElement(By.xpath(input2)).click();
		}
	}

	@Given("^User navigate to a config \"(.*?)\" Page$")
	public void user_navigate_to_a_config_url_file_Page(String input2) throws Throwable {

		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream input = loader.getResourceAsStream("config/Configuration.properties");
			prop.load(input);

			System.out.println(prop.getProperty(input2));
			String urlpath = prop.getProperty(input2);

			driver.get(urlpath);
			driver.navigate().refresh();

			input.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@When("^The user see the Object by css \"(.*?)\"$")
	public void the_user_see_the_object_on_page(String input) throws Throwable {

//	WebDriverWait wait = new WebDriverWait(driver, 60);

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(input)));
		Thread.sleep(2000);
//System.out.println("The user see the text: "+input+" - on page ");
	}

///
/// Wait for object to appear on screen

	@When("^The user see the Object by id \"(.*?)\" on page$")
	public void the_user_see_the_object_by_id_on_page(String input) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(input)));
//System.out.println("The user see the text: "+input+" - on page ");
	}

	@When("^The user see the text by xpath \"(.*?)\"$")
	public void The_user_see_the_text_by_xpath(String input) throws Throwable {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath(input)));

		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}

	}

	@When("^I wait for \"(.*?)\" milliseconds$")
	public void i_wait_for_x_milliseconds(String milliseconds) {
		try {

			Thread.sleep(Integer.parseInt(milliseconds));
//System.out.println("I wait for "+ milliseconds +" milliseconds");

		} catch (Exception ex) {
//ignore
		}

	}

	@When("^User see the text \"(.*?)\" of the object \"(.*?)\" then he press \"(.*?)\"$")
	public void user_see_the_text_then_he_press(String input, String value1, String value2) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(value1)));

		String heading_text = driver.findElement(By.cssSelector(value1)).getText();
		System.out.println("Validating A value on the page:    " + heading_text + " ");

		if (heading_text.equals(input)) {
			driver.findElement(By.cssSelector(value2)).click();
		} else if (!heading_text.equals(input)) {
			driver.findElement(By.cssSelector(value1)).click();

		}
	}

	@And("^Move the driver to iframe num \"(.*?)\" name of iframe \"(.*?)\"$")
	public void move_the_driver_to_iframe_num_name_of_iframe(int value, String input1) throws Throwable {

		// List<WebElement> iframeElement = driver.findElements(By.tagName(input1));
		driver.findElement(By.tagName(input1));
		driver.switchTo().frame(value);

		// System.out.println(" The total number of iframes are: " +
		// iframeElements.size());
	}

	@And("^find how many iframe on page put the correct name of iframe \"(.*?)\"$")
	public void the_user_find_iframe(String input1) throws Throwable {

		int iframenum;

		List<WebElement> iframeElements = driver.findElements(By.tagName(input1));

		Thread.sleep(1000);
		iframenum = iframeElements.size();
		Thread.sleep(1000);
		System.out.println("The total number of iframes are " + iframenum);

	}

	@When("^The user see the Object by className \"(.*?)\"$")
	public void the_user_see_the_object_by_className(String input) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(input)));
		Thread.sleep(2000);
//System.out.println("The user see the text: "+input+" - on page ");
	}

	@And("^Users press the button by className \"(.*?)\"$")
	public void users_press_the_button_by_classname(String input) throws Throwable {

		driver.findElement(By.className(input)).click();
		Thread.sleep(1000);

		System.out.println("Users press the button by className:    " + input + " ");
	}

	@And("^User enter random value to field by css \"(.*?)\"$")
	public void user_enter_random_value_to_field(String input) throws Throwable {

		Random random = new Random();
		int index = random.nextInt(10000);
		String Randnumber = Integer.toString(index);
		Randnumber = "automation" + Randnumber;

		driver.findElement(By.cssSelector(input)).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(input)).sendKeys(Randnumber);
		System.out.println("Randnumber test " + Randnumber);

		User_name_contact = Randnumber;

		FileWriter fr = new FileWriter("C://Spring/AccountDetail.txt", true);
		BufferedWriter br = new BufferedWriter(fr);

		br.write("Account name" + "       " + Randnumber);
		br.write("\r");
		br.write("\n");
		br.write("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		br.write("\r");
		br.write("\n");
		br.close();
	}

	@And("^User enter the saved random value to field by css \"(.*?)\"$")
	public void User_enter_the_saved_random_value_to_field_by_css(String input) throws Throwable {

		driver.findElement(By.cssSelector(input)).sendKeys(User_name_contact);
		System.out.println("the saved random value is  : " + User_name_contact);

	}

	@And("^User enter the text to field by css \"(.*?)\" with the value from config \"(.*?)\"$")
	public void user_enter_the_text_to_field_by_css_with_the_value_from_config(String input, String value1)
			throws Throwable {

		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream input33 = loader.getResourceAsStream("config/Configuration.properties");
			prop.load(input33);

			String varibletofind2 = prop.getProperty(value1);
			System.out.println("The value is" + varibletofind2);

			driver.findElement(By.cssSelector(input)).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(input)).sendKeys(varibletofind2);

			input33.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@And("^User enter the text to field by ID \"(.*?)\" with the value from config \"(.*?)\"$")
	public void user_enter_the_text_to_field_by_id_with_the_value_from_config(String input, String value1)
			throws Throwable {

		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream input44 = loader.getResourceAsStream("config/Configuration.properties");
			prop.load(input44);

			String varibletofind2 = prop.getProperty(value1);
			System.out.println("The value is" + varibletofind2);

			driver.findElement(By.id(input)).click();
			Thread.sleep(2000);
			driver.findElement(By.id(input)).sendKeys(varibletofind2);

			input44.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@And("^User Set focus to object \"(.*?)\"$")
	public void user_set_focus_to_object(String input) throws Throwable {

		driver.findElement(By.cssSelector(input)).sendKeys("");

	}

	@And("^User Send the Keyboard value \"(.*?)\"$")
	public void user_send_the_Keyboard_value(String input) throws Throwable {

		Actions action = new Actions(driver);
		action.sendKeys(input).perform();

//Keyboard keyboard = ((HasInputDevices) driver).getKeyboard();
//keyboard.sendKeys(input);
//System.out.println("User Sends the KeyboardValue:  " + input);

//.SendKeys(Keys.Enter);
	}

	@And("^User copy the selected text$")
	public void user_send_keyboard_key_copy() throws Throwable {

		Actions action = new Actions(driver);
		action = new Actions(driver);
		action.keyDown(Keys.CONTROL);
		action.sendKeys("c");
		action.keyUp(Keys.CONTROL);
		action.build().perform(); // copy is performed
	}

	@And("^User past the selected text$")
	public void user_send_keyboard_key_past() throws Throwable {

		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL);
		action.sendKeys("v");
		action.keyUp(Keys.CONTROL);
		action.build().perform(); // paste is performed

	}

	@And("^User send control alt R$")
	public void User_send_control_alt_R() throws Throwable {

		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL);
		action.keyDown(Keys.ALT);
		action.sendKeys("r");
		// action.keyDown(Keys.ALT);
		action.keyUp(Keys.CONTROL);
		action.build().perform(); // paste is performed

	}

	@And("^User send control alt P$")
	public void User_send_control_alt_P() throws Throwable {

		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL);
		action.keyDown(Keys.ALT);
		action.sendKeys("p");
		// action.keyDown(Keys.ALT);
		action.keyUp(Keys.CONTROL);
		action.build().perform(); // paste is performed

	}

	@And("^User send control alt F$")
	public void User_send_control_alt_f() throws Throwable {

		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL);
		action.keyDown(Keys.ALT);
		action.sendKeys("f");
		// action.keyDown(Keys.ALT);
		action.keyUp(Keys.CONTROL);
		action.build().perform(); // paste is performed

	}

	@And("^Move with tab up one level$")
	public void Move_with_tab_up_one_level() throws Throwable {
		Actions action = new Actions(driver);
		action.keyDown(Keys.SHIFT);
		action.sendKeys(Keys.TAB);
		action.keyUp(Keys.SHIFT);
		action.build().perform(); // paste is performed

	}

	@And("^User Send keyboard key Ctrl Enter$")
	public void user_send_keyboard_key_Ctrl_Enter() throws Throwable {
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.CONTROL).build().perform();
		builder.sendKeys(Keys.ENTER).build().perform();
		builder.release().perform();
	}

	@And("^User Choose Select All$")
	public void user_chooseSelectAll() throws Throwable {

		Actions action = new Actions(driver);
		action = new Actions(driver);
		action.keyDown(Keys.CONTROL);
		action.sendKeys("a");
		action.keyUp(Keys.CONTROL);
		action.build().perform(); // Select all is performed
	}

	@And("^User Send keyboard Delete$")
	public void user_send_keyboard_Delete() throws Throwable {
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.DELETE).build().perform();
		builder.release().perform();

	}

	@And("^User Send keyboard key Shift Tab$")
	public void user_send_keyboard_key_Shift_Tab() throws Throwable {
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.SHIFT).build().perform();
		builder.sendKeys(Keys.TAB).build().perform();
		builder.release().perform();
	}

	@And("^User Send keyboard key Arrow Down$")
	public void user_send_keyboard_key_ARROW_DOWN() throws Throwable {
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.ARROW_DOWN).build().perform();
		builder.release().perform();
	}

	@And("^User Send keyboard key Tab$")
	public void user_send_keyboard_key_tab() throws Throwable {
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.TAB).build().perform();
		builder.release().perform();
	}

	@And("^User checks the element visible on page \"(.*?)\" and print the value \"(.*?)\"$")
	public void user_checks_the_element_visible_on_page_and_print(String input, String value) throws Throwable {

		try {
			boolean flag = driver.findElement(By.cssSelector(input)).isDisplayed();
			if (flag) {

				System.out.println(value);
			}
		} catch (NoSuchElementException e) {
//System.out.println(value);
			System.out.println("XXX");
		}
	}

	@And("^User press browser f5$")
	public void User_press_browser_f5() throws Throwable {
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.F5).build().perform();
		builder.release().perform();
	}

	@And("^User press browser refresh$")
	public void User_press_browser_refresh() throws Throwable {
		driver.navigate().refresh();
	}

	@And("^User Send keyboard key space$")
	public void user_send_keyboard_key_space() throws Throwable {

		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.SPACE).build().perform();
		builder.release().perform();
	}

	@And("^User Send keyboard key Enter$")
	public void user_send_keyboard_key_enter() throws Throwable {

		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.ENTER).build().perform();
		builder.release().perform();
	}

	@And("^User Send keyboard key Esc$")
	public void user_send_keyboard_key_Escap() throws Throwable {

		// Actions action = new Actions(driver);
		// action.sendKeys(Keys.ESCAPE).build().perform();

		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE);

	}

	@And("^System writes data to name of file \"(.*?)\" and the text to write \"(.*?)\"$")
	public void system_writes_data_to_file(String input, String value) throws Throwable {

		FileWriter fr = new FileWriter("C://Spring_Parameters/BlockDomain/" + input, true);
		BufferedWriter br = new BufferedWriter(fr);

		br.write(value);
		br.write("\r");
		br.write("\n");
		br.write("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		br.write("\r");
		br.write("\n");
		br.close();

	}

	@Given("^User navigate to new window$")
	public void user_navigates_to_new_window() throws Throwable {

		// String windowHandle="";
		parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				// <!--Perform your operation here for new window-->
				// driver.close(); //closing child window
				// driver.switchTo().window(parentWindow); //cntrl to parent window
			}
		}
	}

	@Given("^User navigate back to parent window$")
	public void user_navigates_back_to_parent_window() throws Throwable {

		driver.switchTo().window(parentWindow);

	}

	@Given("^User navigate to \"(.*?)\"$")
	public void user_navigates_to(String input) throws Throwable {

		driver.get(input);
		System.out.println("User navigates to: " + input);
	}

	@And("^Users press the button by xpath \"(.*?)\"$")
	public void users_press_the_button_by_xpath(String input) throws Throwable {

		driver.findElement(By.xpath(input)).click();
		System.out.println("Users press the button by xpath    " + input + " ");
	}

	@And("^Users press the button by id \"(.*?)\"$")
	public void users_press_the_button_by_id(String input) throws Throwable {

		driver.findElement(By.id(input)).click();
		System.out.println("Users press the button by id:    " + input + " ");
	}

	@And("^Users press the object by id \"(.*?)\"$")
	public void users_press_the_object_by_id(String input) throws Throwable {

		driver.findElement(By.id(input)).click();
		System.out.println("Users press the object by id:    " + input + " ");
	}

	@And("^Users press the object by css \"(.*?)\"$")
	public void users_press_the_object_by_css(String input) throws Throwable {

		driver.findElement(By.cssSelector(input)).click();
		System.out.println("Users press the object by css:    " + input + " ");
	}

	@And("^Users press the button by cssselector once only \"(.*?)\" and the End param is \"(.*?)\"$")
	public void users_press_the_button_by_cssselector_once_only(String input, String value) throws Throwable {

		if (!value.equals("Quite")) {
			driver.findElement(By.cssSelector(input)).click();
		}

		System.out.println("Users press the button by cssselector Only Once:    " + input + " " + value);
	}

	@And("^Users press the button by cssselector \"(.*?)\"$")
	public void users_press_the_button_by_cssselector(String input) throws Throwable {

		driver.findElement(By.cssSelector(input)).click();
//System.out.println("Users press the button by cssselector: "+ input +" ");
	}

	@And("^Users press the combobutton by class_And_cssselector \"(.*?)\" and value \"(.*?)\"$")
	public void users_press_the_combobutton_by_class_And_cssselector_and_value(String input, String value1)
			throws Throwable {

		driver.findElement(By.className(input)).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(value1)).click();
		System.out.println("Users press the button by cssselector:    " + input + " ");
	}

	@And("^Users press the combobutton by cssselector \"(.*?)\" and value \"(.*?)\"$")
	public void users_press_the_combobutton_by_cssselector_and_value(String input, String value1) throws Throwable {

		driver.findElement(By.cssSelector(input)).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector(value1)).click();
//System.out.println("Users press the button by cssselector: "+ input +" ");
	}

	@And("^Users press the combobutton by xpath \"(.*?)\" and value \"(.*?)\"$")
	public void users_press_the_combobutton_by_xpath_and_value(String input, String value1) throws Throwable {

		driver.findElement(By.xpath(input)).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(value1)).click();
//System.out.println("Users press the button by cssselector: "+ input +" ");
	}

	@And("^Users press the combobox by xpath \"(.*?)\" and value \"(.*?)\"$")
	public void users_press_the_combobox_by_xpath_and_value(String input, String value1) throws Throwable {

		String toSearch = "//*[text()[contains(.," + value1 + ")]]";

		driver.findElement(By.xpath(input)).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(toSearch)).click();

	}

	@And("^Users press the button by css \"(.*?)\"$")
	public void users_press_the_button_by_csss(String input) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(input)));
		driver.findElements(By.cssSelector(input)).get(0).click();
		System.out.println("Users press the button:    " + input + " ");

	}

	@And("^User clear the data from textbox \"(.*?)\"$")
	public void user_clear_the_data_from_textbox(String input) throws Throwable {

		driver.findElement(By.cssSelector(input)).clear();
		driver.findElement(By.cssSelector(input)).sendKeys(Keys.DELETE);

//System.out.println("User Clear the data form the textBox");
	}

	@And("^User clear the data from textbox by ID \"(.*?)\"$")
	public void user_clear_the_data_from_textbox_by_id(String input) throws Throwable {

		driver.findElement(By.id(input)).clear();
		System.out.println("User Clear the data form the textBox");
	}

	@And("^Users press the button \"(.*?)\"$")
	public void users_press_the_button(String input) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(input)));
		driver.findElements(By.cssSelector(input)).get(0).click();
		System.out.println("Users press the button:    " + input + " ");
	}

	@And("^User enter the text to field by classname \"(.*?)\" With the Value \"(.*?)\"$")
	public void user_enter_the_text_to_field_by_className_with_the_value(String input, String value1) throws Throwable {

		driver.findElement(By.className(input)).click();
		Thread.sleep(1000);
		driver.findElement(By.className(input)).sendKeys(value1);
		System.out.println("User enter the text to field by classname :    " + input + "with the value: " + value1);

	}

	@And("^User enter the text to field css \"(.*?)\" with the value \"(.*?)\"$")
	public void user_enter_the_text_to_field_with_the_value_css(String input, String value1) throws Throwable {

		driver.findElement(By.cssSelector(input)).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(input)).sendKeys(value1);
//System.out.println("User enter the text to field : "+ input +"with the value:
//"+ value1);
	}

	@And("^User enter random email to field \"(.*?)\"$")
	public void user_enter_random_email_to_field(String input) throws Throwable {

		Random random = new Random();
		int index = random.nextInt(10000);
		String Randnumber = Integer.toString(index);
		Randnumber = "GAdiAuto" + Randnumber + "@test.com";

		driver.findElement(By.cssSelector(input)).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(input)).sendKeys(Randnumber);
		System.out.println("User enter random email : " + Randnumber);

	}

	@And("^Credit card num \"(.*?)\" ExpDateMonth \"(.*?)\" ExpDateYear \"(.*?)\" Cvv \"(.*?)\" ZipCode \"(.*?)\"$")
	public void creditcard_num_expdate_cvv_zipcode(String input, String value1, String value2, String value3,
			String value4) throws Throwable {

//String myText ="4242424242424242";
		Keyboard keyboard = ((HasInputDevices) driver).getKeyboard();
//enter a key
		keyboard.sendKeys(input);
		Thread.sleep(2000);
		keyboard.sendKeys(value1);
		Thread.sleep(2000);
		keyboard.sendKeys(value2);
		Thread.sleep(2000);
		keyboard.sendKeys(value3);
		Thread.sleep(2000);
		keyboard.sendKeys(value4);

		System.out.println("User enter the Credit card num : " + input + " " + value1 + " " + value2 + " " + value3
				+ " " + value4);

//driver.findElement(By.cssSelector(input)).sendKeys(Keys.chord(Keys.CONTROL,
//"a"));
//Thread.sleep(3000);
//driver.findElement(By.cssSelector(input)).sendKeys(Keys.chord(Keys.CONTROL,
//"c"));
//Thread.sleep(3000);
//driver.findElement(By.cssSelector(value1)).sendKeys(Keys.chord(Keys.CONTROL,
//"v")); //text copied to clipboard
//Thread.sleep(3000);

	}

	@And("^User paste the random Keyboard value$")
	public void user_paste_the_random_Keyboard_value() throws Throwable {

		Random random = new Random();
		int index = random.nextInt(10000);
		String Randnumber = Integer.toString(index);
		Randnumber = "Auto" + Randnumber;

		Actions action = new Actions(driver);
		action.sendKeys(Randnumber).perform();
	}

	@And("^User enter the text to fieldxpath \"(.*?)\" with the value \"(.*?)\"$")
	public void user_enter_the_text_to_fieldxpath_with_the_value(String input, String value1) throws Throwable {

//driver.findElement(By.cssSelector(input)).sendKeys(value1);
		driver.findElement(By.xpath(input)).sendKeys(value1);
		System.out.println("User enter the text to field :    " + input + "with the value: " + value1);
	}

	@And("^User enters ObjectTextByID \"(.*?)\" with the value \"(.*?)\"$")
	public void user_enters_objecttextbyid(String input, String value1) throws Throwable {

		driver.findElement(By.id(input)).sendKeys(value1);
		System.out.println("User enters ObjectTextByIDd :    " + input + "with the value: " + value1);
	}

	@And("^Users close the current tab$")
	public void Users_close_the_current_tab() throws Throwable {
		driver.close();
		driver.switchTo().window("");
//https://stackoverflow.com/questions/25871042/webdriver-getwindowhandle-method
//https://crossbrowsertesting.com/blog/test-automation/how-to-handle-multiple-tabs-in-selenium-webdriver/
		System.out.println("Users Move to the base tab");
	}

	@And("^User enters a random number to field by css \"(.*?)\" With random string$")
	public void User_enters_random_number_to_field_by_css_with_the_value(String input) throws Throwable {

		String uuid = UUID.randomUUID().toString().substring(1, 5);
		String uuid2 = UUID.randomUUID().toString().substring(1, 5);
		String uuid3 = uuid + " " + uuid2;

		driver.findElement(By.cssSelector(input)).sendKeys(uuid3);
		System.out.println("User sendKeys:   " + uuid3);
	}

	@And("^User enters a random number to field by xpath \"(.*?)\"$")
	public void User_enters_random_number_to_field_by_xpath(String input) throws Throwable {

		Random random = new Random();
		int index = random.nextInt(10000);
		String Randnumber = Integer.toString(index);

		driver.findElement(By.xpath(input)).sendKeys(Randnumber);
		System.out.println("User sendKeys:   " + Randnumber);
	}

	@And("^Users Click the object on page by cssselector \"(.*?)\"$")
	public void users_click_the_object_on_page_by_cssselector(String input) throws Throwable {

		driver.findElement(By.cssSelector(input)).click();
		Thread.sleep(1000);

		System.out.println("Users Click on object on page by cssselector:  " + input + " ");
	}

	@And("^Users press the enter key on \"(.*?)\"$")
	public void users_press_the_enter_key(String input) throws Throwable {

		driver.findElement(By.cssSelector(input)).sendKeys(Keys.RETURN);
		Thread.sleep(1000);

		System.out.println("Users press the enter key on:  " + input + " ");
	}

	@And("^User navigate Back to home tab$")
	public void user_navigate_back_to_home_tab() throws Throwable {

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		Thread.sleep(1000);
		System.out.println("User navigate Back to home tab");

	}

	@And("^User opens new blank tab$")
	public void user_opens_new_blank_tab() throws Throwable {

		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}

	@And("^User opens new tab \"(.*?)\"$")
	public void user_opens_new_tab(int input) throws Throwable {

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(input));
		Thread.sleep(1000);
		System.out.println("User opens new tab");

	}

	@And("^User press the scroll down$")
	public void user_press_the_scroll_down() throws Throwable {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

		WebElement Element = driver.findElement(By.linkText("Linux"));
		Thread.sleep(1000);
		System.out.println("User press the scroll down button");

	}

	@And("^User Scroll page and press By linkText \"(.*?)\"$")
	public void Scroll_page_and_press_By_linkText(String input) throws Throwable {

		((FindsByAndroidUIAutomator<?>) driver)
				.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(input));");

		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		// WebElement Element = driver.findElement(By.linkText(input));
		// This will scroll the page till the element is found
		// js.executeScript("arguments[0].scrollIntoView();", Element);
	}

	@And("^User press the mouse doubleClick by xpath \"(.*?)\"$")
	public void user_press_the_mousedoubleClick_by_xpath(String input) throws Throwable {

		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.xpath(input));
		actions.doubleClick(elementLocator).perform();
	}

	@And("^User press mouse right click on the item by css \"(.*?)\" and the item on the menue \"(.*?)\"$")
	public void User_press_right_click_mouse_by_css(String input, String item) throws Throwable {

		Actions action = new Actions(driver);

		WebElement link = driver.findElement(By.cssSelector(input));
		action.contextClick(link).perform();
		// Click on Edit link on the displayed menu options
		WebElement element = driver.findElement(By.cssSelector(item));
		element.click();
	}

	@And("^User press mouse right click on the item by xpath \"(.*?)\" and the item on the menue \"(.*?)\"$")
	public void User_press_right_click_mouse_by_xpath(String input, String item) throws Throwable {

		Actions action = new Actions(driver);
		WebElement link = driver.findElement(By.xpath(input));
		action.contextClick(link).perform();
		// Click on Edit link on the displayed menu options
		WebElement element = driver.findElement(By.xpath(item));
		element.click();

	}

	@And("^User press the PageDown$")
	public void user_press_the_pagedown() throws Throwable {

		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.PAGE_DOWN).build().perform();
		builder.release().perform();
	}

	@And("^User press the keyDown$")
	public void user_press_key_down() throws Throwable {

		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.DOWN).build().perform();
		builder.release().perform();
	}

	@And("^User press the browser maximizer$")
	public void user_press_the_browser_maximizer() throws Throwable {

		driver.manage().window().maximize();
	}

	@And("^User Report \"(.*?)\"$")
	public void User_Report(String input) throws Throwable {

		System.out.println("User Report:    " + input);
	}

	@And("^user enter value to text field by xpath \"(.*?)\" to field \"(.*?)\"$")
	public void userEntervalueFromTextbyXpath(String value, String input) throws Throwable {

		driver.findElement(By.xpath(input)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(input)).sendKeys(value);
		Thread.sleep(1000);

	}

	//// InnerText

	@Then("^I validate the input Text by CssSelector InnerText \"(.*?)\" With the Value \"(.*?)\"$")
	public void i_validate_the_input_by_CssSelector_innertext_With_the_value(String input, String value1)
			throws Throwable {

		WebElement element = driver.findElement(By.cssSelector(input));
		String val = element.getAttribute("innerText");

		System.out.println("Validating A value on the page:    " + val + " ");

		if (!val.equals(value1)) {
			throw new IllegalStateException("The validation has failed");
		}

	}

	@Then("^I validate by xpath isDisplayed the Object to search \"(.*?)\"$")
	public void i_validate_by_xpath_isDisplayed_the_Value_to_search(String value1) throws Throwable {

		WebElement element = driver.findElement(By.xpath(value1));

		if (element.isDisplayed()) {
			System.out.println("Element found using text");
		} else {
			System.out.println("Element not found");
			throw new IllegalStateException("The validation has failed");
		}

	}

//  get Atribute is good for this condition: "   value="gadi+4@ddd.co">   "
//  input aria-invalid="false" autocomplete="off" class="jss623 jss618 jss791" disabled="" name="email" type="text" data-test-id="users-settings-username" value="gadi+4@ssss.co">

	@Then("^I validate the input Text by ID \"(.*?)\" With the Value \"(.*?)\"$")
	public void i_validate_the_input_by_id_on_the_page(String input, String value1) throws Throwable {

		String heading_text = driver.findElement(By.id(input)).getAttribute("value");
		System.out.println("Validating A value on the page:    " + heading_text + " ");

		if (!heading_text.equals(value1)) {
			throw new IllegalStateException("The validation has failed");
		}

	}

	@Then("^I validate the input Text by CSS \"(.*?)\" get Atribute \"(.*?)\" With the Value \"(.*?)\"$")
	public void i_validate_the_input_by_CssSelector_get_Atribute_with_the_value(String input, String value1,
			String value2) throws Throwable {

		String heading_text = driver.findElement(By.cssSelector(input)).getAttribute(value1);
		System.out.println("Validating A value on the page:    " + heading_text + " ");

		if (!heading_text.equals(value2)) {
			throw new IllegalStateException("The validation has failed");
		}
	}

//getText is good for this condition: "
//data-test-id="account-setting-plan-name">essential</h3>"
//<h3 class="jss151 jss158 jss509"
//data-test-id="account-setting-plan-name">essential</h3>

	@Then("^I validate the input Text by CssSelector getText \"(.*?)\" With the Value \"(.*?)\"$")
	public void i_validate_the_input_by_CssSelector_getText_With_the_value(String input, String value1)
			throws Throwable {

		String heading_text = driver.findElement(By.cssSelector(input)).getText();
		System.out.println("Validating A value on the page:    " + heading_text + " ");

		if (!heading_text.equals(value1)) {
			throw new IllegalStateException("The validation has failed");
		}

	}

	@Then("^I validate the input Text by className \"(.*?)\" With the Value \"(.*?)\"$")
	public void i_validate_the_input_by_className_on_the_page(String input, String value1) throws Throwable {

		String heading_text = driver.findElement(By.className(input)).getAttribute("title");
		// String heading_text = driver.findElement(By.cssSelector(input)).getText();
		System.out.println("Validating A value on the page:    " + heading_text + " ");

		if (!heading_text.equals(value1)) {
			throw new IllegalStateException("The validation has failed");
		}
	}

	@Then("^I validate the input by cssse \"(.*?)\"$")
	public void i_validate_the_input_by_cssse(String input) throws Throwable {

		int count = driver.findElements(By.cssSelector(input)).size();
		if (count == 1) {
			System.out.println("Element found using text");

		} else {
			System.out.println("Element not found");
			throw new IllegalStateException("The validation has failed");
		}

	}

	@Then("^I validate the text by xpath \"(.*?)\" that the Value is \"(.*?)\"$")
	public void i_validate_the_text_by_xpath_and_the_value_is(String input, String value1) throws Throwable {

		String heading_text = driver.findElement(By.xpath(input)).getText();
		// String heading_text =
		// driver.findElement(By.xpath(input)).getAttribute("value");
		System.out.println("Validating A value on the page:    " + heading_text + " ");

		if (!heading_text.equals(value1)) {
			throw new IllegalStateException("The validation has failed");
		}
	}

	@Then("^Android Validate the text exists on page \"(.*?)\"$")
	public void android_validate_the_text_exists_on_page(String input) throws Throwable {

		try {

			((AndroidDriver<?>) driver).findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
							+ input + "\").instance(0))");

			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Element not found using text");
			e.printStackTrace();
		}
	}

	@Then("^The user see the Object bycss \"(.*?)\"$")
	public void the_user_see_the_object_on_page_bycsselector(String input) throws Throwable {

//	WebDriverWait wait = new WebDriverWait(driver, 60);

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(input)));
		Thread.sleep(2000);
//System.out.println("The user see the text: "+input+" - on page ");
	}

	@Then("^The user press accept on Allert message$")
	public void the_user_press_accept_on_Allert_message() throws Throwable {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.alertIsPresent());
			boolean hasAlert = ExpectedConditions.alertIsPresent().apply(driver) != null;
			if (hasAlert) {
				driver.switchTo().alert().accept();
			}
		} catch (NoAlertPresentException ex) {
			System.out.println("No Alert Present ");
		}

	}

	@Then("^Validate the childText area is \"(.*?)\"$")
	public void validate_message_text(String text) throws Throwable {
		List<WebElement> elements = driver.findElements(By.xpath("//*[@class='divChildText']"));
		if (elements.size() < 1) {
			elements = driver.findElements(By.xpath("//*[@class='divChildText pointer']"));
		}
		WebElement[] arraylements = new WebElement[elements.size()];
		elements.toArray(arraylements);
		boolean found = false;
		int arraylength = arraylements.length;
		for (int i = 0; i < arraylength; i++) {
			if (arraylements[i].getText().contains(text)) {
				if (arraylements[arraylength - 1].getText().contains(text)) {
					System.out.println("Found it within the last message!");
					found = true;
				}
				System.out.println("Found the text : " + text + "in message number : " + Integer.toString(i));
				found = true;
			}
		}
		if (found == false) {
			throw new Exception("Didnt find the text : " + text);
		}
		System.out.println("Last message received is : " + arraylements[arraylength - 1].getText());
	}

	@And("User press message \"(.*?)\" within mail$")
	public void user_press_message_within_mail(String message) throws Throwable {
		List<WebElement> elements = driver.findElements(By.xpath("//*[@class='divChildText']"));
		if (elements.size() < 1) {
			elements = driver.findElements(By.xpath("//*[@class='divChildText pointer']"));
		}
		WebElement[] arraylements = new WebElement[elements.size()];
		elements.toArray(arraylements);
		int arraylength = arraylements.length;
		for (int i = 0; i < arraylength; i++) {
			String test = arraylements[i].getText();
			if (arraylements[i].getText().contains(message)) {
				arraylements[i].click();
			}
		}
	}

	@And("^Attaching a file from my computer to upload \"(.*?)\"$")
	public void Attaching_a_file_from_my_computer_to_upload(String input) throws Throwable {

		// Actions builder = new Actions(driver);
		Robot rb = new Robot();
		// copying File path to Clipboard
		StringSelection str = new StringSelection(input);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		System.out.println("After save file ");

		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		// release Contol+V for pasting
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		// for pressing and releasing Enter
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		// driver.switchTo().alert().sendKeys("Text");
	}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXran
	@And("^Answer survey$")
	public void Answer_survey() throws Throwable {
		WebElement element = driver
				.findElement(By.xpath("(//*[namespace-uri()='http://www.w3.org/1999/xhtml'][@src='/innerDemo'])"));
		driver.switchTo().frame(element);

		// WebElement element2 = driver.findElement(By.cssSelector("body"));
		// WebElement element3 = element2.findElement(By.id("cb_divChatIframe"));
		WebElement element4 = driver.findElement(By.name("by_iframe_chat"));
		driver.switchTo().frame(element4);
		// WebElement element5 = driver.findElement(By.id("divSurveyWrapper"));

		WebElement element6 = driver.findElement(By.xpath("//*[@id=\"divSurveyWrapper\"]/div/button[1]/a"));
		element6.click();

	}

	@And("^Answer survey1$")
	public void Answer_survey1() throws Throwable {
		WebElement element = driver
				.findElement(By.xpath("(//*[namespace-uri()='http://www.w3.org/1999/xhtml'][@src='/innerDemo'])"));
		driver.switchTo().frame(element);

		// WebElement element2 = driver.findElement(By.cssSelector("body"));
		// WebElement element3 = element2.findElement(By.id("cb_divChatIframe"));
		WebElement element4 = driver.findElement(By.name("by_iframe_chat"));
		driver.switchTo().frame(element4);
		// WebElement element5 = driver.findElement(By.id("divSurveyWrapper"));

		WebElement element6 = driver.findElement(By.xpath(
				"//*[@class='BY_input theme_main_border_secondary_color BY_select_input optionDisplay invalid theme_danger_border']"));
		element6.click();

	}

	@And("^Switch to tab \"(.*?)\" and open url \"(.*?)\"$")
	public void switch_to_tab(String tabnum, String url) throws Throwable {
		int num = Integer.parseInt(tabnum);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(num));
		if (url.contains("http")) {
			driver.get(url);
		} else {
			System.out.println("nourl");
		}
	}

	@And("^set option \"(.*?)\" for xpath \"(.*?)\"$")
	public void select_option(String selction, String xpath) throws Throwable {
		Select dropdown = new Select(driver.findElement(By.xpath(xpath)));
		dropdown.selectByVisibleText(selction);
	}

	@And("^pick option \"(.*?)\" for xpath \"(.*?)\"$")
	public void pick_option(String selecton, String xpath) throws Throwable {
		List<WebElement> options = driver.findElements(By.xpath(xpath));
		for (WebElement option : options) {
			if (option.getText().contains(selecton)) {
				option.click();
				break;
			}
		}

	}

	@And("Select saved text from options by xpath \"(.*?)\"$")
	public void select_saved_text_from_options_by_xpath(String xpath) throws Throwable {
		List<WebElement> options = driver.findElements(By.xpath(xpath));
		for (WebElement option : options) {
			if (option.getText().contains(User_name_contact)) {
				option.click();
				break;
			}
		}
	}

	@And("^Choose file to upload \"(.*?)\" to xpath \"(.*?)\"$")
	public void choose_file_to_upload_to_xpath(String filepath, String xpath) throws Throwable {
		try {
			WebElement chooseFile = driver.findElement(By.xpath(xpath));
			chooseFile.sendKeys(filepath);
		} catch (Exception a) {
			driver.switchTo().activeElement().sendKeys(filepath);
		}
	}

	@And("^Press object number \"(.*?)\" for xpath \"(.*?)\"$")
	public void press_object_number_for_xpath(String number, String xpath) throws Throwable {
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		int object_number = Integer.parseInt(number);
		String test = "(" + xpath + ")" + "[" + object_number + "]";
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		WebElement[] arraylements = new WebElement[elements.size()];
		elements.toArray(arraylements);
		arraylements[object_number].click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@And("^Copy file name \"(.*?)\"$")
	public void copy_file(String file) throws Throwable {
		StringSelection org_folder = new StringSelection("C:/Users/ran.e/Desktop/photos");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(org_folder, null);
		StringSelection file_to_choose = new StringSelection(file);
		Robot robot = new Robot();
		int i = 0;
		while (i < 5) {
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			i++;
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file_to_choose, null);
		Thread.sleep(5000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(5000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		int y = 0;
		Thread.sleep(5000);
		while (y < 2) {
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			y++;
		}
		Thread.sleep(5000);
		robot.keyPress(KeyEvent.VK_RIGHT);
		robot.keyRelease(KeyEvent.VK_RIGHT);
		Thread.sleep(5000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
	}

	@And("^Copy folder \"(.*?)\"$")
	public void copy_folder(String folder) throws Throwable {
		StringSelection org_folder = new StringSelection(folder);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(org_folder, null);
		Robot robot = new Robot();
		int i = 0;
		while (i < 5) {
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			i++;
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		int y = 0;
		Thread.sleep(5000);
		while (y < 4) {
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			y++;
		}
		Thread.sleep(5000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_A);
		Thread.sleep(5000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
	}

	@And("^Press existing user by name \"(.*?)\"$")
	public void press_existing_user_by_name(String user) throws Throwable {
		WebElement chooseFile = driver.findElement(By.xpath("//*[@class='theme_main_border_color settings_table']"));
		List<WebElement> tableRows = chooseFile.findElements(By.tagName("tr"));
		int index = tableRows.size();
		String[] stringarr = new String[index];
		WebElement[] elements = new WebElement[index];
		tableRows.toArray(elements);

		for (int i = 0; i < Array.getLength(elements); i++) {
			String text = tableRows.get(i).getText();
			stringarr[i] = text;
			System.out.println("This is stringarr: " + stringarr[i]);
			if (stringarr[i].contains(user) && stringarr[i].contains("Active")) {
				System.out.println("GOOD" + i);
				elements[i].click();
				break;
			}
		}

	}

	@And("^Search element within \"(.*?)\" xpathes \"((?:[^,]*)(?:,\\s?[^,]*)*)\"$")
	public void SearchElementWithinXpathes(String how_many_elements, List<String> xpathes) throws Throwable {
		int elements_counter = Integer.parseInt(how_many_elements);
		int counter = 0;
		int i = 0;
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		System.out.println("List : " + xpathes);
		String[] b_4_change = new String[elements_counter];
		b_4_change = xpathes.toArray(b_4_change);
		String[] after_change = new String[elements_counter];
		while (counter < elements_counter) {
			// after_change[counter] = b_4_change[i] + b_4_change[i+1];

			try {
				WebElement element = driver.findElement(By.xpath(b_4_change[counter]));
				element.click();
				break;
			} catch (Exception ex) {
				System.out.println("Level up: " + counter);
				counter++;
				i = i + 2;
			}
		}
	}

	@And("^User Send keyboard key Arrow Down for \"(.*?)\" times$")
	public void user_send_keyboard_key_ARROW_DOWN_xtimes(String input) throws Throwable {
		int i = 0;
		int repeat = Integer.parseInt(input);
		Actions builder = new Actions(driver);
		while (i < repeat) {
			builder.sendKeys(Keys.ARROW_DOWN).build().perform();
			builder.release().perform();
			i++;
		}
	}

	@And("^User Send keyboard key Arrow Up for \"(.*?)\" times$")
	public void user_send_keyboard_key_UP_DOWN_xtimes(String input) throws Throwable {
		int i = 0;
		int repeat = Integer.parseInt(input);
		Actions builder = new Actions(driver);
		while (i < repeat) {
			builder.sendKeys(Keys.ARROW_UP).build().perform();
			builder.release().perform();
			i++;
		}
	}

	@And("^User Send keyboard Tab for \"(.*?)\" times$")
	public void user_send_keyboard_key_TAB_xtimes(String input) throws Throwable {
		int i = 0;
		int repeat = Integer.parseInt(input);
		Actions builder = new Actions(driver);
		while (i < repeat) {
			builder.sendKeys(Keys.TAB).build().perform();
			builder.release().perform();
			i++;
		}
	}

	@And("^Check module and click conversation number \"(.*?)\" for \"(.*?)\"$")
	public void check_module_and_click_conv(String number, String module) throws Throwable {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		int numbertoint = Integer.parseInt(number);
		numbertoint = numbertoint - 1;
		try {
			Boolean isPresent_mail = driver
					.findElement(
							By.xpath("//*[@class='divModuleImage theme_panel module_image_medium'][@title='Mail']"))
					.isDisplayed();
			if (isPresent_mail = true) {
				if (module.equals("mail") || module.equals("Mail")) {
					List<WebElement> mailement = driver.findElements(
							By.xpath("//*[@class='divModuleImage theme_panel module_image_medium'][@title='Mail']"));
					WebElement[] test = new WebElement[0];
					test = mailement.toArray(new WebElement[0]);
					test[numbertoint].click();
				}
			}
		} catch (Exception a) {
			System.out.println("Didnt find mail modules");
		}
		try {
			Boolean isPresent_chat = driver
					.findElement(
							By.xpath("//*[@class='divModuleImage theme_panel module_image_medium'][@title='Chat']"))
					.isDisplayed();
			if (isPresent_chat = true) {
				if (module.equals("Chat") || module.equals("chat")) {
					List<WebElement> mailement = driver.findElements(
							By.xpath("//*[@class='divModuleImage theme_panel module_image_medium'][@title='Chat']"));
					WebElement[] test = new WebElement[0];
					test = mailement.toArray(new WebElement[0]);
					test[numbertoint].click();
				}
			}
		} catch (Exception b) {
			System.out.println("Didnt find chat");
		}
		try {
			Boolean isPresent_forms = driver
					.findElement(
							By.xpath("//*[@class='divModuleImage theme_panel module_image_medium'][@title='Forms']"))
					.isDisplayed();
			if (isPresent_forms = true) {
				if (module.equals("Forms") || module.equals("forms")) {
					List<WebElement> mailement = driver.findElements(
							By.xpath("//*[@class='divModuleImage theme_panel module_image_medium'][@title='Forms']"));
					WebElement[] test = new WebElement[0];
					test = mailement.toArray(new WebElement[0]);
					test[numbertoint].click();
				}
			}
		} catch (Exception c) {
			System.out.println("Didnt find forms");
		}
		try {
			Boolean isPresent_cs = driver
					.findElement(
							By.xpath("//*[@class='divModuleImage theme_panel module_image_medium'][@title='Forms']"))
					.isDisplayed();
			if (isPresent_cs = true) {
				if (module.equals("Customer Service") || module.equals("customer service")) {
					List<WebElement> mailement = driver.findElements(By.xpath(
							"//*[@class='divModuleImage theme_panel module_image_medium'][@title='Customer Service App']"));
					WebElement[] test = new WebElement[0];
					test = mailement.toArray(new WebElement[0]);
					test[numbertoint].click();
				}
			}
		} catch (Exception d) {
			System.out.println("Didnt find Customer Service App");
		}
		try {
			Boolean isPresent_whatsapp = driver
					.findElement(
							By.xpath("//*[@class='divModuleImage theme_panel module_image_medium'][@title='WhatsApp']"))
					.isDisplayed();
			if (isPresent_whatsapp = true) {
				if (module.equals("Whatsapp") || module.equals("whatsapp")) {
					List<WebElement> mailement = driver.findElements(By
							.xpath("//*[@class='divModuleImage theme_panel module_image_medium'][@title='WhatsApp']"));
					WebElement[] test = new WebElement[0];
					test = mailement.toArray(new WebElement[0]);
					test[numbertoint].click();
				}
			}
		} catch (Exception e) {
			System.out.println("Didnt find Whatsapp");
		}
	}

	@And("^Choose intent \"(.*?)\" for env \"(.*?)\"$")
	public void choose_intent(String tosearch, String env) throws Throwable {
		int index = 1;
		WebElement baseTable = driver.findElement(By.id("tblAllIntents"));
		List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
		int tablelength = tableRows.size();
		for (WebElement each : tableRows) {
			String x = tableRows.get(index).getText();
			System.out.println("this is x: " + x);
			index++;
			Boolean isFound = x.contains(tosearch);
			if (isFound == true) {
				if (env.contains("Qaqa")) {
					index = index * 2 + 3;
					System.out.println("this is index: " + index);
					break;
				}
				if (env.contains("Wispp")) {
					index = index * 2 + 1;
					System.out.println("this is index: " + index);
					break;
				}
			}
		}
		List<WebElement> clicking_this = driver.findElements(By.xpath("//*[@class='pointer']"));
		WebElement[] elements_array = new WebElement[0];
		elements_array = clicking_this.toArray(new WebElement[0]);
		String y = elements_array[index].getText();
		if (y.equals("Delete")) {
			index = index + 1;
		}
		elements_array[index].click();
	}

	@And("^Copy text from xpath \"(.*?)\"$")
	public void copy_text_from_xpath(String input) throws Throwable {
		WebElement texttocopy = driver.findElement(By.xpath(input));
		Actions act = new Actions(driver);
		act.moveToElement(texttocopy).doubleClick().click().build().perform();
		texttocopy.sendKeys(Keys.LEFT_CONTROL + "c");
		act.release().perform();
	}

	@And("^Paste text to xpath \"(.*?)\"$")
	public void paste_text_to_xpath(String input) throws Throwable {
		WebElement texttocopy = driver.findElement(By.xpath(input));
		Actions act = new Actions(driver);
		act.moveToElement(texttocopy).click().build().perform();
		texttocopy.sendKeys(Keys.LEFT_CONTROL + "v");
		act.release().perform();
	}

	@And("^User enter random mailinator to field$")
	public void user_enter_random_mailinator() throws Throwable {

		Random random = new Random();
		int index = random.nextInt(10000);
		String Randnumber = Integer.toString(index);
		Randnumber = "rndmail" + Randnumber + "@mailinator.com";
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String fullstring = Randnumber.toString();
		StringSelection str = new StringSelection(fullstring);
		clipboard.setContents(str, null);
		Thread.sleep(2000);
		System.out.println("User enter random email : " + Randnumber);

	}

	@And("^Key to hold \"(.*?)\" and key to press \"(.*?)\"$")
	public void user_press_the_keys(String key1, String key2) throws Throwable {
		Actions actionProvider = new Actions(driver);
		if (key1.contains("ctrl")) {
			key1 = "Keys.CONTROL";
		}
		Action keydown = actionProvider.keyDown(key1).sendKeys(key2).keyUp(key1).build();
		keydown.perform();
	}

	@And("^New tab$")
	public void temp_mail() throws Throwable {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.open()");
	}

	@And("^switch to iframe num \"(.*?)\"$")
	public void switch_to_iframe(int value) throws Throwable {
		driver.switchTo().frame(value);
	}

	@And("^Generate random name by css \"(.*?)\"$")
	public void generate_random_name_by_css(String input) throws Throwable {
		String AlphaNumbericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		int low = 2;
		int high = 10;
		Random random = new Random();
		int n = random.nextInt(high - low) + low;
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			sb.append(AlphaNumbericString.charAt(i));
		}
		WebElement element = driver.findElement(By.cssSelector(input));
		element.sendKeys(sb);
	}

	@And("^Paste text to css \"(.*?)\"$")
	public void paste_text_to_css(String input) throws Throwable {
		WebElement texttocopy = driver.findElement(By.cssSelector(input));
		Actions act = new Actions(driver);
		act.moveToElement(texttocopy).click().build().perform();
		texttocopy.sendKeys(Keys.LEFT_CONTROL + "v");
		act.release().perform();
	}

	@And("^Robot press esc$")
	public void robot_press_esc() throws Throwable {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}

	@And("^Generate random name by xpath \"(.*?)\"$")
	public void generate_random_name_by_xpath(String input) throws Throwable {
		String AlphaNumbericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		int low = 2;
		int high = 10;
		Random random = new Random();
		int n = random.nextInt(high - low) + low;
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			sb.append(AlphaNumbericString.charAt(i));
		}
		WebElement element = driver.findElement(By.xpath(input));
		element.sendKeys(sb);
	}

	@And("^User create new tab$")
	public void user_create_new_tab() throws Throwable {
		WebElement body = driver.findElement(By.tagName("body"));
		body.sendKeys("ctrl", "t");
	}

	@And("Close driver$")
	public void close_driver() throws Throwable {
		int defaulttab = 0;
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("No. of tabs: " + tabs.size());
		while (defaulttab < tabs.size()) {
			ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
			if (newtabs.size() > 1) {
				System.out.println("No. of tabs within loop: " + newtabs.size());
				driver.switchTo().window(tabs.get(newtabs.size() - 1));
				driver.close();
			} else {
				driver.switchTo().window(tabs.get(newtabs.size() - 1));
				driver.get("https://qaqa.commbox.io/");
				break;
			}

		}
	}

//	 DAN PROJECT DAN PROJECT DAN PROJECT DAN PROJECT DAN PROJECT DAN PROJECT DAN PROJECT DAN PROJECT

	@And("^Set driver wait time to \"(.*?)\"$")
	public void choose_by_link_from_list_of_elements_by_xpath(int time) throws Throwable {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	@And("^open excel file from config \"(.*?)\"$")

	public void open_excel_file_from_config(String input2) throws Throwable {

		// Open config file and take the path to Excel file

		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		InputStream input = loader.getResourceAsStream("config/Configuration.properties");

		prop.load(input);

		// System.out.println(prop.getProperty(input2));

		ExcelFilepath = prop.getProperty(input2);

		input.close();

	}

	@And("^Get value from Row num \"(.*?)\" and Cell num \"(.*?)\"$")

	public void get_value_from_Row_And_Cell(int input1, int input2) throws Throwable {

		// Create an object of File class to open xlsx file

		File file = new File(ExcelFilepath);

		// Create an object of FileInputStream class to read excel file

		// FileInputStream inputStream = new FileInputStream(file);

		XSSFWorkbook workbook = new XSSFWorkbook(file);

		XSSFSheet sheet = workbook.getSheetAt(0);

		Row row = sheet.getRow(input1);

		Cell cell = row.getCell(input2);

		// System.out.println(cell);

		// System.out.println(sheet.getRow(input1).getCell(input2));

		String cellval = cell.getStringCellValue();

		System.out.println(cellval);

		// System.out.print(row.getCell(input2).getStringCellValue());

		workbook.close();

	}

	@And("^Get value in loop from Row num \"(.*?)\" and Cell num \"(.*?)\"$")

	public void get_value_in_loop_from_Row_And_Cell(int input1, int input2) throws Throwable {

		// Create an object of File class to open xlsx file

		File file = new File(ExcelFilepath);

		XSSFWorkbook workbook = new XSSFWorkbook(file);

		XSSFSheet sheet = workbook.getSheetAt(0);

		DataFormatter formatter = new DataFormatter();

		PropertiesConfiguration config = new PropertiesConfiguration(

				"../DanAutomation/src/test/resources/config/Configuration.properties");

		String Excel_Header[] = { "hotel_id", "bookingNo", "masterId", "last_name", "first_name", "bookingFrom",
				"bookingTo", "adult_count", "child_count", "infant_count", "room_type", "roomNo", "nationality_code",
				"plan_code", "total_price", "currency", "c_folio_number", "club_membership_no" };

		int i = input1;

		Row row = sheet.getRow(i);

		for (int j = 0; j < input2; j++) {

			String Cell_Value = formatter.formatCellValue(row.getCell(j));

			// System.out.print(sheet.getRow(i).getCell(j).getStringCellValue() + ",");

			// String CellValue = formatter.formatCellValue(); //Returns the formatted value
			// of a cell as a String regardless of the cell type.

			System.out.print(Cell_Value + " ,  ");

			config.setProperty(Excel_Header[j], Cell_Value);

			config.save();

		}

		workbook.close();

	}

	// ########### Use this for my own function ############
	@And("^Update config file for key \"(.*?)\" with value \"(.*?)\"$")

	public void update_config_value(String Key, String Value1) throws Throwable {

		PropertiesConfiguration config = new PropertiesConfiguration(
				"../DanAutomation/src/test/resources/config/Configuration.properties");

		// setProperty(): Sets a new value for the specified property.

		// This implementation checks if the auto save mode is enabled and saves the
		// configuration if necessary.

		config.setProperty(Key, Value1);

		config.save();
//########### Use this for my own function ############
	}

	@And("^Using properties \"(.*?)\" With the Value from ConfigFile \"(.*?)\"$")
	public void Using_properties_with_the_value_from_ConfigFile(String input1, String value) throws Throwable {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream input = loader.getResourceAsStream("config/Configuration.properties");
//			prop.load(input);

		String varibletofind1 = prop.getProperty(value);
		Thread.sleep(2000);
//			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//			driver.findElement(MobileBy.xpath(input1)).sendKeys(varibletofind1);

		System.out.println("User enter the text to field:    " + input1 + "    " + varibletofind1);
		input.close();
	}

	@When("^I SignUp new user for email \"(.*?)\"$")

	public void I_SignUp_new_user_for_email(String UserEmail) throws Throwable {

		String API_URL = BASE_URL + "/users";

		String UserPassword = "123456";
		String UserFirstName = "tommybb";
		String UserLastName = "bb";
		String UserNumber = "0505000000";
		String validRequest = "{\n    \"user_account\": {\n        \"email\": \"" + UserEmail
				+ "\",\n        \"password\": \"" + UserPassword + "\",\n        \"password_confirmation\": \""
				+ UserPassword + "\"\n    },\n    \"profile\": {\n        \"first_name\": \"" + UserFirstName
				+ "\",\n        \"last_name\": \"" + UserLastName + "\",\n        \"mobile_number\": \"" + UserNumber
				+ "\",\n        \"mobile_acode\": \"+972\"\n    }\n}";

		RestAssured.baseURI = API_URL;

		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		request.header("X-DEVICE-ID", "d43fb20f94706af793613cac923d4d85");

		response = request.body(validRequest).post(API_URL);

		System.out.println(response.statusCode());
		System.out.println(response.asPrettyString());

	}

	@Given("^I SignUp new user with random email$")

	public void I_SignUp_new_user_with_random_email() throws Throwable {

		String AlphaNumbericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		int low = 2;
		int high = 10;
		Random random = new Random();
		int n = random.nextInt(high - low) + low;
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {
			sb.append(AlphaNumbericString.charAt(i));
		}

		int index = random.nextInt(10000);
		String Randnumber = Integer.toString(index);
		String str = Randnumber.toString();
		Thread.sleep(2000);

		String API_URL = BASE_URL + "/users";

		StringBuilder UserFirstName = sb;
		String UserLastName = str;
		String UserEmail = sb + UserLastName + "@mailinator.com";
		String UserPassword = "123456";
		String UserNumber = "0505000000";
		String validRequest = "{\n    \"user_account\": {\n        \"email\": \"" + UserEmail
				+ "\",\n        \"password\": \"" + UserPassword + "\",\n        \"password_confirmation\": \""
				+ UserPassword + "\"\n    },\n    \"profile\": {\n        \"first_name\": \"" + UserFirstName
				+ "\",\n        \"last_name\": \"" + UserLastName + "\",\n        \"mobile_number\": \"" + UserNumber
				+ "\",\n        \"mobile_acode\": \"+972\"\n    }\n}";

//		System.out.println(UserLastName);
//		System.out.println(UserEmail);
//		System.out.println(UserFirstName);
		RestAssured.baseURI = API_URL;

		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		request.header("X-DEVICE-ID", "d43fb20f94706af793613cac923d4d85");

		response = request.body(validRequest).post(API_URL);

		System.out.println(response.statusCode());
//		System.out.println(response.asPrettyString());

	}

	@And("^I go to mailinator and get OTP for email \"(.*?)\"$")

	public void I_go_to_mailinator_and_get_OTP(String mailAddress) throws Throwable {

		driver.get(mailAddress);

		Thread.sleep(2000);

		driver.findElement(By.cssSelector("tbody>tr:nth-child(1)[ng-repeat=\"email in emails\"]")).click();

		Thread.sleep(2000);

		driver.switchTo().frame(driver.findElement(By.id("texthtml_msg_body")));

		String heading_text = (driver.findElement(By.cssSelector("body,iframe>#document>html>body"))
				.getAttribute("innerText"));

		otp = CharMatcher.inRange('0', '9').retainFrom(heading_text);

		System.out.println("Otp is: " + otp);

	}

	@Then("^I send OTP for email \"(.*?)\"$")

	public void I_send_OTP_for_email(String UserEmail) throws Throwable {

		String API_URL = BASE_URL + "/users/sendOtp";

		String validRequest = "{\n    \"email\": \"" + UserEmail + "\",\n    \"sendToEmail\": true\n}";

		RestAssured.baseURI = API_URL;

		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		request.header("X-DEVICE-ID", "d43fb20f94706af793613cac923d4d85");

		response = request.body(validRequest).post(API_URL);
		String success_msg = response.jsonPath().getString("Result.msg");
		error_msg = response.jsonPath().getString("Errors[0]");
		System.out.println(response.statusCode());
//		System.out.println(response.asPrettyString());

		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println(success_msg);

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		} else {
			try {
				System.out.println("Error: " + error_msg);
				assert false : "Error: " + error_msg;

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}

	@Then("^I check OTP for Token with email \"(.*?)\"$")

	public void I_check_OTP_for_Token_with_email(String UserEmail) throws Throwable {

		String API_URL = BASE_URL + "/users/checkOtp";

		String validRequest = "{\n    \"email\": \"" + UserEmail + "\",\n     \"otp\": \"" + otp + "\"\n}";

		RestAssured.baseURI = API_URL;

		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		request.header("X-DEVICE-ID", "d43fb20f94706af793613cac923d4d85");

		response = request.body(validRequest).post(API_URL);

		JWT_Token = response.jsonPath().getString("Result.JWT_Token");
		reset_token = response.jsonPath().getString("Result.reset_token");
		invitation_code = response.jsonPath().getString("Result.invitation_code");
		user_id = response.jsonPath().getString("Result.user_id");
		error_msg = response.jsonPath().getString("Errors[0]");

		String Response_Name[] = { "JWT_Token", "reset_token", "invitation_code", "user_id" };
		String Response_Value[] = { JWT_Token, reset_token, invitation_code, user_id };
//		String local_user_id = response.jsonPath().getString("Result.user_id");

//		user_id = Integer.parseInt(local_user_id);

		PropertiesConfiguration config = new PropertiesConfiguration(
				"../DanAutomation/src/test/resources/config/Configuration.properties");

		for (int j = 0; j < Response_Name.length; j++) {

			config.setProperty(Response_Name[j], Response_Value[j]);
			Thread.sleep(1000);

		}
		config.save();
		Thread.sleep(5000);

//		System.out.println(JWT_Token);
		System.out.println(response.statusCode());
		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success");

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		} else {
			try {
				System.out.println("Error: " + error_msg);
				assert false : "Error: " + error_msg;

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}

	@Then("^I Sign out$")

	public void I_Sign_out() throws Throwable {

//		ClassLoader loader = Thread.currentThread().getContextClassLoader();
//		InputStream input = loader.getResourceAsStream("config/Configuration.properties");
//			prop.load(input);
		Thread.sleep(5000);
//		String varibletofind1 = prop.getProperty("JWT_Token");

		String API_URL = BASE_URL + "/users/session";

		RestAssured.baseURI = API_URL;

		RequestSpecification request = RestAssured.given();

		request.header("Authorization", "Bearer " + JWT_Token);
		request.header("Content-Type", "application/json");

		response = request.delete(API_URL);
		error_msg = response.jsonPath().getString("Errors[0]");
//		input.close();
		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success");

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		} else {
			try {
				System.out.println("Error: " + error_msg);
				assert false : "Error: " + error_msg;

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}

	@Given("^Update User Profile and Attach User Picture$")

	public void Update_User_Profile_and_Attach_User_Picture() throws Throwable {

		String API_URL = BASE_URL + "/users/profile";

		RestAssured.baseURI = API_URL;

		String UserFirstName = "tomytest";
		String UserLastName = "testtest";
		String UserNumber = "0505555777";
		String FileName = "TommyProfile";
		String PictureUri = "data:image/png;base64,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";
		String PictureType = "png";
		String validRequest = "{ \n  \"profile\": {\n    \"first_name\": \"" + UserFirstName
				+ "\",\n      \"last_name\": \"" + UserLastName + "\",\n      \"mobile_number\": \"" + UserNumber
				+ "\",\n      \"mobile_acode\": \"+972\",\n        \"picture\": {\n            \"uri\": \"" + PictureUri
				+ "\",\n            \"type\": \"" + PictureType + "\",\n            \"name\": \"" + FileName
				+ "\"\n        }\n }\n}";

		RequestSpecification request = RestAssured.given();

		request.header("Authorization", "Bearer " + JWT_Token);
		request.header("Content-Type", "application/json");

		response = request.body(validRequest).patch(API_URL);
		error_msg = response.jsonPath().getString("Errors[0]");

		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success");

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		} else {
			try {
				System.out.println("Error: " + error_msg);
				assert false : "Error: " + error_msg;

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}

	@Given("^Link reservation by \"(.*?)\"$")

	public void Link_reservation_by(String LinkChoice) throws Throwable {

		String API_URL = BASE_URL + "/reservations/lookup";

//		PropertiesConfiguration config = new PropertiesConfiguration(
//				"../DanAutomation/src/test/resources/config/Configuration.properties");

		String ReservationNumber = "13817";
		Integer HotelId = 8;
		String BookingFrom = "2022-04-19";
		String BookingTo = "2022-04-20";
		String UserLastName = "Autostar0812";
		String validRequest = "";

		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		InputStream input = loader.getResourceAsStream("config/Configuration.properties");

		prop.load(input);

		if (LinkChoice == "number") {

			validRequest = prop.getProperty("byNumberBody");

		} else if (LinkChoice == "date") {

			validRequest = prop.getProperty("byDateBody");
		}

		RestAssured.baseURI = API_URL;

		RequestSpecification request = RestAssured.given();

		request.header("Authorization", "Bearer " + JWT_Token);
		request.header("Content-Type", "application/json");
		request.header("X-DEVICE-ID", "d43fb20f94706af793613cac923d4d85");

		response = request.body(validRequest).post(API_URL);

		reservationId = response.jsonPath().getString("Result.id");
		error_msg = response.jsonPath().getString("Errors[0]");

		input.close();
//		config.setProperty("reservationId", reservationId);

//		config.save();
		System.out.println(response.statusCode());

		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success");

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		} else {
			try {
				System.out.println("Error: " + error_msg);
				assert false : "Error: " + error_msg;

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}

	@Given("^Link reservation by number$")

	public void Link_reservation_by_number() throws Throwable {

		String API_URL = BASE_URL + "/reservations/lookup";

		PropertiesConfiguration config = new PropertiesConfiguration(
				"../DanAutomation/src/test/resources/config/Configuration.properties");

		String ReservationNumber = "13941";
		Integer HotelId = 8;
		String BookingFrom = "";
//		String BookingFrom = "2022-04-19";
		String BookingTo = "";
//		String BookingTo = "2022-04-20";
//		String UserLastName = "";
		String UserLastName = "Autostar1410";

		String validRequest = "{\n  \"reservation_nr\": \"" + ReservationNumber + "\",\n  \"hotel_id\": \"" + HotelId
				+ "\",\n  \"booking_from\": \"" + BookingFrom + "\",\n  \"booking_to\": \"" + BookingTo
				+ "\",\n  \"last_name\": \"" + UserLastName + "\"\n}";

		RestAssured.baseURI = API_URL;

		RequestSpecification request = RestAssured.given();

		request.header("Authorization", "Bearer " + JWT_Token);
		request.header("Content-Type", "application/json");
		request.header("X-DEVICE-ID", "d43fb20f94706af793613cac923d4d85");

		response = request.body(validRequest).post(API_URL);

		reservationId = response.jsonPath().getString("Result.id");
		error_msg = response.jsonPath().getString("Errors[0]");

		config.setProperty("reservationId", reservationId);

		config.save();
		System.out.println(response.statusCode());
		System.out.println(response.asPrettyString());

		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success");

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		} else {
			try {
				System.out.println("Error: " + error_msg);
				assert false : "Error: " + error_msg;

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}

	@Given("^Link reservation by date$")

	public void Link_reservation_by_date() throws Throwable {

		String API_URL = BASE_URL + "/reservations/lookup";

		PropertiesConfiguration config = new PropertiesConfiguration(
				"../DanAutomation/src/test/resources/config/Configuration.properties");

//		String ReservationNumber = "13785";
		Integer HotelId = 8;
		String BookingFrom = "2022-04-25";
//		String BookingTo = "2022-04-20";
		String BookingTo = "";
//		String UserLastName = "Autostar0810";
		String UserLastName = "Autostar1411";

		String validRequest = "{\n  \"hotel_id\": \"" + HotelId + "\",\n  \"booking_from\": \"" + BookingFrom
				+ "\",\n  \"booking_to\": \"" + BookingTo + "\",\n  \"last_name\": \"" + UserLastName + "\"\n}";

		RestAssured.baseURI = API_URL;

		RequestSpecification request = RestAssured.given();

		request.header("Authorization", "Bearer " + JWT_Token);
		request.header("Content-Type", "application/json");
		request.header("X-DEVICE-ID", "d43fb20f94706af793613cac923d4d85");

		response = request.body(validRequest).post(API_URL);

		reservationId = response.jsonPath().getString("Result.id");
		error_msg = response.jsonPath().getString("Errors[0]");

		config.setProperty("reservationId", reservationId);

		config.save();
		System.out.println(response.statusCode());
		System.out.println("Reservation ID is: " + reservationId);
		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success");

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		} else {
			try {
				System.out.println("Error: " + error_msg);
				assert false : "Error: " + error_msg;

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}

	@Then("^I unlink reservation with reservation ID$")

	public void I_unlink_reservation_with_reservation_ID() throws Throwable {
//		int IntResId = Integer.parseInt(reservationId);

		String API_URL = BASE_URL + "/reservations/" + reservationId;

		System.out.println(API_URL);
		RestAssured.baseURI = API_URL;

		RequestSpecification request = RestAssured.given();

		request.header("Authorization", "Bearer " + JWT_Token);
		request.header("Content-Type", "application/json");

		response = request.delete(API_URL);
		error_msg = response.jsonPath().getString("Errors[0]");

		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success");

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		} else {
			try {
				System.out.println("Error: " + error_msg);
				assert false : "Error: " + error_msg;

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}

	@And("^I get value from excel for \"(.*?)\"$")

	public void I_get_value_from_excel_by(String input1) throws Throwable {

		// import org.apache.poi.ss.usermodel.*;

		Boolean Flag = false;

		File file = new File(ExcelFilepath);

		XSSFWorkbook workbook = new XSSFWorkbook(file);

		XSSFSheet sheet = workbook.getSheetAt(0);

		DataFormatter formatter = new DataFormatter();

		for (Row row : sheet) {

			if (Flag.booleanValue())

				break;

			for (Cell cell : row) {

				if (Flag.booleanValue())

					break;

				CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());

				// System.out.print(cellRef.formatAsString());

				// System.out.print(" - ");

				// get the text that appears in the cell by getting the cell value and applying
				// any data formats (Date, 0.00, 1.23e9, $1.23, etc)

				String text = formatter.formatCellValue(cell);

				// System.out.println(text);

				if (text.equalsIgnoreCase(input1)) {

					int i = row.getRowNum();

					Flag = true;

					row = sheet.getRow(i);

					for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {

						String Cell_Value = formatter.formatCellValue(row.getCell(j));

						Excel_Content[j] = Cell_Value;

						System.out.print(Excel_Content[j] + " ,  ");

						// System.out.print(Cell_Value + " , ");

					}

				}

			}

		}

		workbook.close();

	}

	@When("^I prepare reservation data by \"(.*?)\"$")
	public void I_Prepare_reservation_data_by(String ReservationBy) throws Throwable {

		Integer HotelId = Integer.parseInt(Excel_Content[0]);
		String ReservationNumber = Excel_Content[1];
		String UserLastName = Excel_Content[3];
		String BookingFrom = Excel_Content[5];
		String BookingTo = Excel_Content[6];

		API_URL = BASE_URL + "/reservations/lookup";
		RestAssured.baseURI = API_URL;
		
		String ResLowerCase = ReservationBy.toLowerCase();
		ValidRequest1 = "{\n  \"hotel_id\": \"" + HotelId + "\",\n  \"booking_from\": \"" + BookingFrom
				+ "\",\n  \"booking_to\": \"" + BookingTo + "\",\n  \"last_name\": \"" + UserLastName + "\"\n}";

		switch (ResLowerCase) {
		case "date":
			ValidRequest1 = "{\n  \"hotel_id\": \"" + HotelId + "\",\n  \"booking_from\": \"" + BookingFrom
					+ "\",\n  \"booking_to\": \"" + BookingTo + "\",\n  \"last_name\": \"" + UserLastName + "\"\n}";
			break;
		case "number":

			ValidRequest1 = "{\n  \"reservation_nr\": \"" + ReservationNumber + "\",\n  \"hotel_id\": \"" + HotelId
					+ "\",\n  \"booking_from\": \"" + BookingFrom + "\",\n  \"booking_to\": \"" + BookingTo
					+ "\",\n  \"last_name\": \"" + UserLastName + "\"\n}";
			break;
		}

		System.out.println(ValidRequest1);

		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success");

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		} else {
			try {
				System.out.println("Error: " + error_msg);
				assert false : "Error: " + error_msg;

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}

	@Then("^I choose API action \"(.*?)\" and send response$")
	public void I_choose_API_action_and_send_response(String ApiAction) throws Throwable {

		Header h1 = new Header("Authorization", "Bearer " + JWT_Token);
		Header h2 = new Header("Content-Type", "application/json");
		Header h3 = new Header("X-DEVICE-ID", "d43fb20f94706af793613cac923d4d85");
		List<Header> list = new ArrayList<Header>();
		list.add(h1);
		list.add(h2);
		list.add(h3);

		Headers header = new Headers(list);

		RequestSpecification request = RestAssured.given().headers(header);
		;

		if (ValidRequest1 != null || ValidRequest1 != "") {

			switch (ApiAction) {

			case "post":
				API_STRING = request.body(ValidRequest1).post(API_URL);
				break;
			case "patch":
				API_STRING = request.body(ValidRequest1).patch(API_URL);
				break;
			case "get":
				API_STRING = request.get(API_URL);
				break;
			case "delete":
				API_STRING = request.delete(API_URL);
				break;

			}
		}

		response = API_STRING;
		reservationId = response.jsonPath().getString("Result.id");
		System.out.println(response.statusCode());
		System.out.println(response.asPrettyString());
		error_msg = response.jsonPath().getString("Errors[0]");

		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success");

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		} else {
			try {
				System.out.println("Error: " + error_msg);
				assert false : "Error: " + error_msg;

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}

}