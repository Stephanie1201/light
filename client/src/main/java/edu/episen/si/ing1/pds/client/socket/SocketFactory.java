package edu.episen.si.ing1.pds.client.socket;

import edu.episen.si.ing1.pds.client.configuration.ClientConfig;


import java.net.Socket;

public enum SocketFactory {
    Instance;
    private Socket socket;
// This class create socket
    SocketFactory() {
        try {
            final ClientConfig clientConfig = new ClientConfig();
            socket = new Socket(clientConfig.getConfig().getIpAddress(), clientConfig.getConfig().getListenPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
