<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authentication</title>
</head>
<body>
    <form method="post" action="sign-in">
        <label for="login">Login</label>
        <input type="text" id="login" name="login" placeholder="Enter your login">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Enter your password">
        <input type="submit" value="Sign in">
    </form>
    <p>${requestScope.Message}</p>
    <a href="change">Forgot password</a>
</body>
</html>
