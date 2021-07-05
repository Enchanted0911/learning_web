package com.jun.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
        String goodsName;
        // 1. 调用请求对象，读取请求头参数，得到用户选择商品名
        goodsName = request.getParameter("goodsName");
        // 2. 调用请求对象，向Tomcat索要当前用户在服务端的私人储物柜
        HttpSession session = request.getSession();
        // 3. 将用户选购商品添加到当前用户私人储物柜
        Integer goodsNum = (Integer) session.getAttribute(goodsName);
        session.setAttribute(goodsName, goodsNum == null ? 1 : goodsNum + 1);
    }
}
