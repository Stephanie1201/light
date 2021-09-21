package edu.episen.si.ing1.pds.client.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class ClientConfig {
    private static final Logger logger = LoggerFactory.getLogger(ClientConfig.class.getName());
    private static final String episenClientConfigEnVar="CLIENT_CONFIGURATION";
    private String episenClientConfigFileLocation;
    private ClientCoreConfig config;

    //call the variable "CLIENT-CONFIG" present to VM
    public ClientConfig() throws IOException{
        episenClientConfigFileLocation = System.getenv(episenClientConfigEnVar);
        logger.info("Config file = {}", episenClientConfigFileLocation);
        final ObjectMapper mapper = new ObjectMapper((new YAMLFactory()));
        config = mapper.readValue(new File(episenClientConfigFileLocation),ClientCoreConfig.class);
        logger.info("config = {}",config.toString());
    }
    public ClientCoreConfig getConfig(){
        return  config;
    }
}
