package com.jun.controller;

import com.jun.dao.QuestionDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author Wu
 */
public class QuestionDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId;
        QuestionDao dao = new QuestionDao();
        int result = 0;
        //1.调用对象读取请求头参数(用户编号)
        questionId = request.getParameter("questionId");
        //2.调用dao将用户编号填充到delete命令并发送到数据库服务器
        result = dao.delete(questionId);
        request.setAttribute("info", result == 1 ? "试题删除成功" : "试题删除失败");
        request.getRequestDispatcher("/info.jsp").forward(request, response);
    }
}
