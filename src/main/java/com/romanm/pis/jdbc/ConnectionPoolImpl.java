package com.romanm.pis.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolImpl implements ConnectionPool {
    //Maximum active connections
    private Integer maxActive;
    //Maximum waiting time
    private Long maxWait;
    //Free Queue
    private final LinkedBlockingQueue<Connection> idle = new LinkedBlockingQueue<>();
    //Busy queue
    private final LinkedBlockingQueue<Connection> busy = new LinkedBlockingQueue<>();
    //Number of connection pool active connections
    private final AtomicInteger activeSize = new AtomicInteger(0);
    //Connection pool close flag
    private final AtomicBoolean isClosed = new AtomicBoolean(false);
    //Total connections obtained
    private final AtomicInteger createCount = new AtomicInteger(0);
    //Counters waiting for zookeeper client creation to complete
    private static final ThreadLocal<CountDownLatch> latchThreadLocal = ThreadLocal.withInitial(() -> new CountDownLatch(1));

    public ConnectionPoolImpl(Integer maxActive, Long maxWait) {
        this.init(maxActive, maxWait);
    }

    @Override
    public void init(Integer maxActive, Long maxWait) {
        this.maxActive = maxActive;
        this.maxWait = maxWait;
    }

    @Override
    public Connection getResource() throws Exception {
        Connection connection;
        long nowTime = System.currentTimeMillis();
        final CountDownLatch countDownLatch = latchThreadLocal.get();

        //Whether idle queue idle has connection
        if ((connection = idle.poll()) == null) {
            //Determine whether the number of connections in the pool is less than maxActive
            if (activeSize.get() < maxActive) {
                //First increase the number of connections in the pool, and then judge whether it is less than or equal to maxActive
                if (activeSize.incrementAndGet() <= maxActive) {
                    //Create a zookeeper connection
                    //connection = new Connection("localhost", 5000, );
                    countDownLatch.await();
                    System.out.println("Thread:" + Thread.currentThread().getId() + "Get connection:" + createCount.incrementAndGet() + "strip");
                    busy.offer(connection);
                    return connection;
                } else {
                    //If it is found that it is greater than maxActive after increase, subtract the increased
                    activeSize.decrementAndGet();
                }
            }
            //Wait for busy queue to release connection if active thread is full
            try {
                System.out.println("Thread:" + Thread.currentThread().getId() + "Waiting for idle resources");
                long waitTime = maxWait - (System.currentTimeMillis() - nowTime);
                connection = idle.poll(waitTime, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new Exception("Waiting exception");
            }
            //Judge whether it is timeout
            if (connection != null) {
                System.out.println("Thread:" + Thread.currentThread().getId() + "Get connection:" + createCount.incrementAndGet() + "strip");
                busy.offer(connection);
                return connection;
            } else {
                System.out.println("Thread:" + Thread.currentThread().getId() + "Get connection timeout, please try again!");
                throw new Exception("Thread:" + Thread.currentThread().getId() + "Get connection timeout, please try again!");
            }
        }
        //The idle queue has a connection and returns directly
        busy.offer(connection);
        return connection;
    }

    @Override
    public void release(Connection connection) throws Exception {
        if (connection == null) {
            System.out.println("connection Empty");
            return;
        }
        if (busy.remove(connection)){
            idle.offer(connection);
        } else {
            activeSize.decrementAndGet();
            throw new Exception("Release failure");
        }
    }

    @Override
    public void close() {
        if (isClosed.compareAndSet(false, true)) {
            idle.forEach((connection) -> {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            busy.forEach((connection) -> {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
