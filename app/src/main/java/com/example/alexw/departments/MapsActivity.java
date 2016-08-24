package com.example.alexw.departments;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button infoButton;
    private AutoCompleteTextView autoCompleteTextView;
    // private TextView textView;

    View.OnClickListener btnTestListener;
    GoogleMap.OnMapClickListener onMapClickListener;
    GoogleMap.OnMarkerClickListener onMarkerClickListener;


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
        infoButton = (Button) findViewById(R.id.infoButton);

        // textView = (TextView)findViewById(R.id.textView);


        btnTestListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.hybridViewBtn:
                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        //Toast.makeText(MapsActivity.this,R.string.hybrid, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.normalViewBtn:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;
                    case R.id.infoButton:
                        Intent intent = new Intent(MapsActivity.this, Info.class);
                        startActivity(intent);
                }

            }
        };
        hybridViewBtn.setOnClickListener(btnTestListener);
        normalViewBtn.setOnClickListener(btnTestListener);
        infoButton.setOnClickListener(btnTestListener);

        Department physiology = new Department(getString(R.string.physiology), Constants.PHYSIOLOGY_COORDINATES);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.departmentsArray, android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setThreshold(1);
        //registerForContextMenu(autoCompleteTextView);


        /*autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0 :
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        break;
                    case MENU_DEPARTMENT_PHYSIOLOGY:
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                        break;

                }
            }
        });*/


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                switch (charSequence.toString()) {
                    case "Кафедра патологической физиологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                        break;
                    case "Кафедра патологической анатомии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                        break;
                    case "Кафедра гистологии, цитологии, эмбриологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                        break;
                    case "Кафедра нормальной и топографической анатомии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                        break;
                    case "Кафедра судебной медицины и правоведения":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                        break;
                    case "Кафедра базисной и клинической фармакологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        break;
                    case "Кафедра военно - медицинской подготовки и экстремальной медицины":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        break;
                    case "Кафедра физики, математики, информатики и компьютерных технологий":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        break;
                    case "Кафедра микробиологии, вирусологии и иммунологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        break;
                    case "Кафедра фундаментальной и клинической физиологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        break;
                    case "Кафедра фундаментальных дисциплин":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        break;
                    case "Кафедра общественного здоровья и здравоохранения":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        break;
                    case "Кафедра общей и клинической эпидемиологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        break;
                    case "Кафедра физического воспитания":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        break;


                    case "Кафедра гигиенических дисциплин":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FORTH_CORPUS_COORDINATES, 17));
                        break;
                    case " Кафедра общей гигиены":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FORTH_CORPUS_COORDINATES, 17));
                        break;
                    case "Кафедра иностранных и латинского языка":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FORTH_CORPUS_COORDINATES, 17));
                        break;
                    case "Кафедра кыргызского и русского языков":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FORTH_CORPUS_COORDINATES, 17));
                        break;
                    case "Кафедра клинической реабилитологии и физиотерапии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FORTH_CORPUS_COORDINATES, 17));
                        break;
                    case "Кафедра философии и общественных наук":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FORTH_CORPUS_COORDINATES, 17));
                        break;
                    case "Кафедра фармакогнозии и химии лекарственных средств":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FORTH_CORPUS_COORDINATES, 17));
                        break;
                    case "Кафедра управления и экономики фармации, технологии лекарственных средств":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FORTH_CORPUS_COORDINATES, 17));
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        autoCompleteTextView.addTextChangedListener(textWatcher);
    }


   /* final int MENU_DEPARTMENT_PHYSIOLOGY = 1;
    final int MENU_DEPARTMENT_PATHOPHYSIOLOGY = 2;


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, MENU_DEPARTMENT_PHYSIOLOGY, 0, R.string.physiology);
        menu.add(0, MENU_DEPARTMENT_PATHOPHYSIOLOGY, 0, R.string.pathological_physiology);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_DEPARTMENT_PHYSIOLOGY:
                autoCompleteTextView.setText(R.string.physiology);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                break;
            case MENU_DEPARTMENT_PATHOPHYSIOLOGY:
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                break;

        }
        return super.onContextItemSelected(item);
    }*/

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
                // mMap.addMarker(new MarkerOptions().position(latLng).title("My marker"));
            }
        };

        onMarkerClickListener = new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return false;
            }
        };


        mMap.setOnMapClickListener(onMapClickListener);
        mMap.setOnMarkerClickListener(onMarkerClickListener);


        LatLng mortuary = Constants.PATHOPHYSILOGY_COORDINATES;
        mMap.addMarker(new MarkerOptions().position(mortuary).title(getString(R.string.mortuary_title)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mortuary, 17));

        LatLng physiology = Constants.PHYSIOLOGY_COORDINATES;
        mMap.addMarker(new MarkerOptions().position(physiology).title(getString(R.string.main_corp)));


    }


}
