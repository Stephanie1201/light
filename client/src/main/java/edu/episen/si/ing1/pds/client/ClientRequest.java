package edu.episen.si.ing1.pds.client;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.episen.si.ing1.pds.client.configuration.ClientConfig;
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
        clientSocket = new Socket(config.getConfig().getIpAddress(), config.getConfig().getListenPort());
        clientDataFileLocation = System.getenv(clientDataEnvVar);
    }

    public void startConnection() throws IOException, ClassNotFoundException, InterruptedException{
        outputStream = clientSocket.getOutputStream();
        inputStream = clientSocket.getInputStream();
        byte[] inputData;
        final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        Student mapJsonFile = mapper.readValue(new File(clientDataFileLocation), Student.class);

        outputStream.write(mapper.writeValueAsBytes(mapJsonFile));
        logger.info(("Request submitted"));
        while (inputStream.available() == 0){}
        inputData = new byte[inputStream.available()];
        inputStream.read(inputData);
        String serverResponse = new String(inputData);
        System.out.println(formatString(serverResponse));
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
