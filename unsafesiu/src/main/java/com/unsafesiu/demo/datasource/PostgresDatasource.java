package com.unsafesiu.demo.datasource;

import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class PostgresDatasource {
    private Connection postgresConnection;

    public PostgresDatasource() throws IOException {

        Properties props = new Properties();
        props.load(new FileInputStream(new File("C:/Users/user1/Desktop/UTN/5. Quinto año/Seguridad en Aplicacion Web/TP/unsafe-siu/unsafesiu/src/main/resources/application.properties")));

        try {
            postgresConnection = DriverManager.getConnection(
                    props.getProperty("app.datasource.jdbc-url"),
                    props.getProperty("app.datasource.username"),
                    props.getProperty("app.datasource.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public Connection getConnection() {
        return postgresConnection;
    }
}
