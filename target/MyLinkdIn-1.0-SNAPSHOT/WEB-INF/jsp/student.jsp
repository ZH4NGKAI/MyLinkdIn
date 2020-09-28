<%-- 
    Document   : studenthome
    Created on : Apr 16, 2020, 1:52:33 AM
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
            <h2>Student Career</h2>
            <a href="student.htm?option=stdinfo">My Information</a>
            <br><br>
            <a href="student.htm?option=stdapp">My Applications</a>
            <br><br>
            <a href="student.htm?option=stdjob">View Jobs</a>
        </div>
    </body>
</html>
