package com.github.mateuszrasinski.recruitment.router.controller;

import com.github.mateuszrasinski.recruitment.router.dao.Event;
import com.github.mateuszrasinski.recruitment.router.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Event> getAllEvents() {
        return eventService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Event getEvent(Long id) {
        return eventService.get(id);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void create(@PathVariable Long id, @RequestBody Event event) {
        event.setId(id);
        eventService.create(event);
    }
}
