package com.jun.controller;

import com.jun.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class UserDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId;
        UserDao dao = new UserDao();
        int result = 0;
        PrintWriter out = null;
        //1.调用对象读取请求头参数(用户编号)
        userId = request.getParameter("userId");
        //2.调用dao将用户编号填充到delete命令并发送到数据库服务器
        result = dao.delete(userId);
        //3.调用响应对象将处理结果y以二进制写入到响应体，交给浏览器
        response.setContentType("text/html");
        out = response.getWriter();
        if (result == 1) {
            out.print("<font style='color:red;font-size:40'>用户信息删除成功</font>");
        } else {
            out.print("<font style='color:red;font-size:40'>用户信息删除失败</font>");
        }
    }
}
