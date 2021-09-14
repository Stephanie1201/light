package edu.episen.si.ing1.pds.client.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketUtility {
    private Socket socket = SocketFactory.Instance.getSocket();
    private final ObjectMapper mapper = new ObjectMapper();

    public ResponseSocket sendRequest(RequestSocket request) {
        ResponseSocket responseSocket = null;

        try{
            String requestString  = mapper.writeValueAsString(request);

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter.println(requestString);

            String message = bufferedReader.readLine();
            ResponseSocket socketResponse = mapper.readValue(message, ResponseSocket.class);

            if(socketResponse.getRequest().equals("empty_pool")){
                throw new IllegalAccessException(socketResponse.getData().toString());

            }
            responseSocket = socketResponse;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
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
