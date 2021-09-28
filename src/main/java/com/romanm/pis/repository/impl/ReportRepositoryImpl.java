package com.romanm.pis.repository.impl;

import com.romanm.pis.domain.Report;
import com.romanm.pis.jdbc.ConnectionPool;
import com.romanm.pis.jdbc.JdbcConnectionOptions;
import com.romanm.pis.repository.ReportRepository;

import java.util.List;
import java.util.Optional;

public class ReportRepositoryImpl implements ReportRepository {

    private ConnectionPool connectionPool;
    private JdbcConnectionOptions jdbcConnectionOptions;

    public ReportRepositoryImpl(ConnectionPool connectionPool, JdbcConnectionOptions jdbcConnectionOptions) {
        this.connectionPool = connectionPool;
        this.jdbcConnectionOptions = jdbcConnectionOptions;
    }

    @Override
    public Report save(Report report) {
        return null;
    }

    @Override
    public Optional<Report> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Report> findAll() {
        return null;
    }
}
