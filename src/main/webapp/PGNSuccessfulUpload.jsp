<%@ page import="BusinessObjects.Game" %><%--
  Created by IntelliJ IDEA.
  User: benkostka
  Date: 21.04.23
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>File Upload Result</title>
</head>
<body>
<h2>File Upload Result</h2>
<p>The file was successfully uploaded and parsed.</p>
<p>Filename: <%= request.getAttribute("fileName")%><% Game parsedFile = (Game) request.getAttribute("parsedFile");%></p>
<h1> <%= parsedFile.toString()%></h1>
</body>
</html>

