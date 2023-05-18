<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<!-- basic -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- mobile metas -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<!-- site metas -->

<meta name="keywords" content="">
<meta name="description" content="">
<meta name="author" content="">
<!-- bootstrap css -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<!-- style css -->
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- Responsive-->
<link rel="stylesheet" href="css/responsive.css">
<!-- fevicon -->
<link rel="icon" href="images/fevicon.png" type="image/gif" />
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
<!-- Tweaks for older IEs-->
<link rel="stylesheet"
	href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<!-- owl stylesheets -->
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/owl.theme.default.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
	media="screen">

<title>Game-Ansicht</title>
</head>
<body>
<!-- header section start -->
	<div class="header_section">
		<div class="container-fluid">
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<div class="logo">
					<a href="Menu.jsp"><img src="images/logo.png"></a>
				</div>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item"><a class="nav-link"
							href="datenschutz.html">Data protection</a></li>
						<li class="nav-item"><a class="nav-link"
							href="impressum.html">Imprint</a></li>
					</ul>
					<div class="search_icon">
						<ul>
							<li class="nav-item"><a class="nav-link"
								href="LogoutServlet">LOGOUT</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
	</div>
	<!-- header section end -->
	<!-- banner section start -->
	<div class="banner_section layout_padding">
		
			

	<main class="py-4">

		<div class="container">

			<div class="row justify-content-center">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header"> <% out.println(request.getAttribute("teamName")+"-Games"); %> </div> 
						<div class="card-body">

							<div style="margin-bottom: 20px;">
							
							<%
									String[][] teamGames = (String[][]) request.getAttribute("teamGames");
									if (teamGames[0][0].equals("This team don't have any games!")) {
										out.println(teamGames[0][0]);
									} else {
									%>

								<input type="text" id="filter0" placeholder="Filter By White">
								<input type="text" id="filter1" placeholder="Filter By Black">
								<input type="text" id="filter2" placeholder="Filter By Event">
								<input type="text" id="filter3" placeholder="Filter By Date">
								<input type="text" id="filter4" placeholder="Filter By Result">

							</div>

							<table id="filter" class="table">

								<thead>
									<tr>
										<th>White</th>
										<th>Black</th>
										<th>Event</th>
										<th>Date</th>
										<th>Result</th>
										<th>Moves</th>
									</tr>
								</thead>
								<tbody>

									<%
									for (int i = 0; i < teamGames.length; i++) {

										out.println(teamGames[i][0]);
										out.println(teamGames[i][1]);
										out.println(teamGames[i][2]);
										%>
									</td>
										<%
										out.println(teamGames[i][3]);
										out.println(teamGames[i][4]);
										%>
									</td>
									<td>
										<%
										out.println(teamGames[i][5]);
										%>
									</td>
									<td>
										<%
										out.println(teamGames[i][6]);
										%>
									</td>
									<td>
										<%
										out.println(teamGames[i][7]);
										%>
									</td>
									<td>
										<%
										out.println(teamGames[i][8]);
										%>
									</td>
									</tr>
									<%
									}}
									%>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>


		</div>

	</main>
					
				</div>
		
	

<!-- footer section start -->
	<div class="footer_section layout_padding">
		<center>At the end of this project it should be possible for
			every chess player to store and manage your chess scoresheet in your
			own account. Furthermore, it should be possible to display every move
			on a chess board. If you are part of a team, your own games and the
			team games will be displayed separately to create a better overview.</center>
	</div>
	<!--  footer section end -->
	<!-- Javascript files-->
	<script src="js/jquery.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="js/jquery-3.0.0.min.js"></script>
	<script src="js/plugin.js"></script>
	<!-- sidebar -->
	<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="js/custom.js"></script>
	<!-- javascript -->
	<script src="js/owl.carousel.js"></script>
	<script
		src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
	<script src="./js/TableFilter.min.js" defer></script>
	
	

</body>
</html>