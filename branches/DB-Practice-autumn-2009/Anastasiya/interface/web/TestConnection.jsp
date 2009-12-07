<%-- 
    Document   : TestConnection.jsp
    Created on : 07.12.2009, 22:04:06
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% db.ConnectionProvider.HOST = request.getParameter("textHost"); %>
        <% db.ConnectionProvider.PORT = request.getParameter("textPort"); %>
        <% db.ConnectionProvider.DATABASE = request.getParameter("textDatabase"); %>
        <% db.ConnectionProvider.PASSWORD = request.getParameter("textPassword"); %>
        <% db.ConnectionProvider.LOGIN = request.getParameter("textLogin"); %>
        Проверка соединения...
        <% try { %>
        <% if(db.ConnectionProvider.getConnection() != null){ %>
        <jsp:forward page="allEmployeers.jsp"/>
        <% } else { %>
        <jsp:forward page="welcomeJSF.jsp?message=error"/>
        <% } %>
        <% }catch(java.lang.Exception e) {%>
        <jsp:forward page="welcomeJSF.jsp?message=error"/>
        <% }%>
    </body>
</html>
