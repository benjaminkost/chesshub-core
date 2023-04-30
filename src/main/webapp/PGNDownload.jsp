<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://c2a.chesstempo.com/pgnviewer/v2.5/pgnviewerext.vers1.css"
	media="all" rel="stylesheet" crossorigin>
<script defer language="javascript"
	src="https://c1a.chesstempo.com/pgnviewer/v2.5/pgnviewerext.bundle.vers1.js"
	crossorigin></script>

<link
	href="https://c1a.chesstempo.com/fonts/MaterialIcons-Regular.woff2"
	rel="stylesheet" crossorigin>

<link href="https://c1a.chesstempo.com/fonts/chessalphanew-webfont.woff"
	media="all" rel="stylesheet" crossorigin>

<meta charset="UTF-8">

<!-- basic -->
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

<title>PGN Viewer</title>

<script type="text/javascript">
function extractMoves() {
    const moveListContainer = document.getElementsByClassName("ct-move-list-container")[0];
    const moves = moveListContainer.querySelectorAll('.ct-move-notation');
    let formattedMoves = '';

    moves.forEach(move => {
        formattedMoves += move.textContent.trim() + ' ';
    });

    formattedMoves += '*';
    document.getElementById('eMoves').value = formattedMoves.trim();
}
</script>

<style>
ct-pgn-viewer.ct-pgn-viewer move-list .ct-move-list-container {
	height: 280px;
}
</style>
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

	<ct-pgn-viewer board-pieceStyle="merida" board-boardStyle="green-white" board-pieceMovementSpeed="50" move-list-autoplaySpeed="1000" move-list-fontSize="14" move-list-resizable="true">
	<%
	out.println(request.getAttribute("game"));
	%> </ct-pgn-viewer>
	
	<br>
	<form action="GameDownloadServlet">
	<input type="hidden" name="eMoves" id="eMoves" value="" />
	<button onclick="extractMoves()">SAVE THE GAME</button>
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