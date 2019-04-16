package ru.moysklad.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHelper {

    private final String host;
    private final String user;
    private final String password;

    public ConnectionHelper() {
        Properties properties = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(".\\src\\main\\resources\\db\\db.properties"))) {
            properties.load(in);
        } catch (IOException e) {
            System.out.println("PROPERTIES ERROR");
        }
        this.host = properties.getProperty("db.host");
        this.user = properties.getProperty("db.user");
        this.password = properties.getProperty("db.password");
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("POSTGRES DRIVER ERROR");
        }
        return DriverManager.getConnection(host, user, password);
    }
}