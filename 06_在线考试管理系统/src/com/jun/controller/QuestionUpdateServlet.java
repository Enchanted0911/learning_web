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
public class QuestionUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title, optionA, optionB, optionC, optionD, answer, questionId;
        QuestionDao dao = new QuestionDao();
        Question question = null;
        int result = 0;
        // 1. 调用请求对象读取请求头参数信息
        title = request.getParameter("title");
        optionA = request.getParameter("optionA");
        optionB = request.getParameter("optionB");
        optionC = request.getParameter("optionC");
        optionD = request.getParameter("optionD");
        answer = request.getParameter("answer");
        questionId = request.getParameter("questionId");
        // 2. 调用DAO实现更新操作
        question = new Question(Integer.valueOf(questionId), title, optionA, optionB, optionC, optionD, answer);
        result = dao.update(question);
        // 3. 调用info.jsp将操作结果写入到响应体
        request.setAttribute("info", result == 1 ? "试题更新成功" : "试题更新失败");
        request.getRequestDispatcher("/info.jsp").forward(request, response);
    }
}
