<%-- 
    Document   : createAccount
    Created on : Mar 7, 2024, 8:19:08 AM
    Author     : Son
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" />(6 - 20 chars)<br>
            <c:if test="${not empty errors.usernameLenghtError}">
                <font color="red">
                ${errors.usernameLenghtError}
                </font><br>
            </c:if>
            <c:if test="${not empty errors.userNameIsExisted}">
                <font color="red">
                ${errors.userNameIsExisted}
                </font><br>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" />(6 - 30 chars)<br>
            <c:if test="${not empty errors.passwordLenghtError}">
                <font color="red">
                ${errors.passwordLenghtError}
                </font><br>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /><br>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                ${errors.confirmNotMatched}
                </font><br>
            </c:if>
            Full Name* <input type="text" name="txtFullname" 
                              value="${param.txtFullname}" />(2 - 50 chars)<br>
            <c:if test="${not empty errors.fullNameLenghtError}">
                <font color="red">
                ${errors.fullNameLenghtError}
                </font><br>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
        </form>
            <a href="login.html">You have account</a>
    </body>
</html>
