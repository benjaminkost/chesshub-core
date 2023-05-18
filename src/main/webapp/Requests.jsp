<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="BusinessObjects.Request"
	import="BusinessObjects.User"
	import="static Management.UserManagement.getUserById"
	import="java.util.List"%>
<html>
<head>
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

<title>Requests</title>
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
		<div class="container">
			<form action="ManageRequestServlet" action="get">
				<h1>Requests</h1>
				<br>
				<h2>Sent requests:</h2>
				<a class="nav-item"> <%
 List<String> senderRequests = (List<String>) request.getAttribute("senderRequests");
 for (String req : senderRequests) {
 	out.println(req);
 }
 %> <br>
					<h2>
						<a>
							<button name="action" type="submit" value="3">DELETE&emsp;</button>
						</a>
					</h2>

				</a> <br> <br>
				<h2>Received requests:</h2>
				<a class="nav-item"> <%
 List<String> recipientRequests = (List<String>) request.getAttribute("recipientRequests");
 for (String req : recipientRequests) {
 	out.println(req.toString());
 }
 %> <br>
					<h2>
						<a>
							<button name="action" type="submit" value="1">ACCEPT&emsp;</button>
						</a> <a>
							<button name="action" type="submit" value="2">DENY&emsp;</button>
						</a>
					</h2>

				</a>
			</form>

		</div>
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
</body>
</html>

