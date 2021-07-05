package com.jun.dao;

import com.jun.DBUtil.DBUtil;
import com.jun.entity.Users;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wu
 */
public class UserDao {
    /**用户注册
     *
     * @param user
     * @return int
     */
    public int add(Users user) {
        String sql = "insert into users(userName, password, gender, email) values(?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getGender());
            ps.setString(4, user.getEmail());
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }

    /**
     * 利用先创建好的Connection添加数据对象，提高效率
     * @param user
     * @param request
     * @return int
     */
    public int add(Users user, HttpServletRequest request) {
        String sql = "insert into users(userName, password, gender, email) values(?, ?, ?, ?)";
        Connection conn = DBUtil.getConnection(request);
        PreparedStatement ps = null;
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getGender());
            ps.setString(4, user.getEmail());
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, request);
        }
        return result;
    }

    /**查询所有用户信息
     *
     * @return userList
     */
    public List findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from users";
        List userList = new ArrayList();
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer userId = rs.getInt("userId");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                Users users = new Users(userId, userName, password, gender, email);
                userList.add(users);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return userList;
    }

    /**根据用户编号删除用户信息
     *
     * @param userId
     * @return
     */
    public int delete(String userId) {
        String sql = "delete from users where userId = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            ps = DBUtil.createPreparedStatement(sql);
            ps.setInt(1, Integer.valueOf(userId));
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }

    /**登录验证
     *
     * @param userName
     * @param password
     * @return int
     */
    public int login(String userName, String password) {
        String sql = "select count(*) from users where userName = ? and password = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            ps = DBUtil.createPreparedStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }
}
