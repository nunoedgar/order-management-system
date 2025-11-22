package com.nunostudios.ordermanagement.service;

import com.nunostudios.ordermanagement.model.OrderReport;

public interface ReportService {
    OrderReport generateReport(String orderId, String customerName, double totalAmount, String status);
    double calculateTotalWithTax(String orderId, String customerName, double totalAmount, String status, double taxRate);
}
