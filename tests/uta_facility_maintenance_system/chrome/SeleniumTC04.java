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
import uta_facility_maintenance_system.model.MarReport;
import uta_facility_maintenance_system.util.SQLConnection;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTC04 extends UFMSFunctions {
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public String sAppURL, sSharedUIMapPath, testDelay;
  static SQLConnection DBMgr = SQLConnection.getInstance();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    prop = new Properties();	  
    prop.load(new FileInputStream("./Configuration/UFMS_Configuration.properties"));
	sAppURL = prop.getProperty("sAppURL");
	sSharedUIMapPath = prop.getProperty("sSharedUIMapPath");
	testDelay=prop.getProperty("testDelay");
	prop.load(new FileInputStream(sSharedUIMapPath));
  }

  private static ArrayList<MarReport> returnMatchingMarReportList(String queryString) {
		ArrayList<MarReport> marReportListInDB = new ArrayList<MarReport>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();

		try {
			stmt = conn.createStatement();
			ResultSet marReportList = stmt.executeQuery(queryString);
			while (marReportList.next()) {
				MarReport marReport = new MarReport();
				marReport.setMarNumber(marReportList.getString("mar_number"));
				marReport.setFacilityName(marReportList.getString("facility_name"));
				marReport.setFacilityType(marReportList.getString("facility_type"));
				marReport.setUrgency(marReportList.getString("urgency"));
				marReport.setDescription(marReportList.getString("description"));
				marReport.setReportedBy(marReportList.getString("reported_by"));
				marReport.setCreatedDate(marReportList.getString("created_date"));
				marReport.setAssignTo(marReportList.getString("assigned_to"));
				marReport.setAssignDate(marReportList.getString("assigned_date"));
				marReport.setEstimateOfRepair(marReportList.getString("estimate_of_repair"));
				marReportListInDB.add(marReport);
			}
		} catch (SQLException e) {
		}
		return marReportListInDB;
  }
  
  public static ArrayList<MarReport> myRepairlist(String repairer) {
		return returnMatchingMarReportList(
				" SELECT * FROM mar_report WHERE assigned_to = '" + repairer + "' ORDER BY assigned_date");
  }
  
  private String [][] getMyRepairListFromDB(int listSize, String repairer) throws SQLException { // this method gets the list assign repairs table contents from the DB
	    ArrayList<MarReport> fromDB = myRepairlist(repairer);
	    String [][] arrayDB = new String [listSize-1][7];
	    int i = 0;
	    for (MarReport p:fromDB) {
	    	arrayDB[i][0] = p.getMarNumber();
	    	arrayDB[i][1] = p.getAssignTo();
	    	arrayDB[i][2] = p.getFacilityName();
	    	arrayDB[i][3] = p.getFacilityType();
	    	arrayDB[i][4] = p.getUrgency();
	    	arrayDB[i][5] = p.getDescription();
	    	arrayDB[i][6] = p.getEstimateOfRepair();
	 		i++;
	    }
	    return arrayDB;
  }
  
  private String [][] getAssignRepairTableContentsFromPage(int listSize) { // this method gets the list assign repairs contents from the web page
	  String [][] repairArray = new String[listSize-1][7];
	  for (int i = 0; i < listSize-1; i++) {
		repairArray[i][0] =  driver.findElement(By.xpath(prop.getProperty("ListViewMyRepairs_prefixAddressAssignRepairTable")+(i+2)+
								prop.getProperty("ListViewMyRepairs_AssignTableMarNumberCol"))).getText();
		repairArray[i][1] = driver.findElement(By.xpath(prop.getProperty("ListViewMyRepairs_prefixAddressAssignRepairTable")+(i+2)+
								prop.getProperty("ListViewMyRepairs_AssignTableAssignedToCol"))).getText();
		repairArray[i][2] = driver.findElement(By.xpath(prop.getProperty("ListViewMyRepairs_prefixAddressAssignRepairTable")+(i+2)+
								prop.getProperty("ListViewMyRepairs_AssignTableFacilityNameCol"))).getText();
		repairArray[i][3] = driver.findElement(By.xpath(prop.getProperty("ListViewMyRepairs_prefixAddressAssignRepairTable")+(i+2)+
								prop.getProperty("ListViewMyRepairs_AssignTableFacilityTypeCol"))).getText();
		repairArray[i][4] = driver.findElement(By.xpath(prop.getProperty("ListViewMyRepairs_prefixAddressAssignRepairTable")+(i+2)+
								prop.getProperty("ListViewMyRepairs_AssignTableUrgencyCol"))).getText();
		repairArray[i][5] = driver.findElement(By.xpath(prop.getProperty("ListViewMyRepairs_prefixAddressAssignRepairTable")+(i+2)+
								prop.getProperty("ListViewMyRepairs_AssignTableDescriptionCol"))).getText();
		repairArray[i][6] = driver.findElement(By.xpath(prop.getProperty("ListViewMyRepairs_prefixAddressAssignRepairTable")+(i+2)+
								prop.getProperty("ListViewMyRepairs_AssignTableEstimateofRepairCol"))).getText();
	  }
	  return repairArray;
  }
  
  private Boolean arraysDiff (String [][] array1, String [][] array2) { // this method compares the contents of the two tables
	  Boolean diff = false || (array1.length != array2.length);
	  for (int i = 0; i < array1.length && !diff; i++) {
		  System.out.println(array1[0][0]);
		 diff  = !array1[i][0].equals(array2[i][0]) || !array1[i][1].equals(array2[i][1]) || 
				 !array1[i][2].equals(array2[i][2]) || !array1[i][3].equals(array2[i][3]) || 
				 !array1[i][4].equals(array2[i][4]) || !array1[i][5].equals(array2[i][5]) ||
				 !array1[i][6].equals(array2[i][6]);
	  }
	  return diff;
  }
  
  
  @Test
  public void TC04a() throws Exception {
	driver.get(baseUrl + "/uta_facility_maintenance_system/");
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
    driver.get(sAppURL);
    UFMS_UserRegistraton(driver, "charan567", "Charan", "V", "Charan@567", "Charan@567", "Repairer", "1001626250", "1234567890", "charan@gmail.com", "415 Summit Ave", "Arlington", "Texas", "76013");
    
    UFMS_LoginUser(driver, "charan123", "Charan@123");
    
    MainApp_function(driver,FunctionEnum.listViewMyRepairs); //select list Companies from homepage
    // check repairs listing headers
    verifyHeaders_Repairs(driver,"ListViewMyRepairs_col1InListRepairTableHeader", "Mar number","ListViewMyRepairs_col2InListRepairTableHeader", "assigned to",
    							  "ListViewMyRepairs_col3InListRepairTableHeader", "Facility name","ListViewMyRepairs_col4InListRepairTableHeader", "Facility type",
    							  "ListViewMyRepairs_col5InListRepairTableHeader", "Urgency", "ListViewMyRepairs_col6InListRepairTableHeader", "Description", 
    							  "ListViewMyRepairs_col7InListRepairTableHeader", "Estimate_Of_repair",methodName + "verifyHeaders test case 1");
   
	WebElement repairTable = driver.findElement(By.xpath(prop.getProperty("ListViewMyRepairs_RepairTable")));
	int rows = repairTable.findElements(By.tagName("tr")).size(); //find the number of rows in the table including the header
	assertFalse(arraysDiff(getMyRepairListFromDB(rows, "charan123"), getAssignRepairTableContentsFromPage(rows)));
	driver.findElement(By.xpath(prop.getProperty("UFMS_HomePage"))).click(); //go back to homepage	
  }
  
  @Test
  @FileParameters("tests/uta_facility_maintenance_system/chrome/TC04b_test_cases.csv")
  public void TC04b(int testCaseNumber, String path, String col1, String col2, String col3, String col4, String col5, 
		  			String col6, String col7,  String data1, String data2, String data3, String data4, String data5, 
		  			String data6, String data7)
  					throws Exception{
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	driver.get(sAppURL);
	UFMS_LoginUser(driver, "charan123", "Charan@123");
	MainApp_function(driver, FunctionEnum.listViewMyRepairs);
	Thread.sleep(500);
	driver.findElement(By.xpath(path)).click();
	Thread.sleep(1000);
	verifyHeaders_Repairs(driver, "ViewSpecificRepair_MarNumber_Title", col1, "ViewSpecificRepair_FacilityName_Title", col2, 
							"ViewSpecificRepair_FacilityType_Title",col3, "ViewSpecificRepair_Urgency_Title", col4,
							"ViewSpecificRepair_Description_Title", col5, "ViewSpecificRepair_AssignedDate_Title", col6,
							"ViewSpecificRepair_EstimateofRepair_Title", col7, methodName);
	verifyContents_Repairs(driver, "ViewSpecificRepair_MarNumber_Value", data1, "ViewSpecificRepair_FacilityName_Value", 
							data2, "ViewSpecificRepair_FacilityType_Value", data3, "ViewSpecificRepair_Urgency_Value", data4,
							"ViewSpecificRepair_Description_Value", data5, "ViewSpecificRepair_AssignedDate_Value", data6, 
							"ViewSpecificRepair_EstimateofRepair_Value", data7, methodName);
	
	driver.findElement(By.xpath(prop.getProperty("ViewSpecificRepair_logout_Lnk"))).click();
	
  }
  
  @Test
  @FileParameters("tests/uta_facility_maintenance_system/chrome/TC04d_test_cases.csv")
  public void TC04d(int testCaseNumber, String time, String ErrorMsg) throws InterruptedException {
	  String methodName= new Throwable().getStackTrace()[0].getMethodName();
	  driver.get(sAppURL);
	  UFMS_LoginUser(driver, "charan123", "Charan@123");
	  MainApp_function(driver, FunctionEnum.listViewMyRepairs);
	  Thread.sleep(500);
	  driver.findElement(By.xpath(prop.getProperty("ViewReservedRepairs_View_Lnk"))).click();
	  driver.findElement(By.xpath(prop.getProperty("ModifyTime_Time_Txt"))).sendKeys(time);
	  driver.findElement(By.xpath(prop.getProperty("ModifyTime_Modify_Btn"))).click();
	  verify_RepairerTime_ErrorMsg(driver, "ModifyTime_ErrorMsg_Txt", ErrorMsg, methodName);
	  driver.findElement(By.xpath(prop.getProperty("ModifyTime_Logout_Btn"))).click();
  }
  
  @Test
  @FileParameters("tests/uta_facility_maintenance_system/chrome/TC04e_test_cases.csv")
  public void TC04e(int testCaseNumber, String Message) throws InterruptedException {
	  String methodName= new Throwable().getStackTrace()[0].getMethodName();
	  driver.get(sAppURL);
	  UFMS_LoginUser(driver, "charan123", "Charan@123");
	  MainApp_function(driver, FunctionEnum.listViewMyRepairs);
	  Thread.sleep(500);
	  driver.findElement(By.xpath(prop.getProperty("ViewReservedRepairs_View_Lnk"))).click();
	  driver.findElement(By.xpath(prop.getProperty("ViewSpecificRepair_Cancel_Btn"))).click();
	  verify_CancelRepair_Msg(driver, "CancelRepair_Msg_Txt", Message, methodName);
	  driver.findElement(By.xpath(prop.getProperty("CancelRepair_Logout_Btn"))).click();
  }
  
  @Test
  public void verifyAllLinks() throws Exception {
	  driver.get(sAppURL);
	  UFMS_LoginUser(driver, "charan123", "Charan@123");
	  driver.findElement(By.xpath(prop.getProperty("RepairerHomePage_RequestReservation_Lnk"))).click();
	  assertEquals("Request Reservation", driver.getTitle());
	  driver.findElement(By.xpath(prop.getProperty("RequestReservation_Home_Lnk"))).click();
	  driver.findElement(By.xpath(prop.getProperty("RepairerHomePage_ViewProfile_Lnk"))).click();
	  assertEquals("Modify User Details", driver.getTitle());
	  driver.findElement(By.xpath(prop.getProperty("ViewProfile_Home_Lnk"))).click();
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
