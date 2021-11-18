package com.romanm.pis.service;

import com.romanm.pis.dao.EventDAO;
import com.romanm.pis.domain.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Repository
@Transactional
public class EventService {

    private final EventDAO eventDAO;

    @Autowired
    public EventService(@Qualifier("eventDAOImplEM") EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public List<Event> getAllEvents() {
        List<Event> events = eventDAO.findAll();

        return events;
    }

    public void createEvent(MultiValueMap<String, String> paramMap) {
        String shortDescription = paramMap.getFirst("shortDescription");
        String dateTime = paramMap.getFirst("dateTime");
        String longDescription = paramMap.getFirst("longDescription");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        assert dateTime != null;
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);

        Event event = new Event();
        event.setShortDescription(shortDescription);
        event.setDateAndTime(localDateTime);
        event.setLongDescription(longDescription);

        eventDAO.save(event);
    }
}
