package com.romanm.pis.service;

import com.romanm.pis.controller.UserTypeController;
import com.romanm.pis.dao.DAOFactory;
import com.romanm.pis.dao.EventDAO;
import com.romanm.pis.dao.ReportDAO;
import com.romanm.pis.domain.Event;
import com.romanm.pis.domain.Report;
import com.romanm.pis.dto.ReportDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
public class ReportService {
    private final Logger logger = LoggerFactory.getLogger(UserTypeController.class);

    private final EventDAO eventDAO;

    @Autowired
    public ReportService(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public List<Report> getAllReports(String eventId) {
        ReportDAO reportDAO = DAOFactory.getInstance().createReportDao();
        List<Report> reports = reportDAO.findAllByEventId(Long.parseLong(eventId));

        return reports;
    }

    public void createReport(Long eventId, MultiValueMap<String, String> paramMap) {
        ReportDAO reportDAO = DAOFactory.getInstance().createReportDao();
        String reportTopic = paramMap.getFirst("reportTopic");
        String reportText = paramMap.getFirst("reportText");

        Event event = null;
        try {
            event = eventDAO.findById(eventId)
                    .orElseThrow(() -> new Exception("Event with Id=" + eventId + " not found"));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }


        Report report = new Report();
        report.setTopic(reportTopic);
        report.setText(reportText);
        report.setEvent(event);

        reportDAO.save(report);
    }

    public void editReport(ReportDto reportDto) {
        ReportDAO reportDAO = DAOFactory.getInstance().createReportDao();
        Long reportId = reportDto.getReportId();
        String reportTopic = reportDto.getReportTopic();
        String reportText = reportDto.getReportText();

        Report report = new Report();
        report.setId(reportId);
        report.setTopic(reportTopic);
        report.setText(reportText);

        reportDAO.save(report);
    }
}
