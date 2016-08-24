package com.example.alexw.departments;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

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
                    case R.id.infoButton:
                        autoCompleteTextView.setText("");
                        break;
                }

            }
        };
        hybridViewBtn.setOnClickListener(btnTestListener);
        normalViewBtn.setOnClickListener(btnTestListener);
        infoButton.setOnClickListener(btnTestListener);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.departmentsArray, android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
        //registerForContextMenu(autoCompleteTextView);
















        /**
         * ATTENTION! Your eyes may start bleeding after you see code below. Do it on your own risk.
         */























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
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        break;
                    case "Кафедра общей гигиены":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        break;
                    case "Кафедра иностранных и латинского языка":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        break;
                    case "Кафедра кыргызского и русского языков":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        break;
                    case "Кафедра клинической реабилитологии и физиотерапии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        break;
                    case "Кафедра философии и общественных наук":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        break;
                    case "Кафедра фармакогнозии и химии лекарственных средств":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        break;
                    case "Кафедра управления и экономики фармации, технологии лекарственных средств":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        break;

                    case "Кафедра акушерства и гинекологии №1":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.OBSTETRICS_AND_GYNECOLOGY_1_COORDINATES, 18));
                        mMap.addMarker(new MarkerOptions().position(Constants.OBSTETRICS_AND_GYNECOLOGY_1_COORDINATES).title(getString(R.string.obstetrics_and_gynecology1_title)));
                        break;
                    case "Кафедра акушерства и гинекологии №2":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.OBSTETRICS_AND_GYNECOLOGY_2_COORDINATES, 18));
                        mMap.addMarker(new MarkerOptions().position(Constants.OBSTETRICS_AND_GYNECOLOGY_2_COORDINATES).title(getString(R.string.obstetrics_and_gynecology2_title)));
                        break;
                    case "Кафедра анестезиологии, реанимации и интенсивной терапии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.ANESTHESIOLOGY_COORDINATES, 18));
                        mMap.addMarker(new MarkerOptions().position(Constants.ANESTHESIOLOGY_COORDINATES).title("Кафедра анестезиологии, реанимации и интенсивной терапии"));
                        break;
                    case "Кафедра оториноларингологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.OTORHINOLARYNGOLOGY_COORDINATES, 18));
                        mMap.addMarker(new MarkerOptions().position(Constants.OTORHINOLARYNGOLOGY_COORDINATES).title("Кафедра оториноларингологии"));
                        break;
                    case "Кафедра офтальмологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.OPHTHALMOLOGY_COORDINATES, 18));
                        mMap.addMarker(new MarkerOptions().position(Constants.OPHTHALMOLOGY_COORDINATES).title("Кафедра офтальмологии"));
                        break;
                    case "Кафедра лучевой диагностики и терапии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.ROENTGENOLOGY, 18));
                        mMap.addMarker(new MarkerOptions().position(Constants.ROENTGENOLOGY).title("Кафедра лучевой диагностики и терапии"));
                        break;
                    case "Кафедра неврологии с курсом медицинской генетики":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.NEUROLOGY, 18));
                        mMap.addMarker(new MarkerOptions().position(Constants.NEUROLOGY).title("Кафедра неврологии с курсом медицинской генетики"));
                        break;
                    case "Кафедра пропедевтики внутренних болезней с курсом эндокринологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.ENDOCRINOLOGY, 18));
                        mMap.addMarker(new MarkerOptions().position(Constants.ENDOCRINOLOGY).title("Кафедра пропедевтики внутренних болезней с курсом эндокринологии"));
                        break;
                    case "Кафедра пропедевтики детских болезней":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PROPED_PEDIATRICS, 18));
                        mMap.addMarker(new MarkerOptions().position(Constants.PROPED_PEDIATRICS).title("Кафедра пропедевтики детских болезней"));
                        break;
                    case "Кафедра психиатрии, психотерапии и наркологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PSYCHIATRY, 18));
                        mMap.addMarker(new MarkerOptions().position(Constants.PSYCHIATRY).title("Кафедра психиатрии, психотерапии и наркологии"));
                        break;
                    case "Кафедра сестринского дела":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.NURSE, 18));
                        mMap.addMarker(new MarkerOptions().position(Constants.NURSE).title("Кафедра сестринского дела"));
                        break;
                    case "Кафедра терапии общей практики с курсом семейной медицины":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FAMILY_MEDICINE, 18));
                        mMap.addMarker(new MarkerOptions().position(Constants.FAMILY_MEDICINE).title("Кафедра терапии общей практики с курсом семейной медицины"));
                        break;
                    case "Кафедра травматологии, ортопедии и экстремальной хирургии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.TRAUMATOLOGY, 18));
                        mMap.addMarker(new MarkerOptions().position(Constants.TRAUMATOLOGY).title("Кафедра травматологии, ортопедии и экстремальной хирургии"));
                        break;
                    case "Кафедра урологии и андрологии до- и последипломного обучения":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.UROLOGY, 18));
                        mMap.addMarker(new MarkerOptions().position(Constants.UROLOGY).title("Кафедра урологии и андрологии до- и последипломного обучения"));
                        break;

                }
            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        autoCompleteTextView.addTextChangedListener(textWatcher);
    }


    /**
     * I will turn this feature on after I figure out how to make it work without having to repeat
     * code 50 times
     */
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


        onMarkerClickListener = new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return false;
            }
        };


        mMap.setOnMarkerClickListener(onMarkerClickListener);


        LatLng mortuary = Constants.PATHOPHYSILOGY_COORDINATES;
        mMap.addMarker(new MarkerOptions().position(mortuary).title(getString(R.string.mortuary_title)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mortuary, 17));

        LatLng physiology = Constants.PHYSIOLOGY_COORDINATES;
        mMap.addMarker(new MarkerOptions().position(physiology).title(getString(R.string.main_corp)));

        LatLng fourthCorpus = Constants.FOURTH_CORPUS_COORDINATES;
        mMap.addMarker(new MarkerOptions().position(fourthCorpus).title(getString(R.string.fourth_corpus)));


    }


}
