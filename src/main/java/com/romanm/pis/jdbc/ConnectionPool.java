package com.romanm.pis.jdbc;

import java.sql.Connection;

public interface ConnectionPool {

    /**
     * Initialize pool resources
     * @param maxActive Maximum number of active connections in the pool
     * @param maxWait Maximum waiting time
     */
    void init(Integer maxActive, Long maxWait);

    /**
     * Get resources from pool
     * @return Link resources
     */
    Connection getResource() throws Exception;

    /**
     * Release connection
     * @param connection Connection in use
     */
    void release(Connection connection) throws Exception;

    /**
     * Release connection pool resources
     */
    void close();
}
