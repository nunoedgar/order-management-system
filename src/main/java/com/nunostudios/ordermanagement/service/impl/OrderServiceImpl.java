package com.nunostudios.ordermanagement.service.impl;

import com.nunostudios.ordermanagement.dto.OrderRequestDTO;
import com.nunostudios.ordermanagement.dto.OrderResponseDTO;
import com.nunostudios.ordermanagement.enums.OrderStatus;
import com.nunostudios.ordermanagement.event.OrderEventPublisher;
import com.nunostudios.ordermanagement.mapper.OrderMapper;
import com.nunostudios.ordermanagement.model.Order;
import com.nunostudios.ordermanagement.repository.OrderRepository;
import com.nunostudios.ordermanagement.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderEventPublisher orderEventPublisher;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, OrderEventPublisher orderEventPublisher) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderEventPublisher = orderEventPublisher;
    }

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
        Order order = orderRepository.save(orderMapper.toEntity(dto));
        OrderResponseDTO responseDto = orderMapper.toResponseDto(order);
        orderEventPublisher.publishOrderStatusUpdated(responseDto);
        return responseDto;
    }

    @Override
    public List<OrderResponseDTO> getOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toResponseDto).toList();
    }

    @Override
    public OrderResponseDTO getOrderById(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found with ID " + id));
        return orderMapper.toResponseDto(order);
    }

    @Override
    public OrderResponseDTO updateStatus(UUID id, OrderStatus newStatus) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found with ID " + id));

        if (order.getStatus() == OrderStatus.COMPLETED && newStatus == OrderStatus.PROCESSING) {
            throw new IllegalArgumentException("Cannot revert a completed order to processing");
        }

        order.setStatus(newStatus);
        order.setUpdatedAt(LocalDateTime.now());

        Order saved = orderRepository.save(order);

        OrderResponseDTO responseDto = orderMapper.toResponseDto(saved);

        orderEventPublisher.publishOrderStatusUpdated(responseDto);

        return responseDto;
    }
}
