<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify Repair Time</title>
</head>
<body>
<h1>Please enter the repair time</h1>
<input name="errMsg" value="<c:out value='${errorMsgs.errorMsg}'/>"
		type="text"
		style="background-color: white; color: red; border: none; width: 800px"
		disabled="disabled">
<form name="modifytime" action="MarReportController?action=modifytime&id=${MARS.marNumber}" method="post">
<table>
		<tr>
			<td>
				<table border="1" class="myTable">
					<tr>
						<td>Mar Number:</td>
						<td>${MARS.marNumber}</td>
					</tr>

					<tr>
						<td>Facility Name:</td>
						<td>${MARS.facilityName}</td>
					</tr>

					<tr>
						<td>Facility Type:</td>
						<td>${MARS.facilityType}</td>
					</tr>

					<tr>
						<td>Urgency:</td>
						<td>${MARS.urgency}</td>
					</tr>

					<tr>
						<td>Description:</td>
						<td>${MARS.description}</td>
					</tr>

					<tr>
						<td>Assigned Date:</td>
						<td>${MARS.assignDate}</td>
					</tr>
					
					<tr>
						<td>Repair Time(HH:mm AM/PM):</td>
						<td><input type = "time" min = "00:00" max="23:59" name="repairTime" value="<c:out value='${MARS.assignTime}'/>"></td>
						<td><input name="timeError"
								value="<c:out value='${errorMsgs.timeError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
					</tr>

					<tr>
						<td>Estimate of Repair</td>
						<td>"${MARS.estimateOfRepair}"</td>
					</tr>

					<tr>
					</tr>
					<tr>
					<td>
					<input type ="submit" value = "Modify">
					
					</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a><br>
	<a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a>
</form>
<br>
<input id="successid" value='${formtext.greetingText}' style ="font-weight: bold; font-size: 20px; background-color: white; border: none; width: 1000px;" disabled="disabled" />
</body>
</html>