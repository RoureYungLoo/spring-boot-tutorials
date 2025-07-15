package com.xxx.springboot13listeners.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/* 监听HTTP session 对象 */
@Component
public class MyHttpSessionListener implements HttpSessionListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AtomicInteger onlineUserCount = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setMaxInactiveInterval(10); // 设置10秒有效期
        se.getSession().getServletContext().setAttribute("count", onlineUserCount.incrementAndGet());
        logger.info("新用户上线了, 当前人数：{}", onlineUserCount.get());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        se.getSession().getServletContext().setAttribute("count", onlineUserCount.decrementAndGet());
        logger.info("用户下线了, 当前人数：{}", onlineUserCount.get());
    }
}
