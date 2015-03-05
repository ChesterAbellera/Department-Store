package com.example.app.model;

public class Region {
    
    private int regionnumber;
    private String regionname;
    private String address;
    private String phonenumber;
    private String email;
    

    public Region(int r, String rn, String a, String p, String e) 
    {
        this.regionnumber = r;
    }

    public Region(String rn, String a, String p, String e)
    {
        this(-1, rn, a, p, e);
    }
            
            
            
    /* Get/Set Methods */
    /* ------------------------------- */
     public int getRegionnumber() {
        return regionnumber;
    }

    public void setRegionnumber(int regionnumber) {
        this.regionnumber = regionnumber;
    }
    /* ------------------------------- */
    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }
    /* ------------------------------- */
     public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    /* ------------------------------- */
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    /* ------------------------------- */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    /* ------------------------------- */
}