package com.nunostudios.ordermanagement.service.impl;

import com.nunostudios.ordermanagement.model.Order;
import com.nunostudios.ordermanagement.model.OrderReport;
import com.nunostudios.ordermanagement.repository.OrderRepository;
import com.nunostudios.ordermanagement.service.ReportService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ReportServiceImpl implements ReportService {

    private final ObjectFactory<OrderReport> reportFactory;
    private final OrderRepository orderRepository;

    public ReportServiceImpl(ObjectFactory<OrderReport> reportFactory, OrderRepository orderRepository) {
        this.reportFactory = reportFactory;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderReport generateReport(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        OrderReport report = reportFactory.getObject();
        report.setOrderId(order.getId().toString());
        report.setCustomerName(order.getCustomerName());
        report.setTotalAmount(order.getPrice());
        report.setStatus(order.getStatus().toString());
        return report;
    }

    @Override
    public BigDecimal calculateTotalWithTax(UUID orderId, BigDecimal taxRate) {
        OrderReport report = generateReport(orderId);
        return report.calculateTotalWithTax(taxRate);
    }


}
