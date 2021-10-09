package edu.episen.si.ing1.pds.backend.server;

import edu.episen.si.ing1.pds.backend.server.configuration.ServerConfig;
import edu.episen.si.ing1.pds.backend.server.pool.DataSource;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BackendService {
    private static final Logger serverLogger = LoggerFactory.getLogger(BackendService.class.getName());

    public static void main(String[] args) throws Exception {
        final Options options = new Options(); // create options
        final Option testMode = Option.builder().longOpt("testMode").build(); // use longOpt to write --testMode in cmd
        final Option testModeT = Option.builder().longOpt("testModeT").build();
        final Option maxConnection = Option.builder().longOpt("maxConnection").hasArg().argName("maxConnection").build();
        options.addOption(testMode);  // add the "testMode" option to your options
        options.addOption(maxConnection);
        options.addOption(testModeT);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine commandLine = parser.parse(options, args);
        int maxConnectionV = 10;
        boolean inTestMode = false;
        boolean inTestModeT = false;
        if (commandLine.hasOption("testMode")) {
            inTestMode = true;
            ServerConfig serverConfig=new ServerConfig();//pour lire le fichier yaml du serveur pour la configuration
            DataSource ds = DataSource.getInstance();//pour l'initialisation du pool de connexion
            ServerCore serverCore=new ServerCore(serverConfig,ds); //crée la socket du serveur
            serverCore.serve(); // C'est la classe où le serveur gère la présence des clients
            ds.closePool();
        }
        if (commandLine.hasOption("maxConnection")) {
            maxConnectionV = Integer.parseInt(commandLine.getOptionValue("maxConnection"));
        }

        serverLogger.info("yes! BackendService is running (testMode={}), (testModeT={}), (maxConnection={}). ", inTestMode, inTestModeT, maxConnectionV);

    }
}

