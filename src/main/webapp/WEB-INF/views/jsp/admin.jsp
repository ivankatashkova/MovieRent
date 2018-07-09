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
</ul> 
	<div style = "margin:auto;border:1px solid silver;padding:10px;text-align:center;width:50%; margin-top:100px;background-color:white;">
		Movies
		<table style= "margin:auto;border:1px solid silver;padding:10px;text-align:center;width:60%; margin-top:20px;background-color:white;margin-bottom:20px;">
			<tr>
				<th>Name</th>
				<th>Year</th>
				<th>Rent</th>
				<th>Buy</th>
				<th></th>
			</tr>
			<c:forEach var="movie" items="${movies}" varStatus="loop">
				<tr style = "background-color:${loop.index % 2 == 0 ? 'silver' : 'white'}">
					<td><c:out value="${movie.name}"></c:out></td>
					<td><c:out value="${movie.year}"></c:out></td>
					<td><c:out value="${movie.rentPrice}"></c:out> </td>
					<td><c:out value="${movie.price}"></c:out> </td>
					<td><a href="/MovieRent/delete/${movie.id}" style = "border: none; text-decoration: none;">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		<form action="/MovieRent/addMovie" method = "post">
		<input type = "text" name = "name" placeholder = "Movie name">
		<input type = "text" name = "year" placeholder = "year"><br/>
		<input type = "text" name = "rentPrice" placeholder = "Rent price">
		<input type = "text" name = "price" placeholder = "Price"><br/>
		<input type = "text" name = "url" placeholder = "Url"><br/>
		<input type = "submit" value = "Add new movie" style = "text-align:center; border: none; background-color: silver;">
		</form>
	</div>
</body>
</html>