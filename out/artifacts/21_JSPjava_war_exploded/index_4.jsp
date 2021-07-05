<%@ page import="com.jun.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: Wu
  Date: 2021/3/11
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Student stu = new Student(10, "mike");
%>
学员编号<%=stu.getSid()%><br>
学员姓名<%=stu.getSname()%>

</body>
</html>
