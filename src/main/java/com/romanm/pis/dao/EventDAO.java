package com.romanm.pis.dao;

import com.romanm.pis.domain.Event;

import java.util.List;
import java.util.Optional;

public interface EventDAO {
    Event save(Event event);

    Optional<Event> findById(Long id);

    List<Event> findAll();
}
