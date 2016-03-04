package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.AtomBeans;

/**
 * Created by bbsmp on 15/12/10.
 */
public class LocationItem {
    private long lon = 0;
    private long lat = 0;
    private String city = "";
    private String district = "";
    private String street = "";
    private String position = "";
    private String addrees = "";


    public LocationItem() {
    }

    public LocationItem(long lon, long lat, String city, String district, String street, String position, String addrees) {
        this.lon = lon;
        this.lat = lat;
        this.city = city;
        this.district = district;
        this.street = street;
        this.position = position;
        this.addrees = addrees;
    }

    public LocationItem(String addrees) {
        this.addrees = addrees;
    }


}
