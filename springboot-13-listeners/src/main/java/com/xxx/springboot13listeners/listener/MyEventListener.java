package com.xxx.springboot13listeners.listener;

import com.xxx.springboot13listeners.entity.User;
import com.xxx.springboot13listeners.event.MyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener implements ApplicationListener<MyEvent> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(MyEvent event) {
        User user = event.getUser();
        logger.info("监听到MyEvent事件发生了，数据:{}", user);

    }
}
