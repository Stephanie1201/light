package edu.episen.si.ing1.pds.backend.server;

import edu.episen.si.ing1.pds.backend.server.configuration.ServerConfig;
import edu.episen.si.ing1.pds.backend.server.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerCore {
    private static final Logger logger = LoggerFactory.getLogger(ServerCore.class.getName());
    private ServerSocket serverSocket;
    private DataSource dataSource;

    public ServerCore(final ServerConfig config, DataSource dataSource) throws IOException{
        serverSocket = new ServerSocket(config.getConfig().getListenPort());
        this.dataSource = dataSource;
    }

    public void serve(){
        try{
            serverSocket.setReuseAddress(true);
            while (true){
                Socket client = serverSocket.accept();
                logger.info("new client connected");

                ClientRequestManager clientSock = new ClientRequestManager(client, dataSource.receiveConnection());

                new Thread((Runnable) clientSock).start();//comment because of clientSocket
            }
        } catch (SocketException e) {
            logger.info(" A client has disconnected");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                    DataSource.getInstance().closePool();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
