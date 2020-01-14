package uta_facility_maintenance_system.model;

import java.io.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uta_facility_maintenance_system.data.UserDetailDAO;

public class UserDetail implements Serializable {
	private static final long serialVersionUID = 3L;
	private String username;
	private String first_name;
	private String last_name;
	private String password;
	private String confirm_password;
	private String user_role;
	private String uta_id;
	private String phone;
	private String email;
	private String street_address;
	private String city;
	private String state;
	private String zipcode;
	private Pattern pattern;
	private Matcher matcher;
	private String greetingText;
	private String loginUserName;
	private String loginPassword;
	private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,12})";

	public void setLoginDetails(String loginUserName, String loginPassword) {
		setLoginUserName(loginUserName);
		setLoginPassword(loginPassword);
	}

	public void validateLoginDetails(String action, UserDetail userDetailObject,
			UserDetailErrorMsgs userDetailerrMsgObject) {
		userDetailerrMsgObject.setLoginUserNameError(validateLoginUserName(userDetailObject.getLoginUserName()));
		userDetailerrMsgObject.setLoginPasswordError(validateLoginPassword(userDetailObject.getLoginPassword()));
		userDetailerrMsgObject.setLoginErrorMsg();

	}

	private String validateLoginUserName(String loginUserName) {
		String result = "";
		if (loginUserName.equals("")) {
			result = "Username Should not be empty";
		}
		return result;
	}

	private String validateLoginPassword(String loginPassword) {
		String result = "";
		if (loginPassword.equals("")) {
			result = "Password cannot be blank";

		}

		return result;
	}

	public void setUserDetails(String username, String first_name, String last_name, String password,
			String confirm_password, String user_role, String uta_id, String phone, String email, String street_address,
			String city, String state, String zipcode) {
		setUsername(username);
		setFirst_name(first_name);
		setLast_name(last_name);
		setPassword(password);
		setConfirm_password(confirm_password);
		setUser_role(user_role);
		setUta_id(uta_id);
		setPhone(phone);
		setEmail(email);
		setStreet_address(street_address);
		setCity(city);
		setState(state);
		setZipcode(zipcode);
	}

	public void validateUserDetail(String action, UserDetail userDetailObject,
			UserDetailErrorMsgs userDetailerrMsgObject) {
		if (action.equals("saveUserDetail")) {
			userDetailerrMsgObject.setPasswordError(validatePassword(userDetailObject.getPassword()));
			userDetailerrMsgObject.setConfirmPasswordError(
					validateConfirmPassword(userDetailObject.getConfirm_password(), userDetailObject.getPassword()));

			userDetailerrMsgObject.setUserNameError(validateUserName(action, userDetailObject.getUsername()));
			userDetailerrMsgObject.setFirstNameError(validateFirstName(userDetailObject.getFirst_name()));
			userDetailerrMsgObject.setLastNameError(validateLastName(userDetailObject.getLast_name()));

			userDetailerrMsgObject.setUserRoleError(validateUserRole(userDetailObject.getUser_role()));
			userDetailerrMsgObject.setUtaIdError(validateUTAId(userDetailObject.getUta_id()));
			userDetailerrMsgObject.setPhoneError(validatePhone(userDetailObject.getPhone()));
			userDetailerrMsgObject.setEmailError(validateEmail(userDetailObject.getEmail()));
			userDetailerrMsgObject.setStreetAddressError(validateStreetAddress(userDetailObject.getStreet_address()));
			userDetailerrMsgObject.setCityError(validateCity(userDetailObject.getCity()));
			userDetailerrMsgObject.setStateError(validateState(userDetailObject.getState()));
			userDetailerrMsgObject.setZipCodeError(validateZipcode(userDetailObject.getZipcode()));
			userDetailerrMsgObject.setErrorMsg();
		}
	}

	private String validateUserName(String action, String username) {
		String result = "";
		if (username.equals("")) {
			result = "Username should not be empty";
		} else {
			if (isTextAnInteger(username)) {
				result = "Username must be a text";
			} else {

				if (!stringSize(username, 3, 12)) {
					result = "Username must be between 3 and 12 characters";
				} else if (Character.isUpperCase(username.charAt(0))) {
					result = "Username should start with only lower case";
				} else if (username.contains("@") || username.contains("!") || username.contains("*")
						|| username.contains("$")) {
					result = "Username cannot have special characters";
				} else if (!UserDetailDAO.userNameUnique(username))
					result = "Username already in database";
			}

		}

		return result;
	}

	// Validating firstName
	private String validateFirstName(String firstName) {
		String result = "";
		if (firstName.equals("")) {
			result = "First name should not be empty";
		}
		if (isTextAnInteger(firstName)) {
			result = "First Name should be only in text format";
		}
		if (firstName.contains("@") || firstName.contains("!") || firstName.contains("*") || firstName.contains("$")) {
			result = "First name cannot have special characters";
		}
		return result;
	}

	// Validating lastName
	private String validateLastName(String lastName) {
		String result = "";
		if (lastName.equals("")) {
			result = "Last name should not be empty";
		}
		if (isTextAnInteger(lastName)) {
			result = "Last Name should be only in text format";
		}
		if (lastName.contains("@") || lastName.contains("!") || lastName.contains("*") || lastName.contains("$")) {
			result = "Last name cannot have special characters";
		}
		return result;
	}

	// Validating Password using regex
	private String validatePassword(String password) {
		String result = "";
		boolean answer;
		if (password.equals("")) {
			result = "Password cannot be blank";
		} else {
			pattern = Pattern.compile(PASSWORD_PATTERN);
			matcher = pattern.matcher(password);
			answer = matcher.matches();
			if (!(answer)) {
				result = "Password should contain 1 UPPERCASE 1 LOWERCASE 1 SPECIAL CHARACTER 1 NUMBER and should be of length 8 to 12 characters.";
			}
		}

		return result;
	}

	// Validating Confirm Password
	private String validateConfirmPassword(String confirmPassword, String password) {
		String result = "";
		if (confirmPassword.equals("")) {
			result = "Confirm Password cannot be blank";
		}
		if (!(confirmPassword.equals(password))) {
			result = "Password did not match.";
		}
		return result;
	}

	// Validating UTA ID
	private String validateUTAId(String utaid) {
		String result = "";
		if (utaid.length() != 10) {
			result = "UTA ID should be 10 digits in length";
		}
		if (!isTextAnInteger(utaid)) {
			result = "UTA ID should must be a number";
		}
		return result;

	}

	// Validate Phone
	private String validatePhone(String phone) {
		String result = "";
		if (phone.length() != 10)
			result = "Phone number must be 10 digits in length";
		if (!isTextAnInteger(phone))
			result = "Phone number must be a number";
		return result;
	}

	// Validating email
	private String validateEmail(String email) {
		String result = "", extension = "";

		if (!stringSize(email, 7, 30))
			result = "Email address must be between 7 and 30 characters long";
		else if (!email.contains("@"))
			result = "Email address needs to contain @";
		else {
			extension = email.substring(email.length() - 4, email.length());
			if (!extension.equals(".edu") && !extension.equals(".com"))
				result = "Invalid domain name";
		}
		return result;
	}

	// Validating Street Address
	private String validateStreetAddress(String streetAddress) {
		String result = "";
		if (streetAddress.equals("")) {
			result = "Address cannot be blank";
		}
		return result;
	}

	// Validating city
	private String validateCity(String city) {
		String result = "";
		if (city.equals("")) {
			result = "City cannot be blank";
		}
		return result;
	}

	// Validating zipcode
	private String validateZipcode(String zipcode) {
		String result = "";
		if (zipcode.equals("")) {
			result = "Zipcode cannot be blank";
		} else if (!(zipcode.length() == 5)) {
			result = "Zipcode can be only 5 digits";
		}

		return result;
	}

	// Validating user_rolse
	private String validateUserRole(String user_role) {
		String result = "";
		if (user_role.equals("")) {
			result = "Please select a user role";
		}
		return result;
	}

	// Validating State
	private String validateState(String state) {
		String result = "";
		if (state.equals("")) {
			result = "Please select a State";
		}
		return result;

	}

	private boolean stringSize(String string, int min, int max) {
		return string.length() >= min && string.length() <= max;
	}

	private boolean isTextAnInteger(String string) {
		boolean result;
		try {
			Long.parseLong(string);
			result = true;
		} catch (NumberFormatException e) {
			result = false;
		}
		return result;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	public String getUta_id() {
		return uta_id;
	}

	public void setUta_id(String uta_id) {
		this.uta_id = uta_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet_address() {
		return street_address;
	}

	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getGreetingText() {
		return greetingText;
	}

	public void setGreetingText(String greetingText) {
		this.greetingText = greetingText;
	}

	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

}
