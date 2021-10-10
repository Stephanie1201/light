package edu.episen.si.ing1.pds.backend.server.configuration;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;

//this class take the parametter which is present in environnement variable
public class DatabaseCoreConfig {

    private String DRIVER_NAME;
    private String DATABASE_URL;
    private String USER;
    private String PASSWORD;
    private int MAX_CONNECTION;

    public DatabaseCoreConfig() {
    }

    @JsonGetter("DRIVER_NAME")
    public String getDRIVER_NAME() {
        return DRIVER_NAME;
    }

    public void setDRIVER_NAME(String DRIVER_NAME) {
        this.DRIVER_NAME = DRIVER_NAME;
    }

    @JsonGetter("DATABASE_URL")
    public String getDATABASE_URL() {
        return DATABASE_URL;
    }

    public void setDATABASE_URL(String DATABASE_URL) {
        this.DATABASE_URL = DATABASE_URL;
    }

    @JsonGetter("USER")
    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    @JsonGetter("PASSWORD")
    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    @JsonGetter("MAX_CONNECTION")
    public int getMAX_CONNECTION() {
        return MAX_CONNECTION;
    }

    public void setMAX_CONNECTION(int MAX_CONNECTION) {
        this.MAX_CONNECTION = MAX_CONNECTION;
    }

    @Override
    public String toString() {
        return "DatabaseCoreConfig{" +
                "DRIVER_NAME='" + DRIVER_NAME + '\'' +
                ", DATABASE_URL='" + DATABASE_URL + '\'' +
                ", USER='" + USER + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", MAX_CONNECTION=" + MAX_CONNECTION +
                '}';
    }
}
