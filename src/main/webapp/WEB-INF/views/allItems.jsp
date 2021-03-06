<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean id="items" scope="request" type="java.util.List<mate.academy.internetshop.model.Item>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Items</title>
</head>
<body>
<h1>All Items List</h1><br />
    <table bgcolor="#5f9ea0" border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Price</th>
            <th>Add To Bucket</th>
        </tr>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>
                    <c:out value="${item.id}" />
                </td>
                <td>
                    <c:out value="${item.title}" />
                </td>
                <td>
                    <c:out value="${item.price}" />
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/servlet/addItemToBucket?item_id=${item.id}">Add</a>
                </td>
            </tr>
        </c:forEach>
    </table><br />
    <a href="${pageContext.request.contextPath}/servlet/viewBucket">View items in the bucket</a><br />
    <a href="${pageContext.request.contextPath}/index">Back to the index page</a><br />
</body>
</html>
