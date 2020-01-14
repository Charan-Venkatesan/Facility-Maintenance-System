<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facilities List</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="header_resize">
      <div class="logo"><h1><a href="/uta_facility_maintenance_system">UTA MAC Management Application</a></h1></div>
      <div class="menu_nav">
      </div>
  </div>
<form action="/uta_facility_maintenance_system/FacilityController?action=listSpecificFacility" method="post">          
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Facility Type</th>
				<th class="myTableHead" style="padding-right: 35px; text-align: left">Facility Name</th> 
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Interval</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Duration</th> 
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Venue</th> 
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Status</th> 
			</tr>

 		<c:forEach items="${faccilitylist}" var="item" varStatus="status">
			<tr class="myTableRow"> 	
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.facilityType}" /></td>
			<td class="myTableCell" style="padding-right: 35px; "><c:out value="${item.facilityName}" /></td>
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.interval}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.duration}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.venue}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.bookingStatus}" /></td>
            <td> <a href="/uta_facility_maintenance_system/FacilityController?action=listSpecificFacility&id=${item.facilityName}">View</a></td>
			</tr>			
		</c:forEach>
		
</table>
<table>
 <tr>
	<td><a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a></td>
	<td><a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a></td>
	</tr>
</table>

 </form>

</body>
</html>