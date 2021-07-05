package com.jun.controller;

import com.jun.dao.QuestionDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * @author Wu
 */
public class QuestionFindServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao dao = new QuestionDao();
        // 1. 调用DAO推送查询命令得到所有试题
        List list = dao.findAll();
        // 2. 将得到的试题添加到请求作用域对象作为共享数据
        request.setAttribute("key2", list);
        // 3. 请求转发向Tomcat调用question.jsp将结果与html标签写入到响应体
        request.getRequestDispatcher("/questions.jsp").forward(request, response);
    }
}
