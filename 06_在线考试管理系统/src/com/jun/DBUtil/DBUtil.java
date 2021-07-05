package com.jun.DBUtil;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.*;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * JDBC工具类, 简化JDBC编程
 * @author Wu
 */
public class DBUtil {
    private DBUtil() {};
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/wjstest01?serverTimezone=UTC";
    private static String user = "root";
    private static String password = "WJSpjy3344";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取数据库连接对象
     * @return 连接对象
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 通过全局作用域对象得到Connection
     * @param request
     * @return Connection
     */
    public static Connection getConnection(HttpServletRequest request) {
        Connection conn = null;
        // 1. 通过请求对象得到全局作用域对象
        ServletContext application = request.getServletContext();
        // 2. 从全局作用域对象得到map
        Map map = (Map) application.getAttribute("key1");
        // 3. 从map得到一个处于空闲状态的Connection
        var it = map.keySet().iterator();
        while (it.hasNext()) {
            conn = (Connection) it.next();
            if ((boolean) map.get(conn)) {
                map.put(conn, false);
                break;
            }
        }
        return conn;
    }

    /**
     * 通过全局作用域对象得到PreparedStatement对象
     * @param sql
     * @param request
     * @return PreparedStatement
     */
    public static PreparedStatement createPreparedStatement(String sql, HttpServletRequest request) {
        PreparedStatement ps = null;
        try {
            ps = getConnection(request).prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ps;
    }

    /**
     * 获取预编译的数据库操作对象
     * @param sql
     * @return PreparedStatement
     */
    public static PreparedStatement createPreparedStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ps;
    }

    /**
     * 回收资源
     * @param conn
     * @param stmt
     * @param rs
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
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

    /**
     * 将Connection设置为空闲, 并销毁PreparedStatement
     * @param conn
     * @param stmt
     * @param request
     */
    public static void close(Connection conn, Statement stmt, HttpServletRequest request) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        ServletContext application = request.getServletContext();
        Map map = (Map) application.getAttribute("key1");
        map.put(conn, true);
    }
}
