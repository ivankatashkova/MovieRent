<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MovieRent</title>
</head>
<style>
body {
   background-image: url("img/bg.jpg");
   background-color: #cccccc;
     background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
	}
</style>
<body style=" background-color:#ffffcc">
<h1 style= "color:black; text-align:center; margin-top:50px;">MovieRent</h1>
<h3 style= "color:black; text-align:center; margin-top:10px;">Here you can rent and buy movies</h3>
	<div style= "margin:auto;border:1px solid silver;padding:10px;text-align:center;width:30%; margin-top:100px;background-color:white;">
		<form action="login" method="post">
			<p><c:out value="${msg}"></c:out></p>
			<p><input type = "text" placeholder = "email" name = "email"></p>
			<p><input type = "password" placeholder = "password" name = "password"></p>
			<p><input type = "submit" value = "Log in" style = "background-color: silver; border: none; height: 30px;"></p>
		</form>
		 <p style="font-size:14;"><a href = "" style = "color:black;">Forgot password </a></p>
		 <p style="font-size:14;"><a href = "/MovieRent/register" style = "color:black;">I don't have registration! </a></p>
	</div>
	
</body>
</html>