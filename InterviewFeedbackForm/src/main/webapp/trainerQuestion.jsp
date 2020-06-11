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
<link rel="stylesheet" href=".\frontresources\trainerQuestion.css" />
<script type="text/javascript" src=".\frontresources\tab.js"></script>
<script type="text/javascript" src=".\frontresources\questionLogs.js"/></script>
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
				
			</div>

			<div class="form_list">

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
			<div id="msgContainer">
				<p id="succMessage">${succMsg}</p>
				<p id="errMessage">${errMsg}</p>
			</div>
			<div class="margin_auto" id="questionHub">
				<form:form action="/searchQuestions" method="post" id="searchForm">
					<div>
						<div class="displayFlexRow">
							<div class="displayFlexColumn" id="clientBox">
								<label for="clientSearch" class="field_Label"> Client </label> 
								<input
									id="client" name="clientName"
									class="field field_full ui-autocomplete-input" type="text"
									autocomplete="off" value="${searchCriteria.clientName}" >
							</div>
							<div class="displayFlexColumn" id="tagBox">
								<label for="TagSearch" class="field_Label"> Tag(s) </label> <input
									id="tagListPopup" name="tagName"
									class="field field_full ui-autocomplete-input" type="text"
									 autocomplete="off" value="${searchCriteria.tagName}">
							</div>

							<input type="submit" name="button" id="searchBtn" value="Search" class="button alignselfBottom searchButton">
							
						</div>
						<div class="displayFlexColumn" id="roleBox">
							<label for="clientSearch" class="field_Label"> Search By
								Client Division </label> <input id="clientDivision" name="clientDivision"
								class="field ui-autocomplete-input" type="text" 
								autocomplete="off" value="${searchCriteria.clientDivision}">
						</div>
						
						<div class="displayFlexColumn" id="roleBox">
							<label for="clientSearch" class="field_Label"> Search By
								Role </label> <input id="role" name="roleName"
								class="field ui-autocomplete-input" type="text"
								autocomplete="off" value="${searchCriteria.roleName}">
						</div>
						<div class="displayFlexColumn" id="questionBox">
							<label for="clientSearch" class="field_Label"> Search By Question </label> 
								<input id="questionListPopup" name="questionBody"
								class="field ui-autocomplete-input" type="text" 
								autocomplete="off" value="${searchCriteria.questionBody}">
						</div>
						<label for="sortBy" id="sortByTitle"> Sort By: </label>				
						<select id="sortBy" name="sortResult">				
							<option value="default" ${searchCriteria.sortResult=="default"?"selected":""}>Default</option>
							<option value="likes" ${searchCriteria.sortResult=="likes"?"selected":""}>Likes</option>
							<option value="dislikes" ${searchCriteria.sortResult=="dislikes"?"selected":""}>Dislikes</option>
							<option value="alphabetical" ${searchCriteria.sortResult=="alphabetical"?"selected":""}>Alphabetical</option>
						</select>
					</div>
				</form:form>
				<form:form action="/downloadSearchResult" method="post" target="_blank">
					<div class="displayFlexRow" >
							<input type="${visiableControl}" name="Download" style="width:250px" value="Download with Search Criteras" class="button alignselfBottom searchButton">
							<input type="${disableControl}" name="Download" style="width:250px; background:#909090" value="Download Enabled After Search" class="button alignselfBottom searchButton" disabled>
							<input type="hidden" name="clientName" value="${searchCriteria.clientName}">
							<input type="hidden" name="tagName" value="${searchCriteria.tagName}">
							<input type="hidden" name="clientDivision" value="${searchCriteria.clientDivision}">
							<input type="hidden" name="roleName" value="${searchCriteria.roleName}">
							<input type="hidden" name="questionBody" value="${searchCriteria.questionBody}">
							<input type="hidden" name="sortResult" value="${searchCriteria.sortResult}">
					</div>

				</form:form>

				<div class="bordered" id="questionContainer">
					<p class="form_header">Search Results:</p>
					<c:forEach items="${resultQuestions}" var="item" varStatus="status">
						<div class="questionEntryWrapper bordered">
							<section class="clientEntryWrapper">
								<span class="clientWrapperLabel">Client(s):</span>
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
							<section class="roleEntryWrapper">
								<span class="roleWrapperLabel">Role:</span>
								<div class="roleTagContainer displayFlexRow">
									<div class="roleTag bordered">${resultRoles[status.index]}</div>
								</div>
							</section>
							<section class="questionEntry">
								<span class="questionLabel">Question</span>
								<div class="questionEntryBody">
									<form:form action="/updateQuestion" method="post" id="updateQuestion">
										<input id="questionListPopup" name="oldQuestionBody"
										class="field field_full ui-autocomplete-input" type="text" value="${item.getQuestionBody()}"
										autocomplete="off" readonly="readonly">
										<input type="hidden" name="qid" value="${item.getQuestionId()}"/>
										<input id="questionListPopup" name="questionBodyUpdate"
										class="field field_full ui-autocomplete-input" type="text" value=""
										autocomplete="off">
										<c:set var="check" value="${resultsQuestionLogs[status.index]}"/>
										<c:if test="${not empty check}">
											<button class="button" type="button" onclick="showLogs(this.id)" id="${status.index}">Show Logs</button>
											<br/>
											<br/>
										</c:if>										
										<div id="logs_${status.index}" style="display:none;">
											Question Log History:
											<br/>
											<c:forEach items="${resultsQuestionLogs[status.index] }" var="ql">
												<p>
													Old Question: ${ql.getOldQuestion()}<br/>
													Date updated: [${ql.getMessageDate()}]<br/>
													Current Question edited By: ${ql.getTrainerName() }
												</p>
											</c:forEach>
										</div>
										<input type="submit" name="button" id="updateBtn" value="Update Question"
										class="button alignselfBottom updateButton">
									</form:form>
								</div>
							</section>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<script src=".\frontresources\script.js"></script>
</body>
</html>