<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean id="items" scope="request" type="java.util.List<mate.academy.internetshop.model.Item>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
<h1>View All Items In Bucket</h1>
    <table bgcolor="#5f9ea0" border="1">
        <tr>
            <th>ID</th>
            <th>Item Title</th>
            <th>Item Price</th>
            <th>Delete From Bucket</th>
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
                    <a href="${pageContext.request.contextPath}/deleteItemFromBucket?item_id=${item.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table><br />
    <form action="${pageContext.request.contextPath}/completeOrder?items=${items}" method="get">
        <div class="button">
            <button type="submit">Complete Order</button>
        </div>
    </form>

    <a href="${pageContext.request.contextPath}/allItems">Add more items to the bucket</a><br />
    <a href="${pageContext.request.contextPath}/index">Back to the index page</a><br />
</body>
</html>
