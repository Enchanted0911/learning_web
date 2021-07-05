package com.jun.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author Wu
 */
public class OneServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 将数据添加到请求作用域对象，作为共享数据
        request.setAttribute("key1", "hello JAVA");
        // 2. 代替浏览器，向Tomcat索要TwoServlet来完成剩余任务
        request.getRequestDispatcher("/two").forward(request, response);
    }
}
