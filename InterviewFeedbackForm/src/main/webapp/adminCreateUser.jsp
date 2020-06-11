<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href=".\frontresources\fdm.png" />
<meta charset="ISO-8859-1">
<title>Create User</title>
<link rel="stylesheet" href=".\frontresources\common.css" />
<link rel="stylesheet" href=".\frontresources\adminHome.css" />
<style>
/* The message box is shown when the user clicks on the password field */
#message, #message2 {
  display:none;
  
  position: relative;
  
}

#message p {
  padding: 10px 40px;
  font-size: 15px;
}

/* Add a green text color and a checkmark when the requirements are right */
.valid {
  color: green;
}

.valid:before {
  position: relative;
  left: -15px;
  content: "\2713";
}

/* Add a red text color and an "x" when the requirements are wrong */
.invalid {
  color: red;
}

.invalid:before {
  position: relative;
  left: -15px;
  content: "\2613";
}
ul{
	list-style-type: none;

}

</style>
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
				<span>Admin: Create User</span>
			</div>
		</nav>

		<div class="home_container">
			<nav class="displayFlexRow " id="home_nav">
				<h1 class="alignselfCenter" id="homeNavTitle">Admin: Create
					User</h1>
				<div class="displayFlexRow" id="homeNavButtons"></div>
			</nav>




			<div class="admin_container bordered top_item">
				<h1 class="title">Create User Panel</h1>
				<div id="errContainer">
					<p id="errMessage">${err}</p>
					<p id="succMessage">${succ}</p>
				</div>
				<div class="option_container bordered">
					<form method="post" action="adminCreateUser">
						<div class="field_wrapper">
							<label class="field_Label">User Type </label> <select id="cars"
								name="type" class="field">
								<option value="Trainer">Trainer</option>
								<!-- <option value="Trainee">Trainee</option> -->
								<option value="AccountManager">AccountManager</option>
								<option value="Admin">Admin</option>
							</select>
						</div>
						<div class="field_wrapper">
							<label class="field_Label">Username </label> <input type="text"
								name="username" class="field">

						</div>
						<div class="field_wrapper">
							<label class="field_Label">Password </label> <input
								type="password" name="password" class="field" id="password"> <span
								id="message">
								<h5>Password must contain the following:</h5>
								<ul>
									<li id="letter" class="invalid">A lowercase letter</li>
									<li id="capital" class="invalid">An uppercase letter</li>
									<li id="number" class="invalid">A number</li>
									<li id="length" class="invalid">Minimum 6 characters</li>
								</ul>

							</span>
						</div>
						<div class="field_wrapper">
							<label class="field_Label">Password Confirm </label> <input
								type="password" name="password_c" class="field" id="confirm_pw"> <span
								id="message2"> </span>
						</div>
						<div class="field_wrapper">
							<label class="field_Label">First Name </label> <input type="text"
								name="firstname" class="field">
						</div>
						<div class="field_wrapper">
							<label class="field_Label">Last Name </label> <input type="text"
								name="lastname" class="field">
						</div>
						<div class="field_wrapper">
							<label class="field_Label">Email </label> <input type="text"
								name="email" class="field">
						</div>
						<input type="submit" value="Submit" class="button submit">

					</form>

					<form method="get" action="backToHome">
						<input type="submit" value="Back Home" class="button submit">
					</form>
				</div>


			</div>

		</div>
	</div>
	<script type="text/javascript"
		src=".\frontresources\passwordValidaton.js"></script>
</body>
</html>
