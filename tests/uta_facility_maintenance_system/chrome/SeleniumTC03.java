package uta_facility_maintenance_system.chrome;

import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import uta_facility_maintenance_system.UFMSFunctions;

import uta_facility_maintenance_system.model.UserDetail;
import uta_facility_maintenance_system.util.SQLConnection;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTC03 extends UFMSFunctions {

	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	public String sAppURL, sSharedUIMapPath, testDelay;
	static SQLConnection DBMgr = SQLConnection.getInstance();

	
	
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "c:/ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		prop = new Properties();
		prop.load(new FileInputStream("./Configuration/UFMS_Configuration.properties"));
		sAppURL = prop.getProperty("sAppURL");
		sSharedUIMapPath = prop.getProperty("sSharedUIMapPath");
		testDelay = prop.getProperty("testDelay");
		prop.load(new FileInputStream(sSharedUIMapPath));
	}


	@Test
	public void verifyAllLinks() throws Exception {
		driver.get(sAppURL);

		UFMS_LoginUser(driver, "john123", "John@123");
		driver.findElement(By.xpath(prop.getProperty("AdminHomePage_ViewProfile_Lnk"))).click();
		assertEquals("Modify User Details", driver.getTitle());
		driver.findElement(By.xpath(prop.getProperty("ViewProfile_HomePage_Lnk"))).click();
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("AdminHomePage_LogOutInAdminHomePage_Lnk"))).click();
		Thread.sleep(1_000);
		assertEquals("UTA MAC Facility Maintenance", driver.getTitle());
	}

	@Test
	@FileParameters("tests/uta_facility_maintenance_system/chrome/TC03a_test_cases.csv")
	public void TC03a(String uname, String errMsg) throws Exception {
		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		UFMS_UserRegistraton(driver, "john123", "John", "Robb", "John@123", "John@123","Admin", "1001999999", "6825641234", "john.robb@uta.edu", "Greek Row, Apt 201", "Arlington", "Texas", "76019" );
		UFMS_LoginUser(driver, "john123", "John@123");
		MainApp_function(driver, FunctionEnum.adminSearchesUser); // select Search For User from homepage
		// Check with incorrect username
		Thread.sleep(1_000);
		searchUser_function(driver, uname, methodName + " searchUserFunction test case ");
		verifySearchUserErrorMessages(driver, errMsg, methodName + " verifySearchUserErrorMessages");
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("SearchForUser_HomePage_Lnk"))).click(); // go back to homepage
	}

	@Test
	@FileParameters("tests/uta_facility_maintenance_system/chrome/TC03b_test_cases.csv")
	public void TC03b(String uname, String ConfirmationMsg) throws Exception {
		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		UFMS_LoginUser(driver, "john123", "John@123");
		MainApp_function(driver, FunctionEnum.adminSearchesUser); // select Search For User from homepage
		// Modify user_role of selected user
		Thread.sleep(1_000);
		searchUser_function(driver, uname, methodName + " searchUserFunction test case ");

		driver.findElement(By.xpath(prop.getProperty("SearchForUser_ViewBtnToModifyUserRole_Lnk"))).click();
		Thread.sleep(1_000);
		new Select(driver.findElement(By.xpath(prop.getProperty("ModifyUserRole_UserRoleDropdownOption"))))
				.selectByVisibleText("User");
		driver.findElement(By.xpath(prop.getProperty("ModifyUserRole_ModifyUserDetails_Btn"))).click();
		Thread.sleep(1_000);
		verifyChangedUserRole_ConfirmationMsg(driver, ConfirmationMsg,
				methodName + " verifyChangeduserRoleConfirmationMsg");

		driver.findElement(By.xpath(prop.getProperty("ModifyUserRole_LogOut_Lnk"))).click();
		Thread.sleep(1_000);

	}

	@Test
	@FileParameters("tests/uta_facility_maintenance_system/chrome/TC03c_test_cases.csv")
	public void TC03c(String col1, String col2, String col3, String col4, String col5, String col6, String col7)
			throws Exception {
		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		UFMS_LoginUser(driver, "john123", "John@123");
		Thread.sleep(1_000);
		MainApp_function(driver, FunctionEnum.adminSearchesUser); // select Search For User from homepage

		// check user detail listing headers
		verifyHeaders_User(driver, "SearchForUser_col1InUserListTableHeader", col1,
				"SearchForUser_col2InUserListTableHeader", col2, "SearchForUser_col3InUserListTableHeader", col3,
				"SearchForUser_col4InUserListTableHeader", col4, "SearchForUser_col5InUserListTableHeader", col5,
				"SearchForUser_col6InUserListTableHeader", col6, "SearchForUser_col7InUserListTableHeader", col7,
				methodName + " verifyHeaders test case ");		 
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("SearchForUser_HomePage_Lnk"))).click(); // go back to homepage
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
