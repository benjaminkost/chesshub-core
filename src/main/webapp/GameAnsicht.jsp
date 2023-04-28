<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="BusinessObjects.Game"
	import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<style>
.normal {
	color: #000000;
	background-color: #ffffff;
}

.spezial {
	color: #000000;
	background-color: #66FF99;
	cursor: pointer;
}
</style>

<title>Game-Ansicht</title>
</head>
<body>

	<nav class="navbar navbar-expand-md navbar-light bg-white shadow-sm">
		<div class="container">
			<a class="navbar-brand"> MY GAMES </a>
		</div>
	</nav>

	<main class="py-4">

		<div class="container">

			<div class="row justify-content-center">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header"></div>
						<div class="card-body">

							<div style="margin-bottom: 20px;">

								<input type="hidden" id="filter0" placeholder="Filter By Player">
								<input type="text" id="filter1" placeholder="Filter By Opponent">
								<input type="text" id="filter2" placeholder="Filter By Event">
								<input type="text" id="filter3" placeholder="Filter By Date">
								<input type="text" id="filter4" placeholder="Filter By Result">

							</div>

							<table id="filter" class="table">

								<%
								List<Game> partien = (List<Game>) request.getAttribute("partien");
								if (partien.isEmpty()) {
									out.println("You have not any Games!");
								} else {
								%>

								<thead>
									<tr>
										<th>Player</th>
										<th>Opponent</th>
										<th>Event</th>
										<th>Date</th>
										<th>Result</th>
										<th>Moves</th>
									</tr>
								</thead>
								<tbody>

									<%
									for (Game partie : partien) {

										out.println(
										"<tr class=normal onmouseover=this.className='spezial'; onmouseout=this.className='normal'; onclick=window.location.href='./GameByGameIdServlet?gameId="
												+ partie.getGame_ID() + "';>");
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
										out.println(partie.getEvent());
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

								</tbody>
							</table>
							<%
							}
							%>
						</div>
					</div>
				</div>
			</div>


		</div>

	</main>

	<script src="./js/TableFilter.min.js" defer></script>

</body>
</html>