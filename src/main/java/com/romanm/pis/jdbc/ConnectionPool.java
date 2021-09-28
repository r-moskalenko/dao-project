package com.romanm.pis.jdbc;

import java.sql.Connection;

public interface ConnectionPool {
    /**
     * Get resources from pool
     * @return Link resources
     */
    Connection getResource() throws Exception;
}
