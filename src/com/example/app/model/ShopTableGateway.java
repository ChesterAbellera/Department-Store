package com.example.app.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShopTableGateway {
    
    private Connection mConnection;
    
    private static final String TABLE_NAME = "shops";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_SHOPMANAGERNAME = "shopmanagername";
    private static final String COLUMN_PHONENUMBER = "phonenumber";
    private static final String COLUMN_DATEOPENED = "dateopened";
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_SHOPID = "shopID";
    private static final String COLUMN_REGIONNUMBER = "regionnumber";

    public ShopTableGateway(Connection connection) {
        mConnection = connection;
    }
    
    public int insertShop(String a, String sm, String p, String d, String u, int r) throws SQLException {
    
        String query;               // The SQL Query to execute
        PreparedStatement stmt;     // the java.sql.PreparedStatement object used to execute the SQL Query
        int numRowsAffected;
        int id = -1;
        
        // The required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_ADDRESS + ", " +
                COLUMN_SHOPMANAGERNAME + ", " +
                COLUMN_PHONENUMBER + ", " +
                COLUMN_DATEOPENED + ", " +
                COLUMN_URL + ", " +
                COLUMN_REGIONNUMBER +
                ") VALUES (?, ?, ?, ?, ?, ?)";
        
        // Create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, a);
        stmt.setString(2, sm);
        stmt.setString(3, p);
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date;
        try {
            date = new Date(format.parse(d).getTime());
        } 
        catch (ParseException ex) {
            java.util.Date now = new java.util.Date();
            date = new Date(now.getTime());
        }
        stmt.setDate(4, date);
        stmt.setString(5, u);
        //stmt.setInt(6, sid);
        stmt.setInt(6, r);
        
        
        // Execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();
        
        if (numRowsAffected == 1)
        {
            // If one row was inserted, retrieve the id assigned to that row
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            
            id = keys.getInt(1);
        }
        // Return the ID assigned to the row in the database
        return id;
    }




    




    // Code for DELETING THE SHOP
    public boolean deleteShop(int id) throws SQLException
    {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        // The required SQL DELETE statement with place holders for the id of the row to be removed
        query =" DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_SHOPID + " = ? ";
    
        // Create a PreparedStatement object to execute the query and insert the id into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, id);

        // Execute the query
        numRowsAffected = stmt.executeUpdate();

        // Return true if one and only one row was deleted from the database
        return(numRowsAffected == 1);          
    }
    
    
    
    
    public List<Shop> getShops() throws SQLException
    {
        String query;       // The SQL query to execute
        Statement stmt;     // The java.sql.Statement object used  to execute the SQL query
        ResultSet rs;       // The java.sql.ResultSet representing the result of SQL query
        List<Shop> shops;   // The java.util.List containing the Shop objects created

        String address, shopmanagername, phonenumber, dateopened, url;
        int shopID, regionnumber;
        
        Shop s;
        
        // executes an SQL SELECT statement to get a java.util.ResultSet
        // to represent the results of the SELECT statement.
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);
        
        // Iterates through the result set, extracting the data from each row
        // and storing it in a Shop object, which is inserted into an initially
        // empty ArrayList
        
        
        
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        shops = new ArrayList<Shop>();
        while(rs.next())
        {
            address = rs.getString(COLUMN_ADDRESS);
            shopmanagername = rs.getString(COLUMN_SHOPMANAGERNAME);
            phonenumber = rs.getString(COLUMN_PHONENUMBER);
            Date date = rs.getDate(COLUMN_DATEOPENED);
            dateopened = format.format(date);
            url = rs.getString(COLUMN_URL);
            shopID = rs.getInt(COLUMN_SHOPID);
            regionnumber = rs.getInt(COLUMN_REGIONNUMBER);
            
            s = new Shop(address, shopmanagername, phonenumber, dateopened, url, shopID, regionnumber);
            shops.add(s);
        }
        
        //Returns the list of Shop objects retrieved
        return shops;
    }

    boolean updateShop(Shop s) throws SQLException 
    {
        String query;                   // The SQL Query to execute
        PreparedStatement stmt;         // The Java.sql. PreparedStatement object used to create the SQL Query
        int numRowsAffected;
        
        // The required SQL INSERT statement qith place holders for the values to be inserted
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_ADDRESS          + " = ?, " +
                COLUMN_SHOPMANAGERNAME  + " = ?, " +
                COLUMN_PHONENUMBER      + " = ?, " +
                COLUMN_DATEOPENED       + " = ?, " +
                COLUMN_URL              + " = ?, " +
                COLUMN_REGIONNUMBER     + " = ? " +
                " WHERE " + COLUMN_SHOPID + " = ?";
        
        // Create a PreparedStatement object to execute the query and insert the new value into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, s.getAddress());
        stmt.setString(2, s.getShopmanagername());
        stmt.setString(3, s.getPhonenumber());
        stmt.setString(5, s.getUrl());
        stmt.setString(4, s.getDateopened());
        stmt.setInt(7, s.getShopID());
        stmt.setInt(6, s.getRegionnumber());
        
        // Execute the query
        numRowsAffected = stmt.executeUpdate();
        
        // Return true if one and only row was updated in the database
        return(numRowsAffected == 1);
    }

}