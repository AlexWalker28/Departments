package com.example.alexw.departments;

/**
 * Created by alexw on 18.02.2017.
 */

public class Coordinates {

    public Coordinates(){}

    public Coordinates(double lat, double lng){
        this.lat = lat;
        this.lng = lng;
    }

    private double lat;
    private double lng;

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

}

