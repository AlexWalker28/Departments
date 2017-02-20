package com.example.alexw.departments;

import com.google.android.gms.maps.model.LatLng;


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

    public LatLng convertCoordinatesToLanLng(Coordinates coordinates){
        return new LatLng(coordinates.getLat(), coordinates.getLng());
    }

}

