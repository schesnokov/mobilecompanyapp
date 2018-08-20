package com.mobilecompany.services.impl;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

/**
 * The type Jms config.
 */
@Configuration
@EnableJms
@ComponentScan("com.mobilecompany")
public class JmsConfig {

    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String BROKER_USERNAME = "admin";
    private static final String BROKER_PASSWORD = "admin";

    /**
     * Connection factory active mq connection factory.
     *
     * @return the active mq connection factory
     */
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setPassword(BROKER_PASSWORD);
        connectionFactory.setUserName(BROKER_USERNAME);
        return connectionFactory;
    }

    /**
     * Jms template jms template.
     *
     * @return the jms template
     */
    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        return template;
    }

    /**
     * Jms listener container factory default jms listener container factory.
     *
     * @return the default jms listener container factory
     */
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1-1");
        return factory;
    }
}