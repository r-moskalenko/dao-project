package com.romanm.pis.servlet.command;

import com.romanm.pis.dao.DAOFactory;
import com.romanm.pis.dao.ReportDAO;
import com.romanm.pis.domain.Report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateReportService implements Command {
    @Override
    public void post(HttpServletRequest request, HttpServletResponse response) {
        ReportDAO reportDAO = DAOFactory.getInstance().createReportDao();
        Long reportId = Long.parseLong(request.getParameter("reportId"));
        String reportTopic = request.getParameter("reportTopic");
        String reportText = request.getParameter("reportText");

        Report report = new Report();
        report.setId(reportId);
        report.setTopic(reportTopic);
        report.setText(reportText);

        reportDAO.save(report);
    }
}
