<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MAR Form</title>
</head>
<body>
<div class="header_resize">
      <div class="logo"><h1><a href="/uta_facility_maintenance_system">MAC Management Application</a></h1></div>
      <div class="menu_nav">
      </div>
  </div>
<table>
  <tr>
   <td>
    <form name="MacForm" action="/uta_facility_maintenance_system/MarReportController?action=updateMar" method="post">
    <table>
    <tr>
    <td> Mar Number: </td>
    <td> <input name="mar_number" value="<c:out value='${MAC.marNumber}'/>" type="text" maxlength="45">  </td>
    </tr>
    
    <tr>
    <td> Estimate of repair: </td>
    <td> <select name="estimate_of_repair" value="<c:out value='${MAC.estimateOfRepair}'/>">
   <option value="default" selected="selected">choose an estimate of repair</option>
    <option>Multiple days</option>
	<option>One day</option>
	<option>Multiple hours</option>
	<option>One hour</option>
	<option>30 minutes</option>
	</select></td>
    </tr>   
    <tr>
    <td> Assigned To: </td>
    <td> <select name="assignedto">
    <c:forEach items="${userNameList}" var="MacForm">
        <option value="${MacForm.userName}">${MacForm.userName}</option>
    </c:forEach>
	</select>
	</tr>
	</table>
    <input type="submit" value="Update">
    <a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a><br>
    <a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a>
    <br>
    <input id="successid" value='${MAC.greetingText}' style ="font-weight: bold; font-size: 20px; background-color: white; border: none; width: 1000px;" disabled="disabled" />
    </form>
    
    
</td>
</tr>
</table>
</body>
</html>