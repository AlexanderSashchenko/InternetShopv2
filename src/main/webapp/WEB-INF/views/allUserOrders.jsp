<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean id="orders" scope="request" type="java.util.List<mate.academy.internetshop.model.Order>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All User Orders</title>
</head>
<body>
<h1>View All Orders By User!</h1>
    <table bgcolor="#5f9ea0" border="1">
        <tr>
            <th>ID</th>
            <th>Delete Order</th>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>
                    <c:out value="${order.id}" />
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/servlet/deleteOrder?order_id=${order.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table><br />
    <a href="${pageContext.request.contextPath}/index">Back to the index page</a><br />
</body>
</html>
