package edu.episen.si.ing1.pds.client.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.Socket;


public class SocketUtility {
    private final static Socket socket = SocketFactory.Instance.getSocket();
    private final ObjectMapper mapper = new ObjectMapper();


    public ResponseSocket sendRequest(RequestSocket request) {
        ResponseSocket responseSocket = null;
        try {
            String requestStr = mapper.writeValueAsString(request);

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.println(requestStr);

            String msg;

            while ((msg = reader.readLine()) != null){
              //  Thread.sleep(15000);
                ResponseSocket responseS = mapper.readValue(msg, ResponseSocket.class);
                if(responseS.getRequest().equals("empty_pool")) {
                    throw new IllegalAccessException(responseS.getData().toString());
                }
                System.out.println("DATA: " + responseS.getData());
                responseSocket = responseS;
                break;
            }




        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseSocket;
    }

    public void closeSocket(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
