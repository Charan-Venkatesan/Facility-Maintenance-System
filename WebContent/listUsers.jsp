<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Users</title>
</head>
<body>
	<form action="UserDetailController?action=searchUser" method="post">
		Username: <input type="text" name="username" placeholder="username" required /><br /> 
		<input type="hidden" name="action" value="searchUser">
		<input name="usernameError" value="<c:out value='${errorMsgs}'/>" type="text"
			   style="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">
		<input type="submit" name="search" value="Search User" />
	</form>

<%-- 	<input name="errMsg" value="<c:out value='${errorMsgs}'/>" type="text"
		style="background-color: white; color: red; border: none; width: 800px"
		disabled="disabled"> --%>
		
		<table border="1" class="myTable"> 
		<tr class="myTableRow">
		    <th class ="myTableHead" style="padding-right: 20px; text-align: left">Username</th>
			<th class ="myTableHead" style="padding-right: 20px; text-align: left">First Name</th>
			<th class ="myTableHead" style="padding-right: 20px; text-align: left">Last Name</th>
			<th class ="myTableHead" style="padding-right: 20px; text-align: left">User Role</th>
			<th class ="myTableHead" style="padding-right: 40px; text-align: left">UTA ID</th>
		    <th class ="myTableHead" style="padding-right: 40px; text-align: left">Phone</th>
		    <th class ="myTableHead" style="padding-right: 40px; text-align: left">EMAIL ID</th>
		 </tr>
		
		<c:forEach items="${USERS}" var="item" varStatus="status">
		<tr class="myTableRow">
		<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.username}" /></td>
		<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.first_name}" /></td>
		<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.last_name}" /></td>
		<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.user_role}" /></td>
		<td class="myTableCell" style="padding-right: 40px; "><c:out value="${item.uta_id}" /></td>
		<td class="myTableCell" style="padding-right: 40px; "><c:out value="${item.phone}" /></td>
		<td class="myTableCell" style="padding-right: 40px; "><c:out value="${item.email}" /></td>
		<td> <a href="UserDetailController?action=viewSelectedUser&id=${item.username}">View</a></td> 
		
		</tr>
		</c:forEach>
		</table>
		<a href="UserDetailController?action=homepage"  target="_top"><span>Home</span></a><br>
		<a href="/uta_facility_maintenance_system/UserDetailController?action=logout"  target="_top"><span>LogOut</span></a>

</body>
</html>