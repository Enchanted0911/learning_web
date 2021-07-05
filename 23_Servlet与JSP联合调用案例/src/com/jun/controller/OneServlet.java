package com.jun.controller;

import com.jun.entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wu
 */
public class OneServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student s1 = new Student(10, "mike");
        Student s2 = new Student(20, "allen");
        List<Student> stuList = new ArrayList<>();
        stuList.add(s1);
        stuList.add(s2);

        // 将处理结果添加到请求作用域对象
        request.setAttribute("key1", stuList);

        // 通过请求转发方案，向Tomcat申请调用user_show.jsp
        // 同时将request与response通过Tomcat交给user_show.jsp使用
        request.getRequestDispatcher("/user_show.jsp").forward(request, response);
    }
}
