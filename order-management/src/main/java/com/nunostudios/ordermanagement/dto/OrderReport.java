package com.nunostudios.ordermanagement.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Getter
@Setter
public class OrderReport {

    private final String reportId;
    private String orderId;

    public OrderReport() {
        this.reportId = java.util.UUID.randomUUID().toString();
    }

    public String generate() {
        return "Report " + reportId + " for order " + orderId;
    }
}
