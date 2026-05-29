package com.abdulajeejunnisa.orderapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url="jdbc:postgresql://localhost:5433/demo";
    private static final String uname="postgres";
    private static final String pass="muskan";
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,uname,pass);
    }
}
