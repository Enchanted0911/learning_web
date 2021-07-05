package com.weidongli.junyao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * 解决SQL注入问题使用PreparedStatement接口   java.sql.PreparedStatement
 * PreparedStatement继承了Statement  java.sql.Statement
 * PreparedStatement属于预编译的数据库操作对象
 * PreparedStatement的原理是 : 预先对SQL语句框架进行编译，然后给SQL语句传值
 * @author Wu
 */
public class JDBC_preparedS {
    public static void main(String[] args) {
        //初始化一个界面
        Map<String, String> userLoginInfo = initUI();

        //验证用户名和密码
        boolean loginSuccess = login(userLoginInfo);

        //输出结果
        System.out.println(loginSuccess ? "登录成功" : "登录失败");
    }
    private static boolean login(Map<String, String> userLoginInfo) {
        boolean loginSuccess = false;
        ResourceBundle bundle = ResourceBundle.getBundle("com.weidongli.junyao.JDBC");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        Connection conn = null;

        // 这里使用PreparedStatement 预编译的数据库操作对象
        PreparedStatement ps = null; 
        ResultSet rs = null;
        try {
            // 注册驱动
            Class.forName(driver);

            // 获取连接
            conn = DriverManager.getConnection(url, user, password);

            // 获取预编译的数据库操作对象
            // SQL语句的框架,其中一个?表示一个占位符,将来接收一个"值",注意 : 占位符不能用单引号括起来。
            String sql = "select * from t_user where loginName = ? and loginPwd = ?";
            ps = conn.prepareStatement(sql);
            // 给占位符?传值, 第一个?下标是1, 第二个?下标是2, JDBC所有下标从1开始
            ps.setString(1, userLoginInfo.get("loginName"));
            ps.setString(2, userLoginInfo.get("loginPwd"));

            // 执行SQL
            rs = ps.executeQuery();

            if (rs.next()) {
                loginSuccess = true;
            }
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
            if (ps != null) {
                try {
                    ps.close();
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
        return loginSuccess;
    }
    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);
        System.out.print("用户名: ");
        String loginName = s.nextLine();
        System.out.print("密码: ");
        String loginPwd = s.nextLine();
        Map<String, String> userLoginInfo = new HashMap<>(16);
        userLoginInfo.put("loginName", loginName);
        userLoginInfo.put("loginPwd", loginPwd);
        return userLoginInfo;
    }
}
