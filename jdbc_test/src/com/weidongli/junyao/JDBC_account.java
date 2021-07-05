package com.weidongli.junyao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class JDBC_account {
    /**
     * SQL脚本:
     *  drop table if exists t_act;
     *  create table t_act(
     *      actno int;
     *      balance double(7, 2); // 注意 7 表示有效数字的位数, 2 表示小数位的个数
     *  )；
     *  insert into t_act(actno, balance) values(111, 20000);
     *  insert into t_act(actno, balance) values(222, 0);
     *  commit;
     *  select * from t_act;
     * @param args
     */
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("com.weidongli.junyao.JDBC");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            String sql = "update t_act set balance = ? where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, 10000);
            ps.setInt(2, 111);
            int count = ps.executeUpdate();
            ps.setDouble(1, 10000);
            ps.setInt(2, 222);
            count += ps.executeUpdate();
            System.out.println(count == 2 ? "转账成功" : "转账失败");
            conn.commit();
        } catch (ClassNotFoundException e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
