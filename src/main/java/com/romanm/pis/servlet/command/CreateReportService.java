package com.romanm.pis.servlet.command;

import com.romanm.pis.dao.DAOFactory;
import com.romanm.pis.dao.EventDAO;
import com.romanm.pis.dao.ReportDAO;
import com.romanm.pis.dao.impl.EventDAOImpl;
import com.romanm.pis.domain.Event;
import com.romanm.pis.domain.Report;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateReportService implements Command {
    private static final Logger logger = LogManager.getLogger(CreateReportService.class);

    @Override
    public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/create-report.jsp").forward(request, response);
    }
    @Override
    public void post(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String[] uriParts = request.getRequestURI().split("/");
//        Long eventId = Long.parseLong(uriParts[uriParts.length - 3]);
//
//        ReportDAO reportDAO = DAOFactory.getInstance().createReportDao();
//        EventDAO eventDAO = DAOFactory.getInstance().createEventDao();
//        String reportTopic = request.getParameter("reportTopic");
//        String reportText = request.getParameter("reportText");
//
//        Event event = null;
//        try {
//             event = eventDAO.findById(eventId)
//                    .orElseThrow(() -> new Exception("Event with Id=" + eventId + " not found"));
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        }
//
//
//        Report report = new Report();
//        report.setTopic(reportTopic);
//        report.setText(reportText);
//        report.setEvent(event);
//
//        reportDAO.save(report);
//
//        response.sendRedirect("events");
    }
}
