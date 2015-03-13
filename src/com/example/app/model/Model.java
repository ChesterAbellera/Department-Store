package com.example.app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    List<Shop> shops;
    ShopTableGateway shopGateway;

    List<Region> regions;
    RegionTableGateway regionGateway;

    private Model() {

        try {
            Connection conn = DBConnection.getInstance();
            this.shopGateway = new ShopTableGateway(conn);
            this.regionGateway = new RegionTableGateway(conn);

            this.shops = this.shopGateway.getShops();
            this.regions = this.regionGateway.getRegions();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //==========================================================================
    // SHOP SECTION
    //==========================================================================
    // CODE TO CREATE A SHOP
    //--------------------------------------------------------------------------
    public boolean addShop(Shop s) {
        boolean result = false;
        try {
            int id = this.shopGateway.insertShop(s.getShopAddress(), s.getDateopened(), s.getPhonenumber(), s.getShopmanagername(), s.getUrl(), s.getRegionnumber());

            if (id != -1) {
                s.setShopID(id);
                this.shops.add(s);
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    //--------------------------------------------------------------------------
    // CODE TO FIND A SHOP BY SHOPID
    //--------------------------------------------------------------------------
    public List<Shop> getShops() {
        return this.shops;
    }

    Shop findShopByShopId(int shopID) {
        Shop s = null;
        int i = 0;
        boolean found = false;
        while (i < this.shops.size() && !found) {
            s = this.shops.get(i);
            if (s.getShopID() == shopID) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            s = null;
        }
        return s;
    }

    //--------------------------------------------------------------------------
    // CODE TO DELETE A SHOP
    //--------------------------------------------------------------------------
    public boolean removeShop(Shop s) {
        boolean removed = false;

        try {
            // Removes the Shop in the database
            removed = this.shopGateway.deleteShop(s.getShopID());
            if (removed) {
                removed = this.shops.remove(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return removed;
    }

    //--------------------------------------------------------------------------
    // CODE TO UPDATE A SHOP
    //--------------------------------------------------------------------------
    boolean updateShop(Shop s) {
        boolean updated = false;

        try {
            // Updates the Shop in the database
            updated = this.shopGateway.updateShop(s);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updated;
    }

    //==========================================================================
    // REGION SECTION
    //==========================================================================
    // CODE TO CREATE A REGION
    public boolean addRegion(Region r) {
        boolean result = false;
        try {
            int id = this.regionGateway.insertRegion(r.getRegionname(), r.getRegionalmanager(), r.getPhonenumber(), r.getEmail());

            if (id != -1) {
                r.setRegionnumber(id);
                this.regions.add(r);
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // CODE TO FIND A REGION BY REGIONNUMBER
    public List<Region> getRegions() {
        return this.regions;
    }

    Region findRegionByRegionNumber(int regionnumber) {
        Region r = null;
        int i = 0;
        boolean found = false;
        while (i < this.regions.size() && !found) {
            r = this.regions.get(i);
            if (r.getRegionnumber() == regionnumber) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            r = null;
        }
        return r;
    }

    // CODE TO DELETE A REGION
    public boolean removeRegion(Region r) {
        boolean removed = false;

        try {
            // Removes the Shop in the database
            removed = this.regionGateway.deleteRegion(r.getRegionnumber());
            if (removed) {
                removed = this.regions.remove(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return removed;
    }

    // CODE TO UPDATE A REGION
    boolean updateRegion(Region r) {
        boolean updated = false;

        try {
            // Updates the Shop in the database
            updated = this.regionGateway.updateRegion(r);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updated;
    }

}
