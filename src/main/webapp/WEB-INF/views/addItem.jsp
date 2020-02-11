<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean id="items" scope="request" type="java.util.List<mate.academy.internetshop.model.Item>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Item</title>
</head>
<body>
    <h1>Add New Items</h1>
    <form action="${pageContext.request.contextPath}/servlet/addItem" method="post">
        <label for="addItemTitle">Add Item Title</label>
            <input type="text" id="addItemTitle" name="title">
        <label for="addItemPrice">Add Item Price</label>
            <input type="text" id="addItemPrice" name="price">
        <br />
        <p><button type="submit">Add Item</button></p>
    </form>
    <a href="${pageContext.request.contextPath}/index">Back to the index page</a><br />
    <hr>
    <h3>Items list</h3>
    <table bgcolor="#5f9ea0" border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Price</th>
            <th>Delete From Shop</th>
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
                    <a href="${pageContext.request.contextPath}/servlet/deleteItem?item_id=${item.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table><br />

<a href="${pageContext.request.contextPath}/index">Back to the index page</a><br />
</body>
</html>
