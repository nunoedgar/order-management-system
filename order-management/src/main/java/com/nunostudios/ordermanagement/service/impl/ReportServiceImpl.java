package com.nunostudios.ordermanagement.service.impl;

import com.nunostudios.ordermanagement.model.OrderReport;
import com.nunostudios.ordermanagement.service.ReportService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    private final ObjectFactory<OrderReport> reportFactory;

    public ReportServiceImpl(ObjectFactory<OrderReport> reportFactory) {
        this.reportFactory = reportFactory;
    }

    @Override
    public OrderReport generateReport(String orderId, String customerName, double totalAmount, String status) {
        OrderReport report = reportFactory.getObject();
        report.setOrderId(orderId);
        report.setCustomerName(customerName);
        report.setTotalAmount(totalAmount);
        report.setStatus(status);
        return report;
    }

    @Override
    public double calculateTotalWithTax(String orderId, String customerName, double totalAmount, String status, double taxRate) {
        OrderReport report = generateReport(orderId, customerName, totalAmount, status);
        return report.calculateTotalWithTax(taxRate);
    }


}
