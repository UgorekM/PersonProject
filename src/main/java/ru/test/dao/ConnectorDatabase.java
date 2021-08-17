package ru.test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDatabase {
    public static Connection getConnection() {
        ResourceBundle resource = ResourceBundle.getBundle("database");

        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
