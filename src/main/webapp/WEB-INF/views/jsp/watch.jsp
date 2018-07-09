<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MovieRent | Watch </title>
</head>
<style>
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
}
li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover {
    background-color: silver;
}

.active {
    background-color: silver;
}

body {
   background-image: url("/MovieRent/img/bg.jpg");
   background-color: #cccccc;
     background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
	}

</style>
<body>
 <ul>
  <li><a href="/MovieRent/home" style="float:left;">Home</a></li>
  <li> <form action = "/MovieRent/logout" method = "post"><input type= "submit"  value = "Logout" style="background-color:black; border:none; float:right;  display: block;color: white;text-align: center;padding: 14px 16px; text-decoration: none;"></form></li>
  <li><a href="/MovieRent/profile" style="float:right;">Profile</a></li>
</ul> 

<div style= "margin:auto;border:1px solid silver;padding:10px;text-align:center;width:50%; margin-top:100px;background-color:white;">
	<h3><c:out value="${msg}"></c:out></h3>
	<a href = "${movie.url}"><img alt="" src="<c:out value="${movie.img }"></c:out>"> <c:out value="${movie.img }"></c:out></a>
</div>


</body>
</html>