package edu.episen.si.ing1.pds.backend.server.configuration;

//i need to see the contenent of EPISEN_SRV_CONFIG
public class ServerCoreConfig {
    private  int listenPort;
    private int soTimeOut;

    public ServerCoreConfig(){ }

    public int getListenPort() { return listenPort; }

    public int getSoTimeout() { return soTimeOut; }

    public void setListenPort(int listenPort) {
        this.listenPort = listenPort;
    }

    public void setSoTimeout(int soTimeOut) { this.soTimeOut = soTimeOut; }

    @Override
    public String toString() {
        return "ServerCoreConfig{" + "listenPort=" + listenPort + ", soTimeOut=" + soTimeOut + '}';
    }
}
