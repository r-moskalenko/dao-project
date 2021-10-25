package com.romanm.pis.dao.impl;

import com.romanm.pis.dao.mapper.EventMapper;
import com.romanm.pis.domain.Event;
import com.romanm.pis.dao.EventDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record EventDAOImpl(Connection connection) implements EventDAO {

    private static final Logger logger = LogManager.getLogger(EventDAOImpl.class);

    private final static String INSERT_INTO_EVENTS =
            "insert into events(date_time, description) value(?, ?);";
    private final static String UPDATE_EVENT =
            "update events set date_time=?, description=? where id=?;";
    private final static String EVENT_FIND_BY_ID_QUERY =
            "select * from events where id=?;";
    private final static String EVENT_FIND_ALL_QUERY =
            "select * from events;";

    @Override
    public Event save(Event event) {
        if (event.getId() == null) {
            Long newId;
            insertEvent(event);
            newId = RequestUtil.getLastInsertId(connection, "event");
            logger.debug("Event (id = "
                    + newId
                    + ") was created: " + event.getDescription());
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
            statement.setString(2, event.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Create new event: " + e.getMessage());
        }
        logger.debug("New event : " + event.getDescription() + " was successfully created");
    }

    private void updateEvent(Event event) {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             UPDATE_EVENT)) {
            statement.setTimestamp(1, Timestamp.valueOf(event.getDateAndTime()));
            statement.setString(2, event.getDescription());
            statement.setLong(3, event.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Create new event: " + e.getMessage());
        }
        logger.debug("New event : " + event.getDescription() + " was successfully created");
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
            while (rs.next()) {
                event = loadEvent(rs);
            }
        } catch (SQLException e) {
            logger.debug("Get event by id=" + id + " sql exception : " + e.getMessage());
        }
        return Optional.ofNullable(event);
    }

    private Event loadEvent(ResultSet rs) throws SQLException {
        EventMapper eventMapper = new EventMapper();
        return eventMapper.extractFromResultSet(rs);
    }

    @Override
    public List<Event> findAll() {
        List<Event> events = new ArrayList<>();
        logger.info("Try to find all events");
        try (PreparedStatement pst =
                     connection.prepareStatement(
                             EVENT_FIND_ALL_QUERY)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Event event = loadEvent(rs);
                events.add(event);
            }
        } catch (SQLException e) {
            logger.debug("Get all events sql exception : " + e.getMessage());
        }
        return events;
    }
}
