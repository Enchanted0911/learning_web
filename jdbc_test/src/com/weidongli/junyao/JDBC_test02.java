package com.weidongli.junyao;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Wu
 */
public class JDBC_test02 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            //1.注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            //2.获取链接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wjstest01?serverTimezone=UTC", "root","WJSpjy3344" );

            //3.获取数据库操作对象
            stmt = conn.createStatement();

            //4.执行SQL语句
            //JDBC中的sql语句不需要分号结尾
            //String sql = "delete from dept where deptno = 50";
            String sql = "update dept set dname = '销售部', loc = '天津' where deptno = 20";
            int count = stmt.executeUpdate(sql);
            System.out.println(count == 1 ? "删除成功" : "删除失败");
            //5.处理查询结果集 只有查询语句才用到
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //6.释放资源
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
