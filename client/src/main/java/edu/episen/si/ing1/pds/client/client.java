package edu.episen.si.ing1.pds.client;

import edu.episen.si.ing1.pds.client.configuration.ClientConfig;
import edu.episen.si.ing1.pds.client.view.HomePageView;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class client {
    private static final Logger clientLogger = LoggerFactory.getLogger(client.class.getName()); // we don't use classic logger
    public static ClientConfig clientConfig;

    public static void main(String[] args) throws Exception{
        final Options options = new Options();
        final Option testMode = Option.builder().longOpt("testMode").build();
        final Option clientMode = Option.builder().longOpt("clientMode").build();
        final Option maxConnection = Option.builder().longOpt("maxConnection").hasArg().argName("maxConnection").build();
        final Option requestName = Option.builder().longOpt("request").hasArg().argName("request").build();
        int maxConnectionV = 5;
        options.addOption(requestName);
        options.addOption(testMode);
        options.addOption(clientMode);
        options.addOption(maxConnection);

        final CommandLineParser parser = new DefaultParser();
        final CommandLine commandLine = parser.parse(options, args);

        clientConfig = new ClientConfig();
        boolean inTestMode = false;
        if (commandLine.hasOption("testMode")) {
            ClientRequest clientRequest = new ClientRequest(clientConfig);
            clientRequest.startConnection((String)commandLine.getOptionValue("request"));//Donner a la méthode la valeur de ta requête
           //clientRequest.stopConnection();
        }
        if (commandLine.hasOption("maxConnection")){
            maxConnectionV = Integer.parseInt(commandLine.getOptionValue("maxConnection"));
        }

        if (commandLine.hasOption("clientMode")){
            HomePageView hm = new HomePageView();
            hm.setVisible(true);
        }
        clientLogger.info("Client is running (testMode={}).",inTestMode);

    }
}
