<%-- 
    Document   : stdjobdetail
    Created on : Apr 18, 2020, 12:10:45 AM
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
            <form action="student.htm?option=apply&job=${job.getId()}" method="post">
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
                        <td>Company: </td>
                        <td>${job.getCompany().getName()}</td>
                    </tr>
                    <tr>
                        <td>Title: </td>
                        <td>${job.getTitle()}</td>
                    </tr>
                    
                    <tr>
                        <td>City: </td>
                        <td>${job.getCity()}</td>
                    </tr>
                    <tr>
                        <td>Description: </td>
                        <td>${job.getDescription()}</td>
                    </tr>   
                    <tr>
                        <td colspan="2"><input type="submit" value="Apply"></td>
                    </tr>                    
                </table>
            </form>
        </div>
    </body>
</html>
