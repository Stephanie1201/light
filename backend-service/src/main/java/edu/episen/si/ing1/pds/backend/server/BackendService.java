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
        int maxConnectionV = 5;
        boolean inTestMode = false;
        boolean inTestModeT = false;
        if (commandLine.hasOption("maxConnection")) {

            maxConnectionV = Integer.parseInt(commandLine.getOptionValue("maxConnection"));
        }

        if (commandLine.hasOption("testMode")) {
            inTestMode = true;
            ServerConfig serverConfig=new ServerConfig();//Read the yaml file server for the configuration


            DataSource ds = DataSource.getInstance();//For initialisation of the connexion pool
            ds.startPool(maxConnectionV);
            ServerCore serverCore =new ServerCore(serverConfig,ds); //Create the serverSocket
            serverCore.serve(); // manage the client connexion
            ds.closePool();
        }


        serverLogger.info("yes! BackendService is running (testMode={}), (testModeT={}), (maxConnection={}). ", inTestMode, inTestModeT, maxConnectionV);

    }
}

