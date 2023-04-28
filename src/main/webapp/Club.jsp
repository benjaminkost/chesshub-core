<%@ page import="BusinessObjects.Club" %>
<%@ page import="BusinessObjects.Team" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Lukas Zander
  Date: 27.04.2023
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Club Administration</title>
</head>
<body>
<%
    Club managedClub = (Club) request.getAttribute("club");
    List<Team> teams = (List<Team>) request.getAttribute("teams");
%>

<h1> Club Administration for <%= managedClub.getName()%> </h1>

<br>
<h3> Teams of your club:</h3>
<table>
    <thead>
    <tr>
        <th>Team-ID</th>
        <th>Team-Name</th>
        <th>Team-Leader</th>
    </tr>
    </thead>
    <tbody>

    <%
        for (Team t: teams) {
    %>
    <tr>
    <td>
        <%=t.getTeam_ID()%>
    </td>
    <td>
        <%
            out.println(t.getName());
        %>
    </td>
    <td>
        <%
            out.println(t.getLeader().getFullName());
        %>
    </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<br>

<h3>Creating a new Team</h3>
<form action="ClubServlet" method="post">
    <input type="hidden" name="form" value="creatingTeam">

    <div class="container">
        <label for="leader_mail"><b>Mail</b></label> <input type="text" placeholder="Enter mail address of team leader" name="leader_mail" required>
        <br>
        <label for="team_name"><b>Name</b></label> <input type="text" placeholder="Enter name for team" name="team_name" required>
        <br>
        <button type="submit">Create team</button>
    </div>
</form>

<h3>Changing the leader of an existing team</h3>
<form action="ClubServlet" method="post">
    <input type="hidden" name="form" value="changingLeader">

    <div class="container">
        <label for="leader_mail"><b>Mail</b></label> <input type="text" placeholder="Enter mail address of new team leader" name="leader_mail" required>
        <br>
        <label for="team_ID"><b>ID</b></label> <input type="text" placeholder="Enter team-ID (see above)" name="team_ID" required>
        <br>
        <button type="submit">Change leader</button>
    </div>
</form>

<h3>Remove a team by its ID</h3>
<form action="ClubServlet" method="post">
    <input type="hidden" name="form" value="removingTeam">

    <div class="container">
        <label for="team_ID"><b>ID</b></label> <input type="text" placeholder="Enter team-ID (see above)" name="team_ID" required>
        <br>
        <button type="submit">Remove team</button>
    </div>
</form>

</body>
</html>
