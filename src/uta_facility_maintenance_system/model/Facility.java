package uta_facility_maintenance_system.model;

import java.io.Serializable;

import uta_facility_maintenance_system.data.FacilityDAO;

public class Facility implements Serializable{
	
	private static final long serialVersionUID = 3L;
	private String facilityType;
	private String facilityName;
	private String interval;
	private String duration;
	private String venue;
//	private String status;
	private String bookedDuration;
	private String bookingStatus;
	private String bookedBy;
	private String greetingText;
	

	public void setFacilityDetails(String facilityType, String facilityName, String interval, String duration,
			String venue) {

		this.facilityType = facilityType;
		this.facilityName = facilityName;
		this.interval = interval;
		this.duration = duration;
		this.venue = venue;

	}
	
	public String getBookedDuration() {
		return bookedDuration;
	}

	public void setBookedDuration(String bookedDuration) {
		this.bookedDuration = bookedDuration;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getBookedBy() {
		return bookedBy;
	}

	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
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
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
        this.interval = interval;
	}
	
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public String getGreetingText() {
		return greetingText;
	}

	public void setGreetingText(String greetingText) {
		this.greetingText = greetingText;
	}
	
	public void validateInputFacilityDetails(String action, Facility newFac,
			FacilityErrorMsgs newFacErrMsgs) {
		
			newFacErrMsgs.setFacilityTypeError(validateFacilityType(newFac.getFacilityType()));
			newFacErrMsgs.setFacilityNameError(validateFacilityName(action, newFac.getFacilityName()));
			newFacErrMsgs.setIntervalError(validateInterval(newFac.getInterval()));
			newFacErrMsgs.setDurationError(validateDuration(newFac.getDuration()));
			newFacErrMsgs.setVenueError(validateVenue(newFac.getVenue()));
			newFacErrMsgs.setErrorMsg();
		
	}

	private String validateFacilityType(String facilityType) {
		String result = "";
		if (facilityType.equals("")) {
			result = "Facility Type cannot be empty";
		} else if (!stringSize(facilityType, 3, 50)) {
			result = "Your Facility Type must between 3 and 50 characters";
		}

		return result;

	}

	private String validateFacilityName(String action, String facilityName) {
		String result = "";
		if (facilityName.equals("")) {
			result = "Facility Name cannot be empty";
		} else if (!stringSize(facilityName, 3, 12)) {
			result = "Your Facility Name must between 3 and 12 characters";
		} else if(!FacilityDAO.facilityNameUnique(facilityName)) {
			result = "Facility already exists in database";
		}

		return result;

	}

	private String validateInterval(String interval) {
		String result = "";
		if (interval.equals("")) {
			result = "Interval cannot be empty";
		} else if (!stringSize(interval, 2, 8)) {
			result = "Your interval must between 2 and 8 characters";
		}

		return result;

	}

	private String validateDuration(String duration) {
		String result = "";
		if (duration.equals("")) {
			result = "Duration cannot be empty";
		} else if (!stringSize(duration, 3, 8)) {
			result = "Your duration must between 3 and 8 characters";
		}

		return result;

	}

	private String validateVenue(String venue) {
		String result = "";
		if (venue.equals("")) {
			result = "Venue cannot be empty";
		}
		return result;

	}

	private boolean stringSize(String string, int min, int max) {
		return string.length() >= min && string.length() <= max;
	}

}

	



