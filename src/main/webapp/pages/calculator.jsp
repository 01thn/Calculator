<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
    <form method="post" action="calc">
        <input type="text" name="var1" placeholder="Variable 1">
        <input type="text" name="var2" placeholder="Variable 2">
        <input type="radio" id="operation" name="operation" value="sum" checked>
        <label for="operation">Sum</label>
        <input type="submit" value="Calculate">
    </form>
    <p>${requestScope.result}</p>
</body>
</html>
