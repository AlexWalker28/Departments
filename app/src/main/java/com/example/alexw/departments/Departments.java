package com.example.alexw.departments;

import com.google.android.gms.maps.model.LatLng;


public class Departments {
    public Departments() {}

    private String name;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    private double lat;
    private double lng;

    public Departments(String name, double latitude, double longitude){
        this.name = name;
        this.lat = latitude;
        this.lng = longitude;
    }

    public String getName() {
        return this.name;
    }



}
