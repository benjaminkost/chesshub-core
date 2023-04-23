<%--
  Created by IntelliJ IDEA.
  User: benkostka
  Date: 21.04.23
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>File Upload Form</title>
</head>
<body>
<h2>File Upload Form</h2>
    <form action="PGNServlet" method="post" enctype="multipart/form-data">
    <input type="file" name="file" /><br /><br />
    <input type="submit" value="Upload" />
</form>
</body>
</html>

