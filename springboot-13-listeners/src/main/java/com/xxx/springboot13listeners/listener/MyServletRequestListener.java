package com.xxx.springboot13listeners.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.util.Random;

@Component
public class MyServletRequestListener implements ServletRequestListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        logger.info("-------- requestDestroyed -----------");
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        logger.info("request域对象中保存的name：{}", request.getAttribute("name"));

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        logger.info("-------- requestInitialized -----------");

        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

        logger.info("sessionId: " + request.getSession().getId());
        logger.info("requestURI: " + request.getRequestURI());

        request.setAttribute("name", "测试" + new Random().nextInt());
    }
}
