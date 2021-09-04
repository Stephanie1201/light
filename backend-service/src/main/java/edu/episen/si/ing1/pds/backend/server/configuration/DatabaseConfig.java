package edu.episen.si.ing1.pds.backend.server.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class DatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class.getName());
    private static final String databaseConfigVarEnv = "DATABASE-CONFIG";
    private String dataConfigFileLocation;
    private  DatabaseCoreConfig config;

    public DatabaseConfig() throws IOException{
        dataConfigFileLocation = System.getenv(databaseConfigVarEnv);
        logger.info("Configuration file = {} ", dataConfigFileLocation);
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        config = mapper.convertValue(new File(dataConfigFileLocation),DatabaseCoreConfig.class);
        logger.info("configuration = {}", config.toString());
    }
    public DatabaseCoreConfig getConfig(){
        return config;
    }
}
