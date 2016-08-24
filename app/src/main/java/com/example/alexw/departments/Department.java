package com.example.alexw.departments;

/**
 * Created by AlexWalker on 24.08.2016.
 */
public class Department {

    public Department(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getName() {
        return this.name;
    }
    public String getLatitude(){
        return this.latitude;
    }
    public String getLongitude(){
        return this.longitude;
    }

    private String name;
    private String latitude;
    private String longitude;


}
