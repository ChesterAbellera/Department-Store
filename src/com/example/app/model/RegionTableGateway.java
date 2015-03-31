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

public class RegionTableGateway {

    private Connection mConnection;

    private static final String TABLE_NAME = "region";
    private static final String COLUMN_REGIONNUMBER = "regionnumber";
    private static final String COLUMN_REGIONNAME = "regionname";
    private static final String COLUMN_REGIONALMANAGER = "regionalmanager";
    private static final String COLUMN_PHONENUMBER = "phonenumber";
    private static final String COLUMN_EMAIL = "email";

    public RegionTableGateway(Connection connection) {
        mConnection = connection;
    }

    public int insertRegion(String rn, String rm, String p, String e) throws SQLException {

        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        int id = -1;

        query = "INSERT INTO " + TABLE_NAME + " ("
                + COLUMN_REGIONNAME + ", "
                + COLUMN_REGIONALMANAGER + ", "
                + COLUMN_PHONENUMBER + ", "
                + COLUMN_EMAIL
                + ") VALUES (?, ?, ?, ?)";

        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, rn);
        stmt.setString(2, rm);
        stmt.setString(3, p);
        stmt.setString(4, e);
        
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
    
    
    
    
    
    
    
    
    
    // Code for DELETING A REGION
    public boolean deleteRegion(int id) throws SQLException
    {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        // The required SQL DELETE statement with place holders for the id of the row to be removed
        query =" DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_REGIONNUMBER + " = ? ";
    
        // Create a PreparedStatement object to execute the query and insert the id into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, id);

        // Execute the query
        numRowsAffected = stmt.executeUpdate();

        // Return true if one and only one row was deleted from the database
        return(numRowsAffected == 1);          
    }
    
    
    
    
    public List<Region> getRegions() throws SQLException
    {
        String query;
        Statement stmt;
        ResultSet rs;
        List<Region> regions;
        
        int regionnumber;
        String regionname, regionalmanager, phonenumber, email;
        
        Region r;
        
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);
        // Iterates through the result set, extracting the data from each row
        // and storing it in a Region object, which is inserted into an initially
        // empty ArrayList
        
        regions = new ArrayList<Region>();
        while(rs.next())
        {
            regionnumber = rs.getInt(COLUMN_REGIONNUMBER);
            regionname = rs.getString(COLUMN_REGIONNAME);
            regionalmanager = rs.getString(COLUMN_REGIONALMANAGER);
            phonenumber = rs.getString(COLUMN_PHONENUMBER);
            email = rs.getString(COLUMN_EMAIL);
            
            
            r = new Region(regionnumber, regionname, regionalmanager, phonenumber, email);
            regions.add(r);
        }
        
        //Returns the list of Shop objects retrieved
        return regions;
    }
    
    
    boolean updateRegion(Region r) throws SQLException 
    {
        String query;                   // The SQL Query to execute
        PreparedStatement stmt;         // The Java.sql. PreparedStatement object used to create the SQL Query
        int numRowsAffected;
        //int re;
        
        // The required SQL INSERT statement qith place holders for the values to be inserted
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_REGIONNAME           + " = ?, " +
                COLUMN_REGIONALMANAGER  + " = ?, " +
                COLUMN_PHONENUMBER       + " = ?, " +
                COLUMN_EMAIL              + " = ? " +
                " WHERE " + COLUMN_REGIONNUMBER + " = ?";
        
        // Create a PreparedStatement object to execute the query and insert the new value into the query
        stmt = mConnection.prepareStatement(query);
        
        stmt.setString(1, r.getRegionname());
        stmt.setString(2, r.getRegionalmanager());
        stmt.setString(3, r.getPhonenumber());
        stmt.setString(4, r.getEmail());
        stmt.setInt(5, r.getRegionnumber());

        // Execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();
        
        // Return true if one and only row was updated in the database
        return(numRowsAffected == 1);
    }
}