<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>view</title>
<style type="text/css">
h1{
		background-color: floralwhite;
	}
</style>
 <link rel="stylesheet" href="bootstrap.min.css">
<script src="bootstrap.min.js"></script>
<script src="jquery.min.js"></script>
<script>
	function msg1(id){
		var idValue = id;
		var c=confirm("Are You Sure");
		if(c==true)
			{
				location.href="EmployeeController?action=delete&employeeId="+idValue; 
			}/* else{
				location.reload();
			} */
		
	}
	
	function applyLeave(id){
		var idValue = id;	
		location.href="LeaveController?action=applyLeave&employeeId="+idValue; 
	}
	
	function leaveHistory(id){
		var idValue = id;	
		location.href="LeaveController?action=leaveHistory&employeeId="+idValue; 
	}
</script>
</head>
<body>
<div class="container">
<center><h1>Leave Management System</h1></center>
	
	<center><p><font size="5">Employee Information</font></p></center>
	<center><span style="color: red">${msg} </span></center>
	<form action="LogoutController">
				<input type="submit" name="submit" value="logout" style="position:relative; left: 1000px" class="btn btn-danger">
	</form>
	<input type="hidden" name="confirm" id="confirm" value=""></input>
	<center><table border="3" class="table table-striped">
	<thead class="thead-dark">
		<tr class="warning">
		<th>Employee Code</th>
		<th>Name</th>
		<th>Date Of Birth</th>
		<th>Gender</th>
		<th>Designation</th>
		<th>Experience</th>
		<th>Contact Number</th>
		<th colspan="1"><a href="EmployeeController?action=add">Add New</a></th>
		</tr>
		</thead>
			<c:forEach items="${details}" var="detail">
			<tr>
				<td><c:out value="${detail.employeeCode}"></c:out></td>
				<td><c:out value="${detail.name}"></c:out></td>
				<td><c:out value="${detail.dob}"></c:out></td>
				<td><c:out value="${detail.gender}"></c:out></td>
				<td><c:out value="${detail.designation}"></c:out></td>
				<td><c:out value="${detail.experiance}"></c:out></td>
				<td><c:out value="${detail.contactNumber}"></c:out></td>
				<td><a href="EmployeeController?action=update&employeeId=<c:out value="${detail.employeeId}"/>"><button>Update</button></a>
			 <a><button onclick="msg1(${detail.employeeId})">Delete</button></a>
			 <a><button onclick="applyLeave(${detail.employeeId})">Apply Leave</button></a>
			 <a><button onclick="leaveHistory(${detail.employeeId})">Leave History</button></a></td> 
			</tr>
			</c:forEach>
			</table></center></div>
</body>
</body>
</html>
