package com.nunostudios.ordermanagement.controller;

import com.nunostudios.ordermanagement.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/orders/{id}/report")
    public String getReport(@PathVariable("id") String orderId) {
        return reportService.createReport(orderId);
    }
}
