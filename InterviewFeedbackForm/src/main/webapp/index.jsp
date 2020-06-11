<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href=".\frontresources\fdm.png" />
<meta charset="ISO-8859-1">
<title>Welcome</title>
<link rel="stylesheet" href=".\frontresources\common.css" />
<link rel="stylesheet" href=".\frontresources\index.css" />
</head>
<body>
	<div id="main_container">
		<div class="logo_container">
			<img src=".\frontresources\fdm-logo-black.png" alt="fdm_logo"
				id="logo" class="margin_auto"> <span class="margin_auto">Sign
				in to forms</span>
		</div>
		<div id="msgContainer">
			<p id="succMessage">${succMsg}</p>
			<p id="errMessage">${errMsg}</p>
		</div>
		<form action="/login" method="Post" id="login_form">
			<div class="field_wrapper top_item">
				<label for="fname" class="field_Label">Username </label> <input
					type="text" class="field" id="fname" name="username">
			</div>


			<div class="field_wrapper margin_auto">
				<label for="lname" class="field_Label">Password </label> <input
					type="password" class="field" id="lname" name="password"> 
					<div>
					<input
					type="checkbox" onclick="togglePw()">   Show Password 
					</div>
					<a
					href="/retrievePassword" method="Post" class="forgetPassword">Forgot
					Password?</a>
			</div>


			<input type="submit" value="Submit" class="submit margin_auto">


		</form>

		<form action="/registerView" method="Post"
			class="formBackgroundColor " id="signup_form">
			<span class="margin_auto">New to trainee forms? <input
				type="submit" value="Sign up!" class=" inputToLink"></span>
		</form>
	</div>

	<script>
		function togglePw() {
			var x = document.getElementById("lname");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}
	</script>


</body>
</html>
