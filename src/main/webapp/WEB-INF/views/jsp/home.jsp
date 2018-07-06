<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			<tr style = "background-color:silver;">
			<td>Gone Girl</td>
			<td>2014</td>
			<td>1.50</td>
			<td>14.00</td>
			<td><input type = "submit" value = "View"></td>
			</tr>
			<tr style = "background-color:white;">
			<td>The Form of Water</td>
			<td>2017</td>
			<td>1.50</td>
			<td>18.00</td>
			<td><input type = "submit" value = "View"></td>
			</tr>
			<tr style = "background-color:silver;">
			<td>Boss baby</td>
			<td>2017</td>
			<td>1.50</td>
			<td>14.00</td>
			<td><input type = "submit" value = "View"></td>
			</tr>
		</table>
	</div>
	
</body>
</html>