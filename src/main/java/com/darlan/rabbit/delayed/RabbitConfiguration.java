package com.darlan.rabbit.delayed;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    public static final String DELAYED_QUEUE = "work.later";
    private static final String DESTINATION_QUEUE = "work.now";
    private static final String HOST = "localhost";
    private static final String USER = "guest";
    private static final String PASS = "guest";
    private static final int DELAY = 10 * 1000;

    @Bean
    Queue queueA() {
        return QueueBuilder.durable(DESTINATION_QUEUE).build();
    }
    
    @Bean
    Queue queueB() {
        return QueueBuilder.durable(DELAYED_QUEUE)
            .ttl(DELAY)
            .deadLetterExchange("")
            .deadLetterRoutingKey(DESTINATION_QUEUE)
        .build();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        final CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(HOST);
        connectionFactory.setUsername(USER);
        connectionFactory.setPassword(PASS);
        return connectionFactory;
    }

    @Bean
    SimpleMessageListenerContainer container(final ConnectionFactory connectionFactory, final MessageListenerAdapter listenerAdapter) {
        final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(DESTINATION_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(final RabbitReceiver rabbitReceiver) {
        return new MessageListenerAdapter(rabbitReceiver, "receiveMessage");
    }

}
