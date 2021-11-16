package com.romanm.pis.service;

import com.romanm.pis.dao.DAOFactory;
import com.romanm.pis.dao.EventDAO;
import com.romanm.pis.domain.Event;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    public List<Event> getAllEvents() {
        EventDAO eventDAO = DAOFactory.getInstance().createEventDao();
        List<Event> events = eventDAO.findAll();

        return events;
    }
}
