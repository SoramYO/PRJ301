<%-- 
    Document   : order
    Created on : Mar 8, 2024, 1:49:22 AM
    Author     : Soram
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
    <body>
        <c:set var="order" value="${sessionScope.ORDER}"/>
        <c:set var="orderDetail" value="${sessionScope.ORDER_DETAIL}"/>
        <c:choose>
            <c:when test="${not empty order and not empty orderDetail}">
                <h1>This is your order</h1>
                Order ID: ${order.id}
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="total" value="0"/>
                        <c:forEach items="${orderDetail}" var="item" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${item.key.name}</td>
                                <td>${item.value}</td>
                                <td>${item.key.unitprice}</td>
                                <td>${item.value * item.key.unitprice}</td>
                            </tr>
                            <c:set var="total" value="${total + (item.value * item.key.unitprice)}"/>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="4">Total</td>
                            <td>${total}</td>
                        </tr>
                    </tfoot>
                </table>
                <a href="DispatchServlet?btAction=showProduct">Go to shopping</a>
            </c:when>
            <c:otherwise>
                Order has not existed!!!
            </c:otherwise>
        </c:choose>

    </body>
</html>
