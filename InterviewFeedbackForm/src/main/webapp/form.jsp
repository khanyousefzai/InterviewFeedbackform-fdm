<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href=".\frontresources\fdm.png" />
<meta charset="ISO-8859-1">
<title>Add Form</title>
<link rel="stylesheet" href=".\frontresources\common.css" />
<link rel="stylesheet" href=".\frontresources\form.css" />
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src =".\frontresources\script.js"></script>
<script type="text/javascript" src =".\frontresources\form.js">
	
</script>
</head>
<body onload="loadDate()">
	<nav class="top_nav">
		<div class="nav_container">
			<img src=".\frontresources\fdm.svg" alt="fdm_logo" class="logo">
			<form class="nav_button_form" action="/signout">
       			 <input class="link"	type="submit" name="Sign up" value="Sign Out">
			</form>
		</div>
	</nav>

  <div class="main_container">

  <spring:url value="/submitForm" var="userActionUrl" />

  	<form:form method="post" modelAttribute="form" action="${userActionUrl}" class="form_container" onkeypress="return event.keyCode != 13;">
  		<spring:bind path="interviewDate">
  			<div class ="field_wrapper">
				<label for="interviewDate" class="field_Label">Date of interview </label>
				<form:input type="date" class="field field_full " id="interviewDate" path="interviewDate" required="required"/>
			</div>
  		</spring:bind>

  		 <div class="grid">
  		 	<spring:bind path="academyLocation">
	  			<div class ="field_wrapper">
					<label for="academyLocation" class="field_Label">FDM Academy Location </label>
					<form:select type="text" class="field field_half " id="academyLocation" path="academyLocation" spellcheck="true" required="required">
						<option value="Select"></option>
						<option value="Toronto">Toronto, Canada</option>
						<option value="New York">New York, USA</option>
						<option value="Herndon">Herndon, USA</option>
						<option value="Austin">Austin, USA</option>
						<option value="Charlotte">Charlotte, USA</option>
						<option value="London">London, UK & Ireland</option>
						<option value="Leeds">Leeds, UK & Ireland</option>
						<option value="Glasgow">Glasgow, UK & Ireland</option>
						<option value="Birmingham">Birmingham, UK & Ireland</option>
						<option value="Frankfurt">Frankfurt, Germany</option>
						<option value="Singapore">Singapore, Singapore</option>
						<option value="Admiralty">Admiralty, Hong Kong</option>
						<option value="Sydney">Sydney, Australia</option>
					</form:select>
				</div>
  			</spring:bind>

  			<spring:bind path="fdmAccountManager">
	  			<div class ="field_wrapper">
					<label for="fdmAccountManager" class="field_Label">Account Manager </label>
					<form:select type="text" class="field_half " id="fdmAccountManager" path="fdmAccountManager" required="required">
					<option value="Select"></option>
						<c:forEach items="${amList}" var="am">
            					<option value="${am}">${am}</option>
        				</c:forEach>
					</form:select>
				</div>
  			</spring:bind>

     	 </div>

     	 <div class="grid">

	  			<div class ="field_wrapper">
					<label for="client" class="field_Label">Client </label>
					<!-- <input type="text" class="field field_half  ui-autocomplete-input" id="client" name="clientName" spellcheck="true" value="" autocomplete="off"/> -->
					<select type="text" class="field_half" id="client" path="client" name="clientName" required="required">
					<option value="Select"></option>
						<c:forEach items="${cList}" var="c">
            					<option value="${c.getClientName()}">${c.getClientName()}</option>
        				</c:forEach>
					</select>
				</div>


  			<spring:bind path="clientManager">
	  			<div class ="field_wrapper">
					<label for="clientManager" class="field_Label">Client Manager </label>
					<form:input type="text" class="field field_half " id="clientManager" path="clientManager" spellcheck="true" required="required"/>
				</div>
				
				
				
  			</spring:bind>
  			
  			<spring:bind path="clientDivision">
  			<div class ="field_wrapper">
					<label for="clientDivision" class="field_Label">Client Division </label>
					<form:input type="text" class="field field_half " id="clientDivision" path="clientDivision" spellcheck="true" />
				</div>
  			
  			</spring:bind>
  			

     	 </div>


     	 <div class="grid">
  		 	<spring:bind path="interviewerName">
	  			<div class ="field_wrapper">
					<label for="interviewerName" class="field_Label">Interviewer Name </label>
					<form:input type="text" class="field field_half " id="interviewerName" path="interviewerName" spellcheck="true" required="required"/>
				</div>
  			</spring:bind>

  			<spring:bind path="interviewMethod">
	  			<div class ="field_wrapper">
					<label for="interviewMethod" class="field_Label">Interview Method </label>
					<form:select type="text" class="field_half " id="interviewMethod" path="interviewMethod" spellcheck="true" required="required">
						<option value="Select"></option>
						<option value ="In person">In Person</option>
						<option value ="Phone Interview">Phone Interview</option>
						<option value ="Video Interview">Video Interview</option>
					</form:select>
				</div>
  			</spring:bind>

     	 </div>

     	 <div class="grid">
  		 	<spring:bind path="role">
	  			<div class ="field_wrapper">
					<label for="role" class="field_Label">Role </label>
					<form:input type="text" class="field field_half " id="role" path="role" spellcheck="true" required="required"/>
				</div>	
  			</spring:bind>

  			<spring:bind path="location">
	  			<div class ="field_wrapper">
					<label for="location" class="field_Label">Role Location </label>
					<form:input type="text" class="field field_half " id="location" path="location" spellcheck="true" required="required"/>
				</div>
  			</spring:bind>

     	 </div>

     	 <div id="question">

     	 </div>
     	 <div onclick="add();" id="questionAdd" class="field_full"> <p class="margin_auto">Add A Question </p> </div>

		<input type="submit" value="Submit" class="button">
		<input type="reset" value="Reset" class="button">
	</form:form>
  </div>



</body>
</html>
