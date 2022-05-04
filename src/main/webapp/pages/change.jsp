<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change password</title>
</head>
<body>
    <form method="post" action="change">
        <label for="login">Login</label>
        <input type="text" id="login" name="login" placeholder="Enter your login">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Enter new password">
        <input type="submit" value="Save">
    </form>
    <p>${requestScope.Message}</p>
</body>
</html>
