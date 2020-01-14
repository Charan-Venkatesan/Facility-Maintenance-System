<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">

<!-- TO MAKE THE URL REFERENCES WORK YOU MUST HAVE SESSION ID DISABLED IN URL - SEE WEB.XML -->

      <div class="logo"><h1><a href="<c:url value='/' />">MAC Management Application</a></h1></div>
  <div class="content">  

      <div class="menu_nav">
        <ul>
          <li><a href="/uta_facility_maintenance_system/MarReportController?action=ReportIssue" target="_top"><span>Create a MAR</span></a></li>          
          <li><a href="AddNewFacility.jsp" target="_top"><span>Add a new facility</span></a></li>
          <li><a href="/uta_facility_maintenance_system/MarReportController?action=viewScheduleRepairer" target="_top"><span>Search a Repairer Schedule by Date</span></a></li>
          <li><a href="SearchAssignedMARsByDate.jsp" target="_top"><span>Search Assigned MARs by Date</span></a></li>
           <li><a href="/uta_facility_maintenance_system/MarReportController?action=listMar" target="_top"><span>Search unassigned MARS</span></a></li>
         	<li><a href="/uta_facility_maintenance_system/MarReportController?action=listallMar" target="_top"><span>view all Mars</span></a></li>
         	<li><a href="/uta_facility_maintenance_system/FacilityController?action=listFacility" target="_top"><span>View Available Facilities</span></a></li>
         	<li><a href="searchFacility.jsp" target="_top"><span>Search Facilities</span></a></li> 
         	<li><a href="/uta_facility_maintenance_system/MarReportController?action=listEachMar" target="_top"><span>view specific Mar</span></a></li>
         	<li><a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a></li>
         	</ul>
      </div> 
    </div>
  </div>
  </div>
  </div>  
</body>
</html>