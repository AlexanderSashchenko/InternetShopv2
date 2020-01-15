<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="container">
        <h1>Login!</h1>
        <div>${authFailMessage}</div>
        <div>${tokenFailMessage}</div>
        <p>Please enter your login and password to enter the system.</p>
        <hr>

        <label for="login"><b>Login</b></label>
        <input type="text" id="login" name="login" placeholder="Enter login" required>

        <label for="password"><b>Password</b></label>
        <input type="password" id="password" name="password" placeholder="Enter password" required><br>

        <hr>
        <button type="submit">Login</button>
    </div>
    <div class="container">
        <p>Don't have an account? Sing up <a href="${pageContext.request.contextPath}/registration">here</a></p>
    </div>
</form>
</body>
</html>
