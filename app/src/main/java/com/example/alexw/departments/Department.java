package com.example.alexw.departments;

import com.google.android.gms.maps.model.LatLng;


public class Department {
    public Department() {}

    private String name;
    private LatLng latLng;

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

}
