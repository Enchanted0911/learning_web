package com.jun.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.of;

/**
 * @author Wu
 */
public class TwoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Integer> hmap = new HashMap<>(16);
        hmap = Map.of("饺子", 30, "面条", 20, "盖浇饭", 15);
        int money = 0, cost = 0, balance = 0;
        String food, userName = null;
        Cookie[] cookies = null;
        Cookie newCard = null;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // 1. 读取请求头参数信息，得到用户点餐食物类型
        food = request.getParameter("food");
        // 2. 读取请求中的Cookie
        cookies = request.getCookies();
        // 3. 刷卡消费
        for (var card : cookies) {
            String key = card.getName();
            String value = card.getValue();
            if ("userName".equals(key)) {
                userName = value;
            } else if ("money".equals(key)) {
                money = Integer.parseInt(value);
                for (var entry : hmap.entrySet()) {
                    if (entry.getKey().equals(food)) {
                        if (entry.getValue() > money) {
                            out.print("用户" + userName + "余额不足，请充值");
                        } else {
                            cost = entry.getValue();
                            balance = money - cost;
                            newCard = new Cookie("money", String.valueOf(balance));
                        }
                    }
                }
            }
        }
        // 4. 将用户会员卡返还给用户
        response.addCookie(newCard);
        // 5. 消费记录写入到响应体
        out.print("用户 " + userName + "本次消费 " + cost + "元, 余额为: " + balance + "元");
    }
}
