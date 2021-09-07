package edu.episen.si.ing1.pds.backend.server.configuration;

//i need to see the contenent of EPISEN_SRV_CONFIG
public class ServerCoreConfig {
    private  int listenPort;
    private int soTimeout;

    public ServerCoreConfig(){ }

    public int getListenPort() { return listenPort; }

    public int getSoTimeout() { return soTimeout; }

    public void setListenPort(int listenPort) {
        this.listenPort = listenPort;
    }

    public void setSoTimeout(int soTimeout) { this.soTimeout = soTimeout; }

    @Override
    public String toString() {
        return "ServerCoreConfig{" + "listenPort=" + listenPort + ", soTimeout=" + soTimeout + '}';
    }
}
