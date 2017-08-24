package com.example.alexw.departments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "MapsFragment";
    private GoogleMap mMap;
    private Button cleanButton;
    private AutoCompleteTextView autoCompleteTextView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<Departments> departmentsArrayList;
    private ArrayList<String> autoCompleteTextViewData;
    private ArrayList<Marker> markerArrayList;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private static boolean isPersistent;

    private MenuItem hybridMapTypeMenuItem;
    private MenuItem reportMistakeMenuItem;
    private MenuItem goKGMAMenuItem;
    private View v;
    private MapView mapView;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_maps, container, false);
        mapView = (MapView) v.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        if(!isPersistent) {
            firebaseDatabase.setPersistenceEnabled(true);
            isPersistent = true;
        }
        databaseReference = firebaseDatabase.getReference().child("Departments");
        cleanButton = (Button) v.findViewById(R.id.cleanButton);
        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextView.setText("");
            }
        });

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.getUiSettings().setCompassEnabled(true);
                mMap.getUiSettings().setAllGesturesEnabled(true);
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setZoomGesturesEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));

                departmentsArrayList = new ArrayList<>();
                autoCompleteTextViewData = new ArrayList<>();
                markerArrayList = new ArrayList<>();
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Departments departments = dataSnapshot.getValue(Departments.class);
                        departmentsArrayList.add(departments);
                        autoCompleteTextViewData.add(departments.getName());

                        for (Departments department : departmentsArrayList){
                            LatLng latLng = new LatLng(department.getLat(), department.getLng());
                            Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(department.getName()));
                            markerArrayList.add(marker);
                        }

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item
                        , autoCompleteTextViewData);
                autoCompleteTextView = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView);
                autoCompleteTextView.setAdapter(arrayAdapter);
                autoCompleteTextView.setThreshold(1);

                TextWatcher textWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        for (Departments department : departmentsArrayList) {
                            if (department.getName().equals(charSequence.toString())) {
                                LatLng coordinates = new LatLng(department.getLat(), department.getLng());
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 17));
                                showInfoWindowForMarker(department);
                                break;
                            }
                        }

                    }


                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                };
                autoCompleteTextView.addTextChangedListener(textWatcher);

            }
        });
        return v;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        hybridMapTypeMenuItem = menu.findItem(R.id.map_type_hybrid_menu_item);
        reportMistakeMenuItem = menu.findItem(R.id.mistake_report_menu_item);
        goKGMAMenuItem = menu.findItem(R.id.kgma_item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mistake_report_menu_item:
                sendMessage();
                break;
            case R.id.map_type_hybrid_menu_item:
                if(!item.isChecked() ){
                    item.setChecked(true);
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                } else {
                    item.setChecked(false);
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                break;
            case R.id.kgma_item:
                goKGMA();
        }
        return true;
    }


    private void sendMessage() {
        Intent sentMessage = new Intent(Intent.ACTION_SENDTO);
        sentMessage.setData(Uri.parse("mailto:feedbackmessagetodeveloper@gmail.com"));
        sentMessage.putExtra(Intent.EXTRA_SUBJECT, "Сообщение об ошибке");
        if (sentMessage.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(sentMessage);
        }
    }

    private void goKGMA() {
        Intent goKGMA = new Intent(Intent.ACTION_VIEW);
        goKGMA.setData(Uri.parse("https://vk.com/lovekgma"));
        startActivity(goKGMA);
    }
    */




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    private void showInfoWindowForMarker(Departments department) {
        for (Marker marker : markerArrayList){
            if (marker.getPosition().latitude == department.getLat()){
                marker.showInfoWindow();
                break;
            }
        }
    }


}
