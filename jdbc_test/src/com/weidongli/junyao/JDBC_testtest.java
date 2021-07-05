package com.weidongli.junyao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @author Wu
 */
public class JDBC_testtest {
    public static void main(String[] args) {
        Map<String, String> userLoginInfo = initUI();
        boolean loginSuccess = login(userLoginInfo);
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
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            String sql = "select * from t_user where loginName = ? and loginPwd = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userLoginInfo.get("loginName"));
            ps.setString(2, userLoginInfo.get("loginPwd"));
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
        System.out.print("用户名 : ");
        String loginName = s.nextLine();
        System.out.print("密码 : ");
        String loginPwd = s.nextLine();
        Map<String, String> userLoginInfo = new HashMap<>(16);
        userLoginInfo.put("loginName", loginName);
        userLoginInfo.put("loginPwd", loginPwd);
        return userLoginInfo;
    }
}
