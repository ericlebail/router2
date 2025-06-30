package com.github.mateuszrasinski.recruitment.router.service;

import com.github.mateuszrasinski.recruitment.router.dao.Event;
import com.github.mateuszrasinski.recruitment.router.dao.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void create(Event event) {
        eventRepository.save(event);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event get(Long id) {
        return eventRepository.findById(id).get();
    }
}
