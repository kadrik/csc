<%@ page import="csc.Controllers.Hub" %>
<%@include file="snippets/header.jsp"%>

<form action="/register" method="post">
        <input name="_returnUrl" type="hidden" value="<%= request.getContextPath() %>" />
        <fieldset>
            <ol>
                <li>
                    <label for="UserName">User name</label>
                    <input id="UserName" name="UserName" type="text"
                        value="<%= Hub.isUserLoggedIn() ? Hub.getUserName() : "" %>" />
                </li>
                <li>
                    <label for="FullName">Full name</label>
                    <input id="FullName" name="FullName" type="text" value="" />
                </li>
            </ol>
            <input type="submit" value="Save" />
        </fieldset>
    </form>

<%@include file="snippets/footer.jsp"%>