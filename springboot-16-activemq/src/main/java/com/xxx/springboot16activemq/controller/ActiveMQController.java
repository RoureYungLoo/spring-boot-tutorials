package com.xxx.springboot16activemq.controller;

import com.xxx.springboot16activemq.service.MsgProduces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

@RestController
@RequestMapping("/activemq")
public class ActiveMQController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Destination queue;

    @Autowired
    private Destination topic;

    @Autowired
    private MsgProduces msgProduces;

    /* 发送点对点消息 */
    @GetMapping("/send/q")
    public String sendMsg(String msg) {
        msgProduces.sendMsg(queue, msg);
        return "success";
    }

    /*发送 pub/sub 消息*/
    @GetMapping("/send/topic")
    public String sendMsgToTopic(String msg) {
        msgProduces.sendMsg(topic, msg);
        return "success";
    }
}
