<%@ page import="com.thn.calculator.storage.SQLOperationStorage" %>
<%@ page import="com.thn.calculator.entity.Operation" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
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
        <%
            PrintWriter pw = response.getWriter();
            SQLOperationStorage sqlOperationStorage = new SQLOperationStorage();
            List<Operation> operations = sqlOperationStorage.getStory((long) request.getSession().getAttribute("id"));
            pw.print("Calculating story");
            pw.print("<table border=\"1\">");
            pw.print("<tr>");
            pw.print("<td>First variable</td>");
            pw.print("<td>Second variable</td>");
            pw.print("<td>Operation</td>");
            pw.print("<td>Result</td>");
            pw.print("</tr>");
            for(Operation operation : operations){
               pw.print("<tr>");
               pw.print("<td>"+operation.getVar1()+"</td>");
               pw.print("<td>"+operation.getVar2()+"</td>");
               pw.print("<td>"+operation.getOperation()+"</td>");
               pw.print("<td>"+operation.getResult()+"</td>");
               pw.print("</tr>");
            }
            pw.print("</table>");
        %>
    </main>
</body>
</html>
