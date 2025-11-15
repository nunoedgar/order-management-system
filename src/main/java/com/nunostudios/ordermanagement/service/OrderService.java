package com.nunostudios.ordermanagement.service;

import com.nunostudios.ordermanagement.dto.OrderRequestDTO;
import com.nunostudios.ordermanagement.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO dto);
    List<OrderResponseDTO> getOrders();
}
