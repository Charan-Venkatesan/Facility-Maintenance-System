<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create MAR</title>
</head>
<body>
<div id="heading">
  <h2>Report Issue</h2>
</div>
<input name="errMsg"  id = "Error_Message" value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
  <tr>
   <td>
   <form name="createMARform" action="/uta_facility_maintenance_system/MarReportController?saveMAR" method="post">
   <table>    
    <tr>
    	<td> Facility Type:* </td>
    	<td> <select name="facilityType">
    	<c:forEach items="${facilityTypeList}" var="facType">
        	<option value="${facType.facilityType}">${facType.facilityType}</option>
   		</c:forEach>
		</select>
 	</tr>
    <tr>
    	<td> Facility Name:* </td>
    	<td> <select name="facilityName">
    	<c:forEach items="${facilityNameList}" var="facName">
        	<option value="${facName.facilityName}">${facName.facilityName}</option>
   		</c:forEach>
		</select>
 	</tr>	
	<tr>
	<td> Urgency:* </td> 
	<td><select name="Urgency" value="<c:out value='${MarReport.urgency}'/>">
		<option value="default" selected="selected">Select a value</option>
		<option value="Unusable" >Unusable</option>
	 	<option value="Major" >Major</option>
	 	<option value="Medium" >Medium</option>
	 	<option value="Minor" >Minor</option>
		</select>
	</td>
	<td><input name="urgencyError"
								value="<c:out value='${errorMsgs.urgencyError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
	</tr>
	<tr>
	<td> Description:* </td>
	<td><input name="description" value="<c:out value='${MarReport.descriptionTest}'/>" type="text"></td>
	<td> <input name="descriptionError"  id = "description_error" value="<c:out value='${errorMsgs.descriptionError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
	</tr>
	<tr>
	<input name="action" value="saveMAR" type="hidden">	
	<td><input type="submit" value="SUBMIT"></td>
	<a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a><br>
	<td><a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a><br></td>
	<td><a href="UserDetailController?action=logout"  target="_top"><span>LogOut</span></a></td>
	</tr>
	</table>
	
	</form>
	 <br>
	 
    <input id="successid" value='${formtext.greetingText}' style ="font-weight: bold; font-size: 20px; background-color: white; border: none; width: 1000px;" disabled="disabled" />
	</td>
	</tr>
	</table>  
</body>
</html>