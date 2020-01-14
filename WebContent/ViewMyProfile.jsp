<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify User Details</title>
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
				<form name="modify_user" action="ModifyMyProfile.jsp" method="post">

					<table style="width: 1300px;">
						<tr>
							<td>Username:*</td>
							<td><input type="text" name="username"
								value="<c:out value='${viewSelectedUser.username}'/>"
								maxlength=12 disabled="disabled" /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.userNameError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>First Name:*</td>
							<td><input type="text" name="firstName"
								value="<c:out value='${viewSelectedUser.first_name}'/>"
								maxlength=20 disabled="disabled" /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.firstNameError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>


						<tr>
							<td>Last Name:*</td>
							<td><input type="text" name="lastName"
								value="<c:out value='${viewSelectedUser.last_name}'/>"
								maxlength=20 disabled="disabled" /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.lastNameError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>

						</tr>
						<tr>
							<td>User Role:*</td>
							<td><input type="text" name="user_role"
								value="<c:out value='${viewSelectedUser.user_role}'/>"
								maxlength=20 disabled="disabled" /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.userRoleError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>UTA ID:*</td>
							<td><input type="text" name="utaID"
								value="<c:out value='${viewSelectedUser.uta_id}'/>" maxlength=10 disabled="disabled" /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.utaIdError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>Phone:*</td>
							<td><input type="text" name="phone"
								value="<c:out value='${viewSelectedUser.phone}'/>" maxlength=10 disabled="disabled" /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.phoneError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>E-mail:*</td>
							<td><input type="text" name="email"
								value="<c:out value='${viewSelectedUser.email}'/>" maxlength=50 disabled="disabled" /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.emailError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>Street Address:*</td>
							<td><input type="text" name="address"
								value="<c:out value='${viewSelectedUser.street_address}'/>"
								maxlength=50 disabled="disabled" /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.streetAddressError}'/>"
								type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>City:*</td>
							<td><input type="text" name="city"
								value="<c:out value='${viewSelectedUser.city}'/>" maxlength=50 disabled="disabled" /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.cityError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>State:*</td>
							<td><input type="text" name="state"
								value="<c:out value='${viewSelectedUser.state}'/>" maxlength=50 disabled="disabled" /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.stateError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						<tr>
							<td>Zip Code:*</td>
							<td><input type="text" name="zipcode"
								value="<c:out value='${viewSelectedUser.zipcode}'/>" maxlength=6 disabled="disabled" /></td>
							<td><input name="usernameError"
								value="<c:out value='${errorMsgs.zipCodeError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>


						<tr>
							<td colspan="2">* Mandatory field</td>
						</tr>

					</table>
					<input type="hidden" name="action" value="ModifyMyProfile.jsp">
					<input type="submit" value="Modify Profile"><br>
					<a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a><br>
					<a href="/uta_facility_maintenance_system/UserDetailController?action=logout"
						 target="_top"><span>LogOut</span></a>
				</form>
					
			</td>
		</tr>
	</table>
</body>
</html>