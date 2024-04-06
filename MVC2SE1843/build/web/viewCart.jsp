<%-- 
Document   : viewCart
Created on : Feb 26, 2024, 9:04:51 AM
Author     : Son
--%>

<%@page import="java.util.Map"%>
<%@page import="sonnx.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Your Cart</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <form action="DispatchServlet">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${items}" var="item" varStatus="counter">
                                <tr>
                                    <td>${counter.count}.</td>
                                    <td>${item.key.name}</td>
                                    <td>${item.value}</td>
                                    <td>
                                        <input type="checkbox" name="chkItem" value="${item.key.sku}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="3">
                                    <c:url var="urlRewriting" value="DispatchServlet">
                                        <c:param name="btAction" value="showProduct"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Add more items to your cart</a>
                                </td>
                                <td>
                                    <input type="submit" value="Remove Selected Items" name="btAction" />
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                    <input type="submit" value="Check out" name="btAction" />
                </form>
            </c:if>
        </c:if>
        <c:if test="${empty cart}">
            <c:url var="url" value="DispatchServlet">
                <c:param name="btAction" value="showProduct"/>
            </c:url>
            <h2>
                No cart is existed!!!
            </h2>
            <a href="${url}">Click here to return to shopping page</a>
        </c:if>
    </body>
</html>

<%--
<% //session có tồn tại rồi
    //1. Cus goes to the cart place
    if (session != null) {
        //2. Cus takes his.her cart ktra giỏ có
        CartObject cart = (CartObject) session.getAttribute("CART");
        if (cart != null) {
            //3. Cus gets items
            Map<String, Integer> items = cart.getItems();
            if (items != null) {
                //4. Show all item
%>
<form action="DispatchServlet">

            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (String key : items.keySet()) {
                    %>
                    <tr>
                        <td>
                            <%= ++count%>
                        </td>
                        <td>
                            <%= key%>
                        </td>
                        <td>
                            <%= items.get(key)%>
                        </td>
                        <td>
                            <input type="checkbox" name="chkItem" 
                                   value="<%= key%>" />
                        </td>
                    </tr>
                    <%
                        }//traverse items
                    %>
                    <tr>
                        <td colspan="3">
                            <a href="product.html">Add more product</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove Selected Item" name="btAction" />
                        </td>

                    </tr>
                </tbody>
            </table>
        </form>
        <%        return;
                    }//end items has existed
                }//end cart has existed

            }//session has existed
        %>

        <h2>
            <font color="red">
            No cart is Not EXISTED!!!!
            </font>
        </h2>
--%>
