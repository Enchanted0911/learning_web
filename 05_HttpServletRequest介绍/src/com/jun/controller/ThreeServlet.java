package com.jun.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
/**
* 浏览器以post方式发送请求，请求参数保存在请求体中,请求体二进制内容由当前请求对象(request)负责解码，request默认使用[ISO-8859-1]字符集
*
 * @author Wu
 */
public class ThreeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通知请求对象，使用UTF-8字符集对请求体内容解码
        request.setCharacterEncoding("utf-8");
        // 通过请求对象获取请求体参数信息
        String value = request.getParameter("userName");
        System.out.println("从请求体得到参数值 " + value);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 通过请求对象获取请求头参数信息
        String userName = request.getParameter("userName");
        System.out.println("从请求头得到参数值 " + userName);
    }
}
