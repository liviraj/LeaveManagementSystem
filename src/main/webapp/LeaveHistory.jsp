<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>view</title>
<style type="text/css">
h1 {
	background-color: floralwhite;
}
</style>
<link rel="stylesheet" href="bootstrap.min.css">
<script src="bootstrap.min.js"></script>
<script src="jquery.min.js"></script>
<script>
function leaveApprove(id, empId){
	var idValue = id;	
	var empValue = empId;
	location.href="LeaveController?action=leaveAction&leaveId="+idValue+"&type=Approved&employeeId=" + empValue; 
}

function leaveReject(id, empId){
	var idValue = id;
	var empValue = empId;
	location.href="LeaveController?action=leaveAction&leaveId="+idValue+"&type=Rejected&employeeId="+ empValue; 
}
</script>
</head>
<body>
	<div class="container">
		<center>
			<h1>Leave Management System</h1>
		</center>

		<center>
			<p>
				<font size="5">Leave History</font>
			</p>
		</center>
		<center>
			<span style="color: red">${msg} </span>
		</center>
		<div class="form-row form-group">
			<div class="col-md-10"></div>
			<div class="col-md-1">
				<a href="LeaveController?action=cancel">
					<button class="btn btn-info" class="btn btn-info">Close</button>
				</a>
			</div>
			<div class="col-md-1">
				<form action="LogoutController">
					<input type="submit" name="submit" value="logout" class="btn btn-danger">
				</form>
			</div>
			
		</div>
		<br>
		<center>
			<br>
			<table border="3" class="table">
				<tr class="warning">
					<th>Reason</th>
					<th>Leave From</th>
					<th>Leave To</th>
					<th>Leave Type</th>
					<th>Leave Status</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${leaveList}" var="leave">
					<tr>
						<td><c:out value="${leave.reason}"></c:out></td>
						<td><c:out value="${leave.leaveFrom}"></c:out></td>
						<td><c:out value="${leave.leaveTo}"></c:out></td>
						<td><c:out value="${leave.leaveType}"></c:out></td>
						<td><c:out value="${leave.leaveStatus}"></c:out></td>
						<td><a><button onclick="leaveApprove(${leave.leaveId}, ${leave.employeeId})">Approve</button></a>
							<a><button onclick="leaveReject(${leave.leaveId}, ${leave.employeeId})">Reject</button></a></td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<a href="LeaveController?action=cancel">
					<button style="position: relative; left: 40px" class="btn btn-info"
						class="btn btn-info">Close</button>
				</a>
			</div>
		</center>

	</div>
</body>
</body>
</html>