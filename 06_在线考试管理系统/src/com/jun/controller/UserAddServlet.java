package com.jun.controller;

import com.jun.dao.UserDao;
import com.jun.entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author Wu
 */
public class UserAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName, password, gender, email;
        UserDao dao = new UserDao();
        Users user = null;
        int result = 0;
        PrintWriter out = null;
        // 1.调用请求对象读取请求头参数信息，得到用户的注册信息
        userName = request.getParameter("userName");
        password = request.getParameter("password");
        gender = request.getParameter("gender");
        email = request.getParameter("email");
        // 2.调用UserDao将用户信息填充到insert命令并借助JDBC规范发送到数据库服务器
        user = new Users(null, userName, password, gender, email);
        Date startDate = new Date();
        result = dao.add(user, request);
        Date endDate = new Date();
        System.out.println("添加新用户消耗时间" + (startDate.getTime() - endDate.getTime()) + "ms");
        // 3.调用响应对象将处理结果以二进制形式写入响应体
        response.setContentType("text/html");
        out = response.getWriter();
        if (result == 1) {
            out.print("<font style='color:red;font-size:40'>用户信息注册成功</font>");
        } else {
            out.print("<font style='color:red;font-size:40'>用户信息注册失败</font>");
        }
        // tomcat负责销毁 请求对象 和 响应对象
        // tomcat负责将http响应协议包推送到发起请求的浏览器上
        // 浏览器根据响应头content-type指定编译器对响应体二进制内容编辑
        //浏览器将编辑后结果在窗口中展示给用户 （结束
    }
}
