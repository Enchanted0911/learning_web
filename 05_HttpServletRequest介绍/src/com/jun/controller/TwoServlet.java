package com.jun.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * @author Wu
 */
public class TwoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 通过请求对象获得请求头中所有请求参数名
        // 将所有请求参数名称保存到一个枚举对象进行返回
        Enumeration paramNames = request.getParameterNames();

        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            // 通过请求对象读取指定的请求参数的值
            String value = request.getParameter(paramName);
            System.out.println("请求参数名 : " + paramName + " 请求参数值 " + value);
        }
    }
}
