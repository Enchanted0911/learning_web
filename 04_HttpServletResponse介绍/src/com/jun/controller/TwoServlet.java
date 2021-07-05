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
public class TwoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 实际写入的是ASCII码50，浏览器显示数字2
        int money = 50;
        PrintWriter out = response.getWriter();
        out.write(money);
        // 实际开发过程中都是用out.print()方法将真实数据写入到响应体中
        out.print(money);
    }
}
