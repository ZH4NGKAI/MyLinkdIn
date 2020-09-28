<%-- 
    Document   : pwdhelp
    Created on : Apr 15, 2020, 7:50:53 PM
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
        <div align="center">
            <h2>Reset Password</h2>
            <form action="login.htm?option=pwdreset" method="post">
                <table border="0" cellpadding="5">
                    <tr>
                        <td>Role: </td>
                        <td>
                            <select name="user">
                                <option value="student">Student</option>
                                <option value="company">Company</option>
                                
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td><input name = "email" type = "text"></td>
                        <td><input type="submit" name="button" value="Send Verify Code"></td>
                    </tr>
                    <tr>
                        <td>Verify Code: </td>
                        <td><input name="code" type="text"></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input name = "password" type="password"></td>
                    </tr>    
                    <tr>
                        <td colspan="2"><input type="submit" name="button" value="Reset"></td>
                    </tr>                    
                </table>
            </form>
        </div>
        
    </body>
</html>
