package com.example.alexw.departments;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by AlexWalker on 24.08.2016.
 */
public class Department {

    /*public Department(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }*/

    public Department(String name, LatLng latLng){
        this.name = name;
        this.latLng = latLng;
    }


    public String getName() {
        return this.name;
    }
    public LatLng getLatLng(){
        return this.latLng;
    }
    public String getLatitude(){
        return this.latitude;
    }
    public String getLongitude(){
        return this.longitude;
    }

    private String name;
    private LatLng latLng;
    private String latitude;
    private String longitude;


}
