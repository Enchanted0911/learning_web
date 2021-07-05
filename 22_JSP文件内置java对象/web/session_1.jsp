<%--
  Created by IntelliJ IDEA.
  User: Wu
  Date: 2021/3/11
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
    JSP文件内置对象 : session
    类型 : HttpSession
    作用 : 在JSP文件运行时,可以通过session添加共享数据或读取共享数据
    浏览器 : http://localhost:8080/myWeb/request.jsp?userName=allen&password=123
--%>

<%
    //HttpSession session = request.getSession();
    session.setAttribute("key1", 100);
%>