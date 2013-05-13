<%--
  Created by IntelliJ IDEA.
  User: cedric
  Date: 5/13/13
  Time: 4:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="otanga.Controllers.Otanga" %>

<!doctype html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="../../stylesheets/otanga.css">
    <title>Otanga</title>
</head>
<body>
<!-- OPTIONAL: include this if you want history support -->
<iframe src="javascript:''" id="__gwt_historyFrame" tabindex="-1" style="position: absolute; width: 0; height: 0; border: 0"></iframe>
<!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
<noscript>
    <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red;
            background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled for this application to display correctly.
    </div>
</noscript>
<div id="header">
    <span id="otanga"><a href="/">Otanga</a></span>
        <span id="login">
            <% if (Otanga.isUserLoggedIn()) { %>
                <%= Otanga.getUserName() %>&nbsp;&nbsp;<a href="<%= Otanga.getLogoutUrl(request.getRequestURI()) %>" style="font-weight: bold;">Sign out</a>&nbsp;
                <%= !Otanga.isProfileStored() ?
                        Otanga.testStoreAndUpdateProfile() + " (new)" :
                        (String)Otanga.getStoredProfile().getProperty("nickname") + " (existing)" %>
            <%}
            else { %>
                <a href="<%= Otanga.getLoginUrl(request.getRequestURI()) %>">Sign in</a>
            <% } %>
        </span>
</div>

<div id=main>
    <%@include file="navigation.jsp"%>

    <div id=content>
