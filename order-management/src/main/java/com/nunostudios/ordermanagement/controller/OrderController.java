package com.nunostudios.ordermanagement.controller;

import com.nunostudios.ordermanagement.dto.OrderRequestDTO;
import com.nunostudios.ordermanagement.dto.OrderResponseDTO;
import com.nunostudios.ordermanagement.enums.OrderStatus;
import com.nunostudios.ordermanagement.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Valid OrderRequestDTO dto) {
        return ResponseEntity.ok(orderService.createOrder(dto));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PatchMapping("/{id}/status")
    @Operation(
            summary = "Updates the status of an existing order",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Novo estado do pedido",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "{ \"status\": \"PROCESSING\" }")
                    )
            )
    )
    public ResponseEntity<OrderResponseDTO> updateStatus(
            @PathVariable UUID id,
            @RequestBody Map<String, String> updates) {

        String newStatus = updates.get("status");
        OrderResponseDTO updatedOrder = orderService.updateStatus(id, OrderStatus.valueOf(newStatus));
        return ResponseEntity.ok(updatedOrder);
    }
}
