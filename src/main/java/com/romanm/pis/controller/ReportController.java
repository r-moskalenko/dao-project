package com.romanm.pis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.romanm.pis.domain.Report;
import com.romanm.pis.dto.ReportDto;
import com.romanm.pis.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReportController {
    private Logger logger = LoggerFactory.getLogger(ReportController.class);
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/reports")
    public String displayReports(@RequestParam String eventId, Model model) {
        System.out.println("displayEvents method");
        List<Report> reportList = reportService.getAllReports(eventId);
        model.addAttribute("listReports", reportList);

        return "report-list";
    }

    @PutMapping("/reports")
    public void editReport(@RequestBody ReportDto reportDto) {
        reportService.editReport(reportDto);
    }

    @GetMapping("/reports/create")
    public String getCreatePage() {
        return "create-report";
    }

    @PostMapping("/reports/create")
    public String createReport(@RequestParam Long eventId, @RequestBody MultiValueMap<String,String> paramMap) {
        reportService.createReport(eventId, paramMap);

        return "redirect:/api/reports?eventId=" + eventId;
    }
}
