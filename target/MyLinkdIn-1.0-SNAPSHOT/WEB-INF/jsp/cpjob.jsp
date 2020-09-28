<%-- 
    Document   : cpjob
    Created on : Apr 17, 2020, 5:51:32 AM
    Author     : mac
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Jobs</title>
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
            <h2>Manage Jobs</h2>
            
            <br>
            <a href="company.htm?option=newjob">Add Job</a>
            <br><br>
            <table border="1" cellpadding="5">
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Date</th>
                    <th>City</th>
                </tr>
                <c:forEach items="${jobs}" var="job">
                
                <tr>
                    <td>${job.getId()}</td>
                    <td>
                        <a href="company.htm?option=jobdetail&job=${job.getId()}">
                            ${job.getTitle()}
                        </a>
                    </td>
                    <td>${job.getDate()}</td>
                    <td>${job.getCity()}</td>
                    <td>
                        <a href="company.htm?option=deletejob&job=${job.getId()}">Delete</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div> 
    </body>
</html>
