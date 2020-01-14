package uta_facility_maintenance_system.model;

public class UserDetailErrorMsgs {
	private String errorMsg;
	private String userNameError;
	private String firstNameError;
	private String lastNameError;
	private String passwordError;
	private String confirmPasswordError;
	private String userRoleError;
	private String utaIdError;
	private String phoneError;
	private String emailError;
	private String streetAddressError;
	private String cityError;
	private String stateError;
	private String zipCodeError;
	private String loginErrorMsg;
	private String loginUserNameError;
	private String loginPasswordError;

	public UserDetailErrorMsgs() {
		this.errorMsg = "";
		this.userNameError = "";
		this.firstNameError = "";
		this.lastNameError = "";
		this.passwordError = "";
		this.confirmPasswordError = "";
		this.userRoleError = "";
		this.utaIdError = "";
		this.phoneError = "";
		this.emailError = "";
		this.streetAddressError = "";
		this.cityError = "";
		this.stateError = "";
		this.zipCodeError = "";
		this.loginUserNameError = "";
		this.loginPasswordError = "";
		this.loginErrorMsg = "";

	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg() {
		if (!userNameError.equals("") || !firstNameError.equals("") || !lastNameError.equals("")
				|| !passwordError.equals("") || !confirmPasswordError.equals("") || !utaIdError.equals("")
				|| !phoneError.equals("") || !emailError.equals("") || !streetAddressError.equals("")
				|| !cityError.equals("") || !stateError.equals("") || !zipCodeError.equals("")
				|| !userRoleError.equals("")) {
			this.errorMsg = "Please correct the following errors";
		}
	}
	
	
	public String getLoginErrorMsg() {
		return loginErrorMsg;
	}
	
	public void setLoginErrorMsg() {
		if(!loginUserNameError.equals("") || !loginPasswordError.equals("")) {
			this.loginErrorMsg = "Please Correct the following error";
		}
	}

	public String getUserNameError() {
		return userNameError;
	}

	public void setUserNameError(String userNameError) {
		this.userNameError = userNameError;
	}

	public String getFirstNameError() {
		return firstNameError;
	}

	public void setFirstNameError(String firstNameError) {
		this.firstNameError = firstNameError;
	}

	public String getLastNameError() {
		return lastNameError;
	}

	public void setLastNameError(String lastNameError) {
		this.lastNameError = lastNameError;
	}

	public String getPasswordError() {
		return passwordError;
	}

	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}

	public String getConfirmPasswordError() {
		return confirmPasswordError;
	}

	public void setConfirmPasswordError(String confirmPasswordError) {
		this.confirmPasswordError = confirmPasswordError;
	}

	public String getUserRoleError() {
		return userRoleError;
	}

	public void setUserRoleError(String userRoleError) {
		this.userRoleError = userRoleError;
	}

	public String getUtaIdError() {
		return utaIdError;
	}

	public void setUtaIdError(String utaIdError) {
		this.utaIdError = utaIdError;
	}

	public String getPhoneError() {
		return phoneError;
	}

	public void setPhoneError(String phoneError) {
		this.phoneError = phoneError;
	}

	public String getEmailError() {
		return emailError;
	}

	public void setEmailError(String emailError) {
		this.emailError = emailError;
	}

	public String getStreetAddressError() {
		return streetAddressError;
	}

	public void setStreetAddressError(String streetAddressError) {
		this.streetAddressError = streetAddressError;
	}

	public String getCityError() {
		return cityError;
	}

	public void setCityError(String cityError) {
		this.cityError = cityError;
	}

	public String getStateError() {
		return stateError;
	}

	public void setStateError(String stateError) {
		this.stateError = stateError;
	}

	public String getZipCodeError() {
		return zipCodeError;
	}

	public void setZipCodeError(String zipCodeError) {
		this.zipCodeError = zipCodeError;
	}

	public String getLoginUserNameError() {
		return loginUserNameError;
	}

	public void setLoginUserNameError(String loginUserNameError) {
		this.loginUserNameError = loginUserNameError;
	}

	public String getLoginPasswordError() {
		return loginPasswordError;
	}

	public void setLoginPasswordError(String loginPasswordError) {
		this.loginPasswordError = loginPasswordError;
	}

}
