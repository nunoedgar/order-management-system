package com.nunostudios.ordermanagement.event;

import com.nunostudios.ordermanagement.dto.OrderResponseDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventPublisher {
    private final KafkaTemplate<String, OrderResponseDTO> kafkaTemplate;

    public OrderEventPublisher(KafkaTemplate<String, OrderResponseDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderStatusUpdated(OrderResponseDTO orderResponseDTO) {
        kafkaTemplate.send("order-status-updated", orderResponseDTO);
    }
}
