<%-- 
    Document   : stdapp
    Created on : Apr 16, 2020, 10:10:59 PM
    Author     : mac
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <div align="left" style="float:left">
                <form action="login.htm" method="post">
                    <input type="hidden" name="option" value="logout">
                    <input type="submit" value="Logout">
                </form>
            </div>
            <div align="right">
                <form action="home.htm" method="post">
                    <input type="hidden" name="option" value="home">
                    <input type="submit" value="Home">
                </form>
            </div>
        </div>
        <div align="center">
            <table border="1" cellpadding="5">
                <tr>
                    <th>ID</th>
                    <th>Date</th>
                    <th>Company</th>
                    <th>Job Title</th>
                    <th>Status</th>
                    
                </tr>
                <c:forEach items="${applications}" var="application">
                
                <tr>
                    <td>${application.getId()}</td>
                    <td>${application.getDate()}</td>
                    <td>${application.getJob().getCompany().getName()}</td>
                    <td>
                        <a href="student.htm?option=jobdetail&job=${application.getJob().getId()}">
                            ${application.getJob().getTitle()}
                        </a>
                    </td>
                    <td>${application.getStatus()}</td>
                    
                    <td>
                        <a href="student.htm?option=cancel&app=${application.getId()}">Cancel</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
