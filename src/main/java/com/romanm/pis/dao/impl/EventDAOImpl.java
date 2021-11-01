package com.romanm.pis.dao.impl;

import com.romanm.pis.dao.mapper.EventMapper;
import com.romanm.pis.domain.Event;
import com.romanm.pis.dao.EventDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class EventDAOImpl implements EventDAO {

    private static final Logger logger = LogManager.getLogger(EventDAOImpl.class);

    private final static String INSERT_INTO_EVENTS =
            "insert into events(date_time, short_description, long_description) value(?, ?, ?);";
    private final static String UPDATE_EVENT =
            "update events set date_time=?, short_description=?, long_description=? where id=?;";
    private final static String EVENT_FIND_BY_ID_QUERY =
            "select * from events where id=?;";
    private final static String EVENT_FIND_ALL_QUERY =
            "select * from events;";

    private final Connection connection;

    public EventDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Event save(Event event) {
        if (event.getId() == null) {
            Long newId;
            insertEvent(event);
            newId = RequestUtil.getLastInsertId(connection, "event");
            logger.debug("Event (id = "
                    + newId
                    + ") was created: " + event.getLongDescription());
            event.setId(newId);
        } else {
            updateEvent(event);
        }
        return event;
    }

    private void insertEvent(Event event) {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             INSERT_INTO_EVENTS)) {
            statement.setTimestamp(1, Timestamp.valueOf(event.getDateAndTime()));
            statement.setString(2, event.getShortDescription());
            statement.setString(3, event.getLongDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Create new event: " + e.getMessage());
        }
        logger.debug("New event : " + event.getLongDescription() + " was successfully created");
    }

    private void updateEvent(Event event) {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             UPDATE_EVENT)) {
            statement.setTimestamp(1, Timestamp.valueOf(event.getDateAndTime()));
            statement.setString(2, event.getShortDescription());
            statement.setString(3, event.getLongDescription());
            statement.setLong(4, event.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Create new event: " + e.getMessage());
        }
        logger.debug("New event : " + event.getLongDescription() + " was successfully created");
    }

    @Override
    public Optional<Event> findById(Long id) {
        Event event = null;
        logger.info("Try to find event by id");
        try (PreparedStatement pst =
                     connection.prepareStatement(
                             EVENT_FIND_BY_ID_QUERY)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            EventMapper eventMapper = new EventMapper();
            event = eventMapper.extractEventFromResultSet(rs);
        } catch (SQLException e) {
            logger.debug("Get event by id=" + id + " sql exception : " + e.getMessage());
        }
        return Optional.ofNullable(event);
    }

    @Override
    public List<Event> findAll() {
        List<Event> events = null;
        logger.info("Try to find all events");
        try (PreparedStatement pst =
                     connection.prepareStatement(
                             EVENT_FIND_ALL_QUERY)) {
            ResultSet rs = pst.executeQuery();
            EventMapper eventMapper = new EventMapper();
            events = eventMapper.extractEventsFromResultSet(rs);
        } catch (SQLException e) {
            logger.debug("Get all events sql exception : " + e.getMessage());
        }
        return events;
    }
}
