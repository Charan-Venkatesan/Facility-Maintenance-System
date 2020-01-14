<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Request Reservation</title>
</head>
<body>
	<h2>Reserve a Facility</h2>
	<div class="menu_nav"></div>
   				<form name="requestReservationform" action="FacilityController?action=reqReservation" method="post">
   				<table>
    				<tr>
    					<td>Facility Name:*</td>
						<td><select name="facilityName" value="<c:out value='${Facility.facilityName}'/>">
						<option value="default" selected="selected">Select a facility name</option>
						<option value="MR 1" >MR 1</option>
	 					<option value="MR 2" >MR 2</option>
	 					<option value="MR 3" >MR 3</option>
	 					<option value="MR 4" >MR 4</option>
	 					<option value="IBBC 1" >IBBC 1</option>
	 					<option value="IBBC 2" >IBBC 2</option>
	 					<option value="IBBC 3" >IBBC 3</option>
	 					<option value="IBBC 4" >IBBC 4</option>
	 					<option value="IBBC 5" >IBBC 5</option>
	 					<option value="IVBC 1" >IVBC 1</option>
	 					<option value="IVBC 2" >IVBC 2</option>
	 					<option value="IVBC 3" >IVBC 3</option>
	 					<option value="IVBC 4" >IVBC 4</option>
	 					<option value="IVBC 5" >IVBC 5</option>
	 					<option value="IVBC 6" >IVBC 6</option>
	 					<option value="IVBC 7" >IVBC 7</option>
	 					<option value="IVBC 8" >IVBC 8</option>
	 					<option value="IVBC 9" >IVBC 9</option>
	 					<option value="SCG" >SCG</option>
	 					<option value="RBC 1" >RBC 1</option>
	 					<option value="RBC 2" >RBC 2</option>
	 					<option value="RBC 3" >RBC 3</option>
	 					<option value="RBC 4" >RBC 4</option>
	 					<option value="RBC 5" >RBC 5</option>
	 					<option value="BMC 1" >BMC 1</option>
	 					<option value="BMC 2" >BMC 2</option>
	 					<option value="BMC 3" >BMC 3</option>
	 					<option value="BMC 4" >BMC 4</option>
	 					<option value="BMC 5" >BMC 5</option>
	 					<option value="BMC 6" >BMC 6</option>
	 					<option value="BMC 7" >BMC 7</option>
	 					<option value="BMC 8" >BMC 8</option>
	 					<option value="BMC 9" >BMC 9</option>
	 					<option value="BMC 10" >BMC 10</option>
	 					<option value=" TT1" >TT1</option>
	 					<option value=" CR 1" >CR 1</option>
	 					<option value=" CR 2" >CR 1</option>
	 					<option value=" CR 3" >CR 1</option>
	 					<option value=" CR 4" >CR 1</option>
	 					<option value=" CR 5" >CR 1</option>
	 					<option value=" OVBC 1" >OVBC 1</option>
	 					<option value=" OVBC 2" >OVBC 2</option>
	 					<option value=" OBBC 1" >OBBC 1</option>
	 					<option value=" OBBC 2" >OBBC 2</option>
						</select>
						</td>
    				</tr>
					<tr>
						<td>Duration:*</td>
						<td>
						<select  name="bookDuration" value="<c:out value='${Facility.bookDuration}'/>">
						<option value="default" selected="selected">Select a facility name</option>
						<option value="Multiple Days" >Multiple Days</option>
	 					<option value="One day" >One day</option>
	 					<option value="Multiple Hours" >Multiple Hours</option>
	 					<option value="one Hour" >one Hour</option>
	 					<option value="30 minutes" >30 minutes</option>
	 					</select>
						</td>
					</tr>
					<tr>
						<td><input type="submit" value="Request Reservation"></td>
						<td><a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a><br></td>
						<td><a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a></td>
					</tr>
			</table>
			</form>
	</td>
	</tr>
	</table>

</body>
</html>