package com.romanm.pis.servlet.command;

import com.romanm.pis.dao.DAOFactory;
import com.romanm.pis.dao.ReportDAO;
import com.romanm.pis.domain.Report;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllReportsService implements Command {
    @Override
    public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] uriParts = request.getRequestURI().split("/");
        Long eventId = Long.parseLong(uriParts[uriParts.length - 2]);

        ReportDAO reportDAO = DAOFactory.getInstance().createReportDao();
        List<Report> reports = reportDAO.findAllByEventId(eventId);
        request.setAttribute("listReports", reports);
        request.getServletContext().getRequestDispatcher("/report-list.jsp").forward(request, response);
    }
}
