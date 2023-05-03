<%@ page import="BusinessObjects.User" %><%--
  Created by IntelliJ IDEA.
  User: Lukas Zander
  Date: 03.05.2023
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Profile</title>
</head>
<body>
<%
    User loggedInUser = (User) request.getAttribute("user");
    String oldFirstName = loggedInUser.getFirstname();
    String oldLastname = loggedInUser.getLastname();
    String oldMail = loggedInUser.getEmail();
%>

<h1> Edit Profile </h1>
<br>
<h3>Please insert the details</h3>
<form action="UserServlet" method="post">
    <div class="container">
        <label for="firstName"><b>FirstName</b></label> <input type="text" name="firstName" value=<%=oldFirstName%>>
        <br>
        <label for="lastName"><b>LastName</b></label> <input type="text" name="lastName" value=<%=oldLastname%>>
        <br>
        <label for="mail"><b>Mail</b></label> <input type="text" name="mail" value="<%=oldMail%>">
        <br>
        <button type="submit">Update profile</button>
    </div>
</form>
</body>
</html>