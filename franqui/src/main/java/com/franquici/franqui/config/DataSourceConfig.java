package com.franquici.franqui.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

import javax.sql.DataSource;
import java.io.File;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;

@Configuration
public class DataSourceConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    private Environment env;
    
    @Bean
    @Primary
    public DataSource dataSource(DataSourceProperties properties) {
        StandardEnvironment environment = (StandardEnvironment) env;
        
        // Primer intento: perfil de producción (Oracle Cloud)
        if (tryConnectWithProfile(properties, "prod")) {
            return properties.initializeDataSourceBuilder().build();
        }
        
        // Segundo intento: perfil de desarrollo (MySQL)
        if (tryConnectWithProfile(properties, "desarrollo")) {
            return properties.initializeDataSourceBuilder().build();
        }
        
        // Tercer intento: perfil de test (H2)
        if (tryConnectWithProfile(properties, "testeo")) {
            return properties.initializeDataSourceBuilder().build();
        }
        
        // Si todos fallan, devuelve una configuración H2 en memoria por defecto
        logger.info("Todos los perfiles de conexión fallaron. Usando H2 en memoria como último recurso.");
        properties.setUrl("jdbc:h2:mem:franquiciadb;DB_CLOSE_ON_EXIT=FALSE");
        properties.setUsername("sa");
        properties.setPassword("");
        properties.setDriverClassName("org.h2.Driver");
        
        return properties.initializeDataSourceBuilder().build();
    }
    
    /**
     * Intenta realizar una conexión usando un perfil específico.
     * @param properties Propiedades de la fuente de datos
     * @param profile Perfil a utilizar
     * @return true si la conexión fue exitosa, false en caso contrario
     */
    private boolean tryConnectWithProfile(DataSourceProperties properties, String profile) {
        StandardEnvironment environment = (StandardEnvironment) env;
        String[] originalProfiles = environment.getActiveProfiles();
        
        try {
            // Activar el perfil específico
            logger.info("Intentando conectar con el perfil: " + profile);
            environment.setActiveProfiles(profile);
            environment.setDefaultProfiles(profile);
            
            // Establecer propiedades según el perfil actual
            String url = env.getProperty("spring.datasource.url");
            String username = env.getProperty("spring.datasource.username");
            String password = env.getProperty("spring.datasource.password");
            String driverClassName = env.getProperty("spring.datasource.driver-class-name");
            
            logger.info("URL de conexión: " + url);
            
            // Si es Oracle, verificar el directorio del wallet si es necesario
            if (driverClassName != null && driverClassName.contains("oracle")) {
                if (url.contains("TNS_ADMIN")) {
                    String walletPathFromURL = url.substring(url.indexOf("TNS_ADMIN=") + 10);
                    if (walletPathFromURL.contains("?")) {
                        walletPathFromURL = walletPathFromURL.substring(0, walletPathFromURL.indexOf("?"));
                    }
                    
                    // Verificar que el directorio existe
                    File walletDir = new File(walletPathFromURL);
                    if (!walletDir.exists() || !walletDir.isDirectory()) {
                        logger.error("El directorio de wallet no existe en: " + walletPathFromURL);
                        return false;
                    }
                }
            }
            
            // Establecer las propiedades
            properties.setUrl(url);
            properties.setUsername(username);
            properties.setPassword(password);
            properties.setDriverClassName(driverClassName);
            
            // Intentar obtener una conexión
            DataSource dataSource = properties.initializeDataSourceBuilder().build();
            dataSource.getConnection().close();
            
            logger.info("Conexión exitosa con el perfil: " + profile);
            return true;
        } catch (Exception e) {
            logger.error("Error conectando con el perfil " + profile + ": " + e.getMessage());
            return false;
        } finally {
            // Restaurar los perfiles originales
            environment.setActiveProfiles(originalProfiles);
        }
    }
}