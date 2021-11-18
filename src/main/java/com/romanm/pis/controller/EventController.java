package com.romanm.pis.controller;

import com.romanm.pis.domain.Event;
import com.romanm.pis.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        eventService.createEvent(paramMap);

        return "redirect:/api/events";
    }
}
