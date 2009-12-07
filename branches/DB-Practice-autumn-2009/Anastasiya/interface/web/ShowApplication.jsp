<%-- 
    Document   : ShowApplication
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
<jsp:useBean id="appl"  class="entities.Application" scope="session"/>

<% String id = request.getParameter("id").trim();%>
<jsp:setProperty name="appl" property="id" value="<%= new java.math.BigDecimal(id) %>"/>
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
                appl.loadById();
        %>
        <%--Выводим данные по самому сотруднику--%>
        <table border="1">
            <tr>
                <td>
                    Название
                </td>
                <td>
                    <%= appl.getName()%>
                </td>
            </tr>
            <tr>
                <td>
                    Описание
                </td>
                <td>
                    <%= appl.getDescription()%>
                </td>
            </tr>

        </table>

        <%--Выводим расширенные данные--%>
        <br><br>
        <div style="border: 2px solid #000000;">
            Тип приложения: <br>
            <%--Если сопровождающий, то выводим список "приписанных" заказчиков--%>
            <% if (appl.getType() == ApplicationType.FREE) {%>
            Бесплатное приложение: <br>
            <% if (appl.isRequired()) {%>
            Необходимо для работы системы.
            <% } else {%>
            Не является необходимым для работы системы
            <% }%>

            <%} else if (appl.getType() == ApplicationType.INDIVIDUAL) {%>
            Индивидуальное приложение. <br>
            <% Pair customer = appl.getIndividualCustomer();%>
            Заказчик: <a href="ShowCustomer?id=<%= customer.getId()%>"> <%= customer.getName()%> </a> <br>
            <% BigDecimal price = appl.getPrice(); %>
            Стоимость: <% if(price == null) {%> не указана <% }else { %> <%= price %> <% } %>
            <%} else if (appl.getType() == ApplicationType.INNER) {%>
            Приложение для внутреннего использования.<br>
            <% if (appl.haveOuterAccess()) {%>
            Доступ имеют партнеры и сотрудники компании.
            <% } else {%>
            Доступ имеют только сотрудники компании.
            <% }%>

            <%} else if (appl.getType() == ApplicationType.OPTIONAL) {%>
            Опциональное приложение.<br>
            Стоимость: <%= appl.getPrice()%>
            <%} else if (appl.getType() == ApplicationType.OTHERS) {%>
            Приложение не имеет типа. Необходимо определить его тип.
            <% }%>
        </div>

        <br><br>
        <div style="border: 2px solid #000000;">
            Разработчики:
            <% Vector<Pair> devel = appl.getDevelopers();%>

            <%if (devel.size() > 0) {%>
            <br>

            <ul>
                <% }%>

                <% for (Pair c : devel) {%>
                <li> <a href = "ShowEmployee.jsp?id=<%= c.getId()%>"><%= c.getName()%> </a> </li>
                <% }%>

                <%if (devel.size() > 0) {%>
            </ul>
            <br>
            <% }%>

        </div>
            <br><br>
        <% if (appl.getType() == ApplicationType.FREE || appl.getType() == ApplicationType.OPTIONAL) {%>
        <div style="border: 2px solid #000000;">
            Заказчики, у которых установлено это приложение:
            <% Vector<Pair> cust = appl.getCustomers();%>

            <%if (cust.size() > 0) {%>
            <br>

            <ul>
                <% }%>

                <% for (Pair c : cust) {%>
                <li> <a href = "ShowCustomer.jsp?id=<%= c.getId()%>"><%= c.getName()%> </a> </li>
                <% }%>

                <%if (cust.size() > 0) {%>
            </ul>
            <br>
            <% }%>

        </div>
        <% }%>
        <%} catch (SQLException ex) {%>
        Ошибка работы с базой данных
        <%} catch (NoDataFoundException ex) {%>
        Не найдены данные для приложения с ID: <%= ex.getMessage()%>
        <%} catch (InconstistencyException ex) {%>
        Данные для приложения ID: <%= ex.getMessage()%> не верны
        <%}%>


        <a href="javascript: history.go(-1)">Back</a>
    </body>
</html>
