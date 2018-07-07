<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MovieRent | Home</title>
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
   background-image: url("img/bg.jpg");
   background-color: #cccccc;
     background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
	}

</style>
<body style="background-color:#ffffcc">
 <ul>
  <li><a href="" style="float:left;">Home</a></li>
  <li><a href="" style="float:right;">Log out</a></li>
  <li><a href="" style="float:right;">Profile</a></li>
</ul> 
	<div>
		<table style= "margin:auto;border:1px solid silver;padding:10px;text-align:center;width:60%; margin-top:100px;background-color:white;">
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
					<td><a href="/MovieRent/movie/${movie.id}">View</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
</body>
</html>