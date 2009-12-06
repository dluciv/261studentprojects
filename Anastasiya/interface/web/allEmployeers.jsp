<%-- 
    Document   : allEmployeers
    Created on : 19.11.2009, 1:07:47
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="java.lang.*" %>
<%@page import="java.sql.*" %>
<%@page import="db.*" %>
<%@page import="entities.*" %>
<%@page import="service.*" %>
<jsp:useBean id="empFilter"  class="service.EmployeeFilter" scope="session"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%

            if (request.getParameter("reload") != null) {
                empFilter.setShowMaintancer(request.getParameter("cbMaintancers") != null);
                empFilter.setShowTester(request.getParameter("cbTesters") != null);
                empFilter.setShowProgrammer(request.getParameter("cbProgrammers") != null);
                empFilter.setShowTechnicalWriter(request.getParameter("cbTechnicalWriters") != null);
                empFilter.setShowOthers(request.getParameter("cbOthers") != null);
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Employeers</title>
    </head>
    <body>
        <table >
            <tbody>
                <tr>
                    <td>
                        <table border="0">
                            <thead>
                                <tr>
                                    <th>Меню</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><a href="allEmployeers.jsp">Сотрудники</a></td>
                                </tr>
                                <tr>
                                    <td><a href="AllApplications.jsp">Приложения</a></td>
                                </tr>
                                <tr>
                                    <td>Заказчики</td>
                                </tr>
                                <tr>
                                    <td>Другое</td>
                                </tr>
                            </tbody>
                        </table>

                    </td>
                    <td>
                        <h1>Список всех сотрудников:</h1>
                        <div style="border: 2px solid #000000;">
                        <form name="filterChange" action="allEmployeers.jsp">
                            <br>Отображать: <br>
                            <table>
                                <tbody>
                                    <tr>
                                        <td>
                                            <% if (!empFilter.isShowMaintancer()) {%>
                                            <input type="checkbox" name="cbMaintancers" value="ON"  />
                                            <% } else {%>
                                            <input type="checkbox" name="cbMaintancers" value="ON" checked="checked" />
                                            <% }%>
                                        <%--</td>
                                        <td>--%>
                                            Сопровождающие
                                        </td>
                                        <td>
                                            <% if (!empFilter.isShowTester()) {%>
                                            <input type="checkbox" name="cbTesters" value="ON"  />
                                            <% } else {%>
                                            <input type="checkbox" name="cbTesters" value="ON" checked="checked" />
                                            <% }%>
                                        <%--</td>
                                        <td>--%>
                                            Тестеры
                                        </td>
                                        <td>
                                            <% if (!empFilter.isShowProgrammer()) {%>
                                            <input type="checkbox" name="cbProgrammers" value="ON"  />
                                            <% } else {%>
                                            <input type="checkbox" name="cbProgrammers" value="ON" checked="checked" />
                                            <% }%>

                                        <%--</td>
                                        <td>--%>
                                            Программисты
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <% if (!empFilter.isShowTechnicalWriter()) {%>
                                            <input type="checkbox" name="cbTechnicalWriters" value="ON"  />
                                            <% } else {%>
                                            <input type="checkbox" name="cbTechnicalWriters" value="ON" checked="checked" />
                                            <% }%>
                                        <%--</td>
                                        <td>--%>
                                            Тех. писатели
                                        </td>
                                        <td>
                                            <% if (!empFilter.isShowOthers()) {%>
                                            <input type="checkbox" name="cbOthers" value="ON"  />
                                            <% } else {%>
                                            <input type="checkbox" name="cbOthers" value="ON" checked="checked" />
                                            <% }%>
                                        <%--</td>
                                        <td>--%>
                                            Остальные
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="submit" value="Обновить" name="reload" />
                                        </td>
                                        <td>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                        </div>
                        <br>
                        <% try {
                java.util.Vector<entities.Employee> list = entities.Employee.load(empFilter);
                        %>
                        <table border="1">
                            <thead>
                                <tr>
                                    <%--<td></td>--%>
                                    <td>Имя</td>
                                    <td>Телефон</td>
                                    <td>ICQ</td>
                                    <td>E-mail</td>
                                    <td>Jabber</td>
                                    <td>Сопровождающий</td>
                                    <td>Тестер</td>
                                    <td>Программист</td>
                                    <td>Тех. писатель</td>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (entities.Employee e : list) {%>
                                <tr>
                                    <td><a href="ShowEmployee.jsp?id= <%= e.getId()%>"><%= e.getName()%></a></td>
                                    <td><%= e.getPhone()%></td>
                                    <td><%= e.getIcq()%></td>
                                    <td><%= e.getEmail()%></td>
                                    <td><%= e.getJabber()%></td>
                                    <td align="center">
                                        <% if (e.isMaintancer()) {%>
                                        <input type="checkbox" name="cb<%= e.getId()%>"
                                               value="OFF"
                                               disabled="disabled"
                                               checked="checked"
                                               />
                                        <% } else {%>
                                        <input type="checkbox" name="cb<%= e.getId()%>"
                                               value="OFF"
                                               disabled="disabled" />
                                        <%}%>
                                    </td>
<%--                                    <% }%>
                                    <%if (empFilter.isShowTester()) {%>--%>
                                    <td align="center">
                                        <% if (e.isTester()) {%>
                                        <input type="checkbox" name="cb<%= e.getId()%>"
                                               value="OFF"
                                               disabled="disabled"
                                               checked="checked"
                                               />
                                        <% } else {%>
                                        <input type="checkbox" name="cb<%= e.getId()%>"
                                               value="OFF"
                                               disabled="disabled" />
                                        <%}%>
                                    </td>
<%--                                    <% }%>
                                    <%if (empFilter.isShowProgrammer()) {%>--%>
                                    <td align="center">
                                        <% if (e.isProgrammer()) {%>
                                        <input type="checkbox" name="cb<%= e.getId()%>"
                                               value="OFF"
                                               disabled="disabled"
                                               checked="checked"
                                               />
                                        <% } else {%>
                                        <input type="checkbox" name="cb<%= e.getId()%>"
                                               value="OFF"
                                               disabled="disabled" />
                                        <%}%>
                                    </td>
<%--                                    <% }%>
                                    <%if (empFilter.isShowTechnicalWriter()) {%>--%>
                                    <td align="center">
                                        <% if (e.isTechWriter()) {%>
                                        <input type="checkbox" name="cb<%= e.getId()%>"
                                               value="OFF"
                                               disabled="disabled"
                                               checked="checked"
                                               />
                                        <% } else {%>
                                        <input type="checkbox" name="cb<%= e.getId()%>"
                                               value="OFF"
                                               disabled="disabled" />
                                        <%}%>
                                    </td>
<%--                                    <% }%>--%>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>

                        <%} catch (java.sql.SQLException ex) {%>
                        <%= ex.getMessage() + "\n"%>
                        <% StackTraceElement[] elements = ex.getStackTrace();
     for (int i = 0; i < elements.length; i++) {%>
                        <%= elements[i]%>
                        <%}%>
                        Error loading employees :(
                        <%}%>

                    </td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
