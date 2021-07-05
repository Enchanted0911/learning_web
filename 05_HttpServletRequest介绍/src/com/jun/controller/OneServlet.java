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
        // 通过请求对象，读取请求行中url信息
        String url = request.getRequestURL().toString();
        // 通过请求对象，读取请求行中method信息
        String method = request.getMethod();
        // 通过请求对象，读取请求行中URI信息
        String uri = request.getRequestURI();
        /*
         * URI : 资源文件精准定位地址，在请求行中并没有URI这个属性
         * 实际上URL中截取一个字符串，格式为 "/网站名/资源文件名"
         * URI用于让Http服务器对被访问的资源文件进行定位
         */
        System.out.println("URL " + url);
        System.out.println("method " + method);
        System.out.println("URI " + uri);
    }
}
