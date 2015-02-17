package com.example.app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    private static Connection sConnection;
    
    public static Connection getInstance() throws ClassNotFoundException, SQLException
    {
        String host, db, user, password;
        
        host = "daneel";
        db = "N00131826";
        user = "N00131826";
        password = "N00131826";
        
        if (sConnection == null || sConnection.isClosed())
        {
            String url = "jdbc:mysql://" + host + "/" + db;
            Class.forName("com.mysql.jdbc.Driver");
            sConnection = DriverManager.getConnection(url, user, password);
            
        }
        
        return sConnection;
    }
}
