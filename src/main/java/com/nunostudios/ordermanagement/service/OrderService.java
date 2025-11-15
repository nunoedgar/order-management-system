package com.nunostudios.ordermanagement.service;

import com.nunostudios.ordermanagement.dto.OrderRequestDTO;
import com.nunostudios.ordermanagement.dto.OrderResponseDTO;
import com.nunostudios.ordermanagement.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO dto);
    List<OrderResponseDTO> getOrders();
    OrderResponseDTO getOrderById(UUID id);
    OrderResponseDTO updateStatus(UUID id, OrderStatus newStatus);
}
