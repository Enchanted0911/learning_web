package com.jun.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.CollationKey;


/**
 * @author Wu
 */
public class OneServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName, money;
        // 1. 调用请求对象读取请求头参数信息
        userName = request.getParameter("userName");
        money = request.getParameter("money");
        // 2. 开卡
        Cookie cardOne = new Cookie("userName", userName);
        Cookie cardTwo = new Cookie("money", money);
        // cookie.setMaxAge(60); cookie在硬盘上存活一分钟
        // 3. 发卡，将Cookie写入到响应头交给浏览器
        response.addCookie(cardOne);
        response.addCookie(cardTwo);
        // 4. 通知Tomcat将 【点餐页面】 内容写入到响应体交给浏览器（请求转发）
        request.getRequestDispatcher("/order.html").forward(request, response);
    }
}
