package com.romanm.pis.controller;

import com.romanm.pis.dao.DAOFactory;
import com.romanm.pis.dao.EventDAO;
import com.romanm.pis.domain.Event;
import com.romanm.pis.dto.EventDto;
import com.romanm.pis.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public String displayEvents(Model model) {
        List<Event> eventList = eventService.getAllEvents();
        model.addAttribute("listEvents", eventList);

        return "event-list";
    }

    @GetMapping("/events/create")
    public String getCreatePage() {
        return "create-event";
    }

    @PostMapping("/events/create")
    public String createEvent(@RequestBody MultiValueMap<String,String> paramMap) {
        EventDAO eventDAO = DAOFactory.getInstance().createEventDao();
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

        return "redirect:/api/events";
    }
}
