<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
  <link rel="stylesheet"
        href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
  <!-- owl stylesheets -->
  <link rel="stylesheet" href="css/owl.carousel.min.css">
  <link rel="stylesheet" href="css/owl.theme.default.min.css">
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
        media="screen">
  <title>E Business</title>
</head>
<body>

<!-- header section start -->
<div class="header_section">
  <div class="container-fluid">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="logo">
        <a href="index.jsp"><img src="images/logo.png"></a>
      </div>
      <button class="navbar-toggler" type="button" data-toggle="collapse"
              data-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false"
              aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item"><a class="nav-link"
                                  href="datenschutz.html">Data protection</a></li>
          <li class="nav-item"><a class="nav-link"
                                  href="impressum.html">Imprint</a></li>
          <li class="nav-item"><a class="nav-link"
                                  href="Registration.jsp">Registration</a></li>
          <li class="nav-item"><a class="nav-link" href="Login.jsp">Login</a>
          </li>
          <li class="nav-item"><a class="nav-link" href="EBusiness.jsp">E Business</a>
          </li>
        </ul>
        <div class="search_icon">
          <ul>
            <li><a><img src="images/chess.png"></a></li>
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
    <h2><b>This page is related to a task in the module 204 E-Business (Berlin School of Economics and Law)</b></h2>
    <hr>
    <h3> Evaluating the current position with the Stockfish Online API </h3>
    <form action="EBusinessServlet" method="post">
      <input type="hidden" name="form" value="evaluation">
      <label for="fen"><b>FEN </b></label><br>
      <input type="text" placeholder="Current position" name="fen" required>
      <br>
      <label for="depth"><b>DEPTH </b></label><br>
      <input type="text" placeholder="Depth <14" name="depth" required>
      <br>
      <button type="submit" style="background:#636363;color:white;">Evaluate current position</button>
    </form>
    <%
      String evaluation = "undefined";
      if(request.getAttribute("evaluation") != null){
        evaluation = request.getAttribute("evaluation").toString();}
    %>
    <br>
    <h4> Evaluation: <%=evaluation%> </h4>
    <hr>
    <br>
    <h3> Calculating the next best move with the Rapid Chess API</h3>
    <form action="EBusinessServlet" method="post">
      <input type="hidden" name="form" value="nextmove">
      <label for="fen"><b>FEN </b></label><br>
      <input type="text" placeholder="Current position" name="fen" required>
      <br>
      <button type="submit" style="background:#636363;color:white;">Calculate next best move</button>
    </form>

    <%
      String best_move = "undefined";
      if(request.getAttribute("best_move") != null){
        best_move = request.getAttribute("best_move").toString();}
    %>
    <br>
    <h4> Best Move: <%=best_move%> </h4>

  </div>
</div>
<!-- footer section start -->
<div class="footer_section layout_padding">
  <center>The goal of this task is, to integrate two APIs for a meaningful usecase.</center>
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
<script
        src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
</body>
</html>