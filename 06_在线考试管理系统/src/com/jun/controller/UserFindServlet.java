package com.jun.controller;

import com.jun.dao.UserDao;
import com.jun.entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * @author Wu
 */
public class UserFindServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        PrintWriter out = null;

//        // 索要当前用户在服务端HttpSession
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            response.sendRedirect("/myWeb/login_error.html");
//            return;
//        }
//
//        // 提供服务
        //1.调用Dao将查询命令推送到数据库服务器上，得到所有用户信息 List
        List<Users> userList = dao.findAll();
        //2.调用响应对象将用户信息结合<table>标签命令以二进制形式写入响应体
        response.setContentType("text/html");
        out = response.getWriter();
        out.print("<table border='2' align='center'>");
        out.print("<tr>");
        out.print("<td>用户编号</td>");
        out.print("<td>用户姓名</td>");
        out.print("<td>用户密码</td>");
        out.print("<td>用户性别</td>");
        out.print("<td>用户邮箱</td>");
        out.print("<td>操作</td>");
        out.print("</tr>");
        for (Users users:userList) {
            out.print("<tr>");
            out.print("<td>" + users.getUserId() + "</td>");
            out.print("<td>" + users.getUserName() + "</td>");
            out.print("<td>******</td>");
            out.print("<td>" + users.getGender() + "</td>");
            out.print("<td>" + users.getEmail() + "</td>");
            out.print("<td><a href='delete?userId="+users.getUserId()+"'>删除用户</a></td>");
            out.print("</tr>");
        }
        out.print("</table>");
    }
}
