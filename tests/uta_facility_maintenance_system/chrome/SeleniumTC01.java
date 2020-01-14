package uta_facility_maintenance_system.chrome;

//import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import java.util.Properties;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

import java.util.Properties;
import java.io.FileInputStream;
import uta_facility_maintenance_system.UFMSFunctions;
import uta_facility_maintenance_system.UFMSFunctions.FunctionEnum;

import org.junit.runners.MethodSorters;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTC01 extends UFMSFunctions {
	// private WebDriver driver;
	// private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	public static String sAppURL, sSharedUIMapPath;
	// public Properties prop;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "c:/ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver();
		// baseUrl = "http://localhost:8080/";
		prop = new Properties();
		prop.load(new FileInputStream("./Configuration/UFMS_Configuration.properties"));
		sAppURL = prop.getProperty("sAppURL");
		sSharedUIMapPath = prop.getProperty("sSharedUIMapPath");
		prop.load(new FileInputStream(sSharedUIMapPath));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void verifyAllLinks() throws Exception {
		driver.get(sAppURL);
		UFMS_LoginUser(driver, "tharoon", "Thar@123");
		driver.findElement(By.xpath(prop.getProperty("UserHomePage_viewProfileLink"))).click();
		assertEquals("Modify User Details", driver.getTitle());
		driver.findElement(By.xpath(prop.getProperty("UserHomePage_viewProfile_homeBtn"))).click();
		driver.findElement(By.xpath(prop.getProperty("UserHomePage_searchFacilities"))).click();
		assertEquals("Search Facilities", driver.getTitle());
		driver.findElement(By.xpath(prop.getProperty("UserHomePage_searchFacilities_homeBtn"))).click();
		driver.findElement(By.xpath(prop.getProperty("UserHomePage_createMar"))).click();
		assertEquals("Create MAR", driver.getTitle());
		driver.findElement(By.xpath(prop.getProperty("UserHomePage_createMar_homeBtn"))).click();
		driver.findElement(By.xpath(prop.getProperty("UserHomePage_searchMar"))).click();
		assertEquals("MAR List", driver.getTitle());
		driver.findElement(By.xpath(prop.getProperty("UserHomePage_searchMar_homeBtn"))).click();
	}

	@Test
	@FileParameters("tests/uta_facility_maintenance_system/chrome/UserDetailTestCases.csv")
	public void TC01(int testCaseNumber, String userName, String firstName, String lastName, String password,
			String confirmPassword, String userRole, String utaID, String phone, String email, String address,
			String city, String state, String zipCode, String errorMsg, String userNameErrorMessage,
			String firstNameErrorMessage, String lastNameErrorMessage, String passwordErrorMessage,
			String confirmPasswordErrorMessage, String userRoleErrorMessage, String utaIDErrorMessage,
			String phoneErrorMessage, String emailErrorMessage, String addressErrorMessage, String cityErrorMessage,
			String StateErrorMessage, String zipCodeErrorMessage) throws Exception {

		driver.get(sAppURL);
		// takeScreenshot(driver,"Registration");
		UFMS_UserRegistraton(driver, userName, firstName, lastName, password, confirmPassword, userRole, utaID, phone,
				email, address, city, state, zipCode);
		takeScreenshot(driver, "Registration " + testCaseNumber);
		verifyRegistrationErrorMessages(driver, errorMsg, userNameErrorMessage, firstNameErrorMessage,
				lastNameErrorMessage, passwordErrorMessage, confirmPasswordErrorMessage, userRoleErrorMessage,
				utaIDErrorMessage, phoneErrorMessage, emailErrorMessage, addressErrorMessage, cityErrorMessage,
				StateErrorMessage, zipCodeErrorMessage);
		// takeScreenshot(driver,"Registration");

	}

	@Test
	@FileParameters("tests/uta_facility_maintenance_system/chrome/LoginTestCases.csv")
	public void TC02(int testCaseNumber, String userName, String password, String errorMsg, String userNameErrorMessage,
			String passwordErrorMessage, String invalidGreetingMessage) {
		driver.get(sAppURL);
		UFMS_LoginUser(driver, userName, password);
		takeScreenshot(driver, "Login " + testCaseNumber);
		verifyLoginErrorMessages(driver, errorMsg, userNameErrorMessage, passwordErrorMessage, invalidGreetingMessage);

	}

	@Test
	@FileParameters("tests/uta_facility_maintenance_system/chrome/CreateMarTestCases.csv")
	public void TC03(int testCaseNumber, String userName, String password, String faciltyType, String faciltyName,
			String urgency, String description, String errorMsg, String descriptionErrorMessage) throws Exception {
		driver.get(sAppURL);
		UFMS_LoginUser(driver, userName, password);
		MainApp_function(driver, FunctionEnum.createMAR);
		UFMS_CreateMAR(driver, faciltyType, faciltyName, urgency, description);
		takeScreenshot(driver, "CreateMAR " + testCaseNumber);
		verifyCreateMARErrorMessages(driver, errorMsg, descriptionErrorMessage);
		// driver.findElement(By.xpath(prop.getProperty("CreateMar_Home_Btn"))).click();
		driver.findElement(By.xpath(prop.getProperty("CreateMar_Logout_Btn"))).click();

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}

