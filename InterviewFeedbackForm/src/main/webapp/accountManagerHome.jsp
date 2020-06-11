<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href=".\frontresources\fdm.png" />
<meta charset="ISO-8859-1">
<title>Trainer Home</title>
<link rel="stylesheet" href=".\frontresources\common.css" />
<link rel="stylesheet" href=".\frontresources\trainerHome.css" />
<script type="text/javascript" src=".\frontresources\tab.js">

</script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
</head>
<body>
	<nav class="top_nav">
		<div class="nav_container">
			<img src=".\frontresources\fdm.svg" alt="fdm_logo" class="logo">
			<form class="nav_button_form" action="/logout" method="post">
				<input class="link" type="submit" name="Sign out"
					value="Sign Out">
			</form>
		</div>
	</nav>

	<div class="main_container margin_auto">
		<nav class="side_nav">
			<span class="user_name">${user.firstname} ${user.lastname}</span>

			<div class="nav_interactable">
				<span>Account Manager Home</span>
			</div>
			
			
			<div class="nav_interactable">
				<form method="GET" action="monthlyReport">
				<input type="submit" style="width:150px;" value="Check Monthly Report">
				</form>
			</div>
		</nav>


		<div class="home_container">
			<nav class="displayFlexRow " id="home_nav">
				<h1 class="alignselfCenter" id="homeNavTitle">Your Form Hub</h1>
				<div class="displayFlexRow" id="homeNavButtons">
					<form class="alignselfBottom " action="/formhub" >
						<input type="submit" name="formHub" value="Forms" class=" background tabs" id="formTab" onclick="changeTabForm()">
					</form>
					<form class="alignselfBottom" action="/questionhub" >
						<input type="submit" name="questionHub" value="Questions" class="  backgroundGrey tabs" id="questionTab" onclick="changeTabQuestion()">
					</form>
				</div>
			</nav>

			<div class="form_container">

			<c:forEach var="question" items="${allForms}">
				<div class="forms">
					<form action="viewForm" method ="Get" class="insideForm">
						<input type="hidden" value = "${question.getFormId()}" name="currentForm">
								<p class="form_header">${question.getRole()} located at ${question.getLocation()}</p>
								<p> Interview Date: ${question.getInterviewDate()}</p>
								<p> Form created by: ${question.getAuthor().getFirstname()} ${question.getAuthor().getLastname()}</p>
								<input type="submit" name="button" value="Open form" class="button">
							 </form>
						 </div>
			</c:forEach>
		</div>
	</div>
	</div>
	<script src=".\frontresources\script.js"></script>
</body>
</html>
