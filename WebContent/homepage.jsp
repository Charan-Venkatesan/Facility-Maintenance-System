<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home Page</title>
</head>
<body>
	<h1>UTA Facility Maintenance System</h1>
	<div class="menu_nav">
		<ul>
			<li><a href="/uta_facility_maintenance_system/UserDetailController?action=viewProfile"  target="_top"><span>View Profile</span></a></li>
			<li><a href="searchFacility.jsp" target="_top"><span>Search
						Facilities</span></a></li>
			<li><a href="/uta_facility_maintenance_system/MarReportController?action=ReportIssue" target="_top"><span>Create a MAR</span></a></li>
			<li><a href="/uta_facility_maintenance_system/MarReportController?action=listMyMars" target="_top"><span>Search MAR</span></a></li>
			<li><a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a></li>
		</ul>
	</div>
</body>
</html>