<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>

	<h1>Registration</h1>
	<form action="RegistrationServlet" method="post">

		<label for="firstname">Firstname:</label> <input type="firstname"
			id="firstname" name="firstname" required><br> <label
			for="lastname">Lastname:</label> <input type="lastname" id="lastname"
			name="lastname" required><br> <label for="email">E-Mail-Address:</label>
		<input type="email" id="email" name="email" required><br>


		<label for="password">Password:</label> <input type="password"
			id="password" name="password" required><br> <input
			type="submit" value="Register">
	</form>

</body>
</html>