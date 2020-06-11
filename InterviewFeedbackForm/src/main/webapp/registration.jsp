<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href=".\frontresources\fdm.png" />
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href=".\frontresources\common.css" />
<link rel="stylesheet" href=".\frontresources\registration.css" />
<script type="text/javascript">
	function changeAccount() {
		let type = document.getElementById("type");
		let lb = document.getElementById("accountLabel");
		let d = document.getElementById("accountDiv");
		if (type.value == "Trainee") {
			lb.innerHTML = "Stream";
			d.style = "display:block"
		} else if (type.value == "Trainer") {
			lb.innerHTML = "Expertise";
			d.style = "display:block";
		} else {
			d.style = "display:none";
		}
	}
</script>
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
			<form class="formed margin_auto" action="/RegisterAttempt"
				method="post">
				<h1>Create Your Account</h1>

				<div class="grid">
					<div class="field_wrapper  ">
						<label for="fname" class="field_Label">First Name </label> 
						<input type="text" class="field" name="firstname" required value = ${user.firstname}>
					</div>
					<div class="field_wrapper flex_con  ">
						<div class="half">
							<label for="fname" class="field_Label2">Last Name </label> <input
								type="text" class="field2 " name="lastname" required value = ${user.lastname}>
						</div>

					</div>
				</div>

				<div class="field_wrapper  ">
					<label for="fname" class="field_Label">Username </label> <input
						type="text" class="field single_field" name="username" required value = ${user.username}>
				</div>
				<div class="grid">
					<div class="field_wrapper  ">
						<label for="fname" class="field_Label">Password </label> <input
							type="password" class="field" name="password" id="password" required value = ${user.password}>
							<div>
					<input
					type="checkbox" onclick="togglePw()">   Show Password 
					</div>
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

					<div class="half">
						<label for="fname" class="field_Label2">Confirm Password</label> <input
							type="password" class="field2" name="confirm_pw" id="confirm_pw" required value = ${confirm_pw}>
							<span id="message2">
					</span>
					</div>
				</div>
				<div class="field_wrapper">
					<label for="fname" class="field_Label">Email Address </label> <input
						type="text" class="field single_field" name="email" id="email" required value = ${user.email}>
				</div>


				<div>
					<input id="type" type="hidden" name="type" value="Trainee">
				</div>

				<div id="accountDiv" class="field_wrapper">
					<label id="accountLabel" for="fname" class="field_Label">Stream
					</label> 
					<select class="field_single_field" name="accountType" required="required">
						<option value="select"></option>
						<option value="Java">Java</option>
						<option value="ITSM">ITSM</option>
						<option value="Sales">Sales</option>
					</select>
				</div>
				
				<div class="field_wrapper">
					  
					<label for="firstSecurityQuestion" class="field_Label">First Security Question:</label>
					<select id="firstSecurityQuestion" name="firstSecurityQuestionId" required="required">
						<option value="${securityQuestion[0].securityQuestionId}">${securityQuestion[0].securityQuestion}</option>
						<option value="${securityQuestion[1].securityQuestionId}">${securityQuestion[1].securityQuestion}</option>
						<option value="${securityQuestion[2].securityQuestionId}">${securityQuestion[2].securityQuestion}</option>
					</select></br>
					<input type="text" class="field" id="firstSecurityAnswer"  name="firstSecurityAnswer" required value = ${securityAnswer.firstSecurityAnswer}></br>
				</div>
				<div class="field_wrapper">
					<label for="secondSecurityQuestion" class="field_Label">Second Security Question:</label>
					<select id="secondSecurityQuestion" name="secondSecurityQuestionId" required="required">
						<option value="${securityQuestion[3].securityQuestionId}">${securityQuestion[3].securityQuestion}</option>
						<option value="${securityQuestion[4].securityQuestionId}">${securityQuestion[4].securityQuestion}</option>
						<option value="${securityQuestion[5].securityQuestionId}">${securityQuestion[5].securityQuestion}</option>
					</select></br>
					<input type="text" class="field" id="secondSecurityAnswer" name="secondSecurityAnswer" required value = ${securityAnswer.secondSecurityAnswer}></br>
				</div>
				<div class="field_wrapper">
					<label for="thirdSecurityQuestion" class="field_Label">Third Security Question:</label>
					<select id="thirdSecurityQuestion" name="thirdSecurityQuestionId" required="required">
						<option value="${securityQuestion[6].securityQuestionId}">${securityQuestion[6].securityQuestion}</option>
						<option value="${securityQuestion[7].securityQuestionId}">${securityQuestion[7].securityQuestion}</option>
						<option value="${securityQuestion[8].securityQuestionId}">${securityQuestion[8].securityQuestion}</option>
					</select></br>
					<input type="text" class="field" id="thirdSecurityAnswer" name="thirdSecurityAnswer" required value = ${securityAnswer.thirdSecurityAnswer} ></br>
				</div>
				
				<input type="submit" name="registerAs" id="signup" class=" button" value ="Submit">

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
	<script>
		function togglePw() {
			var x = document.getElementById("password");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}
	</script>
</body>
</html>
