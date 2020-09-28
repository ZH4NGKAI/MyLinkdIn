<%-- 
    Document   : login
    Created on : Apr 15, 2020, 4:30:01 AM
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
            <h2>My LinkdIn</h2>
            <form action="login.htm" method="post">
                <h3>Sign In</h3>
                <table border="0" cellpadding="5">
                    <tr>
                        <td>Role: </td>
                        <td>
                            <select name="option">
                                <option value="student">Student</option>
                                <option value="company">Company</option>
                                <option value="admin">Admin</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td><input name = "email" type = "text"></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input name = "password" type="password"></td>
                    </tr>    
                    <tr>
                        <td><input type="submit" value="Sign In"></td>
                        </form>
                        <td>
                            <form action ="pwdhelp.htm" method="post">
                                <input type="hidden" name="option" value="pwd">
                                <input type="submit" value="Forgot Password"/>
                            </form>
                        </td>
                    </tr>                    
                </table>
            
            <form action="register.htm" method="post">
                <h3>Sign Up</h3>
                <table border="0" cellpadding="5">
                    <tr>
                        <td>Role: </td>
                        <td>
                            <select name="option">
                                <option value="student">Student</option>
                                <option value="company">Company</option>
                                <!--<option value="admin">Admin</option>-->
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Name: </td>
                        <td><input name = "name" type = "text"></td>
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
                        <td colspan="2"><input type="submit" name="button" value="Sign Up"></td>
                    </tr>                    
                </table>
            </form>
        </div>
<!--        <h1>MyLinkdIn</h1><br>
        <form action="login.htm" method="post">
            <h3>Sign In</h3>
            Role:
            <select name="option">
                <option value="student">Student</option>
                <option value="company">Company</option>
                <option value="admin">Admin</option>
            </select>
            <br><br>
            Email: <input name = "email" type = "text">
            <br><br>
            Password: <input name = "password" type="password">
            <br><br>
            <input type="submit" value="Sign In"/>
        </form>-->
        <!--<br>-->
<!--        <form action ="pwdhelp.htm" method="post">
            Forgot Password?
            <input type="hidden" name="option" value="pwd">
            <input type="submit" value="Help"/>
        </form>
        <br>-->
<!--        <form action = "register.htm" method="post">
            <h3>Sign up</h3>
            Role:
            <select name = "option">
                <option value="student">Student</option>
                <option value="company">Company</option>
            </select>
            <br><br>
            Email: <input type="text" name="email">
            <br><br>
            Name: <input type="text" name="name">
            <br><br>
            Password: <input type="password" name="password">
            <br><br>
            <input type="submit" value="Sign Up"/>
        </form>-->
        
    </body>
</html>
