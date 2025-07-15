package com.xxx.springboot16activemq.service;

import com.xxx.springboot16activemq.config.ActiveMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class QueueConsumer3 {
    private Logger logger = LoggerFactory.getLogger(QueueConsumer3.class);


    /* 监听pub/sub消息 */
    @JmsListener(destination = ActiveMqConfig.TOPIC_NAME, containerFactory = "topicListenerContainer")
    public void receiveMsgFromTopic(String msg) {
        logger.info("consumer3 received topic msg :{}", msg);
    }
}
