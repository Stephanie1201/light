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
                logger.info("Waiting for new client");
                Socket client = serverSocket.accept();
                logger.info("new client connected");

             ClientRequestManager clientSock = new ClientRequestManager(client, dataSource.receiveConnection());
             clientSock.start();//C'est un thread qui va traiter la requête du client en même temps que le main thread :
                                // le serveur accueille les clients en même temps et si clientSock n'était pas thread
                                //  le server allait traiter le client un par un ce qui est mauvais parce que tu laisses
                                // les autres clients attendre
            }
        } catch (SocketException e) {
            logger.info(" A client has disconnected");

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
