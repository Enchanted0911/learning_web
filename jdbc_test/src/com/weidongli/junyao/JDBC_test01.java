package com.weidongli.junyao;

import java.sql.*;

/**
 * @author Wu
 */
public class JDBC_test01 {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        try {
            //注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            //获取链接
            String url = "jdbc:mysql://localhost:3306/wjstest01?serverTimezone=UTC";
            String user = "root";
            String password = "WJSpjy3344";
            conn = DriverManager.getConnection(url, user, password);

            //获取数据库操作对象
            stmt = conn.createStatement();

            //执行SQL
            String sql = "insert into dept (deptno, dname, loc) values(50, '人事部', '北京')";
            //专门执行DML语句的(insert, delete, update)
            //返回值是“影响数据库中的记录条数”
            int count = stmt.executeUpdate(sql);

            //处理查询结果集

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //释放资源
            //为了保证资源一定释放,在finally语句块中关闭资源
            //并且要遵循从小到大依次关闭
            //分别对其try...catch
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
