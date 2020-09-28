<%-- 
    Document   : stdjob
    Created on : Apr 16, 2020, 10:11:09 PM
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
            <table border="0" cellpadding="5">
                    <tr>
                        <td>
                            <form method="post" action="student.htm?option=stdjob&search=true">
                                Company:<input type="text" name="company"/>
                                &nbsp;
                                Title:<input type="text" name="title" />
                                &nbsp;
                                City:<input type="text" name="city"/>
                                &nbsp;
                                <input type="submit" value="Search"/>     
                            </form>
                        </td>
                        <td>
                            <form method="post" action="student.htm?option=stdjob">
                                <input type="submit" value="Clear"/>
                            </form>
                        </td>
                    </tr>                          
            </table>
            <br><br>
            <table border="1" cellpadding="5">
                <tr>
                    <th>ID</th>
                    <th>Company</th>
                    <th>Title</th>
                    <th>Date</th>
                    <th>City</th>
                    
                </tr>
                <c:forEach items="${jobs}" var="job">
                
                <tr>
                    <td>${job.getId()}</td>
                    <td>${job.getCompanyname()}</td>
                    <td>
                        <a href="student.htm?option=jobdetail&job=${job.getId()}">
                            ${job.getTitle()}
                        </a>
                    </td>
                    <td>${job.getDate()}</td>
                    <td>${job.getCity()}</td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
