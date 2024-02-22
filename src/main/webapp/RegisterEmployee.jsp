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

<title>server registration</title>
<style type="text/css">
h1 {
	background-color: floralwhite;
}
</style>
<script>
	var val1 = $("#sel1").val();
	if (val1 == '' || val1 == 0) {
		$(document).ready(function() {
			$('#sel1').val("---select---");
			$('#sel2').val("---select---");
		});
	} else {
		$(document).ready(function() {
			$('#sel1').val('${details.gender}');
			$('#sel2').val('${details.designation}');
		});
	}
</script>
</head>

<body>
	<form action="EmployeeController" method="post" name="rform">
		<center>
			<h1>Employee Management System</h1>
		</center>
		<div class="container">
			<input type="hidden" name="employeeId" value="${details.employeeId}">
			<h2>Employee Registration</h2>

			<div class="col-xs-4">
				<div>
				<label>Employee Code<span style="color: red">*</span></label><br>
					<span style="color: red">${msg}</span>
					<div id="errname">
						<span style="color: red" id="sname"></span>
					</div>
					<input id="employeeCode" class="form-control" type="text" name="employeeCode"
						value="${details.employeeCode}" placeholder="Enter employee code"> <br>
						
					<label>Employee Name<span style="color: red">*</span></label><br>
					<span style="color: red">${msg}</span>
					<div id="errname">
						<span style="color: red" id="sname"></span>
					</div>
					<input id="name" class="form-control" type="text" name="name"
						value="${details.name}" placeholder="Enter name"> <br>

					<label>Date Of Birth<span style="color: red">*</span></label><br>
					<span style="color: red">${msg}</span>
					<div id="errname">
						<span style="color: red" id="sname"></span>
					</div>
					<input id="dob" class="form-control" type="date" path="dob"
						class="date" name="dob" pattern="dd-MM-yyyy"
						value="${details.dob}" /> <br> <label>Gender<span
						style="color: red">*</span></label>
					<div class="form-group">
						<div id="errteam">
							<span style="color: red" id="steam"></span>
						</div>
						<select class="form-control" name="gender" id="sel1"
							value="${details.gender}">
							<option value="" hidden>---select---</option>
							<option value="Male">Male</option>
							<option value="Female">Female</option>
						</select>
					</div>

					 <label>Designation<span style="color: red">*</span></label>
					<div class="form-group">
						<div id="errteam">
							<span style="color: red" id="steam"></span>
						</div>
						<select class="form-control" name="designation" id="sel2"
							value="${details.designation}">
							<option value="" hidden>---select---</option>
							<option value="Software Developer">Software Developer</option>
							<option value="Web Developer">Web Developer</option>
							<option value="Mobile App Developer">Mobile App
								Developer</option>
							<option value="Full Stack Developer">Full Stack
								Developer</option>
							<option value="Front-End Developer">Front-End Developer</option>
							<option value="Back-End Developer">Back-End Developer</option>
							<option value="DevOps Engineer">DevOps Engineer</option>
							<option value="Systems Architect">Systems Architect</option>
							<option value="Database Administrator (DBA)">Database
								Administrator (DBA)</option>
							<option value="Quality Assurance (QA) Engineer/Tester">Quality
								Assurance (QA) Engineer/Tester</option>
							<option value="Scrum Master">Scrum Master</option>
							<option value="Security Analyst">Security Analyst</option>
							<option value="Machine Learning Engineer">Machine
								Learning Engineer</option>
							<option value="Data Scientist">Data Scientist</option>
							<option value="Network Engineer">Network Engineer</option>
							<option value="Technical Support Engineer">Technical
								Support Engineer</option>
							<option value="IT Project Manager"></option>
						</select>
					</div>

					 <label>Experiance<span style="color: red">*</span></label><br>
					<span style="color: red">${msg}</span> <input id="experiance"
						class="form-control" type="text" name="experiance"
						value="${details.experiance}" placeholder="Enter experiance">

					<br> <label>Contact Number<span style="color: red">*</span></label><br>
					<span style="color: red">${msg}</span>
					<div id="errname">
						<span style="color: red" id="sname"></span>
					</div>
					<input id="contactNumber" class="form-control" type="text"
						name="contactNumber" value="${details.contactNumber}"
						placeholder="Enter contact number"> 
					<br>
					<div class="col-xs-2">
						<input class="btn btn-primary" type="submit" name="submit"
							value="${name}"></input>
					</div>
					<button class="btn btn-danger" style="position: relative; left: 50px"
						type="submit" value="Cancel" name="submit">Cancel</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>