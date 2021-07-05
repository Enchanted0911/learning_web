package com.jun.dao;

import com.jun.DBUtil.DBUtil;
import com.jun.entity.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wu
 */
public class QuestionDao {
    public int add(Question question) {
        Connection conn = null;
        ResultSet rs = null;
        String sql = "insert into question(title, optionA, optionB, optionC, optionD, answer) values (?, ?, ?, ?, ?, ?)";
        var ps = DBUtil.createPreparedStatement(sql);
        int result = 0;
        try {
            ps.setString(1, question.getTitle());
            ps.setString(2, question.getOptionA());
            ps.setString(3, question.getOptionB());
            ps.setString(4, question.getOptionC());
            ps.setString(5, question.getOptionD());
            ps.setString(6, question.getAnswer());
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }

    public List findAll() {
        List list = new ArrayList();
        String sql = "select * from question";
        Connection conn = null;
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer questionId = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                Question question = new Question(questionId, title, optionA, optionB, optionC, optionD, answer);
                list.add(question);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }

    public Question findById(String questionId) {
        String sql = "select * from question where questionId=?";
        Connection conn = null;
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        ResultSet rs = null;
        Question question = null;
        try {
            ps.setInt(1, Integer.parseInt(questionId));
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer questionId01 = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                question = new Question(questionId01, title, optionA, optionB, optionC, optionD, answer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return question;
    }

    public int delete(String questionId){
        String sql = "delete from question where questionId = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            ps = DBUtil.createPreparedStatement(sql);
            ps.setInt(1, Integer.parseInt(questionId));
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }

    public int update(Question question) {
        Connection conn = null;
        ResultSet rs = null;
        String sql = "update question set title = ?, optionA = ?, optionB = ?, optionC = ?, optionD = ?, answer = ? where questionId = ?";
        var ps = DBUtil.createPreparedStatement(sql);
        int result = 0;
        try {
            ps.setString(1, question.getTitle());
            ps.setString(2, question.getOptionA());
            ps.setString(3, question.getOptionB());
            ps.setString(4, question.getOptionC());
            ps.setString(5, question.getOptionD());
            ps.setString(6, question.getAnswer());
            ps.setInt(7, question.getQuestionId());
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }
}
