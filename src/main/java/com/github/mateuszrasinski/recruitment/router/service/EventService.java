package com.github.mateuszrasinski.recruitment.router.service;

import com.github.mateuszrasinski.recruitment.router.dao.Event;

import java.util.List;

public interface EventService {
    void create(Event event);

    List<Event> getAll();

    Event get(Long id);
}
