package com.romanm.pis.servlet.command;

import com.romanm.pis.dao.DAOFactory;
import com.romanm.pis.dao.EventDAO;
import com.romanm.pis.domain.Event;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllEventsService implements Command {
    @Override
    public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EventDAO eventDAO = DAOFactory.getInstance().createEventDao();
        List<Event> events = eventDAO.findAll();
        request.setAttribute("listEvents", events);
        request.getServletContext().getRequestDispatcher("/event-list.jsp").forward(request, response);
    }

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response) {

    }
}
