package uta_facility_maintenance_system.model;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;  
import java.sql.Date; 

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class MarReportTest {
	
	MarReport mar;
	CreateMARErrorMsgs marErrorMsgs;
	
	@Before
	public void setUp() throws Exception {
		mar = new MarReport();
		marErrorMsgs = new CreateMARErrorMsgs();
	}

	@Test
	@FileParameters("tests/uta_facility_maintenance_system/model/MAR_Report_Test_cases1.csv")
	public void test(String TestCaseNo, String action,String facilityType, String facilityName, String urgency, String description,   
		    String estimateofrepair, String marNumber, String assignTo, 
			String reportedBy, String createdDate, String assignDate, String assignTime, 
			String count, String resultSetMAR, String userName, String searchDate, String greetingText, String errorMsg, String descriptionError, String timeError) {
		
		
		mar.setMarDetails(facilityType, facilityName, urgency, description);
		mar.validateMARReport(action, mar, marErrorMsgs);
		mar.setMAC(estimateofrepair, marNumber, assignTo);
		mar.validateTime(mar, marErrorMsgs);
		assertTrue(errorMsg.equals(marErrorMsgs.getErrorMsg()));
		assertTrue(descriptionError.equals(marErrorMsgs.getDescriptionError()));
		assertTrue(timeError.equals(marErrorMsgs.getTimeError()));
		
		
		@SuppressWarnings("unused")
		String adate= mar.getAssignDate();
		mar.setAssignDate(assignDate);
		String atime= mar.getAssignTime();
		mar.setAssignTime(assignTime);
		String aResult= mar.getResultSetMAR();
		mar.setResultSetMAR(resultSetMAR);
		
		String assignTorepairer = mar.getAssignTo();
		
		String agreet= mar.getGreetingText();
		mar.setGreetingText(greetingText);
		
		String aname= mar.getUserName();
		mar.setUserName(userName);
		
		String acreate= mar.getCreatedDate();
		mar.setCreatedDate(createdDate);
		
		String afacilityType= mar.getFacilityType();
		mar.setFacilityType(facilityType);
		
		String afacilityName= mar.getFacilityName();
		mar.setFacilityName(facilityName);
		
		String aurgency= mar.getUrgency();
		mar.setUrgency(urgency);
		
		String adescription= mar.getDescription();
		mar.setDescription(description);
		
		
		String arebortedBy= mar.getReportedBy();
		mar.setReportedBy(reportedBy);
		
		String amarnumber= mar.getMarNumber();
		mar.setMarNumber(marNumber);
		
		String aestimate= mar.getEstimateOfRepair();
		mar.setEstimateOfRepair(estimateofrepair);
		
		String searchdate= mar.getSearchDate();
		mar.setSearchDate(searchDate);
		
		mar.setMarReport(marNumber, facilityName, facilityType, urgency, descriptionError, reportedBy, createdDate, assignTo, assignDate, assignTime, estimateofrepair);
		
	}


}
