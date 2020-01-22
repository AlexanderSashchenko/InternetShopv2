<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<h1>Hello mates! Make your choice!</h1>
<a href="${pageContext.request.contextPath}/login">Sign in</a><br />
<a href="${pageContext.request.contextPath}/registration">Sign up</a><br />
<br>
<a href="${pageContext.request.contextPath}/servlet/allUsers">View All Users</a><br />
<a href="${pageContext.request.contextPath}/servlet/addItem">Add New Items</a><br />
<a href="${pageContext.request.contextPath}/servlet/allItems">View All Items</a><br />
<a href="${pageContext.request.contextPath}/servlet/viewBucket">View All Items In Bucket</a><br />
<a href="${pageContext.request.contextPath}/servlet/getAllUserOrders">View All Orders By User</a><br />
<br>
<a href="${pageContext.request.contextPath}/logout">Logout</a><br />

</body>
</html>
