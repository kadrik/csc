<%--
  Created by IntelliJ IDEA.
  User: cedric
  Date: 5/13/13
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="snippets/header.jsp"%>
<%@ if (!Otanga.isUserLoggedIn()) {response.redirectTo(Otanga.getLoginUrl(request.getRequestURI()));} %>
                   <p>proceed with your consultation -)</p>

<%@include file="snippets/footer.jsp"%>