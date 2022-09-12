package com.bridgelabz.rabbitmqspringboot.consumer;

import com.bridgelabz.rabbitmqspringboot.cofig.MessagingConfig;
import com.bridgelabz.rabbitmqspringboot.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    // Created consumer which will consume the published msg from queue
    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message Received From queue: " + orderStatus);
    }
}