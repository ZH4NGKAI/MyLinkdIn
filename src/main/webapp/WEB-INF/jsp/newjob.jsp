<%-- 
    Document   : newjob
    Created on : Apr 17, 2020, 7:00:23 AM
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
            <form action="company.htm?option=addjob" method="post">
                <h3>Add New Job</h3>
                <table border="0" cellpadding="5">
                    <tr>
                        <td>Title: </td>
                        <td><input name = "title" type = "text" ></td>
                    </tr>
                    <tr>
                        <td>City: </td>
                        <td><input name = "city" type = "text"></td>
                    </tr>
                    <tr>
                        <td>Description: </td>
                        <td><input name = "description" type = "text"></td>
                    </tr>   
                    <tr>
                        <td colspan="2"><input type="submit" value="Add"></td>
                    </tr>                    
                </table>
            </form>
        </div>
    </body>
</html>
