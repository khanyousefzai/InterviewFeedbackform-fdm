<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href=".\frontresources\fdm.png" />
<meta charset="ISO-8859-1">
<title>Security Questions</title>
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
		<div id="msgContainer2">
			<p id="errMessage">${errMsg}</p>
		</div>
		<div class="formBackground bordered formBackgroundColor margin_auto">
			<form class="formed margin_auto" action="/resetPasswordAttempt"
				method="post">
				<h1>Please answer the following security questions:</h1>
				
				<div class="field_wrapper">
					<label for="firstSecurityQuestion" class="securityQuestion">${userQuestions[0].securityQuestion}</label>
					 <input	type="text" class="field" name="firstSecurityAnswer" required value = ${firstSecurityAnswer}>
				</div>
				<div class="field_wrapper">
					<label for="firstSecurityQuestion" class="securityQuestion">${userQuestions[1].securityQuestion}</label>
					<input	type="text" class="field" name="secondSecurityAnswer" required value = ${secondSecurityAnswer} >
				</div>
				<div class="field_wrapper">
					<label for="firstSecurityQuestion" class="securityQuestion">${userQuestions[2].securityQuestion}</label>
					<input	type="text" class="field" name="thirdSecurityAnswer" required value = ${thirdSecurityAnswer} >
				</div>
				<div>
					<input id="userId" type="hidden" name="userId" value=${userId}>
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
