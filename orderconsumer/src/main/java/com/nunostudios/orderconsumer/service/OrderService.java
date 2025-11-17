package com.nunostudios.orderconsumer.service;

import com.nunostudios.orderconsumer.dto.OrderResponseDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @KafkaListener(topics = "order-status-updated", groupId = "order-notifier-group")
    public void orderListener(OrderResponseDTO order) {
        System.out.println(
                "Order received -> " +
                        "ID: " + order.getId() +
                        ", Customer: " + order.getCustomerName() +
                        ", Product: " + order.getProductName() +
                        ", Status: " + order.getStatus()
        );
    }
}