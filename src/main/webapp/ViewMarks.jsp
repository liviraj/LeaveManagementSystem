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
	function viewMarks(id) {
		var idValue = id;
		location.href = "StudentMarkController?action=viewMark&studentId="
				+ idValue;
	}
</script>
</head>
<body>
	<div class="container">
		<center>
			<h1>Student Grading System</h1>
		</center>

		<center>
			<p>
				<font size="5">Student Marks Information</font>
			</p>
		</center>
		<center>
			<span style="color: red">${msg} </span>
		</center>

		

		<form action="LogoutController">
			<input type="submit" name="submit" value="logout"
				style="position: relative; left: 1000px" class="btn btn-danger">
		</form>
		<br>
		<a href="StudentMarkController?action=cancel">
			<button style="position: relative; left: 1000px" class="btn btn-info" class="btn btn-info">Close</button>
		</a>
		<input type="hidden" name="confirm" id="confirm" value=""></input>
		<center>
			<label>Percentage : <span style="color: red">${percentage}</span></label><br>
			<label>CGPA : <span style="color: red">${cgpa}</span></label><br>
			<table border="3" class="table">
				<tr class="danger">
					<th>Department</th>
					<th>Semester</th>
					<th>Subject</th>
					<th>Mark</th>
				</tr>
				<c:forEach items="${marks}" var="mark">
					<tr>
						<td><c:out value="${mark.department}"></c:out></td>
						<td><c:out value="${mark.semesterId}"></c:out></td>
						<td><c:out value="${mark.subjectName}"></c:out></td>
						<td><c:out value="${mark.mark}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<a href="StudentMarkController?action=cancel">
					<button style="position: relative; left: 40px" class="btn btn-info"
						class="btn btn-info">Close</button>
				</a>
			</div>
		</center>

	</div>
</body>
</body>
</html>