<%-- 
    Document   : ShowEmployee
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
<jsp:useBean id="empl"  class="entities.Employee" scope="session"/>

<% String id = request.getParameter("id").trim();%>
<jsp:setProperty name="empl" property="id" value="<%= new java.math.BigDecimal(id) %>"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Описание сотрудника</title>
    </head>
    <body>
        <h1>Сотрудник:</h1>
        <%--Ищем данные по переданному id--%>
        <% try {
                empl.loadById();
        %>
        <%--Выводим данные по самому сотруднику--%>
        <table border="1">
            <tr>
                <td>
                    Имя
                </td>
                <td>
                    <%= empl.getName()%>
                </td>
            </tr>
            <tr>
                <td>
                    Телефон
                </td>
                <td>
                    <%= empl.getPhone()%>
                </td>
            </tr>
            <tr>
                <td>
                    #ICQ
                </td>
                <td>
                    <%= empl.getIcq()%>
                </td>
            </tr>
            <tr>
                <td>
                    E-Mail
                </td>
                <td>
                    <%= empl.getEmail()%>
                </td>
            </tr>
            <tr>
                <td>
                    Jabber
                </td>
                <td>
                    <%= empl.getJabber()%>
                </td>
            </tr>
        </table>

        <%--Выводим данные по должностям--%>
        <br><br>
        <div style="border: 2px solid #000000;">
            Обязанности: <br>
            <ul>
                <%--Если сопровождающий, то выводим список "приписанных" заказчиков--%>
                <% if (empl.isMaintancer()) {%>
                <li> Сопровождающий
                    <div>
                        <% Vector<Pair> cust = empl.getCustomers();%>

                        <%if (cust.size() > 0) {%>
                        <br>
                        Заказчики, к которым приписан:
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
                </li>
                <%}%>

                <%--Если программист, то выводим список языков и опыт--%>
                <% if (empl.isProgrammer()) {%>
                <li> Программист (Опыт: <%= empl.getExperience()%> лет)
                    <div>
                        <% Vector<String> lang = empl.getDevelopingLanguages();%>

                        <%if (lang.size() > 0) {%>
                        <br>
                        Владеет языками программирования:
                        <ul>
                            <% }%>

                            <% for (String c : lang) {%>
                            <li> <%= c%> </li>
                            <% }%>

                            <%if (lang.size() > 0) {%>
                        </ul>
                        <br>
                        <% }%>

                    </div>
                </li>
                <%}%>
                <%--Если технический писатель, то выводим список языков--%>
                <% if (empl.isTechWriter()) {%>
                <li> Технический писатель
                    <div>
                        <% Vector<String> lang = empl.getWriterLanguages();%>

                        <%if (lang.size() > 0) {%>
                        <br>
                        Владеет языками:
                        <ul>
                            <% }%>

                            <% for (String c : lang) {%>
                            <li> <%= c%> </li>
                            <% }%>

                            <%if (lang.size() > 0) {%>
                        </ul>
                        <br>
                        <% }%>

                    </div>
                </li>
                <%}%>
                <%--Если тестер, то тестер =) --%>
                <% if (empl.isTester()) {%>
                <li> Тестировщик </li>
                <%}%>
            </ul>
        </div>
        <br><br>
        <div style="border: 2px solid #000000;">
            <%--Теперь выводим список всех приложений, над которыми сотрудник каким-либо образом работает.--%>

            <% Vector<Pair> applics = empl.selectApplications();%>
            <% if (applics.size() > 0) {%>
            Участвует в разработке приложений:
            <ul>
                <% }%>
                <% for (Pair c : applics) {%>
                <li> <a href = "ShowApplication.jsp?id=<%= c.getId()%>"><%= c.getName()%> </a></li>
                <% }%>

                <% if (applics.size() > 0) {%>
            </ul>
        </div>
        <% }%>
        <%} catch (SQLException ex) {%>
        Error retrieiving data
        <%} catch (NoDataFoundException ex) {%>
        No data found on specified employee ID: <%= ex.getMessage()%>
        <%}%>

        <a href="javascript: history.go(-1)">Back</a>
    </body>
</html>
