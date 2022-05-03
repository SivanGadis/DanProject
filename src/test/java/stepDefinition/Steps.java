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

	@And("^I wait for \"(.*?)\" milliseconds$")
	public void i_wait_for_x_milliseconds(String milliseconds) {
		try {

			Thread.sleep(Integer.parseInt(milliseconds));
//System.out.println("I wait for "+ milliseconds +" milliseconds");

		} catch (Exception ex) {
//ignore
		}

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

	@And("^Update config file for key \"(.*?)\" with value \"(.*?)\"$")

	public void update_config_value(String Key, String Value1) throws Throwable {

		PropertiesConfiguration config = new PropertiesConfiguration(
				"../DanAutomation/src/test/resources/config/Configuration.properties");

		config.setProperty(Key, Value1);

		config.save();
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

		String UiD = response.jsonPath().getString("Result.user_id");
		error_msg = response.jsonPath().getString("Errors[0]");
		System.out.println(response.statusCode());
		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success, New user ID is: " + UiD);

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
		error_msg = response.jsonPath().getString("Errors[0]");
//		System.out.println(response.asPrettyString());
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

		String API_URL = BASE_URL + "/users/session";

		RestAssured.baseURI = API_URL;

		RequestSpecification request = RestAssured.given();

		request.header("Authorization", "Bearer " + JWT_Token);
		request.header("Content-Type", "application/json");

		response = request.delete(API_URL);
		error_msg = response.jsonPath().getString("Errors[0]");

		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success, User has been signed out.");

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
				System.out.println("Success, User profile has been updated.");

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

		String API_URL = BASE_URL + "/reservations/" + reservationId;

		RestAssured.baseURI = API_URL;

		RequestSpecification request = RestAssured.given();

		request.header("Authorization", "Bearer " + JWT_Token);
		request.header("Content-Type", "application/json");

		response = request.delete(API_URL);
		error_msg = response.jsonPath().getString("Errors[0]");

		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success, Reservation has been unlinked.");

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

//						System.out.print(Excel_Content[j] + " ,  ");

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

		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success, Data has been prepared and ready for luanch.");

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
//		System.out.println(response.asPrettyString());
		error_msg = response.jsonPath().getString("Errors[0]");

		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success, Reservation has been linked with the ID of: " + reservationId);

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

	@Given("^Pre check in$")

	public void Pre_check_in() throws Throwable {

		String API_URL = BASE_URL + "/reservations/" + reservationId + "/profile";

		RestAssured.baseURI = API_URL;

		Boolean completed = true;
		String etaAt = "14:30";
		String passportNo = "332358530";
		String email = "Tommy.Berman@qualitestgroup.com";
		String mobile = "972123456789";
		String birthDay = "2019-07-10";
		String countryCode = "ISR";
		Boolean parkingReq = true;
		String parkingPlate = "sdf";
		Boolean specialAssitanceNeeded = true;

		String validRequest = "{\r\n  \"guest_profile\": {\r\n    \"completed\": \"" + completed
				+ "\",\r\n    \"eta_at\": \"" + etaAt + "\",\r\n    \"passport_no\": \"" + passportNo
				+ "\",\r\n    \"email\": \"" + email + "\",\r\n    \"mobile\": \"" + mobile
				+ "\",\r\n    \"birthday_date\": \"" + birthDay
				+ "\",\r\n    \"street\": null,\r\n    \"city\": null,\r\n    \"country_code\": \"" + countryCode
				+ "\",\r\n    \"zip\": null,\r\n    \"parking_requested\": \"" + parkingReq
				+ "\",\r\n    \"parking_plate\": \"" + parkingPlate + "\",\r\n    \"special_assistance_needed\": \""
				+ specialAssitanceNeeded + "\"\r\n  }\r\n}\r\n";

		RequestSpecification request = RestAssured.given();

		request.header("Authorization", "Bearer " + JWT_Token);
		request.header("Content-Type", "application/json");

		response = request.body(validRequest).patch(API_URL);
		error_msg = response.jsonPath().getString("Errors[0]");

		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success, User Reservation profile has been updated.");

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

	@Given("^Upload signature$")

	public void Upload_signature() throws Throwable {

		String API_URL = BASE_URL + "/reservations/" + reservationId + "/documents";

		RestAssured.baseURI = API_URL;

		String docType = "signature";
		String fileLocation = "/Users/igorgoremykin/wolfs 2.png";

		RequestSpecification request = RestAssured.given();

		request.header("Authorization", "Bearer " + JWT_Token);
		request.header("Content-Type", "application/json");
		request.contentType("multipart/form-data");
		request.multiPart("file", new File(fileLocation));
		request.multiPart("document[document_type]", docType);

		response = request.patch(API_URL);
		error_msg = response.jsonPath().getString("Errors[0]");

		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success, Document has been uploaded.");

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

	@Given("^CreditCard authorization$")

	public void CreditCard_authorization() throws Throwable {

		String API_URL = BASE_URL + "/payments/authorize_card";
		Integer cardId = 0;

		String validRequest = "{\n    \"payment_authorization\": {\n        \"cardId\": \"" + cardId
				+ "\",\n        \"reservationId\": \"" + reservationId + "\"\n    }\n}";

		RestAssured.baseURI = API_URL;

		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		request.header("X-DEVICE-ID", "d43fb20f94706af793613cac923d4d85");

		response = request.body(validRequest).post(API_URL);
		error_msg = response.jsonPath().getString("Errors[0]");
		System.out.println(response.statusCode());
//		System.out.println(response.asPrettyString());

		if (error_msg == "" || error_msg == null) {
			try {
				assert true;
				System.out.println("Success, Card has been Authorized.");

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