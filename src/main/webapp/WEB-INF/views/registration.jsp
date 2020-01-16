<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <div class="container">
            <h1>Register now!</h1>
            <p>Enter your registration data and proceed to confirm</p>
            <hr>
            <label for="login"><b>Login</b></label>
                <input type="text" id="login" name="login" placeholder="Enter login" required>

            <label for="firstName"><b>First Name</b></label>
                <input type="text" id="firstName" name="firstName" placeholder="Enter your first name" required>

            <label for="lastName"><b>Last Name</b></label>
                <input type="text" id="lastName" name="lastName" placeholder="Enter your last name" required>

            <label for="email"><b>E-mail</b></label>
                <input type="text" id="email" name="email" placeholder="Enter your e-mail" required>

            <label for="password"><b>Password</b></label>
                <input type="password" id="password" name="password" placeholder="Enter password" required><br>

            <label for="passwordConfirm"><b>Confirm Password</b></label>
                <input type="password" id="passwordConfirm" name="passwordConfirm" placeholder="Confirm password"
                       required>
            <br>
            <hr>
            <button type="submit">Register</button>
        </div>
        <div class="container">
            <p>Already have an account? Sing in <a href="${pageContext.request.contextPath}/login">here</a></p>
        </div>
    </form>
</body>
</html>
