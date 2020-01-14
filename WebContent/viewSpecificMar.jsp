<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Specific MAR</title>
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
    <form name="MacForm" action="/uta_facility_maintenance_system/MarReportController?action=viewSpecificMar" method="post">
    <table>
    <!--  <tr>
    <td> Mar Number: </td>
    <td> <input name="mar_number" value="<c:out value='${MAR.mar_number}'/>" type="text" maxlength="45">  </td>
    </tr>-->
    <tr>
    <td> Mar Number: </td>
    <td> <select name="mar_number">
    <c:forEach items="${MAC}" var="item" varStatus="status">
        <option value="${item.marNumber}">${item.marNumber}</option>
    </c:forEach>
	</select>
	</tr>
	</table>
    <input type="submit" value="search"> 
    <a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a>
    <a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a>
    </form>
</td>
</tr>
</table>
</body>
</html>