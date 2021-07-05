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
        String address = "http://www.baidu.com";
        // 写入到响应头
        response.sendRedirect(address);
    }
    // Tomcat在推送响应包之前，看到响应体是空，但是响应头location却存放了一个地址
    // 此时Tomcat将302状态码写入到状态行
    //在浏览器接受到响应包之后，因为302状态码，浏览器不会读取响应体内容，自动根据响应头中location的地址发起第二次请求
}
