<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

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
                                        <td>Заказчики</td>
                                    </tr>
                                    <tr>
                                        <td>Другое</td>
                                    </tr>
                                </tbody>
                            </table></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </body>
    </html>
</f:view>
