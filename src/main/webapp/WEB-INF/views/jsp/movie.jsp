<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MovieRent | ${movie.name}</title>
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
<body style="background-color:#ffffcc">
 <ul>
  <li><a href="/MovieRent/home" style="float:left;">Home</a></li>
  <li> <form action = "/MovieRent/logout" method = "post"><input type= "submit" value = "Logout" style="background-color:black; border:none; float:right;  display: block;color: white;text-align: center;padding: 14px 16px; text-decoration: none;"></form></li>
  <li><a href="/MovieRent/profile" style="float:right;">Profile</a></li>
</ul> 
	<div style= "margin:auto;border:1px solid silver;padding:10px;text-align:center;width:30%; margin-top:100px;background-color:white;">
			<c:out value="${msg}"></c:out>
		<h3><c:out value="${movie.name}"></c:out></h3>
		<h4><c:out value="${movie.year}"></c:out></h4> 
		<img alt="" src="/MovieRent/img/<c:out value="${movie.img }" ></c:out>" width="100px" >
		<h4><i style = "color:green">$<c:out value="${movie.rentPrice}"></c:out></i>/<i style = "color:red;">$<c:out value="${movie.price}"></c:out></i></h4>
		<p><a href = "/MovieRent/rent/${movie.id}" style = "background-color: silver; border: none; text-decoration: none;">Rent</a> <a href = "/MovieRent/buy/${movie.id}" style = "background-color: silver; border: none; text-decoration: none;">Buy</a> <a href = "/MovieRent/favorite/${movie.id}" style = "background-color: silver; border: none; text-decoration: none;">Add to favorite</a></p>
	</div>
</body>
</html>