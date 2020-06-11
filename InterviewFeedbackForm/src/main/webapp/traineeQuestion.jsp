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
<title>Search Question</title>
<link rel="stylesheet" href=".\frontresources\common.css" />
<link rel="stylesheet" href=".\frontresources\traineeQuestion.css" />
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
			<form class="nav_button_form" action="/signout">
				<input class="link" type="submit" name="Sign up" value="Sign Out">
			</form>
		</div>
	</nav>

	<div class="main_container margin_auto">
		<nav class="side_nav">
			<span class="user_name">${user.getFirstname()}
				${user.getLastname()}</span>

			<div class="nav_interactable">
				<span>Form Repo</span>
				<form action="/newForm" method="post">
					<input type="submit" name="" value="+ New form" class="button">
				</form>
			</div>

			<div class="form_list">
				<c:forEach var="question" items="${userForms}">
					<span class="form_summary"> <strong>${question.getRole()}</strong>
						: ${question.getClient().getClientName()}
					</span>
				</c:forEach>
			</div>
		</nav>
	

		<div class="home_container">

			<nav class="displayFlexRow " id="home_nav">
				<h1 class="alignselfCenter" id="homeNavTitle">Question Hub</h1>
				<div class="displayFlexRow" id="homeNavButtons">
					<form class="alignselfBottom " action="/formhub">
						<input type="submit" name="formHub" value="Forms"
							class=" backgroundGrey tabs" id="formTab"
							onclick="changeTabForm()">
					</form>
					<form class="alignselfBottom" action="/questionhub">
						<input type="submit" name="questionHub" value="Questions"
							class=" background tabs" id="questionTab"
							onclick="changeTabQuestion()">
					</form>
				</div>
			</nav>


        <div class="margin_auto" id="questionHub">
          <form:form action="/searchQuestionsTrainee" method="post" id="searchForm">
					<div>
						<div class="displayFlexRow">
							<div class="displayFlexColumn" id="clientBox">
								<label for="clientSearch" class="field_Label"> Client </label> <input
									id="client" name="clientName"
									class="field field_full ui-autocomplete-input" type="text"
									value="" autocomplete="off">
							</div>
							<div class="displayFlexColumn" id="tagBox">
								<label for="TagSearch" class="field_Label"> Tag(s) </label> <input
									id="tagListPopup" name="tagName"
									class="field field_full ui-autocomplete-input" type="text"
									value="" autocomplete="off">
							</div>


							<input type="submit" name="button" id="searchBtn" value="Search"
								class="button alignselfBottom searchButton">
						</div>
						<div class="displayFlexColumn" id="roleBox">
							<label for="clientSearch" class="field_Label"> Search By
								Client Division </label> <input id="clientDivision" name="clientDivision"
								class="field ui-autocomplete-input" type="text" value=""
								autocomplete="off">
						</div>
						<div class="displayFlexColumn" id="roleBox">
							<label for="clientSearch" class="field_Label"> Search By
								Role </label> <input id="role" name="roleName"
								class="field ui-autocomplete-input" type="text" value=""
								autocomplete="off">
						</div>
						
						
						<div class="displayFlexColumn" id="questionBox">
							<label for="clientSearch" class="field_Label"> Search By
								Question </label> <input id="questionListPopup" name="questionBody"
								class="field ui-autocomplete-input" type="text" value=""
								autocomplete="off">
						</div>
						<label for="sortBy" id="sortByTitle"> Sort By: </label>
						<select id="sortBy" name="sortResult">
							<option value="default">Default</option>
							<option value="likes">Likes</option>
							<option value="dislikes">Dislikes</option>
							<option value="alphabetical">Alphabetical</option>
						</select>
					</div>
				</form:form>

				<div class="bordered" id="questionContainer">
					<p class="form_header">Search Results:</p>
					<c:forEach items="${resultQuestions}" var="item" varStatus="status">
						<div class="questionEntryWrapper bordered">
							<section class="clientEntryWrapper">
								<span class="clientWrapperLabel">Client:</span>
								<div class="clientTagContainer displayFlexRow">
									<div class="clientTag bordered">${resultClients[status.index]}</div>
								</div>
							</section>
							<section class="clientEntryWrapper">
								<span class="clientWrapperLabel">Client Division:</span>
								<div class="clientTagContainer displayFlexRow">
									<div class="clientTag bordered">${resultDivisions[status.index]}</div>
								</div>
							</section>							
							<section class="tagEntryWrapper">
								<span class="tagWrapperLabel">Tag(s):</span>
								<div class="tagTagContainer displayFlexRow">
									<c:forEach items="${resultTags[status.index]}" var="tagItem">
										<span class="tagTag bordered">${tagItem.getTagName()}</span>
									</c:forEach>
								</div>
							</section>
							
						<form action="/groupfav" method="post" id="groupFavourite">
							<section class="favouriteEntryWrapper">
								<span class="favouriteWrapperLabel">Favorite Question? Wanna add to the favourite group</span>
								<div class="tagTagContainer displayFlexRow">
									<input type="hidden" id="add_${status.index}" name="questionBody" value="${item}">
									<input id="add_${status.index}" type="submit" name="item" value="Add" class="button alignselfBottom searchButton">
								</div>
							</section>
						</form>
							
							<section class="roleEntryWrapper">
								<span class="roleWrapperLabel">Role:</span>
								<div class="roleTagContainer displayFlexRow">
									<div class="roleTag bordered">${resultRoles[status.index]}</div>
								</div>
							</section>
							<section class="questionEntry">
								<span class="questionLabel">Question</span>
								<div class="questionEntryBody">
									<p class="questionBody">${item}</p>
								</div>
							</section>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		</div>
		<form action="/back" method="post" id="goback">
	    <div class="separator1">
				<input  type="submit" value="Back To Home" class="button alignselfBottom searchButton" >				
		</div>	
		</form>
		<script src=".\frontresources\script.js"></script>
</body>
</html>
