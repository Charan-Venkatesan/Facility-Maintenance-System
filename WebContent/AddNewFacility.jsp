<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADD NEW FACILITY</title>
</head>
<body>
<div id="heading">
  <h2>Add a new facility</h2>
</div>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
	<tr>
   		<td>
   		<form name="addNewFacilityForm" action="/uta_facility_maintenance_system/FacilityController?saveFacility" method="post">
   			<table>
 				<tr>
 					<td>Facility Type:*<td>
 					<td><input name="facility_type" value="<c:out value='${addNewFacility.facilityType}'/>" type="text" maxlength=20></td>
 					<td> <input name="facilityTypeError"  value="<c:out value='${errorMsgs.facilityTypeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
 				</tr>
 				<tr>
 					<td>Facility Name:*<td>
 					<td><input name="facility_name" value="<c:out value='${addNewFacility.facilityName}'/>" type="text" maxlength=20></td>
 					<td> <input name="facilityNameError"  value="<c:out value='${errorMsgs.facilityNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
 				</tr>
 				<tr>
 					<td>Interval:*<td>
 					<td><input name="interval" value="<c:out value='${addNewFacility.interval}'/>" type="text" maxlength=20></td>
 					<td><input name="intervalError"  value="<c:out value='${errorMsgs.intervalError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
 				</tr>
 				<tr>
 					<td>Duration:*<td>
 					<td><input name="duration" value="<c:out value='${addNewFacility.duration}'/>" type="text" maxlength=20></td>
 					<td> <input name="durationError"  value="<c:out value='${errorMsgs.durationError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
 				</tr>
 				<tr>
 					<td>Venue:*<td>
 					<td><input name="venue" value="<c:out value='${addNewFacility.venue}'/>" type="text" maxlength=20></td>
 					<td> <input name="venueError"  value="<c:out value='${errorMsgs.venueError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
 				</tr>
 				<tr>
 					<input name="action" value="saveFacility" type="hidden">	
					<td><input type="submit" value="SUBMIT"></td>
					<td><a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a></td>
					<td><a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a></td>
				</tr>
   			</table>
   			
   		</form>
   		<br>
  	    <input id="successid" value='${formtext1.greetingText}' style ="font-weight: bold; font-size: 20px; background-color: white; border: none; width: 1000px;" disabled="disabled" />
 	  	</td>
  	</tr>
</table>
</body>
</html>