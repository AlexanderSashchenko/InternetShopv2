<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<h1>Hello mates! Make your choice!</h1>
<a href="${pageContext.request.contextPath}/registration">Registration</a><br />
<a href="${pageContext.request.contextPath}/allUsers">View All Users</a><br />
<a href="${pageContext.request.contextPath}/addItem">Add New Items</a><br />
<a href="${pageContext.request.contextPath}/allItems">View All Items</a><br />
<a href="${pageContext.request.contextPath}/viewBucket">View All Items In Bucket</a><br />
<a href="${pageContext.request.contextPath}/getAllUserOrders">View All Orders By User</a><br />

</body>
</html>
