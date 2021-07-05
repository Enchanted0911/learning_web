package com.weidongli.junyao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * @author Wu
 */
public class JDBC_test03 {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("com.weidongli.junyao.JDBC");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        Connection conn = null;
        Statement stmt = null;
        try {
            //注册驱动
            Class.forName(driver);

            //获取连接
            conn = DriverManager.getConnection(url, user, password);

            //获取数据库操作对象
            stmt = conn.createStatement();

            //执行SQL
            String sql = "update dept set dname = '文艺部', loc = '浙江sheng' where deptno = 20";
            int count = stmt.executeUpdate(sql);
            System.out.println(count == 1 ? "更新成功" : "更新失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
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
