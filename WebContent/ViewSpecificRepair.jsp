<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Repair form</title>
</head>
<div class="header_resize">
	<div class="logo">
		<h1>
			<a href="/uta_facility_maintenance_system">UTA MAC Facility
				Maintenance System</a>
		</h1>
	</div>
	<div class="menu_nav"></div>
</div>
<body>
	<table>
		<tr>
			<td>
				<table border="1" class="myTable">
					<tr>
						<td>Mar Number:</td>
						<td><c:out value="${MARS.marNumber}" /></td>
					</tr>

					<tr>
						<td>Facility Name:</td>
						<td><c:out value="${MARS.facilityName}" /></td>
					</tr>

					<tr>
						<td>Facility Type:</td>
						<td><c:out value="${MARS.facilityType}" /></td>
					</tr>

					<tr>
						<td>Urgency:</td>
						<td><c:out value="${MARS.urgency}" /></td>
					</tr>

					<tr>
						<td>Description:</td>
						<td><c:out value="${MARS.description}" /></td>
					</tr>

					<tr>
						<td>Assigned Date:</td>
						<td><c:out value="${MARS.assignDate}" /></td>
					</tr>

					<tr>
						<td>Estimate of Repair</td>
						<td><c:out value="${MARS.estimateOfRepair}" /></td>
					</tr>

					<tr>
					</tr>
					<tr>
					<td>
					<form name="modifytime" action="modifyRepairTime.jsp">
					<input type ="submit" value = "Modify Repair">
					</form>
					<form name = "cancelRepair" action = "/uta_facility_maintenance_system/MarReportController?action=cancelMyRepair&id=${MARS.marNumber }" method = "post">
		
		<input type = "hidden" name = "action" value = "cancelRepair">
		<input type = "submit" value = "Cancel Repair">
		
		</form>
					
					</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a><br>
	<a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a>
</body>
</html>