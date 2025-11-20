package com.nunostudios.ordermanagement.service.impl;

import com.nunostudios.ordermanagement.dto.OrderReport;
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
    public String createReport(String orderId) {
        OrderReport report = reportFactory.getObject();
        report.setOrderId(orderId);
        return report.generate();
    }
}
