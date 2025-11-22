package com.nunostudios.ordermanagement.controller;

import com.nunostudios.ordermanagement.service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/orders/{id}/report")
    public String getReport(@PathVariable("id") UUID orderId) {
        return reportService.generateReport(orderId).summary();
    }

    @GetMapping("/{id}/report/with-tax")
    public BigDecimal calculateTotalWithTax(@PathVariable("id") UUID orderId,
                                        @RequestParam("taxRate") BigDecimal taxRate) {
        return reportService.calculateTotalWithTax(orderId, taxRate);
    }
}
