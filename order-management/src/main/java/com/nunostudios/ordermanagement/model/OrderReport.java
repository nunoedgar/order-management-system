package com.nunostudios.ordermanagement.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Scope("prototype")
@Getter
@Setter
public class OrderReport {

    private final String reportId;
    private final LocalDateTime createdAt;
    private String orderId;
    private String customerName;
    private BigDecimal totalAmount;
    private String status;

    public OrderReport() {
        this.reportId = java.util.UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    public String summary() {
        return String.format(
                "Report %s | Order %s | Customer: %s | Amount: %.2f | Status: %s | Generated at: %s",
                reportId, orderId, customerName, totalAmount, status, createdAt
        );
    }

    public BigDecimal calculateTotalWithTax(BigDecimal taxRate) {
        return totalAmount.add(totalAmount.multiply(taxRate));
    }
}
