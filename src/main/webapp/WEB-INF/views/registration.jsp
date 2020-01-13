<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <label for="login">Login</label>
            <input type="text" id="login" name="login" placeholder="Enter login">

        <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter password">

        <label for="passwordConfirm">Confirm Password</label>
            <input type="password" id="passwordConfirm" name="passwordConfirm" placeholder="Confirm password"><br />

        <p><button type="submit">Register</button></p>
    </form>
</body>
</html>
