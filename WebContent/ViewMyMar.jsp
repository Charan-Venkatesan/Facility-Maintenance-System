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
    <td> <input name="mar_number" value="<c:out value='${MAC.marNumber}'/>" type="text" maxlength="45" disabled="disabled">  </td>
    </tr>
    <tr>
    <td> Urgency: </td>
    <td> <input name="estimate_of_repair" value="<c:out value='${MAC.urgency}'/>" type="text" maxlength="45" disabled="disabled">  </td>
    </tr>
     <tr>
    <td> Description: </td>
    <td> <input name="estimate_of_repair" value="<c:out value='${MAC.description}'/>" type="text" maxlength="45" disabled="disabled">  </td>
    </tr>
     <tr>
    <td> FacilityName: </td>
    <td> <input name="estimate_of_repair" value="<c:out value='${MAC.facilityName}'/>" type="text" maxlength="45" disabled="disabled">  </td>
    </tr>
     <tr>
    <td> FacilityType: </td>
    <td> <input name="estimate_of_repair" value="<c:out value='${MAC.facilityType}'/>" type="text" maxlength="45" disabled="disabled">  </td>
    </tr>
    <tr>
    <td> ReportedBy: </td>
    <td> <input name="estimate_of_repair" value="<c:out value='${MAC.reportedBy}'/>" type="text" maxlength="45" disabled="disabled">  </td>
    </tr>
    <tr>
    <td> ReportedDate: </td>
    <td> <input name="estimate_of_repair" value="<c:out value='${MAC.createdDate}'/>" type="text" maxlength="45" disabled="disabled">  </td>
    </tr>
    
	</table>
   
    <a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a><br>
    <a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a>
    <br>
    </form>
    
    
</td>
</tr>
</table>
</body>
</html>