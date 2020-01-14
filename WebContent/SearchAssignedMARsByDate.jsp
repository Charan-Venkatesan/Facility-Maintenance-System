<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Assigned MARs</title>
</head>
<body>
<div id="heading">
  <h2>Search Assigned MARs</h2>
</div>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
	<tr>
   		<td>
   			<form name="searchAssignedMARform" action="MarReportController?searchAssignedMARs" method="post">
   			<table>
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
			<input name="action" value="searchAssignedMARs" type="hidden">	
   			</form>
   		</td>
   	</tr>		
</table>
</body>
</html>