package com.weidongli.junyao;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * @author Wu
 */
public class JDBC_test04 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResourceBundle bundle = ResourceBundle.getBundle("com.weidongli.junyao.JDBC");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String sql = "select empno, ename, sal from emp";

            //专门执行DQL语句的方法
            rs = stmt.executeQuery(sql);

            //处理查询结果集
            while (rs.next()) {
                //除了以String类型取出外还可以以特定类型取出

                // 注意列名是查询语句结果的列名，例如 empno as empno111 此时就要用empno111不能用empno
                String empno = rs.getString("empno");
                String ename = rs.getString(2);
                double sal = rs.getDouble(3);
                System.out.println(empno + " " + ename + " " + sal);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //注意关闭从下往上关闭
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
