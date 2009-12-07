<%-- 
    Document   : AllCustomers
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
<jsp:useBean id="custFilter"  class="service.CustomerFilter" scope="session"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%

            if (request.getParameter("reload") != null) {
                custFilter.setMaintancerId(request.getParameter("listMaintncer"));
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Customers</title>
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
                                    <td><a href="AllCustomers.jsp">Заказчики</a></td>
                                </tr>
                                <tr>
                                    <td>Другое</td>
                                </tr>
                            </tbody>
                        </table>

                    </td>
                    <td>
                        <h1>Список всех заказчиков:</h1>
                        <div style="border: 2px solid #000000;">
                            <form name="filterChange" action="AllCustomers.jsp">
                                <br>Отображать: <br>
                                <table>
                                    <tbody>
                                        <tr>
                                            <td>
                                                Сопровожающие:
                                            </td>
                                            <td>
                                                <select name="listMaintncer">
                                                    <% if (custFilter.getMaintancerId().equals(CustomerFilter.MAINTANCER_ALL)) {%>
                                                    <option value="<%= CustomerFilter.MAINTANCER_ALL%>" selected="true">Все</option>
                                                    <% } else {%>
                                                    <option value="<%= CustomerFilter.MAINTANCER_ALL%>">Все</option>
                                                    <% }%>

                                                    <%if (custFilter.getMaintancerId().equals(CustomerFilter.MAINTANCER_WITHOUT)) {%>
                                                    <option value="<%= CustomerFilter.MAINTANCER_WITHOUT%>" selected="true">Без сопровождающего</option>
                                                    <% } else {%>
                                                    <option value="<%= CustomerFilter.MAINTANCER_WITHOUT%>">Без сопровождающего</option>
                                                    <% }%>
                                                    <% EmployeeFilter f = new EmployeeFilter();%>
                                                    <% f.setShowMaintancer(true);%>
                                                    <% f.setShowOthers(false);%>
                                                    <% f.setShowProgrammer(false);%>
                                                    <%f.setShowTester(false);%>
                                                    <%f.setShowTechnicalWriter(false);%>
                                                    <%Vector<Employee> maintancers = Employee.load(f);%>
                                                    <%for (Employee e : maintancers) {%>
                                                    <% if(custFilter.getMaintancerId().equals(String.valueOf(e.getId()))) { %>
                                                    <option value="<%= e.getId() %>" selected="true"><%= e.getName() %> </option>
                                                    <% } else { %>
                                                    <option value="<%= e.getId() %>"><%= e.getName() %> </option>
                                                    <% } %>
                                                    <% } %>

                                                </select>
                                            </td>
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
                                <%--<table>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <% if (!applFilter.isShowFree()) {%>
                                                <input type="checkbox" name="cbFree" value="ON"  />
                                                <% } else {%>
                                                <input type="checkbox" name="cbFree" value="ON" checked="checked" />
                                                <% }%>
                                                Бесплатные
                                            </td>
                                            <td>
                                                <% if (!applFilter.isShowIndividual()) {%>
                                                <input type="checkbox" name="cbIndividual" value="ON"  />
                                                <% } else {%>
                                                <input type="checkbox" name="cbIndividual" value="ON" checked="checked" />
                                                <% }%>
                                                Индивидуальные
                                            </td>
                                            <td>
                                                <% if (!applFilter.isShowInner()) {%>
                                                <input type="checkbox" name="cbInner" value="ON"  />
                                                <% } else {%>
                                                <input type="checkbox" name="cbInner" value="ON" checked="checked" />
                                                <% }%>
                                                Внутренние
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <% if (!applFilter.isShowOptional()) {%>
                                                <input type="checkbox" name="cbOptional" value="ON"  />
                                                <% } else {%>
                                                <input type="checkbox" name="cbOptional" value="ON" checked="checked" />
                                                <% }%>
                                                Опциональные
                                            </td>
                                            <td>
                                                <% if (!applFilter.isShowOthers()) {%>
                                                <input type="checkbox" name="cbOthers" value="ON"  />
                                                <% } else {%>
                                                <input type="checkbox" name="cbOthers" value="ON" checked="checked" />
                                                <% }%>
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
                                </table>--%>
                            </form>
                        </div>
                        <br>
                        <% try {
                java.util.Vector<entities.Customer> list = entities.Customer.load(custFilter);
                        %>
                        <table border="1">
                            <thead>
                                <tr>
                                    <%--<td></td>--%>
                                    <td>Название</td>
                                    <td>Телефон</td>
                                    <td>Количество отделений</td>
                                    <td>Приписанный сопровождающий</td>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (entities.Customer e : list) {%>
                                <tr>
                                    <%--<td><%= e.getId()%></td>--%>
                                    <td><a href="ShowCustomer.jsp?id= <%= e.getId()%>"><%= e.getName()%></a></td>
                                    <td><%= e.getPhone()%></td>
                                    <td><%= e.getDepartments()%></td>
                                    <td>
                                        <% if (e.getMaintancer() != null) {%>
                                        <a href="ShowEmployee.jsp?id= <%= e.getMaintancer().getId()%>"><%= e.getMaintancer().getName()%></a>
                                        <% }%>
                                    </td>

                                </tr>
                                <%}%>
                            </tbody>
                        </table>

                        <%} catch (java.sql.SQLException ex) {%>
                        Не удалось получить список заказчиков
                        <%}%>

                    </td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
