package com.weidongli.junyao;

import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * @author Wu
 */
public class JDBC_PD {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("com.weidongli.junyao.JDBC");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String sql = "";
            stmt.executeQuery(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
