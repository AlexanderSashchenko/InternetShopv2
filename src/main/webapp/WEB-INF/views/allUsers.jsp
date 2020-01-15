<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean id="users" scope="request" type="java.util.List<mate.academy.internetshop.model.User>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
<h1>Hello Mates! All Users List Is Here</h1><br />
    <table bgcolor="#5f9ea0" border="1">
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Email</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Delete User</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                    <c:out value="${user.id}" />
                </td>
                <td>
                    <c:out value="${user.login}" />
                </td>
                <td>
                    <c:out value="${user.email}" />
                </td>
                <td>
                    <c:out value="${user.firstName}" />
                </td>
                <td>
                    <c:out value="${user.lastName}" />
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/servlet/deleteUser?user_id=${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table><br />
    <a href="${pageContext.request.contextPath}/registration">Register another user</a><br>
    <a href="${pageContext.request.contextPath}/index">Back to the index page</a><br>
</body>
</html>
