<%-- 
    Document   : search
    Created on : Feb 1, 2024, 7:12:33 AM
    Author     : Son
--%>
<%-- 
<%@page import="sonnx.registration.RegistrationDTO"%>
<%@page import="java.util.List"%> --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%-- thieu taglib --%> <%-- quen add library --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome , ${sessionScope.USER.fullName}
        </font>
        <form action="DispatchServlet">
            <input type="submit" value="Logout" name="btAction" />
        </form>
        <h1>Search Page</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" /> <br>
            <input type="submit" value="Search" name="btAction" />
        </form><br>
        <c:set var="searchValue" value="${param.txtSearchValue}"/> <%-- thieu / --%>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <c:forEach var="dto" items="${result}" varStatus="couter"> 
                        <form action="DispatchServlet">

                            <td>
                                ${couter.count}
                                .</td>
                            <td>
                                ${dto.username}       <%-- result la get --%>  <%-- goi sai phuong thuc get --%>
                                <input type="hidden" name="txtUsername" 
                                       value="${dto.username}" />
                            </td>
                            <td>
                                <input type="text" name="txtPassword" 
                                       value="${dto.password}" />        <%-- goi sai dto --%>
                            </td>
                            <td>
                                ${dto.fullName}
                            </td>
                            <td>
                                <input type="checkbox" name="chkAdmin" 
                                       value="ON" 
                                       <c:if test="${dto.role}">
                                           checked=checked
                                       </c:if>
                                       />
                            </td>
                            <td>
                                <c:url var="deleteLink" value="DispatchServlet">
                                    <c:param name="btAction" value="delete"/>
                                    <c:param name="pk" value="${dto.username}"/>
                                    <c:param name="lastSearchValue" value="${searchValue}"/>     
                                </c:url>
                                <a href="${deleteLink}">Delete</a>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="btAction" />
                                <input type="hidden" name="lastSearchValue" 
                                       value="${searchValue}" />
                            </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                <font color ="">
                No record is matched!!
                </font>
            </h2>
        </c:if>
    </c:if>
</body>
</html>
<%--    <%
        
        Cookie[] cookies = request.getCookies();
        Cookie lastCookie = cookies[cookies.length - 1];
        String username = lastCookie.getName();
        %>
        <font color ="red">
        Wellcome, <%= username %>
        Wellcome, <%= session.getAttribute("account") %>
        </font> 
    <%
    %>
    <h1>Welcome to DB Servlet</h1>
    <form action="DispatchServlet">
        Search Value <input type="text" name="txtSearchValue" 
                            value="<%= request.getParameter("txtSearchValue")%>" /> <br>
        <input type="submit" value="Search" name="btAction" />
    </form>
    <br/>
    <%
        String searchValue = request.getParameter("txtSearchValue");
        //ket qua search dang dat o phia container o trong attribute request scope
        if (searchValue != null) {
            List<RegistrationDTO> result
                    = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
            if (result != null) {
    %>
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>Username</th>
                <th>Password</th>
                <th>Full name</th>
                <th>Role</th>
                <th>Delete</th>
                <th>Update</th>
            </tr>
        </thead>
        <tbody>
            <%
                int count = 0;
                for (RegistrationDTO dto : result) {
                    String urlRewriting = "DispatchServlet"
                            + "?btAction=delete"
                            + "&pk=" + dto.getUsername()
                            + "&lastSearchValue=" + searchValue;
            %>
        <form action="DispatchServlet" method="POST">
            <tr>
                <td>
                    <%= ++count%>
                </td>
                <td>
                    <%= dto.getUsername()%>
                    <input type="hidden" name="txtUsername" 
                           value="<%= dto.getUsername()%>" />
                </td>
                <td>
                    <input type="text" name="txtPassword" 
                           value="<%= dto.getPassword()%>" />
                </td>
                <td>
                    <%= dto.getFullName()%>
                </td>
                <%//  %>
                <td>
                    <input type="checkbox" name="chkAdmin" value="ON"
                           <%
                               if (dto.isRole()) {
                           %>
                           checked="checked"
                           <%
                               } //end role is admin

                               %>
                               /> 
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btAction" />
                        <input type="hidden" name="lastSearchValue" 
                               value="<%= searchValue%>" />
                    </td>

                </tr>
            </form>

            <%
                }//travere result to get each account
            %>
        </tbody>
    </table>

    <%
    } else {
    %>
    <h2>
        <font color ="">
        No record is matched!!
        </font>

    </h2>
    <%
            }//end search is not found
        }//search value is null if user access directly
%>
<form action="DispatchServlet">
    <input type="submit" value="Logout" name="btAction" />
</form>
--%>


