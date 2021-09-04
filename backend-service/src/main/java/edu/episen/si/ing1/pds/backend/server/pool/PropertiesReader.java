package edu.episen.si.ing1.pds.backend.server.pool;

import java.io.IOException;
import java.util.Properties;
/*
This enum is connect with the Connection.properties
it collect the informations as
        DRIVER_NAME = org.postgresql.Driver
        DATABASE_URL = jdbc:postgresql://172.31.249.106:5432/dblight
        USER = postgres
        PASSWORD = lightpds
And after the JDBCConnectionPool use that


A Java Enum is a special Java type used to define collections of constants
 /**
 * There is no need to read many times Connection.properties file.
 */


public enum PropertiesReader {
    instance;
    String DRIVERNAME;
    String DATABASEURL;
    String USER;
    String PASSWORD;

    PropertiesReader(){

        Properties properties = new Properties();
        try{
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("Connection.properties"));
            DRIVERNAME = properties.getProperty("DRIVER-NAME"); /* GetProperty(String) = Recherche la propriété spécifiée dont les paramètres correspondent aux types d’arguments et aux modificateurs spécifiés, en utilisant les contraintes de liaison indiquées.*/
            DATABASEURL= properties.getProperty("DATABASE-URL");
            USER = properties.getProperty("USER");
            PASSWORD = properties.getProperty("PASSWORD");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
