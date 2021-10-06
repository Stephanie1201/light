
package edu.episen.si.ing1.pds.backend.server;

import edu.episen.si.ing1.pds.backend.server.configuration.ServerConfig;
import edu.episen.si.ing1.pds.backend.server.pool.DataSource;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Dans cette class on cree des options, qui prennent en argument serverMode
public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class.getName()); // we don't use classic logger
    public static ServerConfig serverConfig;
    public ServerCore serverCore;

    public static void main(String[] args) throws Exception {
        final Options options = new Options();
        final Option serverMode = Option.builder().longOpt("serverMode").build();
        final Option clientMode = Option.builder().longOpt("clientMode").build();
        options.addOption(serverMode);
        options.addOption(clientMode);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine commandLine = parser.parse(options, args);

        serverConfig = new ServerConfig();
        //commandLine c'est la commande en ligne de commande
        if (commandLine.hasOption("serverMode")){
            //datasource est connecter a jdbcconnection qui se connect à la base de donnée
            //et getInstance permet de creer une instance de type datasource
            DataSource dataSource = DataSource.getInstance();
            logger.info("Server mode.");

            new ServerCore(serverConfig, dataSource).serve();

            dataSource.closePool();
        }
        else if (commandLine.hasOption("clientMode")){
            logger.debug("Client mode.");
        }


    }
}
