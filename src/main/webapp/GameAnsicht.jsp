<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="BusinessObjects.Game"
	import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>

<style> 
.normal { color:#000000; background-color:#ffffff; }
.spezial { color:#000000; background-color:#66FF99; cursor:pointer; }
</style>

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
			<th>Player</th>
			<th>Opponent</th>
			<th>Date</th>
			<th>Result</th>
			<th>Moves</th>
		<tr>
			<%
			for (Game partie : partien) {
			
				out.println("<tr class=normal onmouseover=this.className='spezial'; onmouseout=this.className='normal'; onclick=window.location.href='http://localhost:8080/ChessGameManagement/GameByGameIdServlet?gameId=" + partie.getGame_ID() +"';>");
			%>
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
	<%
	}
	%>
</body>
</html>