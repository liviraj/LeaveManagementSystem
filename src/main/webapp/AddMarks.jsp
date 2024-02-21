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

<title>Students mark registration</title>
<style type="text/css">
h1{
		background-color: floralwhite;
	}
</style>
<script>
function setMark(subjectId, counterInIndex, counterOutIndex) {
    var markInput = document.getElementById('mark'+ subjectId);
    var subjectInfoInputs = document.getElementsByName('subjectInfo');
    
    // Calculate the current position of subjectInfo in the array
    var currentPosition = parseInt(counterOutIndex) * ${subjectDetails.size()} + parseInt(counterInIndex);

    // Use the calculated position to get the specific subjectInfo input
    var subjectInfoInput = subjectInfoInputs[currentPosition];

    // Set the entered mark in the hidden field value
    subjectInfoInput.value = subjectInfoInput.value.replace(/,[0-9]+$/, ',' + markInput.value);
}
</script>
  </head>

<body>
<form action="StudentMarkController" method="post" name="rform" >
<center><h1>Student Grading System</h1></center>
<div class="container">
	<center>
	<h2>Enter Mark Details</h2>
	<label>Student Name: ${student.name}</label><br>
	<label>RollNo: ${student.rollNo}</label><br>
	<label>Department: ${student.department}</label>
	<c:forEach items="${subjectDetails}" var="subjectEntry" varStatus="counterOut">
	<h4>Semester: <c:out value="${subjectEntry.key}"></c:out></h4><br>
		<table border="3" class="table">
		 	<c:forEach var="subject" items="${subjectEntry.value}" varStatus="counter">
				<tr class="danger">
					<th>Subject Code: <c:out value="${subject.subjectCode}"></c:out></th>
					<th>Subject: <c:out value="${subject.subjectName}"></c:out></th>
					<th>Mark: <input id="mark${subject.subjectId}" class="form-control" type="number" name="mark${subject.subjectId}"  placeholder="Enter the mark" onchange="setMark('${subject.subjectId}', '${counter.index}','${counterOut.index}')">
						</th>
					
					<input type="hidden" name="subjectInfo" value="${subject.subjectId},${subjectEntry.key},${subject.subjectName},${subject.subjectCode},${student.studentId},${student.department},${subject.mark}">
				</tr>
			</c:forEach>
		</table>
		<br>
	</c:forEach>
	<input  class="btn btn-info" type="submit" name="submit"  value="submit"></input>
	<button  class="btn btn-info" type="reset" style="position: relative;left: 40px">Reset</button>
      <button  class="btn btn-info" style="position: relative;left: 50px" type="submit" value="cancel" name="submit">Cancel</button>
	</center>
	</div>
</form>
</body>
</html>