<%-- 
    Document   : stdinfo
    Created on : Apr 16, 2020, 11:14:55 PM
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
            <form action="student.htm?option=infoupdate" method="post" enctype="multipart/form-data">
                <h3>My Information</h3>
                
                <table border="0" cellpadding="5">
                    <tr>
                        <td>ID: </td>
                        <td>${sessionScope.USER.getId()}</td>
                    </tr> 
                    <tr>
                        <td>Name: </td>
                        <td><input autocomplete="off" name = "name" type = "text" value="${sessionScope.USER.getName()}"></td>
                   
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td><input name = "email" type = "text" value="${sessionScope.USER.getEmail()}"></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input name = "password" type="password" value="${sessionScope.USER.getPassword()}"></td>
                    </tr>
                    <tr>
                        <td>Resume: </td>
                        <td>
                            <a href="student.htm?option=resume">
                                ${sessionScope.USER.getOriginresume()}
                            </a>
                        </td>
                        <td>
                            <input type="file" name="resume" accept="application/pdf">
                        </td>
                    <tr>
                        <td colspan="2"><input type="submit" value="Update"></td>
                    </tr>                    
                </table>
            </form>
        </div>
    </body>
</html>
