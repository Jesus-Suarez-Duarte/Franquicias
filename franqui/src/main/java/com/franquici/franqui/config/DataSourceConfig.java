package com.franquici.franqui.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean
    @Primary
    public DataSource dataSource(DataSourceProperties properties) {
        try {
            DataSource dataSource = properties.initializeDataSourceBuilder().build();
            // Intenta conectarse para verificar si funciona
            dataSource.getConnection().close();
            logger.info("Conexión exitosa a la base de datos MySQL");
            return dataSource;
        } catch (SQLException e) {
            // Verificar si el error es por base de datos inexistente
            if (e.getMessage().contains("Unknown database") || 
                e.getMessage().contains("database doesn't exist") || 
                e.getMessage().contains("Base de datos desconocida")) {
                
                logger.error("=======================================================");
                logger.error("ERROR: La base de datos 'dev_backend' no existe en MySQL");
                logger.error("Por favor, cree la base de datos con el siguiente comando:");
                logger.error("CREATE DATABASE dev_backend;");
                logger.error("=======================================================");
            } else {
                logger.error("Error conectando a MySQL: " + e.getMessage());
            }
            
            logger.info("Cambiando automáticamente a base de datos H2 para desarrollo...");
            
            // Configura propiedades de H2
            properties.setUrl("jdbc:h2:mem:franquiciadb;DB_CLOSE_ON_EXIT=FALSE");
            properties.setUsername("sa");
            properties.setPassword("");
            properties.setDriverClassName("org.h2.Driver");
            
            return properties.initializeDataSourceBuilder().build();
        }
    }
}