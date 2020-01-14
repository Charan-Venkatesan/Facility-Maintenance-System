<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<div id="heading">
		<h2>Register</h2>
	</div>
	<input name="errMsg" value="<c:out value='${errorMsgs.errorMsg}'/>"
		type="text"
		style="background-color: white; color: red; border: none; width: 800px"
		disabled="disabled">
	<table>
		<tr>
			<td>
				<form name="account_registration"
					action="UserDetailController?saveUserDetail" method="post">

					<table style="width: 1300px;">
						<tr>
							<td>Username:*</td>
							<td><input type="text" name="username"
								value="<c:out value='${userDetail.username}'/>"/></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.userNameError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>First Name:*</td>
							<td><input type="text" name="firstName"
								value="<c:out value='${userDetail.first_name}'/>" maxlength=20 /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.firstNameError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>


						<tr>
							<td>Last Name:*</td>
							<td><input type="text" name="lastName"
								value="<c:out value='${userDetail.last_name}'/>" maxlength=20 /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.lastNameError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>

						</tr>

						<tr>
							<td>Password:*</td>
							<td><input type="password" name="password"
								value="<c:out value='${userDetail.password}'/>"  /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.passwordError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="100"></td>
						</tr>

						<tr>
							<td>Confirm Password:*</td>
							<td><input type="password" name="confirmPassword"
								value="<c:out value='${userDetail.confirm_password}'/>"
								/></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.confirmPasswordError}'/>"
								type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>User Role:*</td>
							<td><select name="user_role" >
									<option value = "" selected="selected">Select a Role</option>
									<option value = "Admin">Admin</option>
									<option value = "Facility Manager">Facility
										Manager</option>
									<option value = "User">User</option>
									<option value = "Repairer">Repairer</option>
							</select></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.userRoleError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>UTA ID:*</td>
							<td><input type="text" name="utaID"
								value="<c:out value='${userDetail.uta_id}'/>" /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.utaIdError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>Phone:*</td>
							<td><input type="text" name="phone"
								value="<c:out value='${userDetail.phone}'/>" /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.phoneError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>E-mail:*</td>
							<td><input type="text" name="email"
								value="<c:out value='${userDetail.email}'/>"/></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.emailError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>Street Address:*</td>
							<td><input type="text" name="address"
								value="<c:out value='${userDetail.street_address}'/>"/></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.streetAddressError}'/>"
								type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>City:*</td>
							<td><input type="text" name="city"
								value="<c:out value='${userDetail.city}'/>" /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.cityError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>State:*</td>
							<td><select name="state">
									<option value = "" selected="selected">Select a State</option>
									<option value = "Alabama">Alabama</option>
									<option value = "Alaska">Alaska</option>
									<option value = "Arizona">Arizona</option>
									<option value = "Arkansas">Arkansas</option>
									<option value = "California">California</option>
									<option value = "Colorado">Colorado</option>
									<option value = "Connecticut">Connecticut</option>
									<option value = "Delaware">Delaware</option>
									<option value = "District Of Columbia">District Of Columbia</option>
									<option value = "Florida">Florida</option>
									<option value = "Georgia">Georgia</option>
									<option value = "Hawaii">Hawaii</option>
									<option value = "Idaho">Idaho</option>
									<option value = "Illinois">Illinois</option>
									<option value = "Indiana">Indiana</option>
									<option value = "Iowa">Iowa</option>
									<option value = "Kansas">Kansas</option>
									<option value = "Kentucky">Kentucky</option>
									<option value = "Louisiana">Louisiana</option>
									<option value = "Maine">Maine</option>
									<option value = "Maryland">Maryland</option>
									<option value = "Massachusetts">Massachusetts</option>
									<option value = "Michigan">Michigan</option>
									<option value = "Minnesota">Minnesota</option>
									<option value = "Mississippi">Mississippi</option>
									<option value = "Missouri">Missouri</option>
									<option value = "Montana">Montana</option>
									<option value = "Nebraska">Nebraska</option>
									<option value = "Nebraska">Nevada</option>
									<option value = "New Hampshire">New Hampshire</option>
									<option value = "New Jersey">New Jersey</option>
									<option value = "New Mexico">New Mexico</option>
									<option value = "New York">New York</option>
									<option value = "North Carolina">North Carolina</option>
									<option value = "North Dakota">North Dakota</option>
									<option value = "Ohio">Ohio</option>
									<option value = "Oklahoma">Oklahoma</option>
									<option value = "Oregon">Oregon</option>
									<option value = "Pennsylvania">Pennsylvania</option>
									<option value = "Rhode Island">Rhode Island</option>
									<option value = "South Carolina">South Carolina</option>
									<option value = "South Dakota">South Dakota</option>
									<option value = "Tennessee">Tennessee</option>
									<option value = "Texas">Texas</option>
									<option value = "Utah">Utah</option>
									<option value = "Vermont">Vermont</option>
									<option value = "Virginia">Virginia</option>
									<option value = "Washington">Washington</option>
									<option value = "West Virginia">West Virginia</option>
									<option value = "Wisconsin">Wisconsin</option>
									<option value = "Wyoming">Wyoming</option>
							</select></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.stateError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						<tr>
							<td>Zip Code:*</td>
							<td><input type="text" name="zipcode"
								value="<c:out value='${userDetail.zipcode}'/>"  /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.zipCodeError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>


						<tr>
							<td colspan="2">* Mandatory field</td>
						</tr>

					</table>
					<input type="hidden" name="action" value="saveUserDetail">
					<input type="submit" value="Register">
					<input type = "reset" value = "Reset">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>