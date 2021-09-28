package com.romanm.pis.repository.impl;

import com.romanm.pis.domain.Event;
import com.romanm.pis.jdbc.ConnectionPool;
import com.romanm.pis.repository.EventRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class EventRepositoryImpl implements EventRepository {

    private static final Logger logger = LogManager.getLogger(EventRepositoryImpl.class);

    private ConnectionPool connectionPool;

    public EventRepositoryImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Event save(Event event) {
        return null;
    }

    private Long selectUserIdByQuery(String query) throws Exception {
        Connection connection = connectionPool.getResource();
        try(Statement stmt = connection.createStatement()) {
            try (ResultSet resultSet = stmt.executeQuery(query)) {
                if (resultSet.next()) {
                    return resultSet.getLong("id");
                } else {
                    throw new Exception("Inserted element not found.");
                }
            }
        }
    }

    private void executeUpdate(Connection connection, String query, Object... params) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i=0; i < params.length; i++) {
                checkParamTypeAndSet(preparedStatement, params[i], i+1);
            }
            int row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkParamTypeAndSet(PreparedStatement preparedStatement, Object param, int index) throws SQLException {
        if (param instanceof String) {
            preparedStatement.setString(index, (String) param);
        } else if(param instanceof Long) {
            preparedStatement.setLong(index, (Long) param);
        } else if (param instanceof Boolean) {
            preparedStatement.setBoolean(index, (Boolean) param);
        }
    }

    @Override
    public Optional<Event> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Event> findAll() {
        return null;
    }
}
