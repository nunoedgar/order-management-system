package com.nunostudios.ordermanagement.controller;

import com.nunostudios.ordermanagement.service.ReportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/orders/{id}/report")
    public String getReport(@PathVariable("id") String orderId,
                            @RequestParam("customerName") String customerName,
                            @RequestParam("amount") double totalAmount,
                            @RequestParam("status") String status) {
        return reportService.generateReport(orderId, customerName, totalAmount, status).summary();
    }

    @GetMapping("/{id}/report/with-tax")
    public double calculateTotalWithTax(@PathVariable("id") String orderId,
                                        @RequestParam("customerName") String customerName,
                                        @RequestParam("amount") double totalAmount,
                                        @RequestParam("status") String status,
                                        @RequestParam("taxRate") double taxRate) {
        return reportService.calculateTotalWithTax(orderId, customerName, totalAmount, status, taxRate);
    }
}
