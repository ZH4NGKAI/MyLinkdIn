<%-- 
    Document   : companyhome
    Created on : Apr 16, 2020, 1:52:41 AM
    Author     : mac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="login.htm" method="post">
            <input type="hidden" name="option" value="logout">
            <input type="submit" value="Logout">
        </form>
        <div align="center">
            <h2>${sessionScope.USER.getName()} Site</h2>
            <a href="company.htm?option=cpinfo">Company Information</a>
            <br><br>
            <a href="company.htm?option=cpjob">View Jobs</a>
<!--            <form action="company.htm" method="post">
                <select name="option">
                    <option value="cpinfo">Company Information</option>
                    <option value="cpjob">View Jobs</option>
                </select>
                <input type="submit" value="Go">
            </form>-->
        </div>
    </body>
</html>
