<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirm favourite question list</title>
</head>
<body>
	<header>
		<div>User Favourite List</div>
	</header>
	<br>


	<h1>User Favourite Questions list</h1>
	<form action="groupfav" method="post">
		<table>
			<tr>
				<td><center>Your questions have been added 
						have been added to the favourite list</td>
			</tr>

			<tr>
				<td><c:forEach
						items="${ questionfavlist.fav_questions }" var="question">
							${ question.category }
					</c:forEach></td>
			</tr>


			<tr>
				<td><br> Press Submit to complete <br> <br></td>
			</tr>
		</table>
		<input type="hidden" name="groupfav" value=${ questionfavlist.id }>
		<button type="submit" name="add">add Questions</button>

	</form>
</body>
</html>
