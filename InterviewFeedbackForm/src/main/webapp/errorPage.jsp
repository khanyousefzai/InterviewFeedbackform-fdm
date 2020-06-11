<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
  	href=".\frontresources\fdm.png" />
<meta charset="ISO-8859-1">
<title>Error</title>
<link rel="stylesheet" href=".\frontresources\common.css" />
</head>
<body>
	<nav class="top_nav">
		<div class="nav_container">
			<img src=".\frontresources\fdm.svg" alt="fdm_logo" class="logo">
			<form class="nav_button_form" action="/signout">
       			 <input class="link" type="submit" name="Sign up" value="Sign Out">
			</form>
		</div>
	</nav>

	<div class = "errorfield" >

		<div class = "errorBody">
			<p>${errorBody}</p>
		</div>

		<div class = "backToHome">
			<form method = "get" action = "backToHome">
			<input type = "submit" value = "Back to homepage">
			</form>
		</div>



	</div>



</body>
</html>
