<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
    <header>
        <a href="logout">logout</a>
    </header>
    <main>
        <h2>${requestScope.Hello}</h2>
    <form method="post" action="calc">
        <input type="text" name="var1" placeholder="Variable 1">
        <input type="text" name="var2" placeholder="Variable 2">
        <input type="radio" id="sum" name="operation" value="sum" checked>
        <label for="sum">Sum</label>
        <input type="radio" id="minus" name="operation" value="minus">
        <label for="minus">Minus</label>
        <input type="radio" id="multiply" name="operation" value="multiply">
        <label for="minus">Multiply</label>
        <input type="radio" id="divide" name="operation" value="divide">
        <label for="minus">Divide</label>
        <input type="submit" value="Calculate">
    </form>
    <p>${requestScope.result}</p>
    </main>
</body>
</html>
