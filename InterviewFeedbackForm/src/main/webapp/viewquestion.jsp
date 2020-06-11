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
<title>Favorites</title>
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
	<c:set var = "count" value = "1"> </c:set> 
	<h1> Favorite Questions Group </h1>
	    <c:forEach  var="question" items="${Questions}">		
			<p> Question: ${count}  <br>
			 ${question.getQuestionBody()}	 
			</p>
			
			<form action="/groupunfav" method="post" id="groupUnFavourite">
					<section class="favouriteEntryWrapper">
							<span class="favouriteWrapperLabel">Already prepared for this question! Wanna delete?</span>
								<div class="tagTagContainer displayFlexRow">
									<input type="hidden" id="delete_${count}" name="questionId" value="${question.getQuestionId()}">
									<input id="delete_${count}" type="submit" name="question" value="Delete" class="button alignselfBottom searchButton">
								</div>
					</section>
			</form>		
			<c:set var = "count" value = "${count + 1}"> </c:set>				
	    </c:forEach>
	    </div>
		<form action="/back" method="post" id="goback">
	    <div class="separator1">
				<input  type="submit" value="Back" class="button alignselfBottom searchButton" >				
		</div>	
		</form>
  </div>
</body>
</html>
