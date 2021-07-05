package com.jun.controller;

import com.jun.dao.QuestionDao;
import com.jun.entity.Question;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author Wu
 */
public class QuestionAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title, optionA, optionB, optionC, optionD, answer;
        QuestionDao dao = new QuestionDao();
        Question question = null;
        int result = 0;
        // 1. 调用请求对象读取请求信息，得到新增信息内容
        title = request.getParameter("title");
        optionA = request.getParameter("optionA");
        optionB = request.getParameter("optionB");
        optionC = request.getParameter("optionC");
        optionD = request.getParameter("optionD");
        answer = request.getParameter("answer");
        // 2. 调用Dao对象将Insert命令推送到数据库，并得到处理结果
        question = new Question(null, title, optionA, optionB, optionC, optionD, answer);
        result = dao.add(question);
        // 3. 通过请求转发，向Tomcat索要info.jsp将结果写入到响应体
        request.setAttribute("info", result == 1 ? "试题添加成功" : "试题添加失败");
        request.getRequestDispatcher("/info.jsp").forward(request, response);

    }
}
