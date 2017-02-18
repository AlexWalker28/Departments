package com.example.alexw.departments;

import com.google.android.gms.maps.model.LatLng;


public class Departments {
    public Departments() {}

    private String name;
    private Coordinates latLng;

    public Departments(String name, Coordinates latLng){
        this.name = name;
        this.latLng = latLng;
    }

    public String getName() {
        return this.name;
    }

    public Coordinates getLatLng(){
        return this.latLng;
    }

}
