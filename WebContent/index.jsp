<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UTA MAC Facility Maintenance</title>
</head>
<body>
	<div id="heading">
		<h2>Register</h2>
	</div>
	<input name="errMsg" value="<c:out value='${errorMsgs.loginErrorMsg}'/>"
		type="text"
		style="background-color: white; color: red; border: none; width: 800px"
		disabled="disabled">
	<table>
		<tr>
			<td>
				<form name="login" action="UserDetailController?action=login"
					method="post">
					<table style="width: 1300px;">
						<tr>
							<td>Username:*</td>
							<td><input type="text" name="usernameLogin"
								value="<c:out value='${userDetail.loginUserName}'/>" /></td>
							<td><input name="usernameErrorLogin"
								value="<c:out value='${errorMsgs.loginUserNameError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>Password:*</td>
							<td><input type="password" name="passwordLogin"
								value="<c:out value='${userDetail.loginPassword}'/>" /></td>
							<td><input name="usernameErrorLogin"
								value="<c:out value='${errorMsgs.loginPasswordError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>


						<tr>
							<td colspan="2">* Mandatory field</td>
						</tr>

					</table>

					<input name="action" value="login" type="hidden"> <input
						type="submit" name="submit" value="Login" /> 
						
						
					<input
						id="greetingField" value='${message.greetingText}'
						style="font-weight: bold; font-size: 20px; background-color: white; border: none; width: 1000px;"
						disabled="disabled" />
						
				</form>
			</td>
		</tr>
	</table>

	<div id="register">
		<a href="registration.jsp">New User?Register</a>
	</div>


</body>
</html>
