<%-- 
    Document   : AllApplications
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
<jsp:useBean id="applFilter"  class="service.ApplicationFilter" scope="session"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%

            if (request.getParameter("reload") != null) {
                applFilter.setShowFree(request.getParameter("cbFree") != null);
                applFilter.setShowIndividual(request.getParameter("cbIndividual") != null);
                applFilter.setShowIner(request.getParameter("cbInner") != null);
                applFilter.setShowOptional(request.getParameter("cbOptional") != null);
                applFilter.setShowOthers(request.getParameter("cbOthers") != null);
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Applications</title>
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
                        <h1>Список всех приложений:</h1>
                        <div style="border: 2px solid #000000;">
                        <form name="filterChange" action="AllApplications.jsp">
                            <br>Отображать: <br>
                            <table>
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
                            </table>
                        </form>
                        </div>
                        <br>
                        <% try {
                java.util.Vector<entities.Application> list = entities.Application.load(applFilter);
                        %>
                        <table border="1">
                            <thead>
                                <tr>
                                    <%--<td></td>--%>
                                    <td>Название</td>
                                    <td>Описание</td>
                                    <td>Тип</td>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (entities.Application e : list) {%>
                                <tr>
                                    <%--<td><%= e.getId()%></td>--%>
                                    <td><a href="ShowApplication.jsp?id= <%= e.getId()%>"><%= e.getName()%></a></td>
                                    <td><%= e.getDescription()%></td>
                                    <td><%= e.getType()%></td>

                                </tr>
                                <%}%>
                            </tbody>
                        </table>

                        <%} catch (java.sql.SQLException ex) {%>
                        Error loading applications :(
                        <%}%>

                    </td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
