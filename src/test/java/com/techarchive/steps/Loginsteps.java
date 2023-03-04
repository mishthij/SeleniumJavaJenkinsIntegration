package com.techarchive.steps;

import com.techarchive.Pages.*;
import com.techarchive.Utitlities.BaseClass;
import com.techarchive.Utitlities.ExcelReader;
import com.techarchive.Utitlities.ReadConfigProperties;
import com.techarchive.Utitlities.Util;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Loginsteps extends BaseClass {
	public static WebDriver driver  = null;

	//public static WebDriver driver;
	Login login;
	Inventory inventory;
//	public static Map<String, String> testdata_map= new HashMap<String, String>();
	ReadConfigProperties config=new ReadConfigProperties();
	String URL;

		@Before
	public void setup(Scenario scenario) throws IOException, InvalidFormatException {
		//Read Excel
			System.out.println("Scnario name  " + scenario.getName());
			System.out.println(	config.getConfigValues("ExcelsheetPath"));
		testdata_map= ExcelReader.getTestData(scenario.getName(),config.getConfigValues("ExcelsheetPath"),config.getConfigValues("Sheetname"));
		System.out.println("This will run before the Scenario");
		//Read Config
			 URL =config.getConfigValues("URL");

		//Driver Intialization
	//	System.setProperty("webdriver.chrome.driver", Util.CHROME_DRIVER_LOCATION);
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("start-maximized");
//		options.addArguments("incognito");
//		//options.addArguments("headless");
//			driver = new ChromeDriver(options);
//			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

			driver = BaseClass.initilize();

	}

	
	@Given("I am at some place")
	public void i_am_at_some_place() {

     System.out.println("This is Given");
	}

	@When("I do some action")
	public void i_do_some_action() {
		System.out.println("This is When");
	}

	@Then("Something happens")
	public void something_happens() {
		System.out.println("This is Then");
	}


	@Given("I navigate to Sauce Labs")
	public void iNavigateToSauceLabs() throws InterruptedException {
	//	driver.get(Util.BASE_URL);
		driver.get(URL);
		Thread.sleep(2000);
	}



	@After
	public void teardown(){
		System.out.println("This will run after the Scenario");
		driver.quit();
	}




	@And("I enter value in {string} field on {string} page")
	public void iEnterInField( String object, String page) throws InterruptedException {
        String pageName = page;
		CheckoutStep1 checkoutStep1;
        switch (pageName){
			case "Login" :
				login = new Login(driver);
				if(object.equalsIgnoreCase("Username")) {
					login.enterUserame(testdata_map.get("Username"));
				}

				if(object.equalsIgnoreCase("Password")) {
					login.enterPassword(testdata_map.get("Password"));
				}
				Thread.sleep(3000);
				break;
			case "CheckoutStep1":
				checkoutStep1 = new CheckoutStep1(driver);
				if (object.equalsIgnoreCase("First name")){
					checkoutStep1.enterFirstName(testdata_map.get("First Name"));
				}
				if (object.equalsIgnoreCase("Last name")){
					checkoutStep1.enterLastName(testdata_map.get("Last Name"));
				}
				if (object.equalsIgnoreCase("Zip")){
					checkoutStep1.enterPostalCode(testdata_map.get("Zip"));
				}
			default:
				System.out.println("No case matched");
		}

	}

	@And("I enter {string} and {string} field")
	public void iEnterAndField(String username, String password) throws InterruptedException {
		login = new Login(driver);
		login.enterUserame(username);
		login.enterPassword(password);
		Thread.sleep(3000);
	}

	@And("I click on {string} on {string} page")
	public void iClickOnOnPage(String object, String page) throws InterruptedException {
		String pageName = page;
		Cart cart;
		CheckoutStep1 checkoutStep1;
		Inventory inventory;
		CheckoutStep2 checkoutStep2;
		switch (pageName){
			case "Login" :
				login = new Login(driver);
				login.pressLoginButton();
				Thread.sleep(3000);
				break;
			case "Cart" :
				cart = new Cart(driver);
				cart.checkout();
				Thread.sleep(3000);
				break;
			case "CheckoutStep1":
				checkoutStep1 = new CheckoutStep1(driver);
				checkoutStep1.pressContinue();
				Thread.sleep(3000);
				break;
			case "checkoutStep2":
				checkoutStep2 = new CheckoutStep2(driver);
				checkoutStep2.finishCheckout();
				Thread.sleep(3000);
				break;
		}
	}
}
