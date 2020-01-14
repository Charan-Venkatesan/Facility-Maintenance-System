<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>MAR List</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<body>
    <div class="header_resize">
      <div class="logo"><h1><a href="/uta_facility_maintenance_system">MAC Management Application</a></h1></div>
      <div class="menu_nav">
      </div>
  </div>

      
 <form action="/uta_facility_maintenance_system/MarReportController" method="post">          
       <table id="myMarTable" border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="padding-right: 35px; text-align: left">FacilityType</th>
				<th class="myTableHead" style="padding-right: 35px; text-align: left">FacilityName</th>
				<th class="myTableHead" style="padding-right: 35px; text-align: left">Urgency</th>
				<th class="myTableHead" style="padding-right: 35px; text-align: left">Description</th>
				<th class="myTableHead" style="padding-right: 35px; text-align: left">Reported by</th>
				<th class="myTableHead" style="padding-right: 35px; text-align: left">Reported date</th>
				<th class="myTableHead" style="padding-right: 35px; text-align: left">Marnumber</th> 
				
				</tr>

 		<c:forEach items="${MAC}" var="item" varStatus="status">
			<tr class="myTableRow">	
			<td class="myTableCell" id="col1" style="padding-right: 35px; "><c:out value="${item.facilityType}" /></td>
			<td class="myTableCell" id="col2" style="padding-right: 35px; "><c:out value="${item.facilityName}" /></td>
			<td class="myTableCell" id="col3" style="padding-right: 35px; "><c:out value="${item.urgency}" /></td>
			<td class="myTableCell" id="col4" style="padding-right: 35px; "><c:out value="${item.description}" /></td>
			<td class="myTableCell" id="col5" style="padding-right: 35px; "><c:out value="${item.reportedBy}" /></td>
			<td class="myTableCell" id="col6" style="padding-right: 35px; "><c:out value="${item.createdDate}" /></td>
			<td class="myTableCell" id="col7" style="padding-right: 35px; "><c:out value="${item.marNumber}" /></td>
			
			<td> <a href="/uta_facility_maintenance_system/MarReportController?action=assignMarToRepairer&id=${item.marNumber}">Assign</a></td>
			</tr>
		</c:forEach>
 </table>
<input name="ListSelectedCompanyButton" type="submit" value="Submit">
<a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a><br>
<a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a>
 </form>
</body>
</html>


