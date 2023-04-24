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
	height: 250px;
}
</style>
</head>
<body>
	<ct-pgn-viewer board-pieceStyle="merida" board-boardStyle="green-white">
	<%
	out.println(request.getAttribute("game"));
	%> </ct-pgn-viewer>
	
	<br>
	<form action="GameDownloadServlet">
	<input type="hidden" name="eMoves" id="eMoves" value="" />
	<button onclick="extractMoves()">SAVE THE GAME</button>
	</form>
</body>
</html>