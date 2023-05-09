<%@ page import="BusinessObjects.Club" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Lukas Zander
  Date: 09.05.2023
  Time: 09:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<%List<Club> clubs = (List<Club>) request.getAttribute("clubs");%>

<h1>Administration Area</h1>

<h3> Existing clubs:</h3>
    <table>
        <thead>
        <tr>
            <th>Club-ID</th>
            <th>Club-Name</th>
            <th>Club-Leader</th>
        </tr>
        </thead>
        <tbody>

        <%
            for (Club c: clubs) {
        %>
        <tr>
            <td>
                <%=c.getClub_ID()%>
            </td>
            <td>
                <%=c.getName()%>
            </td>
            <td>
                <%=c.getPresident().getFullName()%>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
<br>

<h3>Creating a new Club</h3>
<form action="AdminServlet" method="post">
    <input type="hidden" name="form" value="creatingClub">

    <div class="container">
        <label for="leader_mail"><b>Mail</b></label> <input type="text" placeholder="Enter mail address of club leader" name="leader_mail" required>
        <br>
        <label for="club_name"><b>Name</b></label> <input type="text" placeholder="Enter name for club" name="club_name" required>
        <br>
        <button type="submit">Create club</button>
    </div>
</form>

<h3>Changing the leader of an existing Club</h3>
<form action="AdminServlet" method="post">
    <input type="hidden" name="form" value="changingLeader">

    <div class="container">
        <label for="leader_mail"><b>Mail</b></label> <input type="text" placeholder="Enter mail address of new club leader" name="leader_mail" required>
        <br>
        <label for="club_ID"><b>ID</b></label> <input type="text" placeholder="Enter Club-ID (see above)" name="club_ID" required>
        <br>
        <button type="submit">Change leader</button>
    </div>
</form>

<h3>Remove a Club by its ID</h3>
<form action="AdminServlet" method="post">
    <input type="hidden" name="form" value="removingClub">

    <div class="container">
        <label for="club_ID"><b>ID</b></label> <input type="text" placeholder="Enter Club-ID (see above)" name="club_ID" required>
        <br>
        <button type="submit">Remove club</button>
    </div>
</form>

</body>
</html>
