<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
    <form method="post" action="sign-up">
        <label for="login">Login</label>
        <input type="text" id="login" name="login" placeholder="Enter your login">
        <label for="name">Name</label>
        <input type="text" id="name" name="name" placeholder="Enter your name">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Enter your password">
        <input type="submit" value="Sign up">
    </form>
</body>
</html>
