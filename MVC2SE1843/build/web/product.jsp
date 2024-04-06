<%-- 
    Document   : product
    Created on : Mar 7, 2024, 11:41:02 AM
    Author     : Soram
--%>

<%--<%@page import="sonnx.product.ProductDTO"%>
<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Product list</h1>
        <form action="DispatchServlet">
            Search <input type="text" name="txtProductname" value="${param.txtProductname}" />
            <input type="submit" value="Search Product" name="btAction" />
        </form>
        <form action="DispatchServlet">
            <input type="hidden" name="txtProductname" value="${param.txtProductname}" />
            <table border="1">
                <thead>
                    <tr>
                        <th></th>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="result" value="${requestScope.SEARCH_PRODUCT_RESULT}"/>
                    <c:forEach var="dto" items="${result}"  varStatus="counter">
                        <tr>
                            <td>
                                <input type="radio" name="checkProduct" 
                                       value="${dto.sku}" />
                            </td>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.name}
                            </td>
                            <td>
                                ${dto.description}
                            </td>
                            <td>
                                ${dto.unitprice}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            Quantity <input type="text" name="txtProductQuantity" value="1"/> <br>
            <input type="submit" value="Add product to Your cart" name="btAction"/>
            <input type="submit" value="View your cart" name="btAction"/>
        </form>
    </body>
</html>


<%--   <h1>Store</h1>
<%
    List<ProductDTO> result = (List<ProductDTO>) request.getAttribute("SEARCH_RESULT");
    if (result != null && !result.isEmpty()) {
%>
<table border="1">
    <thead>
        <tr>
            <th>No.</th>
            <th>Name</th>
            <th>Description</th>
            <th>Unit price</th>
            <th>Quantity</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <%
            int count = 0;
            for (ProductDTO dto : result) {

                %>
                <tr>
                    <td><%= ++count%></td>
                    <td><%= dto.getName()%></td>
                    <td><%= dto.getDescription()%></td>
                    <td><%= dto.getUnitprice()%></td>
                    <td><%= dto.getQuantity()%></td>
                    <td><%= dto.isStatus()%></td>
                    <td>
                        <form action="DispatchServlet" method="post">  
                            <button type="submit" name="btAction" value="Add">Add</button>
                            <input type="hidden" name="productId" value="<%= dto.getSku()%>">
                        </form>

                    </td>
                </tr>
                <%
                    }
                %>
            <form action="DispatchServlet">
                <input type="submit" value="View cart" name ="btAction">
            </form>
        </tbody>
    </table>
    <% } else { %>
    <h2><font color="red">No product exists!</font></h2>
        <% }%>
--%>
