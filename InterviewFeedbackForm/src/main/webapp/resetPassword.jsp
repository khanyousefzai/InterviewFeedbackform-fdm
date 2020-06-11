<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href=".\frontresources\fdm.png" />
<meta charset="ISO-8859-1">
<title>Reset Password</title>
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
			<form class="formed margin_auto" action="/resetPassword"
				method="post">
				<h1>Hi ${foundUser.firstname}, welcome back!</h1>
				<h2>Please enter your new password here:</h2>				
				
						<div class="grid">
					<div class="field_wrapper  ">
						<label for="fname" class="field_Label">Password</label> 
						<input	type="password" class="field" name="password" id="password" required>
						<span id="message">
							<h5>Password must contain the following:</h5>
							<ul>
								<li id="letter" class="invalid">A lowercase letter</li>
								<li id="capital" class="invalid">An uppercase letter</li>
								<li id="number" class="invalid">A number</li>
								<li id="length" class="invalid">Minimum 6 characters</li>
							</ul>
						</span>
					</div>
				<div>
					<input id="userId" type="hidden" name="userId" value=${foundUser.userId}>
				</div>
				<div class="field_wrapper">
						<label for="fname" class="field_Label">Confirm Password</label> 
						<input type="password" class="field" name="confirm_pw" required id="confirm_pw">
						<span id="message2">
					</span>
					</div>
				</div>
				
				<input type="submit" name="registerAs"  class=" button" value ="Submit">

			</form>
		</div>
		<form action="/"
			class="formBackground formBackgroundColor bordered margin_auto"
			id="backToLogInForm">
			<span class="margin_auto">Already register? <input
				type="submit" value="Log in!" class="inputToLink"></span>
		</form>
	</div>
	<script type="text/javascript" src=".\frontresources\script.js"></script>
	<script type="text/javascript" src=".\frontresources\passwordValidaton.js"></script>
</body>
</html>
