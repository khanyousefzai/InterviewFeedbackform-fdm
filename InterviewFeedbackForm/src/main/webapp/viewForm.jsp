<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href=".\frontresources\fdm.png" />
<meta charset="ISO-8859-1">
<title>Your Form</title>
<link rel="stylesheet" href=".\frontresources\common.css" />
<link rel="stylesheet" href=".\frontresources\viewform.css" />
<script type="text/javascript" src =".\frontresources\form.js">

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

  <div class="main_container">

		<div class="viewform_container bordered">
			<div class="separator">
				<span class="field_label">Role:</span>
				<span class="form_field"> ${currentForm.getRole()} </span>
			</div>

			<div class="separator">
				<span class="field_label">Role Location:</span>
				<span class="form_field"> ${currentForm.getLocation()} </span>
			</div>

			<div class="separator">
				<span class="field_label">Interview Date:</span>
				<span class="form_field">${currentForm.getInterviewDate()}</span>
			</div>

			<div class="separator">
				<span class="field_label">Form Created On:</span>
				<span class="form_field">${currentForm.getFormCreated()}</span>
			</div>

			<div class="separator">
				<span class="field_label">Account Manager:</span>
				<span class="form_field"> ${currentForm.getFdmAccountManager()} </span>
			</div>

			<div class="separator">
				<span class="field_label">Interview Method:</span>
				<span class="form_field"> ${currentForm.getInterviewMethod()} </span>
			</div>

			<div class="separator">
				<span class="field_label">Interviewer Name:</span>
				<span class="form_field"> ${currentForm.getInterviewerName()} </span>
			</div>

			<div class="separator">
				<span class="field_label">Client:</span>
				<span class="form_field">${currentForm.getClient().getClientName()}</span>
			</div>

			<div class="separator">
				<span class="field_label"> Client Manager: </span>
				<span class="form_field"> ${currentForm.getClientManager()} </span>
			</div>
			
			<div class="separator">
				<span class="field_label"> Client Division </span>
				<span class="form_field"> ${currentForm.getClientDivision()} </span>
			</div>

			<spring:url value="/likeQuestion" var="userActionUrl" />
			<form:form method="post" modelAttribute="currentForm" action="${userActionUrl}" >
			<input type="hidden" value = "${currentForm.getFormId()}" name="formId">
	    <c:forEach  var="question" items="${currentForm.getQuestionList()}"  varStatus="status">

				<div class="field_wrapper separator bordered background">
					<div class="questionApproval">
						<div class="">
							<span class="field_label"> Approval: </span>
							<c:choose>
								<c:when test="${question.getApproved() == 1}">
								 <span class="form_field"> Approved </span>
								</c:when>
								<c:otherwise>
									<span class="form_field"> Not yet approved </span>
								</c:otherwise>
							</c:choose>
						</div>

						<div class="likes">

							<form:input path="questionList[${status.index}].questionBody" value="${question.getQuestionBody()}" type="hidden"/>
							<form:input path="questionList[${status.index}].approved" value="${question.getApproved()}" type="hidden"/>
							<form:input path="questionList[${status.index}].questionId" value="${question.getQuestionId()}" type="hidden"/>
							<c:set var="qqq" value="${status.index}" />
							<span onclick="liked(${status.index}, ${question.getLikes()}, ${question.getDislikes()},${check.get(qqq)});">
								<img src="./frontresources/like.svg" alt="likes" width="25px" height="25px">
								<input id="like${status.index}" name="questionList[${status.index}].likes" value="${question.getLikes()}" class="numbers" type="text" readonly>
							</span>

							<span onclick="disliked(${status.index}, ${question.getLikes()}, ${question.getDislikes()},${check.get(qqq)});">
								<img src="./frontresources/dislike.svg" alt="dislikes" width="25px"height="25px">
								<input id="dislike${status.index}" name="questionList[${status.index}].dislikes" value="${question.getDislikes()}" class="numbers" type="text" readonly>
							</span>

							<input id="status${status.index}" name="status" value="7" type="hidden">

						</div>

					</div>
					<span class="field_label"> Question:</span>
					<span class="form_field"> ${question.getQuestionBody()} </span>
				</div>
	    </c:forEach>
			<div class="separator">
				<input type="submit" value="Back" class="button">
			</div>

		</form:form>
		</div>


  </div>

	<script type="text/javascript" src="./frontresources/viewFormLike.js">
	</script>

	<script type="text/javascript">
		var questionLikes;
		var questionDislikes;
		function liked(index, likes, dislikes, status){
			let currentLikes = document.getElementById('like'+index).value;
			let currentDislikes = document.getElementById('dislike'+index).value;
			let questionStatus = document.getElementById('status'+index).value;
			if(status === 0 && questionLikes === undefined && questionDislikes === undefined){
				questionLikes = false;
				questionDislikes = false;
			}else if(status === 1 && questionLikes === undefined && questionDislikes === undefined){
				questionLikes = true;
				questionDislikes = false;
			}else if(status ===2 && questionLikes === undefined && questionDislikes === undefined){
				questionLikes = false;
				questionDislikes = true;
			}

			if(status === 0){
				currentLikes = likes + 1;
				currentDislikes = dislikes;
				questionLikes = true;
				questionDislikes = false;
				document.getElementById('like'+index).value = currentLikes;
				document.getElementById('dislike'+index).value = currentDislikes;

				document.getElementById('status'+index).value = 1;

			}

			if(status === 1 && questionLikes){
				currentLikes = likes - 1;
				currentDislikes = dislikes;
				questionLikes = false;
				questionDislikes = false;
				document.getElementById('like'+index).value = currentLikes;
				document.getElementById('dislike'+index).value = currentDislikes;
				document.getElementById('status'+index).value = 2;
			}else if(status === 1 && !questionLikes){
				currentLikes = likes;
				currentDislikes = dislikes;
				questionLikes = true;
				questionDislikes = false;
				document.getElementById('like'+index).value = currentLikes;
				document.getElementById('dislike'+index).value = currentDislikes;
				document.getElementById('status'+index).value = 7;
			}

			if(status === 2 && questionDislikes){
				currentLikes = likes + 1;
				currentDislikes = dislikes - 1;
				questionLikes = true;
				questionDislikes = false;
				document.getElementById('like'+index).value = currentLikes;
				document.getElementById('dislike'+index).value = currentDislikes;
				document.getElementById('status'+index).value = 3;
			}else if(status === 2 && !questionDislikes){
				currentLikes = likes;
				currentDislikes = dislikes;
				questionLikes = false;
				questionDislikes = false;
				document.getElementById('like'+index).value = currentLikes;
				document.getElementById('dislike'+index).value = currentDislikes;
				document.getElementById('status'+index).value = 7;
			}

		}

		function disliked(index, likes, dislikes, status){
			let currentLikes = document.getElementById('like'+index).value;
			let currentDislikes = document.getElementById('dislike'+index).value;
			let questionStatus = document.getElementById('status'+index).value;
			if(status === 0 && questionLikes === undefined && questionDislikes === undefined){
				questionLikes = false;
				questionDislikes = false;
			}else if(status === 1 && questionLikes === undefined && questionDislikes === undefined){
				questionLikes = true;
				questionDislikes = false;
			}else if(status ===2 && questionLikes === undefined && questionDislikes === undefined){
				questionLikes = false;
				questionDislikes = true;
			}

			if(status === 0){
				currentLikes = likes;
				currentDislikes = dislikes + 1;
				questionLikes = false;
				questionDislikes = true;
				document.getElementById('like'+index).value = currentLikes;
				document.getElementById('dislike'+index).value = currentDislikes;

				document.getElementById('status'+index).value = 4;
			}

			if(status === 2 && questionDislikes){
				currentLikes = likes;
				currentDislikes = dislikes - 1;
				questionLikes = false;
				questionDislikes = false;
				document.getElementById('like'+index).value = currentLikes;
				document.getElementById('dislike'+index).value = currentDislikes;
				document.getElementById('status'+index).value = 5;
			}else if(status === 2 && !questionDislikes){
				currentLikes = likes;
				currentDislikes = dislikes;
				questionLikes = false;
				questionDislikes = true;
				document.getElementById('like'+index).value = currentLikes;
				document.getElementById('dislike'+index).value = currentDislikes;
				document.getElementById('status'+index).value = 7;
			}


			if(status === 1 && questionLikes){
				currentLikes = likes - 1;
				currentDislikes = dislikes + 1;
				questionLikes = false;
				questionDislikes = true;
				document.getElementById('like'+index).value = currentLikes;
				document.getElementById('dislike'+index).value = currentDislikes;
				document.getElementById('status'+index).value = 6;
			}else if(status === 1 && !questionLikes){
				currentLikes = likes;
				currentDislikes = dislikes;
				questionLikes = false;
				questionDislikes = false;
				document.getElementById('like'+index).value = currentLikes;
				document.getElementById('dislike'+index).value = currentDislikes;
				document.getElementById('status'+index).value = 7;
			}

		}
	</script>
</body>
</html>
