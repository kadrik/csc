<%--
  Created by IntelliJ IDEA.
  User: cedric
  Date: 5/13/13
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="otanga.Controllers.*" %>
<% if (!Otanga.isUserLoggedIn()) {response.sendRedirect(Otanga.getLoginUrl(request.getRequestURI()));} %>

<html>
<head>
    <title></title>
</head>
<body>
                   <p>proceed with your consultation -)</p>
</body>
</html>