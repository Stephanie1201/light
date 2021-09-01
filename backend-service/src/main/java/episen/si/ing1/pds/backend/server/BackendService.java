package episen.si.ing1.pds.backend.server;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackendService {
    private static final Logger serverLogger = LoggerFactory.getLogger(BackendService.class.getName());

    public static void main(String[] args) throws ParseException {
        serverLogger.info("great : BackendService is running");
        final Options options = new Options(); // create options
        final Option testMode = Option.builder().longOpt("testMode").build(); // use longOpt to write --testMode in cmd
        final Option maxConnection = Option.builder().longOpt("maxConnection").hasArg().argName("maxConnection").build();
        options.addOption(maxConnection);
        options.addOption(testMode);

        final CommandLineParser parser = new DefaultParser();
        final CommandLine commandLine = parser.parse(options, args);

        int maxConnectionValue = 15; //default, should be set in a property files
        boolean inTestMode = false;
        if (commandLine.hasOption("testMode")) {
            inTestMode = true;
        }
        if (commandLine.hasOption("maxConnection")) {
            maxConnectionValue = Integer.parseInt(commandLine.getOptionValue("maxConnection"));
        }


        serverLogger.info("BackendService is running (testMode={}), (maxConnection={}). ", inTestMode,maxConnectionValue); // permet de changer le comportement des arguments du service
    }

}
