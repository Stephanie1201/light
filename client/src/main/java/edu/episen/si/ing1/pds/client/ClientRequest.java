package edu.episen.si.ing1.pds.client;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.episen.si.ing1.pds.client.configuration.ClientConfig;
import edu.episen.si.ing1.pds.client.socket.RequestSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientRequest {
    private Socket clientSocket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private static final Logger logger = LoggerFactory.getLogger(ClientRequest.class.getName());

    private static String clientDataFileLocation;
    private static String clientDataEnvVar = "CLIENT_DATA_JSON";

    public ClientRequest(final ClientConfig config) throws IOException{
        clientSocket = new Socket("localhost",  config.getConfig().getListenPort());
        //clientSocket = new Socket(config.getConfig().getIpAddress(), config.getConfig().getListenPort());
        clientDataFileLocation = System.getenv(clientDataEnvVar);
    }

    public void startConnection(String requestName) throws IOException, ClassNotFoundException, InterruptedException {
        try{
        outputStream = clientSocket.getOutputStream();
        inputStream = clientSocket.getInputStream();
        byte[] inputData;

        final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        String path= clientDataFileLocation + "/"+requestName+".json";
        RequestSocket mapJsonFile = mapper.readValue(new File(path), RequestSocket.class);
        outputStream.write(mapper.writeValueAsBytes(mapJsonFile)); //envoie au format json
        outputStream.flush();//assure l'envoie
           // outputStream.close();
        logger.info(mapJsonFile.toString());
        logger.info(("Request submitted"));

        while (inputStream.available() == 0) {

        }
        inputData = new byte[inputStream.available()];
        inputStream.read(inputData);
        String serverResponse = new String(inputData);
        logger.info(formatString(serverResponse));
    }catch(Exception ex)
    {
        ex.printStackTrace();
    }
    }
    public void stopConnection() throws IOException {
        clientSocket.close();
        outputStream.close();
        inputStream.close();
    }
    public String formatString(String s){
        return s.replace('.','\n');
    }


}
