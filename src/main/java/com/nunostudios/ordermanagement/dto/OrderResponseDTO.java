package com.nunostudios.ordermanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class OrderResponseDTO {
    private UUID id;
    private String customerName;
    private String productName;
    private int quantity;
    private BigDecimal price;
    private String status;
    private LocalDateTime createdAt;
}