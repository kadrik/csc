<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ page import="com.google.appengine.api.users.*" %>
<%@ page import="com.otanga.Otanga" %>

<!doctype html>
<!-- The DOCTYPE declaration above will set the     -->
<!-- browser's rendering engine into                -->
<!-- "Standards Mode". Replacing this declaration   -->
<!-- with a "Quirks Mode" doctype is not supported. -->

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <!--                                                               -->
    <!-- Consider inlining CSS to reduce the number of requested files -->
    <!--                                                               -->
    <link type="text/css" rel="stylesheet" href="Otanga.css">

    <!--                                           -->
    <!-- Any title is fine                         -->
    <!--                                           -->
    <title>Otanga Starter Project</title>
    
    <!--                                           -->
    <!-- This script loads your compiled module.   -->
    <!-- If you add any GWT meta tags, they must   -->
    <!-- be added before this line.                -->
    <!--                                           -->
    <script type="text/javascript" language="javascript" src="otanga/otanga.nocache.js"></script>
  </head>

  <!--                                           -->
  <!-- The body can have arbitrary html, or      -->
  <!-- you can leave the body empty if you want  -->
  <!-- to create a completely dynamic UI.        -->
  <!--                                           -->
  <body>

    <!-- OPTIONAL: include this if you want history support -->
    <iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position:absolute;width:0;height:0;border:0"></iframe>
    
    <!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
    <noscript>
      <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
      </div>
    </noscript>


<div id="header">
<span id="otanga"><a href="/">Otanga</a></span>
<span id="login">
<%
    //UserService userService = UserServiceFactory.getUserService();
    //User user = userService.getCurrentUser();
    if (Otanga.isUserLoggedIn())
    {%>
	        <%= Otanga.getUserName() %>
	        <a href="<%= Otanga.getLogoutUrl(request.getRequestURI()) %>">logout</a>
	        
	        <%= Otanga.isProfileStored() ? Otanga.testStore() : "not stored" %>
    <%}
    else
    {%>
        <a href="<%= Otanga.getLoginUrl(request.getRequestURI()) %>">login</a>
	<% } %>
</span>
</div>
 

<iframe name="content" width="100%" height="600px" frameborder=0></iframe>


  </body>
</html>
