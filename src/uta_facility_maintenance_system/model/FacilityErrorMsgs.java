package uta_facility_maintenance_system.model;

public class FacilityErrorMsgs {

	private String errorMsg;
	private String facilityNameError;
	private String facilityTypeError;
	private String intervalError;
	private String durationError;
	private String venueError;
	
	public FacilityErrorMsgs() {
		super();
		this.errorMsg = "";
		this.facilityNameError = "";
		this.facilityTypeError = "";
		this.intervalError = "";
		this.durationError = "";
		this.venueError = "";
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg() {
		if (!facilityNameError.equals("") || !facilityTypeError.equals("") || !intervalError.equals("") || !durationError.equals("") || !venueError.equals(""))
			this.errorMsg = "Please correct the following errors";
		
	}

	public String getFacilityNameError() {
		return facilityNameError;
	}

	public void setFacilityNameError(String facilityNameError) {
		this.facilityNameError = facilityNameError;
	}

	public String getFacilityTypeError() {
		return facilityTypeError;
	}

	public void setFacilityTypeError(String facilityTypeError) {
		this.facilityTypeError = facilityTypeError;
	}

	public String getIntervalError() {
		return intervalError;
	}

	public void setIntervalError(String intervalError) {
		this.intervalError = intervalError;
	}

	public String getDurationError() {
		return durationError;
	}

	public void setDurationError(String durationError) {
		this.durationError = durationError;
	}

	public String getVenueError() {
		return venueError;
	}

	public void setVenueError(String venueError) {
		this.venueError = venueError;
	}
	
	
	
}
