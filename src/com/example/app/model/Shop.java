package com.example.app.model;

public class Shop {

    private String address;
    private String shopmanagername;
    private String phonenumber;
    private String url;
    private String dateopened;
    private int shopID;
    private int regionnumber;

    public Shop(String a, String sm, String p, String d, String u, int sid, int r) 
    {
        this.address = a;
        this.shopmanagername = sm;
        this.phonenumber = p;
        this.url = u;
        this.dateopened = d;
        this.shopID = sid;
        this.regionnumber = r;
    }

    public Shop(String a, String sm, String p, String d, String u, int r)
    {
        this(a, sm, p, d, u, -1 , r);
    }
            
            
            
    /* Get/Set Methods */
    /* ------------------------------- */
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    /* ------------------------------- */
    public String getShopmanagername() {
        return shopmanagername;
    }

    public void setShopmanagername(String shopmanagername) {
        this.shopmanagername = shopmanagername;
    }
    /* ------------------------------- */
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    /* ------------------------------- */
    public String getDateopened() {
        return dateopened;
    }

    public void setDateopened(String dateopened) {
        this.dateopened = dateopened;
    }
    /* ------------------------------- */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    /* ------------------------------- */
    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }
    /* ------------------------------- */
    public int getRegionnumber() {
        return regionnumber;
    }

    public void setRegionnumber(int regionnumber) {
        this.regionnumber = regionnumber;
    }
    /* ------------------------------- */
}