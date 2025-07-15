package com.xxx.springboot16activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;


@Configuration
public class ActiveMqConfig {

    /* pub/sub */
    public static final String TOPIC_NAME = "active.topic";

    /* peer to peer */
    public static final String QUEUE_NAME = "active.queue";

    @Bean
    public Destination topic() {
        return new ActiveMQTopic(TOPIC_NAME);
    }

    @Bean
    public Destination queue() {
        return new ActiveMQQueue(QUEUE_NAME);
    }

    @Bean
    public JmsListenerContainerFactory topicListenerContainer(ConnectionFactory connectionFactory) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);

        // 相当于在application.yml中配置：spring.jms.pub-sub-domain=true
        factory.setPubSubDomain(true);

        return factory;

    }

}
