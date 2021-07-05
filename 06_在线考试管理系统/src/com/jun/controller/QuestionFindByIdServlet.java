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
public class QuestionFindByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao dao = new QuestionDao();
        // 1. 调用请求对象读取请求头中参数信息，得到试题编号
        String questionId = request.getParameter("questionId");
        // 2. 调用DAO推送查询命令得到这个试题编号对应的试题信息
        Question question = dao.findById(questionId);
        // 3. 调用question_update.jsp将试题信息写入响应体
        request.setAttribute("key2", question);
        request.getRequestDispatcher("/question_update.jsp").forward(request, response);
    }
}
