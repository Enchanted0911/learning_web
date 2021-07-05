<%@ page import="java.util.List" %>
<%@ page import="com.jun.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: Wu
  Date: 2021/3/11
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 从请求作用域对象得到OneServlet添加进去的集合
    List<Student> stuList = (List)request.getAttribute("key1");
%>

<%-- 将处理结果写入到响应体 --%>
<table border="2" align="center">
    <tr>
        <td>学员编号</td>
        <td>学员姓名</td>
    </tr>
    <%
        for (Student stu : stuList) {
    %>
    <tr>
        <td><%=stu.getSid()%></td>
        <td><%=stu.getSname()%></td>
    </tr>
    <%
        }
    %>
</table>