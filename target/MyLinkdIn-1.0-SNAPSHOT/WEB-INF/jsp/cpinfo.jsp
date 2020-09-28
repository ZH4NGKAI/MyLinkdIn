<%-- 
    Document   : cpinfo
    Created on : Apr 17, 2020, 5:51:14 AM
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
            <form action="company.htm?option=infoupdate" method="post">
                <h3>${sessionScope.USER.getName()} Information</h3>
                <table border="0" cellpadding="5">
                    <tr>
                        <td>ID: </td>
                        <td>${sessionScope.USER.getId()}</td>
                    </tr> 
                    <tr>
                        <td>Verification: </td>
                        <td>${sessionScope.USER.getVerification()}</td>
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
                        <td>Address: </td>
                        <td><input name = "address" type = "text" value="${sessionScope.USER.getAddress()}"></td>
                    </tr>
                    <tr>
                        <td>Description: </td>
                        <td><input name = "description" type = "text" value="${sessionScope.USER.getDescription()}"></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input name = "password" type="password" value="${sessionScope.USER.getPassword()}"></td>
                    </tr>    
                    <tr>
                        <td colspan="2"><input type="submit" value="Update"></td>
                    </tr>                    
                </table>
            </form>
        </div>
    </body>
</html>
