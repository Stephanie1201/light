package edu.episen.si.ing1.pds.backend.server.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;

public class DataSource {
    private static JDBCConnectionPool jdbcConnectionPool;
    private static final Logger logger = LoggerFactory.getLogger(DataSource.class.getName());
    private static DataSource INSTANCE = new DataSource();

//initialisation of jdbcConnectionPool object
public DataSource(){

    }

    public synchronized Connection receiveConnection() {
        Connection connection = null;
        if (jdbcConnectionPool.isEmpty()) { // (if connexion is absent)
            try {
                logger.info("no more connection"); //print this message
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else { //else call the getConnection methode present in jdbcConnectionPool
            connection = jdbcConnectionPool.getConnection();
        }
        return connection;
    }
public synchronized boolean putConnection(Connection connection){
        // this methode notifyAll after add connection to the jdbcConnectionPool
        notifyAll();
        return jdbcConnectionPool.addConnection(connection);
}
public void closePool(){

        jdbcConnectionPool.closeConnection(); //to close the connection
}

public int getNumberConnection(){
        //getSizeArrayConnection return the physicalConnectionSize
        return jdbcConnectionPool.getSizeArrayConnection();
}


public JDBCConnectionPool getJdbcConnectionPool(){

        return jdbcConnectionPool;
}

public static DataSource getInstance(){
        return INSTANCE;
}
public void startPool(int numberConnection){

    jdbcConnectionPool = SingletonPool.Instance.getInstance(numberConnection);
}
}
