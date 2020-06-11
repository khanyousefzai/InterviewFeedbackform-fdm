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
	<div class="errorContainer">
		<h2>${errMsg}</h2>
	</div>
	<div class="main_container margin_auto">
		<div class="formBackground bordered formBackgroundColor margin_auto">
			<form class="formed margin_auto" action="/RegisterAttempt"
				method="post">
				<h1>Create your account</h1>

				<div class="grid">
					<div class="field_wrapper  ">
						<label for="fname" class="field_Label">First name </label> <input
							type="text" class="field" name="firstname">
					</div>
					<div class="field_wrapper flex_con  ">
						<div class="half">
							<label for="fname" class="field_Label2">Last name </label> <input
								type="text" class="field2 " name="lastname">
						</div>

					</div>
				</div>

				<div class="field_wrapper  ">
					<label for="fname" class="field_Label">Username </label> <input
						type="text" class="field single_field" name="username">
				</div>
				<div class="grid">
					<div class="field_wrapper  ">
						<label for="fname" class="field_Label">Password </label> <input
							type="password" class="field" name="password">
					</div>

					<div class="half">
						<label for="fname" class="field_Label2">Confirm </label> <input
							type="password" class="field2" name="confirm_pw">
					</div>
				</div>
				<div class="field_wrapper   ">
					<label for="fname" class="field_Label">Email address </label> <input
						type="text" class="field single_field" name="email">
				</div>


				<div>
					<select id="type" name="type" onchange="changeAccount();">
						<option value="">Sign up as</option>
						<option value="Trainee">Trainee</option>
						<option value="Trainer">Trainer</option>

					</select>
				</div>

				<div id="type" class="field_wrapper   "
					style="display: none">
					<label id="accountLabel" for="fname" class="field_Label">Stream
					</label> <input type="text" class="field single_field" name="accountType">
				</div>




				<input type="submit" name="registerAs" id="signup"
					class=" button">


			</form>

		</div>


		<form action="/" class="formBackground formBackgroundColor bordered margin_auto"
			id="backToLogInForm">
			<span class="margin_auto">Already register? <input
				type="submit" value="Log in!" class="inputToLink"></span>
		</form>
	</div>
</body>
</html>
