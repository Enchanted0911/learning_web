<%--
  Created by IntelliJ IDEA.
  User: Wu
  Date: 2021/3/11
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int age = 15;
%>
<%
    if (age >= 18) {
%>
        <font style="color: red; font-size: 45px">欢迎光临</font>
<%
    } else {

%>
        <font style="color: red; font-size: 45px">谢绝入内</font>
<%
    }
%>