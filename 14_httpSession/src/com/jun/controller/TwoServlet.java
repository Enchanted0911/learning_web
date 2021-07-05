package com.jun.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
        // 1. 调用请求对象，向Tomcat索要当前用户在服务端私人储物柜
        HttpSession session = request.getSession();
        // 2. 将session中所有key读取出来，存放在一个枚举对象中
        Enumeration goodsNames = session.getAttributeNames();
        while (goodsNames.hasMoreElements()) {
            String goodsName = (String) goodsNames.nextElement();
            int goodsNum = (int) session.getAttribute(goodsName);
            System.out.println("商品名称 " + goodsName + " 商品数量 " + goodsNum);
        }
    }
}
