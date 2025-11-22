package com.nunostudios.ordermanagement.service;

import com.nunostudios.ordermanagement.model.OrderReport;

import java.math.BigDecimal;
import java.util.UUID;

public interface ReportService {
    OrderReport generateReport(UUID orderId);
    BigDecimal calculateTotalWithTax(UUID orderId, BigDecimal taxRate);
}
