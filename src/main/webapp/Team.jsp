<%@ page import="BusinessObjects.Team" %>
<!--
  Created by IntelliJ IDEA.
  User: Lukas Zander
  Date: 25.04.2023
  Time: 21:55
  To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Team Administration</title>
</head>
<body>
<b>Team Administration for
<%
    Team managedTeam = (Team) request.getAttribute("team");
%>
<%= managedTeam.getName()
%>
</b>

<br>
<h3>Adding a registered user to the team by his mail</h3>


<form action="TeamServlet" method="post">

    <div class="container">
        <label for="mail"><b>Mail</b></label> <input type="text"
                                                     placeholder="Enter mail address of user" name="mail" required>
        <br>
        <br>
        <button type="submit">Add User</button>
    </div>
</form>
</body>
</html>