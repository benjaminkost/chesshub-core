<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu</title>
</head>
<body>
<h1>
<p><%= request.getAttribute("welcome")%></p>
</h1>

<a href=GamesByUserIdServlet>Games</a> <br>
<a href=SelectColor.jsp>Upload PGN</a> <br>
<a href=GameToPGN.jsp>Game to PGN</a> <br>
<a href=GamesWithoutOpponentServlet>Games without registered opponent</a> <br>

</body>
</html>