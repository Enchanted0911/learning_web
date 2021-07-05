package com.jun.controller;

import jakarta.servlet.RequestDispatcher;
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
        System.out.println("OneServlet 负责 洗菜");
        // 请求转发方案：
        //1. 通过当前请求对象生成资源文件申请报告对象
        //2. 将报告对象发送给Tomcat

        // 只写资源文件名 不要写网站名
        RequestDispatcher report = request.getRequestDispatcher("/two");
        report.forward(request, response);
    }
}
