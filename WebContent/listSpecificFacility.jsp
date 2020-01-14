<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facility Details</title>
</head>
<div class="header_resize">
      <div class="logo"><h1><a href="/uta_facility_maintenance_system">UTA MAC Maintenance System</a></h1></div>
      <div class="menu_nav">
      </div>
  </div>
<body>
<table>
  <tr>
   <td>
         <table border="1" class="myTable"> 
    <tr>
    <td> Facility Type: </td>
    <td> <c:out value="${specificfacility.facilityType}" /> </td>
    </tr>

    <tr>
    <td> Facility Name: </td>
    <td> <c:out value="${specificfacility.facilityName}"/> </td>
    </tr>

    <tr>
    <td> Interval: </td>
    <td> <c:out value="${specificfacility.interval}" /> </td>
    </tr>

    <tr>
    <td> Duration: </td>
    <td> <c:out value="${specificfacility.duration}" /> </td>
    </tr>

    <tr>
    <td> Venue: </td>
    <td><c:out value="${specificfacility.venue}" /></td>
    </tr>
    
    <tr>
    <td> Status: </td>
    <td><c:out value="${specificfacility.bookingStatus}" /></td>
    </tr>    
    </table>
</td>
</tr>
<tr>
		<td><a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a></td>
		<td><a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a></td>
	</tr>
</table>

</body>
</html>