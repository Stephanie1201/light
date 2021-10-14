package edu.episen.si.ing1.pds.client;

import ch.qos.logback.core.net.server.Client;
import edu.episen.si.ing1.pds.client.configuration.ClientConfig;
import edu.episen.si.ing1.pds.client.view.HomePageView;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class client {
    private static final Logger clientLogger = LoggerFactory.getLogger(client.class.getName()); // we don't use classic logger

 public static ClientConfig clientConfig;
    public static void main(String[] args) throws Exception{
        final Options options = new Options();
        final Option testMode = Option.builder().longOpt("testMode").build();
        final Option clientMode = Option.builder().longOpt("clientMode").build();
        final Option requestName = Option.builder().longOpt("request").hasArg().argName("request").build();
        final Option testMode2 = Option.builder().longOpt("testMode2").build();
        final Option numberClients = Option.builder().longOpt("numberClient").hasArg().argName("numberClient").build();

        options.addOption(requestName);
        options.addOption(testMode);
        options.addOption(clientMode);
        options.addOption(testMode2);
        options.addOption(numberClients);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine commandLine = parser.parse(options, args);

        clientConfig = new ClientConfig();
        boolean inTestMode = false;
        if (commandLine.hasOption("testMode")) {
            ClientRequest clientRequest = new ClientRequest(clientConfig);
            clientRequest.startConnection((String)commandLine.getOptionValue("request"));//give to the methode the value of request
           //clientRequest.stopConnection();
        }
        if (commandLine.hasOption("testMode2") && commandLine.hasOption("numberClient")) {
            ArrayList<Thread> clientThreads=new ArrayList<>();
            int numberClientValue = Integer.parseInt(commandLine.getOptionValue("numberClient"));

            for(int i=1;i<=numberClientValue;i++){
                ClientThread client=new ClientThread(clientConfig,"Client "+i); //Je crée les clients et les rajoute dans la array liste
                clientThreads.add(client);
            }
            //Une fois tous les clients rajoutés, je les demarre tous
            for (Thread client: clientThreads
                 ) {
                client.start();

            }
        }

        if (commandLine.hasOption("clientMode")){
            HomePageView hm = new HomePageView();
            hm.setVisible(true);
        }




        //clientLogger.info("Client is running (testMode={}).",inTestMode);

    }
}
