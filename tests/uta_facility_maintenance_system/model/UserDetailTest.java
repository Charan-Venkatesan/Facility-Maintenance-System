package uta_facility_maintenance_system.model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class UserDetailTest {
	UserDetail userDetail;
	UserDetailErrorMsgs userDetailErrorMsgs;

	@Before
	public void setUp() throws Exception {
		userDetail = new UserDetail();
		userDetailErrorMsgs = new UserDetailErrorMsgs();
	}

	@Test
	@FileParameters("tests/uta_facility_maintenance_system/model/User_Details_Test_Cases.csv")
	public void test(int TestCaseNo, String action, String username, String firstName, String lastName, String password, String confirmPassword,
			String userRole, String utaId, String phone, String email, String streetAddress, String city, String state, String zipCode, 
			String errorMsg, String userNameError, String firstNameError, String lastNameError, String passwordError,
			String confirmPasswordError, String userRoleError, String utaIdError, String phoneError, String emailError, 
			String streetAddressError, String cityError, String stateError,	String zipCodeError, String greetingText) {
		
		userDetail.setUserDetails( username, firstName, lastName, password, confirmPassword, userRole, utaId, phone, email, streetAddress, city, state, zipCode);
		@SuppressWarnings("unused")
		String dummy = userDetail.getGreetingText();
		userDetail.setGreetingText(greetingText);
		userDetail.validateUserDetail(action, userDetail, userDetailErrorMsgs);
		assertTrue(errorMsg.equals(userDetailErrorMsgs.getErrorMsg()));
		assertTrue(userNameError.equals(userDetailErrorMsgs.getUserNameError()));
		assertTrue(firstNameError.equals(userDetailErrorMsgs.getFirstNameError()));
		assertTrue(lastNameError.equals(userDetailErrorMsgs.getLastNameError()));
		assertTrue(passwordError.equals(userDetailErrorMsgs.getPasswordError()));
		assertTrue(confirmPasswordError.equals(userDetailErrorMsgs.getConfirmPasswordError()));
		assertTrue(userRoleError.equals(userDetailErrorMsgs.getUserRoleError()));
		assertTrue(utaIdError.equals(userDetailErrorMsgs.getUtaIdError()));
		assertTrue(phoneError.equals(userDetailErrorMsgs.getPhoneError()));
		assertTrue(emailError.equals(userDetailErrorMsgs.getEmailError()));
		assertTrue(streetAddressError.equals(userDetailErrorMsgs.getStreetAddressError()));
		assertTrue(cityError.equals(userDetailErrorMsgs.getCityError()));
		assertTrue(stateError.equals(userDetailErrorMsgs.getStateError()));
		assertTrue(zipCodeError.equals(userDetailErrorMsgs.getZipCodeError()));
	}
}
