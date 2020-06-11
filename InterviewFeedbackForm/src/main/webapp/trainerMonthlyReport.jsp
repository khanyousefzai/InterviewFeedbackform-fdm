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
				<span class="field_label">Monthly Report</span>
			</div>
			
			<div class="separator">
				<span class="field_label">Created Date:</span>
				<span class="form_field"> ${report.getReportCreated()} </span>
			</div>
			
			<div class="separator">
				<span class="field_label">Top 30% Disliked Question:</span>
			</div>

	    <c:forEach  var="question" items="${report.getQuestionList()}">
				<div class="field_wrapper separator bordered background">
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
					<span class="form_field">Dislike: ${question.getDislikes()} </span>
					<span class="form_field"> Question: ${question.getQuestionBody()} </span>
					<span class="form_field"> 
						<form method="GET" action="approveQuestionMonthlyReport" class="sameLineForm">
						<input type="hidden" name="questionId" value = "${question.getQuestionId()}">
						<input type="submit" value="Approve" class="button">
					 	</form>
					 	<form method="GET" action="unapproveQuestionMonthlyReport" class="sameLineForm">
					 	<input type="hidden" name="questionId" value = "${question.getQuestionId()}">
						<input type="submit" value="Unapprove" class="button">
					 	</form>
					 </span>
					<form method="GET" action="updateQuestionMonthlyReport">
					<span class="form_field"><input type="text" name="newQuestionBody"> </span>
					<span class="form_field"> 
						<input type="hidden" name="questionId" value = "${question.getQuestionId()}">
						<input type="hidden" name="oldQuestionBody" value= "${question.getQuestionBody()}">
						<input type="submit" value="Edit" class="button">
					 	
					 </span>
					 </form> 
				</div>
	    </c:forEach>

			<form class="separator" method = "get" action = "backToHome">
				<input type = "submit" value = "Back" class="button">
			</form>

		</div>


  </div>


</body>
</html>
