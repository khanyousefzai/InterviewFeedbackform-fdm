<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href=".\frontresources\fdm.png" />
<meta charset="ISO-8859-1">
<title>Create Client</title>
<link rel="stylesheet" href=".\frontresources\common.css" />
<link rel="stylesheet" href=".\frontresources\adminHome.css" />
</head>
<body onload="showErrorCreateClientPage()">

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
        <span>Admin: Create Client</span>
      </div>
    </nav>

    <div class="home_container">
      <nav class="displayFlexRow " id="home_nav">
        <h1 class="alignselfCenter" id="homeNavTitle">Admin: Create Client</h1>
        <div class="displayFlexRow" id="homeNavButtons">

        </div>
      </nav>




      <div class = "admin_container bordered top_item">
        <h1 class="tittle">Create Client Panel</h1>
        <div id = "errContainer">
        	<p id="errMessage">${err}</p>
        	<p id="succMessage">${succ}</p>
        </div>

        <div class="option_container bordered">
          <form method="post" action="adminCreateClient">

          <div class = "field_wrapper">
            <label class = "field_Label">Client Name </label>
            <input type = "text" name="clientName" class="field">
          </div>
          <div class = "field_wrapper">
            <label class = "field_Label">Account Manager </label>
            <select class="field_half" id="accountManager" name="accountManagerList" multiple>
						<c:forEach items="${accountManagerList}" var="am">
            					<option value="${am.getUserId()}">${am.getFirstname()}</option>
        				</c:forEach>
					</select>
          </div>


          <input type = "submit" value="Submit" class="button submit">

          </form>

        <form method="get" action="backToHome">
          <input type = "submit" value="Back Home" class="button submit">
        </form>
      </div>


      </div>

    </div>
  </div>
</body>
</html>
