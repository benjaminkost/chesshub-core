<%@ page import="BusinessObjects.Team" %>
<%@ page import="BusinessObjects.User" %>
<%@ page import="java.util.List" %>
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
<%
    Team managedTeam = (Team) request.getAttribute("team");
    List<User> members = (List<User>) request.getAttribute("members");
%>

<h1> Team Administration for <%= managedTeam.getName()%> </h1>
<br>
<h3> Team members:</h3>
<% for(User u: members){
%>
 <%=u.getFullName()+" | "+u.getUser_Id()%><br>
<%}%>

<br>
<h3>Adding a registered user to the team by his mail</h3>
<form action="TeamServlet" method="post">
    <input type="hidden" name="form" value="adding">

    <div class="container">
        <label for="mail"><b>Mail</b></label> <input type="text"
                                                     placeholder="Enter mail address of user" name="mail" required>
        <br>
        <br>
        <button type="submit">Add User</button>
    </div>
</form>
<h3>Remove a member from your team by his UserID (see above)</h3>

<form action="TeamServlet" method="post">
    <input type="hidden" name="form" value="removing">

    <div class="container">
        <label for="remove_id"><b>ID</b></label> <input type="text"
                                                     placeholder="Enter ID of user" name="remove_id" required>
        <br>
        <br>
        <button type="submit">Remove User</button>
    </div>
</form>

</body>
</html>