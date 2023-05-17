<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="BusinessObjects.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">

    <style>
        .normal {
            color: #000000;
            background-color: #ffffff;
        }

        .spezial {
            color: #000000;
            background-color: #66FF99;
            cursor: pointer;
        }
    </style>

    <!-- basic -->
    <meta charset="utf-8">
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
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
    <!-- owl stylesheets -->
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">

    <title>Club Administration</title>
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
                    <li class="nav-item"><a class="nav-link" href="datenschutz.html">Data protection</a></li>
                    <li class="nav-item"><a class="nav-link" href="impressum.html">Imprint</a></li>
                </ul>
                <div class="search_icon">
                    <ul>
                        <li class="nav-item"><a class="nav-link" href="LogoutServlet">LOGOUT</a></li>
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
<script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
<script src="./js/TableFilter.min.js" defer></script>

</body>
</html>