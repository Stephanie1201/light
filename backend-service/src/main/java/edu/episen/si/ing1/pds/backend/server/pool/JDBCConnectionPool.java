package edu.episen.si.ing1.pds.backend.server.pool;

import edu.episen.si.ing1.pds.backend.server.configuration.DatabaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

//this class represent all of physical connexion
public class JDBCConnectionPool {
    private ArrayList<Connection> physicalConnections;
    private String driverName;
    private String dataBaseUrl;
    private String user;
    private String passWord;
    private int maxConnection;
    private static boolean isInit = false;
    private   DatabaseConfig databaseConfig;
    private static final Logger logger = LoggerFactory.getLogger(JDBCConnectionPool.class.getName());

    //this methode use propertiesReader to take the DB parameter
    public JDBCConnectionPool() {

        try {
            databaseConfig = new DatabaseConfig();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Que fais ces instructions
         driverName = databaseConfig.getConfig().getDRIVER_NAME();
         dataBaseUrl = databaseConfig.getConfig().getDATABASE_URL();
         user = databaseConfig.getConfig().getUSER();
         passWord = databaseConfig.getConfig().getPASSWORD();
         maxConnection = databaseConfig.getConfig().getMAX_CONNECTION();
         physicalConnections = new ArrayList<>();

    }
public void init(){
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i=0; i< maxConnection; i++){
            Connection connection = null;
            try{
                connection = DriverManager.getConnection(dataBaseUrl,user,passWord);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            physicalConnections.add(connection);
        }
}
public Connection getConnection(){
        return physicalConnections.remove(0);
}
//Close evry connection of the connections list

public void closeConnection(){
        for(Connection connect : physicalConnections){
            try{
                connect.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
}
// connection that the client used

public boolean addConnection(Connection connection){
        return physicalConnections.add(connection);
}

// return the number of connections which rest
public int getSizeArrayConnection(){
        return physicalConnections.size();
    }
//return true if there is no more connection in the pool
    public boolean isEmpty(){
        // isEmty= est vide
        return physicalConnections.size() == 0;
    }
}
