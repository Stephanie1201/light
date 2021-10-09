package edu.episen.si.ing1.pds.backend.server.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class ServerConfig {
    private static final Logger logger = LoggerFactory.getLogger(ServerConfig.class.getName());
    private static final String episenServerConfigEnvVar = "EPISEN_SERVER_CONFIG"; //environement variable name
    private final String episenServerConfigFileLocation;
    private ServerCoreConfig config;

    public ServerConfig() throws IOException{
        episenServerConfigFileLocation = System.getenv(episenServerConfigEnvVar);
        logger.info("Config file = {}",episenServerConfigFileLocation);
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        config = mapper.readValue(new File(episenServerConfigFileLocation), ServerCoreConfig.class);
        logger.info("config = {}", config.toString());
    }
    public ServerCoreConfig getConfig()
    {
        return config;
    }

}
