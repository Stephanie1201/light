package edu.episen.si.ing1.pds.backend.server.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

// les configurations de la base de donnée
//C'est ce qui est afficher lorsqu'on lance le serverMode
public class DatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class.getName());

    //ça c'est la variable d'environement qui est sur la VP, ;le fichier properties n'est plus en local mais sur la vm
    private static final String databaseConfigVarEnv = "LIGHTDATABASE_CONF";

    private String dataConfigFileLocation;
    private  DatabaseCoreConfig config;
 //Recupere les informations qui sont dans le fichier yaml présent sur la VM
    public DatabaseConfig() throws IOException{

        dataConfigFileLocation = System.getenv(databaseConfigVarEnv);
        logger.info("Configuration file = {} ", dataConfigFileLocation);
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());//indique juste que le fichier qui est lu est un fichier yml
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        config = mapper.readValue(new File(dataConfigFileLocation),DatabaseCoreConfig.class); //
        logger.info("configuration = {}", config.toString());
    }
    public DatabaseCoreConfig getConfig(){
        return config;
    }
}
