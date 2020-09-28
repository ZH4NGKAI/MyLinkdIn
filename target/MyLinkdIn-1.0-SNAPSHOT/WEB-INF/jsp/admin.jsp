<%-- 
    Document   : admin
    Created on : Apr 16, 2020, 1:52:59 AM
    Author     : mac
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

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
            <h2>Manage Company</h2>
            <table border="0" cellpadding="5">
                    <tr>
                        <td>
                            <form method="post" action="home.htm?option=unverified">
                                <input type="submit" value="Only Unverified"/>     
                            </form>
                        </td>
                        <td>
                            <form method="post" action="home.htm">
                                <input type="submit" value="All Companies"/>
                            </form>
                        </td>
                    </tr>                          
            </table>
            <table border="1" cellpadding="5">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Verification</th>
                    
                </tr>
                <c:forEach items="${companies}" var="company">
                
                <tr>
                    <td>${company.getId()}</td>
                    <td>
                        <a href="admin.htm?option=cpinfo&cp=${company.getId()}">
                            ${company.getName()}
                        </a>
                    </td>
                    <td>${company.getEmail()}</td>
                    <td>${company.getAddress()}</td>
                    <td>
                        ${company.getVerification()}
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
