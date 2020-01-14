package uta_facility_maintenance_system;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;

public class UFMSFunctions {

	public static WebDriver driver;
	public static Properties prop;
	
	
	public enum FunctionEnum {listViewMyRepairs, adminSearchesUser, searchUnassignedMARS, createMAR}

	public void takeScreenshot(WebDriver driver, String screenshotname) {
		try {
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./ScreenShots/" + screenshotname + ".png"));
		} catch (IOException e) {
		}
		try {
//			  Change the delay value to 1_000 to insert a 1 second delay after 
//			  each screenshot
			Thread.sleep(1_000);
		} catch (InterruptedException e) {
		}
	}
	
	
	public void MainApp_function (WebDriver driver, FunctionEnum function) {
		  switch (function) {
		  	case listViewMyRepairs:
			    driver.findElement(By.xpath(prop.getProperty("RepairerHomePage_ViewReservedRepairs_Lnk"))).click(); //select List assign repairs from homepage
			    break;
		  	case adminSearchesUser:
		  		driver.findElement(By.xpath(prop.getProperty("AdminHomePage_SearchForUser_Lnk"))).click();
		  		break;
		  		
		  	case searchUnassignedMARS:
				  driver.findElement(By.xpath(prop.getProperty("FacilityManager_SearchUnassignedMARS"))).click();
				  break;
		  	case createMAR:
		  		driver.findElement(By.xpath(prop.getProperty("CreateMar_RadioButton"))).click();
		  		break;
		  		
		  }
	  }

	public void UFMS_UserRegistraton(WebDriver driver, String userName, String firstName, String lastName,
			String password, String confirmPassword, String userRole, String utaID, String phone, String email,
			String address, String city, String state, String zipcode) {

		driver.findElement(By.linkText(prop.getProperty("Login_Register_Lnk"))).click();
		driver.findElement(By.xpath(prop.getProperty("Registration_Username_Txt"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Registration_Username_Txt"))).sendKeys(userName);
		driver.findElement(By.xpath(prop.getProperty("Registration_FirstName_Txt"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Registration_FirstName_Txt"))).sendKeys(firstName);
		driver.findElement(By.xpath(prop.getProperty("Registration_LastName_Txt"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Registration_LastName_Txt"))).sendKeys(lastName);
		driver.findElement(By.xpath(prop.getProperty("Registration_Password_Txt"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Registration_Password_Txt"))).sendKeys(password);
		driver.findElement(By.xpath(prop.getProperty("Registration_ConfirmPassword_Txt"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Registration_ConfirmPassword_Txt"))).sendKeys(confirmPassword);
		new Select(driver.findElement(By.xpath(prop.getProperty("Registration_UserRole_Lst"))))
				.selectByVisibleText(userRole);
		driver.findElement(By.xpath(prop.getProperty("Registration_UtaID_Txt"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Registration_UtaID_Txt"))).sendKeys(utaID);
		driver.findElement(By.xpath(prop.getProperty("Registration_Phone_Txt"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Registration_Phone_Txt"))).sendKeys(phone);
		driver.findElement(By.xpath(prop.getProperty("Registration_Email_Txt"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Registration_Email_Txt"))).sendKeys(email);
		driver.findElement(By.xpath(prop.getProperty("Registration_Address_Txt"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Registration_Address_Txt"))).sendKeys(address);
		driver.findElement(By.xpath(prop.getProperty("Registration_City_Txt"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Registration_City_Txt"))).sendKeys(city);
		new Select(driver.findElement(By.xpath(prop.getProperty("Registration_State_Lst")))).selectByVisibleText(state);
		driver.findElement(By.xpath(prop.getProperty("Registration_Zipcode_Txt"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Registration_Zipcode_Txt"))).sendKeys(zipcode);
		driver.findElement(By.xpath(prop.getProperty("Registration_Submit_Btn"))).click();
	}

	public void UFMS_LoginUser(WebDriver driver, String userName, String password) {
		driver.findElement(By.xpath(prop.getProperty("Login_Username_Txt"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Login_Username_Txt"))).sendKeys(userName);
		driver.findElement(By.xpath(prop.getProperty("Login_Password_Txt"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Login_Password_Txt"))).sendKeys(password);
		driver.findElement(By.xpath(prop.getProperty("Login_Login_Btn"))).click();
	}

	public void UFMS_CreateMAR(WebDriver driver, String facilityType, String facilityName, String urgency,
			String description) throws Exception {
		
		new Select(driver.findElement(By.xpath(prop.getProperty("CreateMar_FacilityType_Lst"))))
				.selectByVisibleText(facilityType);
		new Select(driver.findElement(By.xpath(prop.getProperty("CreateMar_FacilityName_Lst"))))
				.selectByVisibleText(facilityName);
		new Select(driver.findElement(By.xpath(prop.getProperty("CreateMar_Urgency_Lst"))))
				.selectByVisibleText(urgency);
		driver.findElement(By.xpath(prop.getProperty("CreateMar_Description_Txt"))).clear();
		driver.findElement(By.xpath(prop.getProperty("CreateMar_Description_Txt"))).sendKeys(description);
		driver.findElement(By.xpath(prop.getProperty("CreateMar_Submit_Btn"))).click();
	}

	public void verifyRegistrationErrorMessages(WebDriver driver, String errorMsg, String userNameError,
			String firstNameError, String lastNameError, String passwordError, String confirmPasswordError,
			String userRoleError, String utaIdError, String phoneError, String emailError, String streetAddressError,
			String cityError, String stateError, String zipCodeError) {

		assertTrue(driver.findElement(By.xpath(prop.getProperty("Registration_errMsg"))).getAttribute("value")
				.equals(errorMsg));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Registration_userNameError"))).getAttribute("value")
				.equals(userNameError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Registration_firstNameError"))).getAttribute("value")
				.equals(firstNameError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Registration_lastNameError"))).getAttribute("value")
				.equals(lastNameError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Registration_passwordError"))).getAttribute("value")
				.equals(passwordError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Registration_confirmPasswordError")))
				.getAttribute("value").equals(confirmPasswordError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Registration_userRoleError"))).getAttribute("value")
				.equals(userRoleError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Registration_utaIdError"))).getAttribute("value")
				.equals(utaIdError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Registration_phoneError"))).getAttribute("value")
				.equals(phoneError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Registration_emailError"))).getAttribute("value")
				.equals(emailError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Registration_streetAddressError")))
				.getAttribute("value").equals(streetAddressError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Registration_cityError"))).getAttribute("value")
				.equals(cityError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Registration_stateError"))).getAttribute("value")
				.equals(stateError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Registration_zipCodeError"))).getAttribute("value")
				.equals(zipCodeError));
	}

	public void verifyLoginErrorMessages(WebDriver driver, String errorMsg, String UserNameError, String passwordError,
			String invalidUsernameGreeting) {
		assertTrue(
				driver.findElement(By.xpath(prop.getProperty("Login_errMsg"))).getAttribute("value").equals(errorMsg));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Login_userNameError"))).getAttribute("value")
				.equals(UserNameError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Login_passwordError"))).getAttribute("value")
				.equals(passwordError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Login_invalidUsernameGreeting"))).getAttribute("value")
				.equals(invalidUsernameGreeting));
	}

	public void verifyCreateMARErrorMessages(WebDriver driver, String errorMsg, String descriptionErrorMessage) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty("CreateMAR_errMsg"))).getAttribute("value")
				.equals(errorMsg));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("CreateMAR_descriptionError"))).getAttribute("value")
				.equals(descriptionErrorMessage));

	}

	// Shambavi's Code(Admin)
	public void verifyHeaders_User(WebDriver driver, String header1OnPage, String expectedHeader1Name,
			String header2OnPage, String expectedHeader2Name, String header3OnPage, String expectedHeader3Name,
			String header4OnPage, String expectedHeader4Name, String header5OnPage, String expectedHeader5Name,
			String header6OnPage, String expectedHeader6Name, String header7OnPage, String expectedHeader7Name,
			String snapShotName) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header1OnPage))).getText().equals(expectedHeader1Name));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header2OnPage))).getText().equals(expectedHeader2Name));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header3OnPage))).getText().equals(expectedHeader3Name));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header4OnPage))).getText().equals(expectedHeader4Name));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header5OnPage))).getText().equals(expectedHeader5Name));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header6OnPage))).getText().equals(expectedHeader6Name));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header7OnPage))).getText().equals(expectedHeader7Name));

		takeScreenshot(driver, snapShotName);
	}

	public void searchUser_function(WebDriver driver, String username, String snapShotName) {
		driver.findElement(By.xpath(prop.getProperty("SearchForUser_TypeUsernameOfUser_Box"))).sendKeys(username);
		takeScreenshot(driver, snapShotName);
		driver.findElement(By.xpath(prop.getProperty("SearchForUser_SeachUser_Btn"))).click();
	}

	public void verifySearchUserErrorMessages(WebDriver driver, String errorMessage, String snapShotName) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty("SearchForUser_ErrorMessageForNoUserFound")))
				.getAttribute("value").equals(errorMessage));
		takeScreenshot(driver, snapShotName);
	}

	public void verifyChangedUserRole_ConfirmationMsg(WebDriver driver, String confirmationMsg, String snapShotName) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUserRole_UserRoleChanged_Message")))
				.getAttribute("value").equals(confirmationMsg));
		takeScreenshot(driver, snapShotName);

	}
	
	// Pavithra Code(Facility Manager)
	public void verifyHeaders_UnassignedMARS(WebDriver driver, String header1OnPage, String expectedHeader1Name,String header2OnPage, String expectedHeader2Name,
			String header3OnPage, String expectedHeader3Name,String header4OnPage, String expectedHeader4Name, 
			String header5OnPage, String expectedHeader5Name, String header6OnPage, String expectedHeader6Name, 
			String header7OnPage, String expectedHeader7Name, String snapShotName) {
		
		 assertTrue(driver.findElement(By.xpath(prop.getProperty(header1OnPage))).getText().equals(expectedHeader1Name));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty(header2OnPage))).getText().equals(expectedHeader2Name));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty(header3OnPage))).getText().equals(expectedHeader3Name));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty(header4OnPage))).getText().equals(expectedHeader4Name));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty(header5OnPage))).getText().equals(expectedHeader5Name));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty(header6OnPage))).getText().equals(expectedHeader6Name));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty(header7OnPage))).getText().equals(expectedHeader7Name));
		 takeScreenshot(driver, snapShotName);	
		 
	}
	
	public void UFMS_AssignMAR(WebDriver driver, String estimate_of_repair, String repairer, String MARnumber, String greetingText, String snapShotName) throws Exception  {
		
		System.out.println(MARnumber);
//		String MAR = driver.findElement(By.xpath(prop.getProperty("AssignMAR_MARNumber"))).getText();
//		System.out.println(MAR);	
		assertTrue(driver.findElement(By.xpath(prop.getProperty("AssignMAR_MARNumber"))).getAttribute("value").equals(MARnumber));
		new Select(driver.findElement(By.xpath(prop.getProperty("AssignMAR_EstimateOfRepair")))).selectByVisibleText(estimate_of_repair);
		new Select(driver.findElement(By.xpath(prop.getProperty("AssignMAR_AssignedTo")))).selectByVisibleText(repairer);
		driver.findElement(By.xpath(prop.getProperty("AssignMAR_update"))).click();
		Thread.sleep(1_000);
		takeScreenshot(driver, snapShotName);
		assertTrue(driver.findElement(By.xpath(prop.getProperty("AssignMAR_GreetingMessage"))).getAttribute("value").equals(greetingText));
		driver.findElement(By.xpath(prop.getProperty("AssignMAR_HomeLink"))).click();
	}
	
	public void UFMS_Logout(WebDriver driver) {
		driver.findElement(By.xpath(prop.getProperty("FacilityManager_Logout"))).click();
	}
	
	
	public void verifyHeaders_Repairs(WebDriver driver, String header1OnPage, String expectedHeader1Name,String header2OnPage, String expectedHeader2Name,
			String header3OnPage, String expectedHeader3Name,String header4OnPage, String expectedHeader4Name, 
			String header5OnPage, String expectedHeader5Name, String header6OnPage, String expectedHeader6Name, 
			String header7OnPage, String expectedHeader7Name, String snapShotName) throws Exception {
		Thread.sleep(1_000);
	 assertTrue(driver.findElement(By.xpath(prop.getProperty(header1OnPage))).getText().equals(expectedHeader1Name));
	 assertTrue(driver.findElement(By.xpath(prop.getProperty(header2OnPage))).getText().equals(expectedHeader2Name));
	 assertTrue(driver.findElement(By.xpath(prop.getProperty(header3OnPage))).getText().equals(expectedHeader3Name));
	 assertTrue(driver.findElement(By.xpath(prop.getProperty(header4OnPage))).getText().equals(expectedHeader4Name));
	 assertTrue(driver.findElement(By.xpath(prop.getProperty(header5OnPage))).getText().equals(expectedHeader5Name));
	 assertTrue(driver.findElement(By.xpath(prop.getProperty(header6OnPage))).getText().equals(expectedHeader6Name));
	 assertTrue(driver.findElement(By.xpath(prop.getProperty(header7OnPage))).getText().equals(expectedHeader7Name));
	 
	 takeScreenshot(driver,snapShotName);
 }
 
 public void verifyContents_Repairs(WebDriver driver, String col1ValueOnPage, String expectedcol1Value,String col2ValueOnPage, String expectedcol2Value,
			String col3ValueOnPage, String expectedcol3Value,String col4ValueOnPage, String expectedcol4Value, String col5ValueOnPage, String expectedcol5Value,
			String col6ValueOnPage, String expectedcol6Value, String col7ValueOnPage, String expectedcol7Value, String snapShotName) {
	 assertTrue(driver.findElement(By.xpath(prop.getProperty(col1ValueOnPage))).getText().equals(expectedcol1Value));
	 assertTrue(driver.findElement(By.xpath(prop.getProperty(col2ValueOnPage))).getText().equals(expectedcol2Value));
	 assertTrue(driver.findElement(By.xpath(prop.getProperty(col3ValueOnPage))).getText().equals(expectedcol3Value));
	 assertTrue(driver.findElement(By.xpath(prop.getProperty(col4ValueOnPage))).getText().equals(expectedcol4Value));
	 assertTrue(driver.findElement(By.xpath(prop.getProperty(col5ValueOnPage))).getText().equals(expectedcol5Value));
	 assertTrue(driver.findElement(By.xpath(prop.getProperty(col6ValueOnPage))).getText().equals(expectedcol6Value));
	 assertTrue(driver.findElement(By.xpath(prop.getProperty(col7ValueOnPage))).getText().equals(expectedcol7Value));
	 takeScreenshot(driver,snapShotName);
 }
 
 public void verify_RepairerTime_ErrorMsg(WebDriver driver, String errorMsgOnPage, String expectedErrorMsg, String snapShotName) {
	 assertTrue(driver.findElement(By.xpath(prop.getProperty(errorMsgOnPage))).getText().equals(expectedErrorMsg));
	 takeScreenshot(driver,snapShotName);
 }
 
 public void verify_CancelRepair_Msg(WebDriver driver, String MsgOnPage, String expectedMsg, String snapShotName) {
	 assertTrue(driver.findElement(By.xpath(prop.getProperty(MsgOnPage))).getText().equals(expectedMsg));
	 takeScreenshot(driver,snapShotName);
 }

}
