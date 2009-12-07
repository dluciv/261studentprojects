<%-- 
    Document   : ShowCustomer
    Created on : 19.11.2009, 1:57:20
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="java.lang.*" %>
<%@page import="java.util.*" %>
<%@page import="java.math.*" %>
<%@page import="db.*" %>
<%@page import="entities.*" %>
<%@page import="service.*" %>
<jsp:useBean id="cust"  class="entities.Customer" scope="session"/>

<% String id = request.getParameter("id").trim();%>
<jsp:setProperty name="cust" property="id" value="<%= new java.math.BigDecimal(id) %>"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Описание приложения</title>
    </head>
    <body>
        <h1>Приложение:</h1>
        <%--Ищем данные по переданному id--%>
        <% try {
                cust.loadById();
        %>
        <%--Выводим данные по самому сотруднику--%>
        <table border="1">
            <tr>
                <td>
                    Название
                </td>
                <td>
                    <%= cust.getName()%>
                </td>
            </tr>
            <tr>
                <td>
                    Телефон
                </td>
                <td>
                    <%= cust.getPhone()%>
                </td>
            </tr>
            <tr>
                <td>
                    Количество отделений
                </td>
                <td>
                    <%= cust.getDepartments()%>
                </td>
            </tr>
            <tr>
                <td>
                    Сопровождающий
                </td>
                <td>
                    <% if (cust.getMaintancer() != null) { %>
                    <a href = "ShowEmployee?id=<%= cust.getMaintancer().getId() %>"> <%= cust.getMaintancer().getName() %> </a>
                    <% } %>
                </td>
            </tr>

        </table>

        <%--Выводим расширенные данные--%>
        <br><br>
        <div style="border: 2px solid #000000;">
            Разрабатываемые индивидуальные приложения: <br>
            <% Vector<Pair> indApplic = cust.getIndividualApplications(); %>
            <% if(indApplic.size() > 0 ) { %>
            <ul>
                <% for(Pair p : indApplic) { %>
                <li><a href="ShowApplication?id=<%= p.getId() %>"><%= p.getName() %></a> </li>
                <% } %>
            </ul>
            <% }else { %>
            Нет индивидуальных приложений
            <% } %>

        </div>

        <br><br>
        <div style="border: 2px solid #000000;">
            Установленные приложения: <br>
            <% Vector<Pair> indstalledApplic = cust.getInstalledApplications(); %>
            <% if(indstalledApplic.size() > 0 ) { %>
            <ul>
                <% for(Pair p : indstalledApplic) { %>
                <li><a href="ShowApplication?id=<%= p.getId() %>"><%= p.getName() %></a> </li>
                <% } %>
            </ul>
            <% }else { %>
            Ни одного приложения не установлено
            <% } %>

        </div>

        <%} catch (SQLException ex) {%>
        Ошибка работы с базой данных
        <%} catch (NoDataFoundException ex) {%>
        Не найдены данные для приложения с ID: <%= ex.getMessage()%>
        <%}%>


        <a href="javascript: history.go(-1)">Back</a>
    </body>
</html>
