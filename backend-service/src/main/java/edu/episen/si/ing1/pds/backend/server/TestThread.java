package edu.episen.si.ing1.pds.backend.server;

import edu.episen.si.ing1.pds.backend.server.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class TestThread {
    private static final Logger threadLogger = LoggerFactory.getLogger(TestThread.class.getName());

    public void testThread() {
        DataSource dataSource = DataSource.getInstance();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection connection = dataSource.receiveConnection();
                long currenttime = System.currentTimeMillis();
                while (System.currentTimeMillis() - currenttime < 8000) {
                }
                dataSource.putConnection(connection);
                threadLogger.info("Number of connection in the pool after Thread1 reques is : " + dataSource.getNumberConnection());

            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection connection = dataSource.receiveConnection();
                long currenttime = System.currentTimeMillis();
                while (System.currentTimeMillis() - currenttime < 8000) {
                }
                dataSource.putConnection(connection);
                threadLogger.info("Number of connection in the pool after Thread2 reques is : " + dataSource.getNumberConnection());

            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection connection = dataSource.receiveConnection();
                long currenttime = System.currentTimeMillis();
                while (System.currentTimeMillis() - currenttime < 8000) {
                }
                dataSource.putConnection(connection);
                threadLogger.info("Number of connection in the pool after Thread3 reques is : " + dataSource.getNumberConnection());

            }
        });

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection connection = dataSource.receiveConnection();
                long currenttime = System.currentTimeMillis();
                while (System.currentTimeMillis() - currenttime < 8000) {
                }
                dataSource.putConnection(connection);
                threadLogger.info("Number of connection in the pool after Thread4 reques is : " + dataSource.getNumberConnection());

            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadLogger.info("Number of connection in the pool after 4 connection requests " + dataSource.getNumberConnection() );
        dataSource.closePool();
    }
}
