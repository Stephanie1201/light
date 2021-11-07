package edu.episen.si.ing1.pds.client.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.episen.si.ing1.pds.client.configuration.ClientConfig;
import edu.episen.si.ing1.pds.client.view.HomePageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;


public class SocketUtility {
    private Socket socket;
    private final ObjectMapper mapper = new ObjectMapper();
    private ClientConfig clientConfig;
    private static final Logger logger = LoggerFactory.getLogger(SocketUtility.class.getName());

    public SocketUtility() {
        try {
            clientConfig = new ClientConfig();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ResponseSocket sendRequest(RequestSocket request) {
        try {
            socket = new Socket(clientConfig.getConfig().getIpAddress(), clientConfig.getConfig().getListenPort());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseSocket responseSocket = null;
        try {
            String requestStr = mapper.writeValueAsString(request);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(requestStr);
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(in);

            String msg = reader.readLine();
            Thread.sleep(2);
            ResponseSocket responseS = mapper.readValue(msg, ResponseSocket.class);
            if (responseS.getRequest().equals("empty_pool")) {
                throw new IllegalAccessException(responseS.getData().toString());
            }
           logger.info("DATA {} " + responseS.getData());
            responseSocket = responseS;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseSocket;
    }

    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
