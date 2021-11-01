package com.romanm.pis.dao.mapper;

import com.romanm.pis.domain.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventMapper {
    public Event extractEventFromResultSet(ResultSet resultSet) throws SQLException {
        Event event = new Event();
        while (resultSet.next()) {
            event.setId(resultSet.getLong("id"));
            event.setShortDescription(resultSet.getString("short_description"));
            event.setLongDescription(resultSet.getString("long_description"));
            event.setDateAndTime(resultSet.getTimestamp("date_time").toLocalDateTime());
        }
        return event;
    }

    public List<Event> extractEventsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Event> events = new ArrayList<>();
        Event event;
        while (resultSet.next()) {
            event = new Event();
            event.setId(resultSet.getLong("id"));
            event.setShortDescription(resultSet.getString("short_description"));
            event.setLongDescription(resultSet.getString("long_description"));
            event.setDateAndTime(resultSet.getTimestamp("date_time").toLocalDateTime());

            events.add(event);
        }
        return events;
    }
}
