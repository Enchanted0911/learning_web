package com.jun.filter;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author Wu
 */
public class OneFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1. 通过拦截请求对象得到请求包参数信息，从而得到来访用户的真实年龄
        String age = servletRequest.getParameter("age");
        // 2. 根据年龄，帮助Http服务器判断本次请求合法性
        int limitAge = 70;
        if (Integer.parseInt(age) < limitAge) {
            // 将拦截请求对象和响应对象交还给Tomcat，由Tomcat继续调用资源文件
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 过滤器代替http服务器拒绝本次请求
            servletResponse.setContentType("text/html");
            PrintWriter out = servletResponse.getWriter();
            out.print("<center><font style='color:red;font-size:40px'>不许看</font></center>");
        }
    }
}
