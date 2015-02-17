package com.example.app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() 
        {
            if (instance == null) {
                instance = new Model();
        }
        return instance;
    }

    List<Shop> shops;
    ShopTableGateway gateway;

    private Model() {

       try {
            Connection conn = DBConnection.getInstance();
            this.gateway = new ShopTableGateway(conn);
            
            this.shops = this.gateway.getShops();
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    
    // ODE TO CREATE A SHOP
    public boolean addShop(Shop s)
    {
        boolean result = false;
        try {
            int id = this.gateway.insertShop(s.getAddress(), s.getDateopened(), s.getPhonenumber(), s.getShopmanagername(), s.getUrl(), s.getRegionnumber());
            
            if (id != -1)
            {
                s.setShopID(id);
                this.shops.add(s);
                result = true;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    
    
    
    // CODE TO DELETE A SHOP
    public boolean removeShop(Shop s){
      boolean removed = false;
      
     try {
         // Removes the Shop in the database
         removed = this.gateway.deleteShop(s.getShopID());
         if(removed){
             removed=this.shops.remove(s);
           }
         } 
         catch (SQLException ex){
             Logger.getLogger(Model.class.getName()).log(Level.SEVERE,null,ex);
       }
      return removed;
    }
    
    
    
    
    // CODE TO FIND A SHOP BY SHOPID
    public List<Shop> getShops() {
        return this.shops;
    }

    Shop findShopByShopId(int shopID) 
    {
       Shop s = null;
       int i = 0;
       boolean found = false;
       while (i < this.shops.size() && !found)
       {
           s = this.shops.get(i);
           if (s.getShopID() == shopID)
           {
               found = true;
           }
           else 
           {
               i++;
           }
        }
       if (!found)
       {
           s = null;
       }
       return s;
    }
    
    
    
    
    // CODE TO UPDATE A SHOP
    boolean updateShop(Shop s) 
    {
        boolean updated = false;
        
        try 
        {
            // Removes the Shop in the database
            updated = this.gateway.updateShop(s);
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE,null,ex);
        }
        return updated;
    } 
}