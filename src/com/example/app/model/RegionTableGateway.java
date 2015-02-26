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
    private static final String COLUMN_SHOPMANAGERNAME = "regionname";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_PHONENUMBER = "phonenumber";
    private static final String COLUMN_EMAIL = "email";
    

    public RegionTableGateway(Connection connection) {
        mConnection = connection;
    }
    
    
    
    
    
}