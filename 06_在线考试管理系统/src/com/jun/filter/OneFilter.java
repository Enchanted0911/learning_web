package com.jun.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @author Wu
 */
public class OneFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 1. 通过请求对象读取请求包中请求行的URI，了解用户访问的资源文件是什么
        String uri = request.getRequestURI();
        // 2. 如果本次请求资源文件与登录相关，应该放行
        String login = "login", myWeb = "/myWeb/";
        if (uri.contains(login) || myWeb.equals(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 3.如果本次请求访问的是其他资源文件，需要得到用户在服务端的Session


        // 1. 拦截后，通过请求对象向Tomcat索要当前用户的HttpSession
        HttpSession session = request.getSession(false);
        // 2. 判断来访用户身份合法性
        if (session == null) {
            request.getRequestDispatcher("/login_error.html").forward(servletRequest, servletResponse);
            return;
        }
        // 3. 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
