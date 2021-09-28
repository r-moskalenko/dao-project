package com.romanm.pis.repository.impl;

import com.romanm.pis.domain.Event;
import com.romanm.pis.jdbc.ConnectionPool;
import com.romanm.pis.jdbc.JdbcConnectionOptions;
import com.romanm.pis.repository.EventRepository;

import java.util.List;
import java.util.Optional;

public class EventRepositoryImpl implements EventRepository {

    private ConnectionPool connectionPool;
    private JdbcConnectionOptions jdbcConnectionOptions;

    public EventRepositoryImpl(ConnectionPool connectionPool, JdbcConnectionOptions jdbcConnectionOptions) {
        this.connectionPool = connectionPool;
        this.jdbcConnectionOptions = jdbcConnectionOptions;
    }

    @Override
    public Event save(Event event) {
        return null;
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
