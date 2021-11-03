package com.romanm.pis.servlet.command;

import com.romanm.pis.dao.DAOFactory;
import com.romanm.pis.dao.EventDAO;
import com.romanm.pis.domain.Event;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateEventService implements Command {
    @Override
    public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/create-event.jsp").forward(request, response);
    }

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EventDAO eventDAO = DAOFactory.getInstance().createEventDao();
        String shortDescription = request.getParameter("shortDescription");
        String dateTime = request.getParameter("dateTime");
        String longDescription = request.getParameter("longDescription");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);

        Event event = new Event();
        event.setShortDescription(shortDescription);
        event.setDateAndTime(localDateTime);
        event.setLongDescription(longDescription);

        eventDAO.save(event);

        response.sendRedirect("getAllEvents");
    }
}
