package com.jun.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author Wu
 */
public class FourServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "http://www.baidu.com?username=mike";
        // 通过响应对象，将地址赋值给响应头中location属性

        // 响应头 location = "http://www.baidu.com"
        response.sendRedirect(result);
        /*浏览器在接受到响应包之后
        * 如果发现响应头中存在location属性
        *自动通过地址栏向location指定网站发送请求
        *sendRedirect方法远程控制浏览器请求行为【请求地址 请求方式 请求参数】
        * */
    }
}
