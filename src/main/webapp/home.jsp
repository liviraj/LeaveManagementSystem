<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="bootstrap.min.css">
<script src="bootstrap.min.js"></script>
<script src="jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>home</title>

<style type="text/css">																																																																																																																																																																																																																																																																																																																																																																																																																																						
h1{
		background-color: floralwhite;
	}
	body {
            background-image: url('employee.png');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed; /* This ensures that the background stays fixed when scrolling */
            margin: 0; /* Remove default margin */
        }
</style>
</head>

<body>
<center><h1>Leave Management System</h1></center>
<div class="container">
<div class="panel panel-success">
<form name="logform" onsubmit="validate">
<nav class="navbar navbar">  
  <div class="container-fluid">  
    <div class="navbar-header">  
    
	<ul class="nav navbar-nav">
	<li><a>Home</a></li>
	<li><a href="LoginController?action=login">Login</a></li>
	<li><a href="LoginController?action=register">New Login</a></li>
	</ul>
	</div>
	</div></nav>
	
</form></div></div>
</body>
</html>