<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Facilities</title>
</head>
<body>
<div class="logo"><h1><a href="/uta_facility_maintenance_system">UTA MAC Application</a></h1></div>
      <div class="menu_nav"></div>
<table>
<tr>
	<td>
	<form action="/uta_facility_maintenance_system/FacilityController?action=searchFacility" method="post">
	<table style="width: 1200px; ">
	
	<tr>
	<td> Facility Type: </td>
    <td> <select name="facility_type">
	<option value = "" selected="selected">Select a Facility Type</option>
	<option value = "Indoor basketball courts" >Indoor basketball courts</option>
	<option value = "Volleyball courts" >Volleyball courts</option>
	<option value = "Multipurpose rooms" >Multipurpose rooms</option>
	<option value = "Indoor soccer gymnasium" >Indoor soccer gymnasium</option>
	<option value = "Racquetball courts" >Racquetball courts</option>
	<option value = "Badminton courts" >Badminton courts</option>
	<option value = "Table Tennis" >Table Tennis</option>
	<option value = "Conference rooms" >Conference rooms</option>
	<option value = "Outdoor Volleyball Courts" >Outdoor Volleyball Courts</option>
	<option value = "Outdoor Basketball Courts" >Outdoor Basketball Courts</option>
	</select></td>
	</tr>
	
	<tr>
  	<td> Date: </td>
 	<td> <input name="date"  type="text" class= "date" pattern="yyyy-MM-dd"/>  </td>
  	
	</tr>
	
	<tr>
    <td>Time:</td>
	<td> <select name="time_slot">
	<option value = "" selected="selected">Select a Time slot</option>
	<option value = "6am - 7am">6am - 7am</option>
	<option value = "7am - 8am">7am - 8am</option>
	<option value = "8am - 9am">8am - 9am</option>
	<option value = "9am - 10am">9am - 10am</option>
	<option value = "10am - 11am">10am - 11am</option>
	<option value = "11am - 12pm">11am - 12pm</option>
	<option value = "12pm - 1pm">12pm - 1pm </option>
	<option value = "1pm - 2pm">1pm - 2pm</option>
	<option value = "2pm - 3pm">2pm - 3pm</option>
	<option value = "3pm - 4pm">3pm - 4pm</option>
	<option value = "4pm - 5pm">4pm - 5pm</option>
	<option value = "5pm - 6pm">5pm - 6pm</option>
	<option value = "6pm - 7pm">6pm - 7pm</option>
	<option value = "7pm - 8pm">7pm - 8pm</option>
	<option value = "8pm - 9pm">8pm - 9pm</option>
	<option value = "9pm - 10pm">9pm - 10pm</option>
	<option value = "10pm - 11pm">10pm - 11pm</option>
	<option value = "11pm - 12am">11pm - 12am</option>
	</select></td>
	</tr>
	</table>
	<table>
	<tr>
		<td><a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a><br></td>
		<td><a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a></td>
	</tr>
	</table>
	<input type="submit" value="Submit">
</form>      
</td>
</tr>
</table>
</body>
</html>

