package com.bridgelabz.rabbitmqspringboot.publisher;

import com.bridgelabz.rabbitmqspringboot.cofig.MessagingConfig;
import com.bridgelabz.rabbitmqspringboot.dto.Order;
import com.bridgelabz.rabbitmqspringboot.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    // Method will accept order as request and publish to queue
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        // Setting orderId
        order.setOrderId(UUID.randomUUID().toString());
        // status will return to the user
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed successfully in " + restaurantName);
        // will convert the msg and publish with routingKey
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
        return "Success !!";
    }
}