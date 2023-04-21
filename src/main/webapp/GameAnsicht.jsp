<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="BusinessObjects.Game"
	import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Game-Ansicht</title>
</head>
<body>

	<%
	List<Game> partien = (List<Game>) request.getAttribute("partien");
	if (partien.isEmpty()) {
		out.println("You have not any Games!");
	} else {
	%>

	<table border=1 width=100% height=50%>
		<tr>
			<th>Game ID</th>
			<th>Player</th>
			<th>Opponent</th>
			<th>Date</th>
			<th>Result</th>
			<th>Moves</th>
		<tr>
		<tr>
			<%
			for (Game partie : partien) {
			%>
			<td>
				<%
				out.println(partie.getGame_ID());
				%>
			</td>
			<td>
				<%
				out.println(partie.getPlayer((int) session.getAttribute("userId")));
				%>
			</td>
			<td>
				<%
				out.println(partie.getOpponent((int) session.getAttribute("userId")));
				%>
			</td>
			<td>
				<%
				out.println(partie.getDate());
				%>
			</td>
			<td>
				<%
				out.println(partie.getResult());
				%>
			</td>
			<td>
				<%
				out.println(partie.getMoves());
				%>
			</td>
		</tr>
		<%
		}
		%>
	</table>
	<br>
	<form action=GameByGameIdServlet>
		GameID: <input type=text name=gameId> <input type=submit>
	</form>
	<%
	}
	%>
</body>
</html>