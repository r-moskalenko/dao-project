package com.romanm.pis.dao.mapper;

import com.romanm.pis.domain.Event;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper {
    public Event extractFromResultSet(ResultSet resultSet) throws SQLException {
        Event event = new Event();
        while (resultSet.next()) {
            event.setId(resultSet.getLong("id"));
            event.setDescription(resultSet.getString("description"));
            event.setDateAndTime(resultSet.getTimestamp("date_time").toLocalDateTime());
        }
        return event;
    }
}
