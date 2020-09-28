<%-- 
    Document   : adcpinfo
    Created on : Apr 18, 2020, 7:09:39 PM
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
        
        <c:choose>
                <c:when test="${company.getVerification()}">
                    <c:set var="action" value="Unverify"/> 
                </c:when>
                <c:otherwise>
                    <c:set var="action" value="Verify"/> 
                </c:otherwise>
        </c:choose>
        <div align="center">
            <form action="admin.htm?option=verify1&&cp=${company.getId()}" method="post">
                <h3>${company.getName()} Information</h3>
                <table border="0" cellpadding="5">
                    <tr>
                        <td>ID: </td>
                        <td>${company.getId()}</td>
                    </tr> 
                    <tr>
                        <td>Verification: </td>
                        <td>${company.getVerification()}</td>
                    </tr>
                    <tr>
                        <td>Name: </td>
                        <td>${company.getName()}</td>
                   
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td>${company.getEmail()}</td>
                    </tr>
                    <tr>
                        <td>Address: </td>
                        <td>${company.getAddress()}</td>
                    </tr>
                    <tr>
                        <td>Description: </td>
                        <td>${company.getDescription()}</td>
                    </tr> 
                    <tr>
                        <td colspan="2"><input type="submit" value="${action}"></td>
                    </tr>                    
                </table>
            </form>
        </div>
    </body>
</html>
