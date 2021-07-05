<%--
  Created by IntelliJ IDEA.
  User: Wu
  Date: 2021/3/11
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        int num1 = 1;
        int num2 = 2;
    %>
<%-- 在JSP文件中，通过输出标记，通知JSP将java变量的值写入到响应体 --%>
    变量num1的值:<%=num1%><br>
    变量num1 + num2的值:<%=num1 + num2%><br>
</body>
</html>
