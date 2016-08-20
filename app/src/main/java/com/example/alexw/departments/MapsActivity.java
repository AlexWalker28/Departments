package com.example.alexw.departments;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button hybridViewBtn;
    private Button normalViewBtn;
    View.OnClickListener btnTestListener;
    GoogleMap.OnMapClickListener onMapClickListener;
    GoogleMap.OnMarkerClickListener onMarkerClickListener;
    EditText findDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        hybridViewBtn = (Button) findViewById(R.id.hybridViewBtn);
        normalViewBtn = (Button) findViewById(R.id.normalViewBtn);
        findDepartment = (EditText)findViewById(R.id.findDepartment);
        btnTestListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.hybridViewBtn:
                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;
                    case R.id.normalViewBtn:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;
                }

            }
        };
        hybridViewBtn.setOnClickListener(btnTestListener);
        normalViewBtn.setOnClickListener(btnTestListener);

        findDepartment.getText().toString(); //TODO


    }


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
        mMap = googleMap;
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);

        onMapClickListener = new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
               mMap.addMarker(new MarkerOptions().position(latLng).title("My marker"));
            }
        };

        onMarkerClickListener = new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.remove();
                return false;
            }
        };

        mMap.setOnMapClickListener(onMapClickListener);
        mMap.setOnMarkerClickListener(onMarkerClickListener);


        LatLng mortuary = new LatLng(42.8384125, 74.6065013);
        mMap.addMarker(new MarkerOptions().position(mortuary).title("Mortuary"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mortuary, 17));


    }
}
