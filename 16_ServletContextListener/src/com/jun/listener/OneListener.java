package com.jun.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * @author Wu
 */
public class OneListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("销毁........");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("创建........");
    }
}
