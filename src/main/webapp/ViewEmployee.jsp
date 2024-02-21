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
				location.href="StudentController?action=delete&studentId="+idValue; 
			}/* else{
				location.reload();
			} */
		
	}
	
	function addMarks(id){
		var idValue = id;	
		location.href="StudentMarkController?action=addMark&studentId="+idValue; 
	}
	
	function viewMarks(id){
		var idValue = id;	
		location.href="StudentMarkController?action=viewMark&studentId="+idValue; 
	}
</script>
</head>
<body>
<div class="container">
<center><h1>Student Grading System</h1></center>
	
	<center><p><font size="5">Student Information</font></p></center>
	<center><span style="color: red">${msg} </span></center>
	<form action="LogoutController">
				<input type="submit" name="submit" value="logout" style="position:relative; left: 1000px" class="btn btn-danger">
	</form>
	<input type="hidden" name="confirm" id="confirm" value=""></input>
	<center><table border="3" class="table">
		<tr class="danger">
		<th>Roll No</th>
		<th>Name</th>
		<th>Date Of Birth</th>
		<th>Gender</th>
		<th>Father Name</th>
		<th>Semester</th>
		<th>Department</th>
		<th colspan="1"><a href="StudentController?action=add">Add New</a></th>
		</tr>
			<c:forEach items="${details}" var="detail">
			<tr>
				<td><c:out value="${detail.rollNo}"></c:out></td>
				<td><c:out value="${detail.name}"></c:out></td>
				<td><c:out value="${detail.dob}"></c:out></td>
				<td><c:out value="${detail.gender}"></c:out></td>
				<td><c:out value="${detail.fatherName}"></c:out></td>
				<td><c:out value="${detail.semester}"></c:out></td>
				<td><c:out value="${detail.department}"></c:out></td>
				<td><a href="StudentController?action=update&studentId=<c:out value="${detail.studentId}"/>"><button>Update</button></a>
			 <a><button onclick="msg1(${detail.studentId})">Delete</button></a>
			 <a><button onclick="addMarks(${detail.studentId})">Add Marks</button></a>
			 <a><button onclick="viewMarks(${detail.studentId})">View Marks</button></a></td> 
			</tr>
			</c:forEach>
			</table></center></div>
</body>
</body>
</html>
