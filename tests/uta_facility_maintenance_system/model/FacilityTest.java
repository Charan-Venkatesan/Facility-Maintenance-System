package uta_facility_maintenance_system.model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class FacilityTest {

	Facility facility;
	FacilityErrorMsgs facilityErrorMsgs;
	
	@Before
	public void setUp() throws Exception {
		facility = new Facility();
		facilityErrorMsgs = new FacilityErrorMsgs();
	}
	
	@Test
	@FileParameters("tests/uta_facility_maintenance_system/model/Add_Facility_Test_Cases.csv")
	public void test(String TestCaseNo, String action, String facilityType, String facilityName, String interval, String duration, String venue,
			String errorMsg, String facilityTypeError, String facilityNameError, String intervalError, String durationError, String venueError, String bookedDuration,
			String bookingStatus, String bookedBy, String greetingText) {
		
		facility.setFacilityDetails(facilityType, facilityName, interval, duration, venue);
		@SuppressWarnings("unused")
		String dummy = facility.getBookedBy();
		facility.setBookedBy(bookedBy);
		facility.getBookingStatus();
		facility.setBookingStatus(bookingStatus);
		facility.getGreetingText();
		facility.getBookedDuration();
		facility.setBookedDuration(bookedDuration);
		facility.setGreetingText(greetingText);
		facility.validateInputFacilityDetails(action, facility, facilityErrorMsgs);
		assertTrue(errorMsg.equals(facilityErrorMsgs.getErrorMsg()));
		assertTrue(facilityTypeError.equals(facilityErrorMsgs.getFacilityTypeError()));
		assertTrue(facilityNameError.equals(facilityErrorMsgs.getFacilityNameError()));
		assertTrue(intervalError.equals(facilityErrorMsgs.getIntervalError()));
		assertTrue(durationError.equals(facilityErrorMsgs.getDurationError()));
		assertTrue(venueError.equals(facilityErrorMsgs.getVenueError()));
	}

}
