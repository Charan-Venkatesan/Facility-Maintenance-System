package uta_facility_maintenance_system.model;

import java.io.Serializable;
import java.util.ArrayList;

import uta_facility_maintenance_system.data.MarReportDAO;

public class MarReport implements Serializable {
	
	private static final long serialVersionUID = 3L;
	private String facilityType;
	private String facilityName;
	private String urgency;
	private String description;
	private String reportedBy;
	private String createdDate;
	private String marNumber;
	private String resultSetMAR;
	private String assignTo;
	private String assignTime;
	private String greetingText;
	private String userName;
	private String assignDate;
	private String estimateOfRepair;
	private String searchDate;
	
	public void setMarDetails(String facilityType, String facilityName,String urgency, String description) {
		this.facilityType = facilityType;
		this.facilityName = facilityName;
		this.urgency = urgency;
		this.description = description;
		this.marNumber = createMarNumber();
	}
	
	public void setMAC(String assigned_to, String mar_number, String estimate_of_repair) {
		setAssignTo(assigned_to);
		setMarNumber(mar_number);
		setEstimateOfRepair(estimate_of_repair);

	}
	
	public void validateMARReport(String action, MarReport createMAR, CreateMARErrorMsgs errorMsgs) {
		if (action.equals("saveMAR")) {
		    errorMsgs.setDescriptionError(validateDescription(createMAR.getDescription()));
			errorMsgs.setErrorMsg();
		}
		
	}
	
	public void validateTime(MarReport marReport, CreateMARErrorMsgs errorMsg) {
		if(marReport.assignTime == "") {
			errorMsg.setTimeError("Please enter a valid time");
			errorMsg.setErrorMsg();
		}
	}
	
	private String validateDescription(String description) {
		String result="";
		if (!stringSize(description,20,100))
			result= "Your Description must between 20 and 100 characters";
		return result;		
	}
	
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}

	
	public String getCreatedDate() {
		return createdDate;
	}
	public String getAssignTime() {
		return assignTime;
	}

	public void setAssignTime(String assignTime) {
		this.assignTime = assignTime;
	}

	

	public void setMarReport(String marNumber, String facilityName, String facilityType, String urgency,
			String description, String reportedBy, String createdDate, String assignTo, String assignDate, String assignTime,
			String estimateOfRepair) {

		setFacilityType(facilityType);
		setFacilityName(facilityName);
		setUrgency(urgency);
		setDescription(description);
		setReportedBy(reportedBy);
		setCreatedDate(createdDate);
		setMarNumber(marNumber);
		setAssignTo(assignTo);
		setAssignDate(assignDate);
		setEstimateOfRepair(estimateOfRepair);
		setAssignTime(assignTime);
	}

	
	/*public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}*/
	public String getResultSetMAR() {
		return resultSetMAR;
	}

	public void setResultSetMAR(String resultSetMAR) {
		this.resultSetMAR = resultSetMAR;
	}

	public String getGreetingText() {
		return greetingText;
	}

	public void setGreetingText(String greetingtext) {
		this.greetingText = greetingtext;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	
	public String getMarNumber() {
		return marNumber;
	}

	public void setMarNumber(String marNumber) {
		this.marNumber = marNumber;
	}

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

	public String getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(String assignDate) {
		this.assignDate = assignDate;
	}

	public String getEstimateOfRepair() {
		return estimateOfRepair;
	}

	public void setEstimateOfRepair(String estimateOfRepair) {
		this.estimateOfRepair = estimateOfRepair;
	}
	
	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
	
	public String createMarNumber() {
		MarReportDAO MarReportDAO = new MarReportDAO();
		ArrayList<MarReport> marList = MarReportDAO.generateMARNumber();
		String marNumber;

		if(marList.isEmpty())
		{
			marNumber = "MAR001";
		}
		else {
			MarReport cMar = marList.get(0);
			String marValue = cMar.getResultSetMAR();
			String valueAfterMar = marValue.substring(3,6);
			int value = Integer.parseInt(valueAfterMar);
			int increase = value+1;
			int len = String.valueOf(increase).length();
			if(len==1)
			{
				String padded = String.format("%03d" , increase);
				marNumber = "MAR" + padded;
				
			}
			else if (len==2)
			{
				String padded = String.format("%03d" , increase);
				marNumber = "MAR" + padded;	
			}
			else
			{
				marNumber = "MAR" + Integer.toString(increase); 
			}
		}
		return marNumber;
	}
	
	public boolean validateRules(String assignedto) {
		int count = MarReportDAO.searchcount(assignedto);
		int countWeek = MarReportDAO.searchcountMonth(assignedto);	
		if (count < 5 && countWeek < 10) {
			return true;
		}
		return false;
	}
	public boolean validateCount(String assignedto) {
		int count = MarReportDAO.searchcount(assignedto);
		if (count >= 5) {
			return true;
		}
		return false;
	}
	
}