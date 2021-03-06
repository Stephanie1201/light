package edu.episen.si.ing1.pds.client.configuration;

public class ClientCoreConfig {
    private int listenPort;
    private String ipAddress;

    //configuration of listenPort and ip adress for client
    public ClientCoreConfig() {
    }

    public int getListenPort() {
        return listenPort;
    }

    public void setListenPort(int listenPort) {
        this.listenPort = listenPort;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    //print of the port an ipadress
    @Override
    public String toString() {
        return "ClientCoreConfig{" +
                "listenPort=" + listenPort +
                ", ipAddress=" + ipAddress +
                '}';
    }
}

