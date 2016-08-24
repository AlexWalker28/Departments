package com.example.alexw.departments;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by alexw on 20.08.2016.
 */
public class Constants {




    public static final LatLng PHYSIOLOGY_COORDINATES = new LatLng(42.842406, 74.607076);
    public static final LatLng PATHOPHYSILOGY_COORDINATES = new LatLng(42.838715, 74.607440);



    public static final String[] DEPARTMENTS = new String[]{
            ,
            "Pathophysiology",
            "Физиология",
            "Патологическая физиология"


    };



    /*String[] departments = getResources().getStringArray(R.array.list_of_deparments);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_dropdown_item_1line.departments);*/


}
