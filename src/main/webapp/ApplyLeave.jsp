<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap.min.css">
<script src="bootstrap.min.js"></script>
<script src="jquery.min.js"></script>

<title>leave applyL̥</title>
<style type="text/css">
h1 {
	background-color: floralwhite;
}
</style>
</head>

<body>
	<form action="LeaveController" method="post" name="rform">
		<center>
			<h1>Leave Management System</h1>
		</center>
		<div class="container">
			<center>
				<h2>Apply Leave</h2>
				<label>Employee Name: ${employee.name}</label><br> <label>Employee
					Code: ${employee.employeeCode}</label><br>
			</center>
			<input type="hidden" name="employeeId" id="employeeId" value="${employee.employeeId}">
			<br>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="inputleaveFrom4">Leave From</label> <input type="date"
						path="leaveFrom" pattern="dd-MM-yyyy" class="form-control"
						id="leaveFrom" name="leaveFrom" placeholder="Leave From">
				</div>
				<div class="form-group col-md-6">
					<label for="inputleaveTo4">Leave To</label> <input type="date"
						path="leaveTo" pattern="dd-MM-yyyy" class="form-control"
						id="leaveTo" name="leaveTo" placeholder="Leave To">
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="inputLeaveType">Leave Type</label> <select
						id="leaveType" name="leaveType" class="form-control">
						<option selected>Choose...</option>
						<option value="Casual Leave">Casual Leave</option>
						<option value="Sick Leave">Sick Leave</option>
						<option value="Privilege Leave">Privilege Leave</option>
					</select>
				</div>
				<div class="form-group col-md-6">
					<label for="inputLeaveStatus">Leave Status</label> <select
						id="leaveStatus" name="leaveStatus" class="form-control">
						<option selected>Choose...</option>
						<option value="Applied">Applied</option>
						<option value="Rejected">Rejected</option>
						<option value="Approved">Approved</option>
					</select>
				</div>
			</div>

			<div class="form-group col-md-12">
				<label for="inputReason">Reason</label> <input type="text"
					class="form-control" id="reason" name="reason" placeholder="Reason">
			</div>

			<div class="form-group col-md-12">
				<input class="btn btn-primary" type="submit" name="submit"
					value="Submit"></input>
				<button class="btn btn-warning" type="reset"
					style="position: relative; left: 20px">Reset</button>
				<button class="btn btn-danger"
					style="position: relative; left: 40px" type="submit" value="cancel"
					name="submit">Cancel</button>
			</div>
		</div>
	</form>
</body>
</html>