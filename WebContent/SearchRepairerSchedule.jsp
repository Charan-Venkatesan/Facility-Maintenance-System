<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Repairer Schedule</title>
</head>
<body>
<div id="heading">
  <h2>Search Repairer Schedule</h2>
</div>
<table>
	<tr>
   		<td>
   		<form name="searchRepairerSchedule" action="MarReportController?viewRepairerSchedule" method="get">
   			<table>
 				<tr>
    				<td> Select Repairer: </td>
    				<td> <select name="user_name">
    				<c:forEach items="${userNameList}" var="repairerName">
        				<option value="${repairerName.userName}">${repairerName.userName}</option>
    				</c:forEach>
					</select>
				</tr>
				<tr>
 					<td>Search Date: *</td>
 					<td><input name=Search_Date type="date"></td>
 				</tr>
				<tr>
					<td><input type="submit" value="SUBMIT"></td>
					<td><a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a></td>
					<td><a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a></td>
				</tr>
   			</table>	
   			<input name="action" value="viewRepairerSchedule" type="hidden">	
   		</form>
 	  	</td>
  	</tr>
</table>
</body>
</html>