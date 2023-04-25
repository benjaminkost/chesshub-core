<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

	<form action="LoginServlet" method="post">

		<div class="container">
			<label for="mail"><b>Mail</b></label> <input type="text"
				placeholder="Enter your mail address" name="mail" required>
			<br> <label for="psw"><b>Password</b></label> <input
				type="password" placeholder="Enter Password" name="psw" required>
			<br>
			<button type="submit">Login</button>
		</div>
		<div class="container" style="background-color: #f1f1f1">
			<span class="psw"> <a href="#">Forgot password?</a></span>
		</div>
	</form>


</body>
</html>