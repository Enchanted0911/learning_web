package com.jun.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Wu
 */
public class OneServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 执行结果
        String result = "hello world!";
        // 响应对象将结果写入到响应体
        //1.通过响应对象，向tomcat索要输出流
        PrintWriter out = response.getWriter();
        //2.通过输出流将响应结果以二进制形式写入到响应体
        out.write(result);

    }
}
