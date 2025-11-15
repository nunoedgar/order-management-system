package com.nunostudios.ordermanagement.service.impl;

import com.nunostudios.ordermanagement.dto.OrderRequestDTO;
import com.nunostudios.ordermanagement.dto.OrderResponseDTO;
import com.nunostudios.ordermanagement.mapper.OrderMapper;
import com.nunostudios.ordermanagement.model.Order;
import com.nunostudios.ordermanagement.repository.OrderRepository;
import com.nunostudios.ordermanagement.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
        Order order = orderRepository.save(orderMapper.toEntity(dto));
        return orderMapper.toResponseDto(order);
    }

    @Override
    public List<OrderResponseDTO> getOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toResponseDto).toList();
    }
}
