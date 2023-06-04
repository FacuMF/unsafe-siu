package com.unsafesiu.demo.datasource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgresDatasource {
	
    private Connection postgresConnection;
    
    @Value("${app.datasource.jdbc-url}")
    private String jdbc_url;
    
    @Value("${app.datasource.username}")
    private String username;
    
    @Value("${app.datasource.password}")
    private String password;

    @Bean
    public void Init() throws IOException{
    	try {
    		postgresConnection = DriverManager.getConnection(jdbc_url, username, password);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public Connection getConnection() {
    	return postgresConnection;
    }

    
//    public PostgresDatasource() throws IOException {
//    	
//        Properties props = new Properties();
//        props.load(new FileInputStream(new File("C:\\Users\\DM_E91222\\Documents\\utn\\SeguridadAppWeb\\unsafe-siu\\unsafesiu\\src\\main\\resources\\application.properties")));
//        try {
//            postgresConnection = DriverManager.getConnection(
//                    props.getProperty("app.datasource.jdbc-url"),
//                    props.getProperty("app.datasource.username"),
//                    props.getProperty("app.datasource.password"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
    

    
    
}
