package edu.episen.si.ing1.pds.client.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.episen.si.ing1.pds.client.configuration.ClientConfig;

import java.io.*;
import java.net.Socket;


public class SocketUtility {
    private Socket socket;
    private final ObjectMapper mapper = new ObjectMapper();
    private ClientConfig clientConfig;
    public SocketUtility(){
        try{
            clientConfig = new ClientConfig();
        }

        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public ResponseSocket sendRequest(RequestSocket request){
        try {
            socket = new Socket(clientConfig.getConfig().getIpAddress(), clientConfig.getConfig().getListenPort());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("1SU. voici la request de RequestSocket " + request);

        ResponseSocket responseSocket = null;

        System.out.println("2.SU Voici responseSocket initialisé à null " + responseSocket);
        try{
            System.out.println("On entre dans le Try");
            String requestStr = mapper.writeValueAsString(request);
            System.out.println("3.SU Voici requestStr qui prend request en paramettre " + requestStr );

            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            writer.println(requestStr); /*Pas compris*/
            System.out.println("4.SU Voici writer qui prend socket.getOutputStream " + writer.checkError() );

            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(in);

            String msg = reader.readLine();

            System.out.println("5.SU Voici msg qui prend  " + msg);
            //while ((msg != null)) {
            Thread.sleep(2);
            ResponseSocket responseS = mapper.readValue(msg, ResponseSocket.class);
            System.out.println("6.SU ResponseSocket " + responseS);
            if (responseS.getRequest().equals("empty_pool")) {
                throw new IllegalAccessException(responseS.getData().toString());
            }
            System.out.println("DATA: " + responseS.getData());

            /*Pourquoi on initialise à null, pour returner le null la après ?*/
            responseSocket = responseS;
            //break;
            //}

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Mtest " );
        }

        System.out.println("responsesockettttttt " + responseSocket);

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
