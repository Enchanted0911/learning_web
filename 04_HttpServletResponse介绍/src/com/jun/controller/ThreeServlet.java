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
public class ThreeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 既有文字信息又有HTML标签命令
        String result = "java<br/>mysql<br/>html<br/>";
        String result2 = "江西省<br/>浙江省<br/>上海市<br/>";
        // 设置响应头content-type
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.print(result2);
    }
}
