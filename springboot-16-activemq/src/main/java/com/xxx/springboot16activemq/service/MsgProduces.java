package com.xxx.springboot16activemq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
public class MsgProduces {

    private final Logger logger = LoggerFactory.getLogger(MsgProduces.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMsg(Destination destination, String msg) {

        logger.info("send msg to {} : {}", destination, msg);

        jmsMessagingTemplate.convertAndSend(destination, msg);
    }
}
