package edu.episen.si.ing1.pds.backend.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.episen.si.ing1.pds.backend.server.pool.DataSource;
import edu.episen.si.ing1.pds.backend.server.socket.ResquestSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ClientRequestManager {
    private static final Logger logger = LoggerFactory.getLogger(ClientRequestManager.class.getName());
    private final static String name = "client-thread-manager";
    private Connection connection;
    private Socket clientSocket;

    public ClientRequestManager(Socket socket, Connection connection) throws IOException{
        this.connection = connection;
        clientSocket = socket;
    }


    public void run(){

        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;

        try{
            // get the outputstream of client (Obtenir la flux de sortie)
            printWriter = new PrintWriter(clientSocket.getOutputStream(),true);

        // get the inputstream of client
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ObjectMapper mapper = new ObjectMapper();

            String line;
            while ((line = bufferedReader.readLine()) != null) { //the line read the inputStream of client
                ResquestSocket resquestSocket = mapper.convertValue(line, ResquestSocket.class);

                if(connection != null)
                    RequestHandler.sendResponse(resquestSocket, printWriter,connection);

                else {
                    System.out.println("DataSource : " + DataSource.getInstance().getNumberConnection());
                    handleReachedLimitPool(printWriter);
                }
            }


        } catch (IOException e) {
            logger.error("client has been disconnected");
            logger.error (e.getLocalizedMessage (), e);

        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * if the client has finished with the connection, we put back the connection in the pool
         */
        finally {
            if (connection != null){
                DataSource.getInstance().putConnection(connection);
                logger.info("Nomber of connection after closing client : " + DataSource.getInstance().getNumberConnection());
            }
            try{
                if (printWriter != null){
                    printWriter.close();
                }
                if(bufferedReader!= null){
                    bufferedReader.close();
                    clientSocket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
 private void handleReachedLimitPool(PrintWriter writer) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();

        Map<String,Object> response = new HashMap<>();
        response.put("request", "empty_pool"); // emty (vide)
        response.put("data", "there is no mire connection in the pool. Retry later ");

        String errorMessage = mapper.writeValueAsString(response);
        writer.println(errorMessage);
 }
}
