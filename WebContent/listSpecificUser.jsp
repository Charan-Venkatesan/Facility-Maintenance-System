<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Specific User</title>
</head>
<body>
<table>
  <tr>
   <td>
         <table border="1" class="myTable"> 
         
    <tr>
    <td> Username: </td>
    <td> <c:out value="${USERS.username}" /> </td>
    </tr>
    
     <tr>
    <td> First Name: </td>
    <td> <c:out value="${USERS.first_name}" /> </td>
    </tr>
    
    <tr>
    <td> Last Name: </td>
    <td> <c:out value="${USERS.last_name}" /> </td>
    </tr>
    
    <tr>
    <td> User Role: </td>
    <td> <c:out value="${USERS.first_name}" /> </td>
    </tr>
    
    <tr>
    <td> UTA ID: </td>
    <td> <c:out value="${USERS.uta_id}" /> </td>
    </tr>
    
    <tr>
    <td> Phone: </td>
    <td> <c:out value="${USERS.phone}" /> </td>
    </tr>
    
    <tr>
    <td> Email ID: </td>
    <td> <c:out value="${USERS.email}" /> </td>
    </tr>
    
      <tr>
    </tr>
    </table>
    <a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a><br>
    <a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a>
</td>
</tr>
</table>

</body>
</html>