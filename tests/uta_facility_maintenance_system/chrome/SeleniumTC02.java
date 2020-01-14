package uta_facility_maintenance_system.chrome;

import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import java.util.List;
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
import java.util.ArrayList;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;


@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTC02 extends UFMSFunctions {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public static String sAppURL, sSharedUIMapPath;
  static SQLConnection DBMgr = SQLConnection.getInstance();

  @Before
  public void setUp() throws Exception {
	  System.setProperty("webdriver.chrome.driver", "c:/ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver();
      prop = new Properties();
      prop.load(new FileInputStream("./Configuration/UFMS_Configuration.properties"));
      sAppURL = prop.getProperty("sAppURL");
      prop.load(new FileInputStream(prop.getProperty("sSharedUIMapPath")));
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      
  }
  private static ArrayList<MarReport> returnMatchingMARList(String queryString) {
	ArrayList<MarReport> listInDB = new ArrayList<MarReport>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				MarReport mcfm = new MarReport();
				mcfm.setMarNumber(userList.getString("mar_number"));				
				mcfm.setEstimateOfRepair(userList.getString("estimate_of_repair"));
				mcfm.setAssignTo(userList.getString("assigned_to"));
				mcfm.setUrgency(userList.getString("urgency"));
				mcfm.setFacilityType(userList.getString("facility_type"));
				mcfm.setFacilityName(userList.getString("facility_name"));
				mcfm.setDescription(userList.getString("description"));
				mcfm.setReportedBy(userList.getString("reported_by"));
				mcfm.setCreatedDate(userList.getString("created_date"));
				listInDB.add(mcfm);
			}
		} catch (SQLException e) {
		}
		System.err.println("printing query string" + queryString);
		return listInDB;
	}
  
  public static ArrayList<MarReport> listUnassignedMAR() {
	return returnMatchingMARList("SELECT * from mar_report WHERE assigned_to is null ORDER BY mar_number");
  }

  private String [][] getUnassignedMARListFromDB(int listSize) throws SQLException { // this method gets the list assign repairs table contents from the DB
	    ArrayList<MarReport> fromDB = listUnassignedMAR();
	    String [][] arrayDB = new String [listSize-1][7];
	    int i = 0;
	    for (MarReport p:fromDB) {
	    	arrayDB[i][0] = p.getFacilityType();
	    	arrayDB[i][1] = p.getFacilityName();
	    	arrayDB[i][2] = p.getUrgency();
	    	arrayDB[i][3] = p.getDescription();
	    	arrayDB[i][4] = p.getReportedBy();
	    	arrayDB[i][5] = p.getCreatedDate();
	    	arrayDB[i][6] = p.getMarNumber();
	 		i++;
	    }
	    return arrayDB;
}
  private String [][] getUnassignedMARListContentFromTable(int rows, List<WebElement> rowValues, int columns) throws Exception { 
	
	  String [][] UnassignMARArray = new String[rows-1][columns];	  
	  for(int i=1; i < rows; i++) {
		  List<WebElement> colVals = rowValues.get(i).findElements(By.tagName("td"));
		  for(int j=0; j < columns; j++) {
			  UnassignMARArray[i-1][j] = colVals.get(j).getText();
			  System.out.println(UnassignMARArray[i-1][j]);
		  }
	  }
	  return UnassignMARArray;
  }
  private Boolean arraysDiff (String [][] array1, String [][] array2) { // this method compares the contents of the two tables
	  Boolean diff = false || (array1.length != array2.length);
	  for (int i = 0; i < array1.length && !diff; i++) {
		 diff  = !array1[i][0].equals(array2[i][0]) || !array1[i][1].equals(array2[i][1]) || 
				 !array1[i][2].equals(array2[i][2]) || !array1[i][3].equals(array2[i][3]) ||
				 !array1[i][4].equals(array2[i][4]) || !array1[i][5].equals(array2[i][5]) ||
				 !array1[i][6].equals(array2[i][6]);
	  }
	  return diff;
  }
  
  @Test
  public void verifyAllLinks() throws Exception {
	  driver.get(sAppURL);
	  UFMS_LoginUser(driver,"pavi0324", "Pavi@123");
	  
	  // Check Create MAR Title
	  driver.findElement(By.xpath(prop.getProperty("FacilityManager_CreateMARNumber"))).click();
	  assertEquals("Create MAR", driver.getTitle());
	  Thread.sleep(1_000);
	  driver.findElement(By.xpath(prop.getProperty("CreateMARNumber_HomeLink"))).click();
	  
	  //Check Add new Facility Title
	  driver.findElement(By.xpath(prop.getProperty("FacilityManager_AddNewFacility"))).click();
	  assertEquals("ADD NEW FACILITY", driver.getTitle());
	  Thread.sleep(1_000);
	  driver.findElement(By.xpath(prop.getProperty("AddNewFacility_HomeLink"))).click();
	  
	  //Check Search Repairer By Date Title
	  driver.findElement(By.xpath(prop.getProperty("FacilityManager_SearchRepairerByDate"))).click();
	  assertEquals("Search Repairer Schedule", driver.getTitle());
	  Thread.sleep(1_000);
	  driver.findElement(By.xpath(prop.getProperty("SearchRepairerByDate_HomeLink"))).click();

	  
	  // Check Assigned MARS Title
	  driver.findElement(By.xpath(prop.getProperty("FacilityManager_SearchAssignedMARSByDate"))).click();
	  assertEquals("Search Assigned MARs", driver.getTitle());
	  Thread.sleep(1_000);
	  driver.findElement(By.xpath(prop.getProperty("SearchAssignedMARSByDate_HomeLink"))).click();
	  
	  //Check View all MARS Title
	  driver.findElement(By.xpath(prop.getProperty("FacilityManager_ViewAllMARS"))).click();
	  assertEquals("MAR List", driver.getTitle());
	  Thread.sleep(1_000);
	  driver.findElement(By.xpath(prop.getProperty("ViewAllMARS_HomeLink"))).click();
	  
	  // Check View Available Facilities Title
	  driver.findElement(By.xpath(prop.getProperty("FacilityManager_ViewAvailableFacilities"))).click();
	  assertEquals("Facilities List", driver.getTitle());
	  Thread.sleep(1_000);
	  driver.findElement(By.xpath(prop.getProperty("ViewAvailableFacilities_HomeLink"))).click();
	  
	  // Check Search Facilities Title
	  driver.findElement(By.xpath(prop.getProperty("FacilityManager_SearchFacilities"))).click();
	  assertEquals("Search Facilities", driver.getTitle());
	  Thread.sleep(1_000);
	  driver.findElement(By.xpath(prop.getProperty("SearchFacilities_HomeLink"))).click();
	  
	  
	  //Check View Specific MAR Title
	  driver.findElement(By.xpath(prop.getProperty("FacilityManager_ViewSpecificMAR"))).click();
	  assertEquals("View Specific MAR", driver.getTitle());
	  Thread.sleep(1_000);
	  driver.findElement(By.xpath(prop.getProperty("ViewSpecificMAR_HomeLink"))).click();
	  
	  driver.findElement(By.xpath(prop.getProperty("FacilityManager_Logout"))).click();
  }
  
  @Test
  public void testSeleniumTC02a() throws Exception {
    driver.get(sAppURL);
    String methodName= new Throwable().getStackTrace()[0].getMethodName();  
    // Click Register Link
    //driver.findElement(By.linkText(prop.getProperty("Login_Register_Lnk"))).click();
    //USer Registration
    UFMS_UserRegistraton(driver, "pavi0324", "Pavithra", "Rathinasabapathy", "Pavi@123", "Pavi@123", "Facility Manager", 
	    				  "1001698735", "1234567891", "pavi.rathina@gmail.com","1006 greek row", "Arlington", "Texas", "76013");
    //Login
    UFMS_LoginUser(driver,"pavi0324", "Pavi@123");     
    MainApp_function(driver,FunctionEnum.searchUnassignedMARS); 
	Thread.sleep(1_000);
	
	verifyHeaders_UnassignedMARS(driver, "SearchUnassignedMAR_FacilityType", "FacilityType", "SearchUnassignedMAR_FacilityName", "FacilityName", "SearchUnassignedMAR_Urgency", "Urgency", "SearchUnassignedMAR_Description",
								"Description", "SearchUnassignedMAR_ReportedBy","Reported by","SearchUnassignedMAR_ReportedDate", "Reported date", 
								"SearchUnassignedMAR_MARNumber", "Marnumber", methodName + "_verifyHeaders_test_case ");
	WebElement unAssigned_MAR_Table = driver.findElement(By.xpath(prop.getProperty("SearchUnassignedMAR_MARTable")));
	int rows = unAssigned_MAR_Table.findElements(By.tagName("tr")).size();
	int columns = unAssigned_MAR_Table.findElements(By.xpath("//*[@id='myMarTable']/tbody/tr[1]/th")).size();
	System.out.println(columns);
	List<WebElement> rowValues = unAssigned_MAR_Table.findElements(By.tagName("tr"));
	assertFalse(arraysDiff(getUnassignedMARListFromDB(rows), getUnassignedMARListContentFromTable(rows, rowValues, columns)));	
	driver.findElement(By.xpath(prop.getProperty("SearchUnassignedMAR_HomeLink"))).click();
  }
 
  
  @Test
  @FileParameters("tests/uta_facility_maintenance_system/chrome/TC02_test_cases.csv")
  public void testSeleniumTC02b(String testCaseNumber, String MARNumberFromCSV, String path, String estimate_of_Repair, String repairer, String GreetingText) throws Exception {  
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	driver.get(sAppURL);
	UFMS_LoginUser(driver,"pavi0324", "Pavi@123"); 
	MainApp_function(driver,FunctionEnum.searchUnassignedMARS);
	Thread.sleep(1_000);
	String MARNo = driver.findElement(By.xpath(prop.getProperty("SearchUnassignedMAR_MARNumberValue"))).getText();
	Thread.sleep(1_000);
	driver.findElement(By.xpath(path)).click();
	Thread.sleep(1_000);	
    UFMS_AssignMAR(driver, estimate_of_Repair, repairer, MARNo,GreetingText, methodName +"_test_case_" + testCaseNumber  );
    UFMS_Logout(driver);   		
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
