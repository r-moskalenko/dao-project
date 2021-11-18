package com.romanm.pis.dao.implEntityManager;

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
public class EventDAOImplEM implements EventDAO {

    private static final Logger logger = LogManager.getLogger(EventDAOImplEM.class);

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager entityManager;

    @Override
    public Event save(Event event) {
        entityManager.persist(event);

        return event;
    }

    @Override
    public Optional<Event> findById(Long id) {
        Event event = entityManager.find(Event.class, id);

        logger.debug("Find event: " + event);

        return Optional.ofNullable(event);
    }

    @Override
    public List<Event> findAll() {
        List<Event> events = entityManager.createQuery("Select e From Event e", Event.class).getResultList();
        return events;
    }
}
