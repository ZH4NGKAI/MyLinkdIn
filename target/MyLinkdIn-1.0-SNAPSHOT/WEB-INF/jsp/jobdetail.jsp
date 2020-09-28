<%-- 
    Document   : jobdetail
    Created on : Apr 17, 2020, 7:03:19 AM
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
            <form action="company.htm?option=updatejob" method="post">
                <h3>${job.getTitle()} Details</h3>
                
                <table border="0" cellpadding="5">
                    <tr>
                        <td>ID: </td>
                        <td>${job.getId()}</td>
                    </tr> 
                    <tr>
                        <td>Date: </td>
                        <td>${job.getDate()}</td>
                    </tr>
                    <tr>
                        <td>Title: </td>
                        <td><input name = "title" type = "text" value="${job.getTitle()}"></td>
                   
                    </tr>
                    
                    <tr>
                        <td>City: </td>
                        <td><input name = "city" type = "text" value="${job.getCity()}"></td>
                    </tr>
                    <tr>
                        <td>Description: </td>
                        <td><input name = "description" type = "text" value="${job.getDescription()}"></td>
                    </tr>   
                    <tr>
                        <td colspan="2"><input type="submit" value="Update"></td>
                    </tr>                    
                </table>
            </form>
            <br><br>
            <table border="1" cellpadding="5">
                <tr>
                    <th>ID</th>
                    <th>Date</th>
                    <th>Student</th>
                    <th>Status</th>
                </tr>
                <c:forEach items="${job.getApplications()}" var="application">
                
                <tr>
                    <td>${application.getId()}</td>
                    <td>${application.getDate()}</td>
                    <td>
                        <a href="company.htm?option=stdinfo&student=${application.getStudent().getId()}">
                            ${application.getStudent().getName()}
                        </a>
                    </td>
                    <td>
                        <form action="company.htm?option=status&application=${application.getId()}" method="post">
                            <input type="text" name="status" value="${application.getStatus()}">
                            <input type="submit" value="Edit Status">
                        </form>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
