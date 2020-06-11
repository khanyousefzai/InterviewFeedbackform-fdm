<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
  	href=".\frontresources\fdm.png" />
<meta charset="ISO-8859-1">
<title>Admin Home</title>
<link rel="stylesheet" href=".\frontresources\common.css" />
<link rel="stylesheet" href=".\frontresources\adminHome.css" />
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
        <span>Admin Home Page</span>
      </div>
    </nav>

    <div class="home_container">
      <nav class="displayFlexRow " id="home_nav">
        <h1 class="alignselfCenter" id="homeNavTitle">Admin Home Page</h1>
        <div class="displayFlexRow" id="homeNavButtons">

        </div>
      </nav>

      <div class = "admin_container bordered top_item">
        <h1 class="tittle">Control Panel</h1>


        <div class="controlPanel">
          <div class = "option bordered">
              <h2 class="alignselfCenter">Remove</h2>
              <form  method = "get" action = "/removePage" class="alignselfCenter">
                <input type = "submit" value = "Remove" class="button buttonspacing">
              </form>
          </div>
          <div class = "option bordered">
              <h2 class="alignselfCenter">Reset</h2>
              <form  method = "get" action = "/resetPage" class="alignselfCenter">
                <input type = "submit" value = "Reset" class="button buttonspacing">
              </form>
          </div>
          <div class = "option bordered">
              <h2 class="alignselfCenter">Create</h2>
              <div class="homeOptions alignselfCenter">
                <form  method = "get" action = "/createUserPage">
                  <input type = "submit" value = "Add User" class="button buttonspacing">
                </form>
                <form  method = "get" action = "/createClientPage">
                  <input type = "submit" value = "Add Client" class="button buttonspacing">
                </form>
              </div>
          </div>
        </div>




      </div>



		</div>

  </div>


	</nav>
</body>
</html>
