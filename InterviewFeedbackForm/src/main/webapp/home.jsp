<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href=".\frontresources\fdm.png" />
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href=".\frontresources\common.css" />
<link rel="stylesheet" href=".\frontresources\home.css" />
<script type="text/javascript" src=".\frontresources\tab.js">

</script>
</head>
<body>
	<nav class="top_nav">
		<div class="nav_container">
			<img src=".\frontresources\fdm.svg" alt="fdm_logo" class="logo">
			<form class="nav_button_form" action="/signout">
       			 <input class="link"	type="submit" name="Sign up" value="Sign Out">
			</form>
		</div>
	</nav>

	<div class="main_container margin_auto">
	    <nav class="side_nav">
	      <span class="user_name">${user.getFirstname()} ${user.getLastname()}</span>

	      <div class="nav_interactable">
	        <span>Form Repo</span>
	        <form action="/newForm" method="Post">
	        	<input type="submit" name="" value="+ New form" class="button">
	        </form>
	      </div>

	      <div class="form_list">
			<c:forEach var="question" items="${userForms}">
				<span class ="form_summary" > <strong>${question.getRole()}</strong> : ${question.getClient().getClientName()} </span>
			</c:forEach>
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
						<form class="alignselfBottom " action="/viewFavourites" >
							<input type="submit" name="favouritesHub" value="Favourites" class=" background tabs" id="favouritesTab" onclick="changeTabFavorites()">
						</form>
					</div>
				</nav>
				<div class="form_container">
					<c:if test = "${userForms.size() == 0}">
								 <div class="forms emptyForm">
									<h1> You haven't submitted any forms yet</h1>
									<h1> Start a new form! </h1>
								 </div>
						</c:if>

					<c:forEach var="question" items="${userForms}">
						<div class="forms">
							<form action="viewForm" method ="Get" class="insideForm">
								<input type="hidden" value = "${question.getFormId()}" name="currentForm">
								<p class="form_header">${question.getRole()} located at ${question.getLocation()}</p>
								<br>
								<p> Interview Date: ${question.getInterviewDate()}</p>
								<input type="submit" name="button" value="Open form" class="button">
								<br><br><br><br>
							</form>
						</div>
					</c:forEach>

				</div>

		</div>
	</div>
</body>
</html>
