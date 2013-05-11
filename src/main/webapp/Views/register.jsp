<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="otanga.Controllers.*" %>

<!doctype html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="../stylesheets/register.css">
    <title>Register</title>
</head>
<body>
    <form action="/register" method="post">
        <input name="_returnUrl" type="hidden" value="<%= request.getContextPath() %>" />
        <fieldset>
            <ol>
                <li>
                    <label for="UserName">User name</label>
                    <input id="UserName" name="UserName" type="text"
                        value="<%= Otanga.isUserLoggedIn() ? Otanga.getUserName() : "" %>" />
                </li>
                <li>
                    <label for="FullName">Full name</label>
                    <input id="FullName" name="FullName" type="text" value="" />
                </li>
            </ol>
            <input type="submit" value="Register" />
        </fieldset>
    </form>
</body>
</html>