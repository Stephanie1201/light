package edu.episen.si.ing1.pds.client;

import edu.episen.si.ing1.pds.client.configuration.ClientConfig;

import java.io.IOException;

public class ClientThread extends Thread{
    public static ClientConfig clientConfig;

    public ClientThread(ClientConfig clientConfig,String name) {
        this.setName(name);//changer le nom du thread en "Client i" tel que i est une variable
        this.clientConfig=clientConfig;
    }


    public void run(){

        try {
            ClientRequest clientRequest = new ClientRequest(clientConfig);
            clientRequest.startConnection("select");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
