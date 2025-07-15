package com.xxx.springboot16activemq.service;

import com.xxx.springboot16activemq.config.ActiveMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
public class QueueConsumer {
    private Logger logger = LoggerFactory.getLogger(QueueConsumer.class);


    /* 监听点对点消息 */
    @JmsListener(destination = ActiveMqConfig.QUEUE_NAME)
    public void receiveMsg(String msg) {
        logger.info("received msg :{}", msg);
    }

    /* 监听pub/sub消息 */
    @JmsListener(destination = ActiveMqConfig.TOPIC_NAME,containerFactory = "topicListenerContainer")
    public void receiveMsgFromTopic(String msg) {
        logger.info("received topic msg :{}", msg);
    }
}
