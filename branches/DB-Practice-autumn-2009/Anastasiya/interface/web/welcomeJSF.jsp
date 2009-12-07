<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Database Test Project</title>
        </head>
        <body>
            <table >
                <tbody>
                    <tr>
                        <td><table border="0">
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
                            <div style="border: 2px solid #000000;" align="center">
                            <% if (request.getParameter("message") != null) { %>
                            Не удалось подключиться к базе! Проверьте корректность введенных настроек!
                            <% } else { %>
                            Введите, пожалуйста, параметры подключения к базе.
                            <% } %>
                            <form action="TestConnection.jsp">
                                <table border="0">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Хост</td>
                                            <td><input type="text" name="textHost" value="<%= db.ConnectionProvider.HOST %>" /></td>
                                        </tr>
                                        <tr>
                                            <td>Порт</td>
                                            <td><input type="text" name="textPort" value="<%= db.ConnectionProvider.PORT %>" /></td>
                                        </tr>
                                        <tr>
                                            <td>База</td>
                                            <td><input type="text" name="textDatabase" value="<%= db.ConnectionProvider.DATABASE %>" /></td>
                                        </tr>
                                        <tr>
                                            <td>Логин</td>
                                            <td><input type="text" name="textLogin" value="<%= db.ConnectionProvider.LOGIN %>" /></td>
                                        </tr>
                                        <tr>
                                            <td>Пароль</td>
                                            <td>
                                                <input type="password" name="textPassword" value="" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="submit" value="OK" name="OK" />
                                            </td>
                                            <td>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>

                            </form>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </body>
    </html>
</f:view>
