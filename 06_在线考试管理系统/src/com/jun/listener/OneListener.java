package com.jun.listener;

import com.jun.DBUtil.DBUtil;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wu
 */
public class OneListener implements ServletContextListener {
    /** 在Tomcat启动时预先创建20个Connection，在userDao.add方法执行时，将事先创建好的Connection交给add方法
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map map = new HashMap(16);
        int size = 20;
        for (int i = 0; i < size; i++) {
            Connection conn = null;
            try {
                conn = DBUtil.getConnection();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            // true 表示这个通道处于空闲状态
            map.put(conn, true);
        }
        ServletContext application = sce.getServletContext();
        application.setAttribute("key1", map);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ServletContext application = sce.getServletContext();
        Map map = (Map)application.getAttribute("key1");
        var it = map.keySet().iterator();
        while (it.hasNext()) {
            Connection conn = (Connection) it.next();
            if (conn != null) {
                DBUtil.close(conn, ps, rs);
            }
        }
    }
}
