<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="heading">
		<h2>Modify User Details</h2>
	</div>
	<input name="errMsg" value="<c:out value='${errorMsgs.errorMsg}'/>"
		type="text"
		style="background-color: white; color: red; border: none; width: 800px"
		disabled="disabled">
	<table>
		<tr>
			<td>
				<form name="modify_user"
					action="/uta_facility_maintenance_system/UserDetailController?modifyUserDetail" method="post">

					<table style="width: 1300px;">
						<tr>
							<td>Username:*</td>
							<td><input type="text" name="username"
								value="<c:out value='${viewSelectedUser.username}'/>" maxlength=12 /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.userNameError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>First Name:*</td>
							<td><input type="text" name="firstName"
								value="<c:out value='${viewSelectedUser.first_name}'/>" maxlength=20 /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.firstNameError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>


						<tr>
							<td>Last Name:*</td>
							<td><input type="text" name="lastName"
								value="<c:out value='${viewSelectedUser.last_name}'/>" maxlength=20 /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.lastNameError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>

						</tr>
						<tr>
							<td>User Role:*</td>
							<td><select name="user_role" value="<c:out value='${viewSelectedUser.user_role}'/>">
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
								value="<c:out value='${viewSelectedUser.uta_id}'/>" maxlength=10 /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.utaIdError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>Phone:*</td>
							<td><input type="text" name="phone"
								value="<c:out value='${viewSelectedUser.phone}'/>" maxlength=10 /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.phoneError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>E-mail:*</td>
							<td><input type="text" name="email"
								value="<c:out value='${viewSelectedUser.email}'/>" maxlength=50 /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.emailError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>Street Address:*</td>
							<td><input type="text" name="address"
								value="<c:out value='${viewSelectedUser.street_address}'/>"
								maxlength=50 /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.streetAddressError}'/>"
								type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>City:*</td>
							<td><input type="text" name="city"
								value="<c:out value='${viewSelectedUser.city}'/>" maxlength=50 /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.cityError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>State:*</td>
							<td><select name="state" value="<c:out value='${viewSelectedUser.state}'/>">
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
								value="<c:out value='${viewSelectedUser.zipcode}'/>" maxlength=6 /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.zipCodeError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>


						<tr>
							<td colspan="2">* Mandatory field</td>
						</tr>

					</table>
					<input type="hidden" name="action" value="modifyUserDetail">
					<input type="submit" value="Modify User">
					<a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a><br>
					<a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a>
				</form>
				<br>
    				<input id="successid" value='${formtext.greetingText}' style ="font-weight: bold; font-size: 20px; background-color: white; border: none; width: 1000px;" disabled="disabled" />
			</td>
		</tr>
	</table>
</body>
</html>