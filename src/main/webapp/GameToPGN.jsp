<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Game to PGN</title>
</head>
<body>
<form action="GameToPGNServlet">
<h2>Please complete the following information:</h2>
    <input type="radio" name="color" value="White" / required> White
    <input type="radio" name="color" value="Black" /> Black <br> <br>
    Opponent: <input type=text name=opponent> <br>
    Result: <input type=text name=result> <br>
    Date: <input type=text name=date> <br>
    Round: <input type=text name=round required> <br>
    Event: <input type=text name=event> <br>
    Site: <input type=text name=site> <br> <br>
    <input type="submit" value="SUBMIT" />
</form>
</body>
</html>