package com.romanm.pis.dao.impl;

import com.romanm.pis.domain.Event;
import com.romanm.pis.dao.EventDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class EventDAOImpl implements EventDAO {

    private static final Logger logger = LogManager.getLogger(EventDAOImpl.class);

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager entityManager;

    private final static String INSERT_INTO_EVENTS =
            "insert into events(date_time, short_description, long_description) value(?, ?, ?);";
    private final static String UPDATE_EVENT =
            "update events set date_time=?, short_description=?, long_description=? where id=?;";
    private final static String EVENT_FIND_BY_ID_QUERY =
            "select * from events where id=?;";
    private final static String EVENT_FIND_ALL_QUERY =
            "select * from events;";

    @Override
    public Event save(Event event) {
        entityManager.persist(event);

        return event;
    }

    @Override
    public Optional<Event> findById(Long id) {
        Event event = entityManager.find(Event.class, id);

        return Optional.ofNullable(event);
    }

    @Override
    public List<Event> findAll() {
        List<Event> events = entityManager.createQuery("Select e From Event e",
                Event.class).getResultList();
        return events;
    }
}
