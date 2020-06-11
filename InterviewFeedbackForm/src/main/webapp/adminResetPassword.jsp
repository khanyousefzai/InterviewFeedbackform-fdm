<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html>
<html>
	<head>
		<link rel="shortcut icon" type="image/x-icon"
				href=".\frontresources\fdm.png" />
		<meta charset="ISO-8859-1">
		<title>Admin Reset</title>
		<link rel="stylesheet" href=".\frontresources\common.css" />
		<link rel="stylesheet" href=".\frontresources\adminHome.css" />
		<script type="text/javascript"
				src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<link rel="stylesheet"
				href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
		<script type="text/javascript"
				src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
		<script type="text/javascript" src =".\frontresources\script.js"></script>
	</head>
	<body>
		<nav class="top_nav">
			<div class="nav_container">
				<img src=".\frontresources\fdm.svg" alt="fdm_logo" class="logo">
					<form class="nav_button_form" action="/logout" method="post">
						<input class="link" type="submit" name="Sign out" value="Sign Out">
					</form>
			</div>
		</nav>

		<div class="main_container margin_auto">
			<nav class="side_nav">
				<span class="user_name">Admin Portal</span>
					<div class="nav_interactable">
						<span>Admin: Reset Password</span>
					</div>
			</nav>
			
			<div class="home_container">
				<nav class="displayFlexRow " id="home_nav">
					<h1 class="alignselfCenter" id="homeNavTitle">Admin: Reset Password</h1>
					<div class="displayFlexRow" id="homeNavButtons">

					</div>
				</nav>

				<div class = "admin_container bordered top_item">
					<h1 class="tittle">Reset Panel</h1>
					<div id = "errContainer">

						<p id="errMessage">${err}</p>
						<p id="succMessage">${succ}</p>
					</div>
					
					<div class="option_container bordered">
						<form  method = "post" action = "/resetuser">
							<div class="field_wrapper">
								<label class = "field_Label">Select User to reset</label>
								<input type="text" id = "username" name="username" class="field ui-autocomplete-input">
							</div>
								<input type = "submit" value = "Reset" class="button submit" onclick="return confirm('Do you want to reset the user?')">
						</form>
						<form method="get" action="backToHome">
							<input type = "submit" value="Back Home" class="button submit">
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>