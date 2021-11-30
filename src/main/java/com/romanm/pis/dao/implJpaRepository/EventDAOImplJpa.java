package com.romanm.pis.dao.implJpaRepository;

import com.romanm.pis.dao.EventDAO;
import com.romanm.pis.domain.Event;
import com.romanm.pis.repository.EventRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class EventDAOImplJpa implements EventDAO {

    private static final Logger logger = LogManager.getLogger(EventDAOImplJpa.class);

    private final EventRepository eventRepository;

    public EventDAOImplJpa(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event save(Event event) {
        eventRepository.save(event);

        return event;
    }

    @Override
    public Optional<Event> findById(Long id) {
        Optional<Event> event = eventRepository.findById(id);

        logger.debug("Find event: " + event);

        return event;
    }

    @Override
    public List<Event> findAll() {
        List<Event> events = eventRepository.findAll();
        logger.debug(events.toString());
        return events;
    }
}
