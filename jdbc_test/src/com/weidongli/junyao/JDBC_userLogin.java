package com.weidongli.junyao;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @author Wu
 */
public class JDBC_userLogin {
    public static void main(String[] args) {
        //初始化一个界面
        Map<String, String> userLoginInfo = initUI();

        //验证用户名和密码
        boolean loginSuccess = login(userLoginInfo);

        //输出结果
        System.out.println(loginSuccess ? "登录成功" : "登录失败");
    }

    /**
     * 用户登录
     * SQL注入
     * 用户名: abc
     * 密码: abc' or '1' = '1
     * @param userLoginInfo 用户登录信息
     * @return false表示失败 true表示成功
     */
    private static boolean login(Map<String, String> userLoginInfo) {
        //JDBC代码
        boolean loginSuccess = false;
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
            String sql = "select * from t_user where loginName = '"+userLoginInfo.get("loginName")+"' and loginPwd = '"+userLoginInfo.get("loginPwd")+"'";
            rs = stmt.executeQuery(sql);
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
        return loginSuccess;
    }

    /**
     * 初始化用户界面
     * @return用户输入的用户名和密码等登录信息
     */
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
