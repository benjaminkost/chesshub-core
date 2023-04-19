<%--
  Created by IntelliJ IDEA.
  User: benkostka
  Date: 19.04.23
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PGN Upload</title>
</head>
<body>
<h1>Datei hochladen</h1>
<form method="post" action="<%= request.getContextPath() %>/pgnservlet">
    <label for="file">Datei auswählen:</label>
    <input type="file" id="file" name="file" />
    <br />
    <input type="submit" value="Hochladen" />
</form>
</body>
</html>

