<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href=".\frontresources\fdm.png" />
<meta charset="ISO-8859-1">
<title>Retrieve Password</title>
<link rel="stylesheet" href=".\frontresources\common.css" />
<link rel="stylesheet" href=".\frontresources\registration.css" />

</head>
<body>
	<nav class="top_nav">
		<div class="nav_container">
			<img src=".\frontresources\fdm.svg" alt="fdm_logo" class="logo">

			<form class="nav_button_form" action="/" method="post">
				<input class="link" type="submit" name="LogIn" value="Log In">
			</form>
		</div>
	</nav>

	<div class="main_container margin_auto">
		<div id="msgContainer">
			<p id="errMessage">${errMsg}</p>
		</div>
		<div class="formBackground bordered formBackgroundColor margin_auto">
			<form class="formed margin_auto" action="/securityQuestion"
				method="post">
				<h1>Please enter your Email:</h1>


				<div class="field_wrapper   ">
					<label for="fname" class="field_Label">Email: </label> <input
						type="text" class="field single_field" name="email" required value = ${email}>
				</div>

				<input type="submit" name="registerAs" id="signup" class=" button" value ="Next" required>

			</form>

		</div>


		<form action="/"
			class="formBackground formBackgroundColor bordered margin_auto"
			id="backToLogInForm">
			<span class="margin_auto">Already have login and password? <input
				type="submit" value="Log in!" class="inputToLink"></span>
		</form>
	</div>
	<script type="text/javascript" src=".\frontresources\script.js"></script>
</body>
</html>
