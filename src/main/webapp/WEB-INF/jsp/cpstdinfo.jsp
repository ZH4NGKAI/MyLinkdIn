<%-- 
    Document   : cpstdinfo
    Created on : Apr 18, 2020, 4:15:26 AM
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
            <h3>My Information</h3>     
            <table border="0" cellpadding="5">  
                <tr>
                     <td>Name: </td>
                     <td>${student.getName()}</td>
                </tr>
                <tr>
                    <td>Email: </td>
                    <td>${student.getEmail()}</td>
                </tr>
                <tr>
                    <td>Resume: </td>
                    <td>
                        <a href="company.htm?option=resume&student=${student.getId()}">
                            ${student.getOriginresume()}
                        </a>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
