package com.romanm.pis.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romanm.pis.dao.DAOFactory;
import com.romanm.pis.dao.ReportDAO;
import com.romanm.pis.domain.Report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

public class UpdateReportService implements Command {
    @Override
    public void post(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ReportDAO reportDAO = DAOFactory.getInstance().createReportDao();
        String requestData = request.getReader().lines().collect(Collectors.joining(" "));
        HashMap<String, String> jsonMap = new ObjectMapper().readValue(requestData, HashMap.class);
        Long reportId = Long.parseLong(jsonMap.get("reportId"));
        String reportTopic = jsonMap.get("reportTopic");
        String reportText = jsonMap.get("reportText");

        Report report = new Report();
        report.setId(reportId);
        report.setTopic(reportTopic);
        report.setText(reportText);

        reportDAO.save(report);
    }
}
