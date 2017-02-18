package com.example.alexw.departments;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button cleanButton;
    private ImageButton actionbar;
    private PopupMenu popup;
    private MenuItem mapTypeHybridMenuItem;
    private AutoCompleteTextView autoCompleteTextView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<Departments> departmentsData;
    private ArrayList<String> autoCompleteTextViewData;
    private ArrayList<LatLng> markersData;
    private Coordinates coordinates;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    Marker army;
    Marker obsGyn1;
    Marker obsGyn2;
    Marker anest;
    Marker otorhin;
    Marker ophtalm;
    Marker roentg;
    Marker neurol;
    Marker edocr;
    Marker propPed;
    Marker psych;
    Marker nurse;
    Marker family;
    Marker trauma;
    Marker urol;
    Marker hospThera;
    Marker haema;
    Marker neonat;
    Marker facThera;
    Marker facTheraCard;

    Marker propSurg;
    Marker facPed;
    Marker surgComb;
    Marker tb;
    Marker mamakeev;
    Marker infect;
    Marker facSurg;

    Marker neurosurg;
    Marker infectChild;
    Marker dermatoVen;
    Marker childSurgery;
    Marker oncology;


    Marker fourthCorp;
    Marker mainCorp;
    Marker mortuaryMarker;


    View.OnClickListener onClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Departments");


        cleanButton = (Button) findViewById(R.id.cleanButton);
        actionbar = (ImageButton)findViewById(R.id.actionbarImageButton);

        popup = new PopupMenu(MapsActivity.this, actionbar);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, popup.getMenu());
        mapTypeHybridMenuItem = popup.getMenu().findItem(R.id.map_type_hybrid_menu_item);


        /*Departments department = new Departments("Кафедра военно - медицинской подготовки и экстремальной медицины"
                 , coordinates = new Coordinates(Constants.ARMY.latitude
                 , Constants.ARMY.longitude));
        Departments department1 = new Departments("Кафедра акушерства и гинекологии №1"
                , coordinates = new Coordinates(Constants.OBSTETRICS_AND_GYNECOLOGY_1_COORDINATES.latitude
                , Constants.OBSTETRICS_AND_GYNECOLOGY_1_COORDINATES.longitude));
        Departments department2 = new Departments("Кафедра акушерства и гинекологии №2"
                , coordinates = new Coordinates(Constants.OBSTETRICS_AND_GYNECOLOGY_2_COORDINATES.latitude
                , Constants.OBSTETRICS_AND_GYNECOLOGY_2_COORDINATES.longitude));
        Departments department3 = new Departments("Кафедра анестезиологии, реанимации и интенсивной терапии"
                , coordinates = new Coordinates(Constants.ANESTHESIOLOGY_COORDINATES.latitude
                , Constants.ANESTHESIOLOGY_COORDINATES.longitude));
        Departments department4 = new Departments("Кафедра госпитальной терапии, профпатологии с курсом гематологии"
                , coordinates = new Coordinates(Constants.HAEMATOLOGY.latitude
                , Constants.HAEMATOLOGY.longitude));
        Departments department5 = new Departments("Кафедра госпитальной педиатрии с курсом неонатологии"
                , coordinates = new Coordinates(Constants.HOSPITAL_PEDIATRICS_AND_NEONATOLOGY.latitude
                , Constants.HOSPITAL_PEDIATRICS_AND_NEONATOLOGY.longitude));
        Departments department6 = new Departments("Кафедра госпитальной хирургии с курсом оперативной хирургии им. академика М.М. Мамакеева"
                , coordinates = new Coordinates(Constants.MAMAKEEV.latitude
                , Constants.MAMAKEEV.longitude));
        Departments department7 = new Departments("Кафедра детских инфекционных болезней"
                , coordinates = new Coordinates(Constants.INFECT_CHILD.latitude
                , Constants.INFECT_CHILD.longitude));
        Departments department8 = new Departments("Кафедра дерматовенерологии"
                , coordinates = new Coordinates(Constants.DERMATOVEN.latitude
                , Constants.DERMATOVEN.longitude));
        Departments department9 = new Departments("Кафедра детской хирургии"
                , coordinates = new Coordinates(Constants.CHILD_SURGERY.latitude
                , Constants.CHILD_SURGERY.longitude));
        Departments department10 = new Departments("Кафедра детской стоматологии"
                , coordinates = new Coordinates(Constants.NURSE.latitude
                , Constants.NURSE.longitude));
        Departments department11 = new Departments("Кафедра инфекционных болезней"
                , coordinates = new Coordinates(Constants.INFECTION.latitude
                , Constants.INFECTION.longitude));
        Departments department12 = new Departments("Кафедра лучевой диагностики и терапии"
                , coordinates = new Coordinates(Constants.ROENTGENOLOGY.latitude
                , Constants.ROENTGENOLOGY.longitude));
        Departments department13 = new Departments("Кафедра неврологии с курсом медицинской генетики"
                , coordinates = new Coordinates(Constants.NEUROLOGY.latitude
                , Constants.NEUROLOGY.longitude));
        Departments department14 = new Departments("Кафедра нейрохирургии до дипломного и последипломного образования"
                , coordinates = new Coordinates(Constants.NEUROSURGERY.latitude
                , Constants.NEUROSURGERY.longitude));
        Departments department15 = new Departments("Кафедра онкологии"
                , coordinates = new Coordinates(Constants.ONCOLOGY.latitude
                , Constants.ONCOLOGY.longitude));
        Departments department16 = new Departments("Кафедра оториноларингологии"
                , coordinates = new Coordinates(Constants.OTORHINOLARYNGOLOGY_COORDINATES.latitude
                , Constants.OTORHINOLARYNGOLOGY_COORDINATES.longitude));
        Departments department17 = new Departments("Кафедра офтальмологии"
                , coordinates = new Coordinates(Constants.OPHTHALMOLOGY_COORDINATES.latitude
                , Constants.OPHTHALMOLOGY_COORDINATES.longitude));
        Departments department18 = new Departments("Кафедра ортопедической стоматологии"
                , coordinates = new Coordinates(Constants.NURSE.latitude
                , Constants.NURSE.longitude));
        Departments department19 = new Departments("Кафедра пропедевтики внутренних болезней с курсом эндокринологии"
                , coordinates = new Coordinates(Constants.ENDOCRINOLOGY.latitude
                , Constants.ENDOCRINOLOGY.longitude));
        Departments department20 = new Departments("Кафедра пропедевтики детских болезней"
                , coordinates = new Coordinates(Constants.PROPED_PEDIATRICS.latitude
                , Constants.PROPED_PEDIATRICS.longitude));
        Departments department21 = new Departments("Кафедра пропедхирургии"
                , coordinates = new Coordinates(Constants.PROPED_SURGERY.latitude
                , Constants.PROPED_SURGERY.longitude));
        Departments department22 = new Departments("Кафедра психиатрии, психотерапии и наркологии"
                , coordinates = new Coordinates(Constants.PSYCHIATRY.latitude
                , Constants.PSYCHIATRY.longitude));
        Departments department23 = new Departments("Кафедра сестринского дела"
                , coordinates = new Coordinates(Constants.NURSE.latitude
                , Constants.NURSE.longitude));
        Departments department24 = new Departments("Кафедра терапии общей практики с курсом семейной медицины"
                , coordinates = new Coordinates(Constants.FAMILY_MEDICINE.latitude
                , Constants.FAMILY_MEDICINE.longitude));
        Departments department25 = new Departments("Кафедра травматологии, ортопедии и экстремальной хирургии"
                , coordinates = new Coordinates(Constants.TRAUMATOLOGY.latitude
                , Constants.TRAUMATOLOGY.longitude));
        Departments department26 = new Departments("Кафедра терапевтической стоматологии"
                , coordinates = new Coordinates(Constants.NURSE.latitude
                , Constants.NURSE.longitude));
        Departments department27 = new Departments("Кафедра урологии и андрологии до- и последипломного обучения"
                , coordinates = new Coordinates(Constants.UROLOGY.latitude
                , Constants.UROLOGY.longitude));
        Departments department28 = new Departments("Кафедра факультетской терапии (НЦКиТ)"
                , coordinates = new Coordinates(Constants.FACULTY_THERAPY.latitude
                , Constants.FACULTY_THERAPY.longitude));
        Departments department29 = new Departments("Кафедра факультетской педиатрии"
                , coordinates = new Coordinates(Constants.FACULTY_PEDIATRICS.latitude
                , Constants.FACULTY_PEDIATRICS.longitude));
        Departments department30 = new Departments("Кафедра факультетской хирургии"
                , coordinates = new Coordinates(Constants.FAC_SURGERY.latitude
                , Constants.FAC_SURGERY.longitude));
        Departments department31 = new Departments("Кафедра фтизиатрии"
                , coordinates = new Coordinates(Constants.TB.latitude
                , Constants.TB.longitude));
        Departments department32 = new Departments("Кафедра хирургии общей практики с курсом комбустиологии"
                , coordinates = new Coordinates(Constants.SURGERY_COMBUST.latitude
                , Constants.SURGERY_COMBUST.longitude));
        Departments department33 = new Departments("Кафедра хирургической стоматологии и челюстно - лицевой хирургии"
                , coordinates = new Coordinates(Constants.NURSE.latitude
                , Constants.NURSE.longitude));

        Departments department34 = new Departments("Морфологический корпус"
                , coordinates = new Coordinates(Constants.PATHOPHYSILOGY_COORDINATES.latitude
                , Constants.PATHOPHYSILOGY_COORDINATES.longitude));
        Departments department35 = new Departments("Главный корпус"
                , coordinates = new Coordinates(Constants.PHYSIOLOGY_COORDINATES.latitude
                , Constants.PHYSIOLOGY_COORDINATES.longitude));
        Departments department36 = new Departments("Четвертый корпус"
                , coordinates = new Coordinates(Constants.FOURTH_CORPUS_COORDINATES.latitude
                , Constants.FOURTH_CORPUS_COORDINATES.longitude));

        databaseReference.push().setValue(department);
        databaseReference.push().setValue(department1);
        databaseReference.push().setValue(department2);
        databaseReference.push().setValue(department3);
        databaseReference.push().setValue(department4);
        databaseReference.push().setValue(department5);
        databaseReference.push().setValue(department6);
        databaseReference.push().setValue(department7);
        databaseReference.push().setValue(department8);
        databaseReference.push().setValue(department9);
        databaseReference.push().setValue(department10);
        databaseReference.push().setValue(department11);
        databaseReference.push().setValue(department12);
        databaseReference.push().setValue(department13);
        databaseReference.push().setValue(department14);
        databaseReference.push().setValue(department15);
        databaseReference.push().setValue(department16);
        databaseReference.push().setValue(department17);
        databaseReference.push().setValue(department18);
        databaseReference.push().setValue(department19);
        databaseReference.push().setValue(department20);
        databaseReference.push().setValue(department21);
        databaseReference.push().setValue(department22);
        databaseReference.push().setValue(department23);
        databaseReference.push().setValue(department24);
        databaseReference.push().setValue(department25);
        databaseReference.push().setValue(department26);
        databaseReference.push().setValue(department27);
        databaseReference.push().setValue(department28);
        databaseReference.push().setValue(department29);
        databaseReference.push().setValue(department30);
        databaseReference.push().setValue(department31);
        databaseReference.push().setValue(department32);
        databaseReference.push().setValue(department33);
        databaseReference.push().setValue(department34);
        databaseReference.push().setValue(department35);
        databaseReference.push().setValue(department36);*/

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.cleanButton:
                        autoCompleteTextView.setText("");
                        break;
                    case R.id.actionbarImageButton:
                        popup.show();
                        break;
                }
            }
        };
        cleanButton.setOnClickListener(onClickListener);
        actionbar.setOnClickListener(onClickListener);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
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
        });

        departmentsData = new ArrayList<>();
        autoCompleteTextViewData = new ArrayList<>();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Departments departments = dataSnapshot.getValue(Departments.class);
                departmentsData.add(departments);
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

        arrayAdapter = new ArrayAdapter<String>(MapsActivity.this, R.layout.support_simple_spinner_dropdown_item
                                                                 , autoCompleteTextViewData);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setThreshold(1);


        /*OrientationEventListener orientationEventListener = new OrientationEventListener(getApplication()) {
            @Override
            public void onOrientationChanged(int i) {
                Configuration newConfig = new Configuration();
                if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE
                        && mMap.getMapType() == GoogleMap.MAP_TYPE_HYBRID) {
                    mapTypeHybridMenuItem.setChecked(true);
                } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT
                        && mMap.getMapType() == GoogleMap.MAP_TYPE_HYBRID) {
                    mapTypeHybridMenuItem.setChecked(true);
                } else {
                    mapTypeHybridMenuItem.setChecked(false);
                }
            }
        };
        orientationEventListener.canDetectOrientation();
        orientationEventListener.enable();*/
    }

    private void sendMessage() {
        Intent sentMessage = new Intent(Intent.ACTION_SENDTO);
        sentMessage.setData(Uri.parse("mailto:feedbackmessagetodeveloper@gmail.com"));
        sentMessage.putExtra(Intent.EXTRA_SUBJECT, "Сообщение об ошибке");
        if (sentMessage.resolveActivity(getPackageManager()) != null) {
            startActivity(sentMessage);
        }
    }

    private void goKGMA() {
        Intent goKGMA = new Intent(Intent.ACTION_VIEW);
        goKGMA.setData(Uri.parse("https://vk.com/lovekgma"));
        startActivity(goKGMA);
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
        mMap.getUiSettings().setMyLocationButtonEnabled(true);


        LatLng mortuary = Constants.PATHOPHYSILOGY_COORDINATES;
        mortuaryMarker = mMap.addMarker(new MarkerOptions().position(mortuary).title(getString(R.string.mortuary_title)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mortuary, 17));

        LatLng physiology = Constants.PHYSIOLOGY_COORDINATES;
        mainCorp = mMap.addMarker(new MarkerOptions().position(physiology).title(getString(R.string.main_corp)));

        final LatLng fourthCorpus = Constants.FOURTH_CORPUS_COORDINATES;
        fourthCorp = mMap.addMarker(new MarkerOptions().position(fourthCorpus).title(getString(R.string.fourth_corpus)));















        /**
         * ATTENTION! Your eyes may start bleeding after you see code below. Do it on your own risk.
         */



















        army = mMap.addMarker(new MarkerOptions().position(Constants.ARMY).title("Кафедра военно - медицинской подготовки и экстремальной медицины"));
        obsGyn1 = mMap.addMarker(new MarkerOptions().position(Constants.OBSTETRICS_AND_GYNECOLOGY_1_COORDINATES).title(getString(R.string.obstetrics_and_gynecology1_title)));
        obsGyn2 = mMap.addMarker(new MarkerOptions().position(Constants.OBSTETRICS_AND_GYNECOLOGY_2_COORDINATES).title(getString(R.string.obstetrics_and_gynecology2_title)));
        anest = mMap.addMarker(new MarkerOptions().position(Constants.ANESTHESIOLOGY_COORDINATES).title("Кафедра анестезиологии, реанимации и интенсивной терапии"));
        otorhin = mMap.addMarker(new MarkerOptions().position(Constants.OTORHINOLARYNGOLOGY_COORDINATES).title("Кафедра оториноларингологии"));
        ophtalm = mMap.addMarker(new MarkerOptions().position(Constants.OPHTHALMOLOGY_COORDINATES).title("Кафедра офтальмологии"));
        roentg = mMap.addMarker(new MarkerOptions().position(Constants.ROENTGENOLOGY).title("Кафедра лучевой диагностики и терапии"));
        neurol = mMap.addMarker(new MarkerOptions().position(Constants.NEUROLOGY).title("Кафедра неврологии с курсом медицинской генетики"));
        edocr = mMap.addMarker(new MarkerOptions().position(Constants.ENDOCRINOLOGY).title("Кафедра пропедевтики внутренних болезней с курсом эндокринологии"));
        propPed = mMap.addMarker(new MarkerOptions().position(Constants.PROPED_PEDIATRICS).title("Кафедра пропедевтики детских болезней"));
        psych = mMap.addMarker(new MarkerOptions().position(Constants.PSYCHIATRY).title("Кафедра психиатрии, психотерапии и наркологии"));
        nurse = mMap.addMarker(new MarkerOptions().position(Constants.NURSE).title("Стоматологический центр"));
        family = mMap.addMarker(new MarkerOptions().position(Constants.FAMILY_MEDICINE).title("Кафедра терапии общей практики с курсом семейной медицины"));
        trauma = mMap.addMarker(new MarkerOptions().position(Constants.TRAUMATOLOGY).title("Кафедра травматологии, ортопедии и экстремальной хирургии"));
        urol = mMap.addMarker(new MarkerOptions().position(Constants.UROLOGY).title("Кафедра урологии и андрологии до- и последипломного обучения"));
        hospThera = mMap.addMarker(new MarkerOptions().position(Constants.HOSPITAL_THERAPY).title("Кафедра госпитальной терапии, профпатологии с курсом гематологии"));
        haema = mMap.addMarker(new MarkerOptions().position(Constants.HAEMATOLOGY).title("Центр гематологии"));
        neonat = mMap.addMarker(new MarkerOptions().position(Constants.HOSPITAL_PEDIATRICS_AND_NEONATOLOGY).title("Кафедра госпитальной педиатрии с курсом неонатологии"));
        facThera = mMap.addMarker(new MarkerOptions().position(Constants.FACULTY_THERAPY).title("Кафедра факультетской терапии (НЦКиТ)"));
        facTheraCard = mMap.addMarker(new MarkerOptions().position(Constants.FACULTY_THERAPY_POLICLINIC).title("Кафедра факультетской терапии (поликлиника)"));


        propSurg = mMap.addMarker(new MarkerOptions().position(Constants.PROPED_SURGERY).title("Кафедра пропедхирургии"));
        facPed = mMap.addMarker(new MarkerOptions().position(Constants.FACULTY_PEDIATRICS).title("Кафедра факультетской педиатрии"));
        surgComb = mMap.addMarker(new MarkerOptions().position(Constants.SURGERY_COMBUST).title("Кафедра хирургии общей практики с курсом комбустиологии"));
        tb = mMap.addMarker(new MarkerOptions().position(Constants.TB).title("Кафедра фтизиатрии"));
        mamakeev = mMap.addMarker(new MarkerOptions().position(Constants.MAMAKEEV).title("Кафедра госпитальной хирургии с курсом оперативной хирургии им. академика М.М. Мамакеева"));
        infect = mMap.addMarker(new MarkerOptions().position(Constants.INFECTION).title("Кафедра инфекционных болезней"));
        facSurg = mMap.addMarker(new MarkerOptions().position(Constants.FAC_SURGERY).title("Кафедра факультетской хирургии"));

        neurosurg = mMap.addMarker(new MarkerOptions().position(Constants.NEUROSURGERY).title("Кафедра нейрохирургии до дипломного и последипломного образования"));
        infectChild = mMap.addMarker(new MarkerOptions().position(Constants.INFECT_CHILD).title("Кафедра детских инфекционных болезней"));
        dermatoVen = mMap.addMarker(new MarkerOptions().position(Constants.DERMATOVEN).title("Кафедра дерматовенерологии"));
        childSurgery = mMap.addMarker(new MarkerOptions().position(Constants.CHILD_SURGERY).title("Кафедра детской хирургии"));
        oncology = mMap.addMarker(new MarkerOptions().position(Constants.ONCOLOGY).title("Кафедра онкологии"));


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                switch (charSequence.toString()) {
                    case "Кафедра патологической физиологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                        mortuaryMarker.showInfoWindow();
                        break;
                    case "Кафедра патологической анатомии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                        mortuaryMarker.showInfoWindow();
                        break;
                    case "Кафедра гистологии, цитологии, эмбриологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                        mortuaryMarker.showInfoWindow();
                        break;
                    case "Кафедра нормальной и топографической анатомии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                        mortuaryMarker.showInfoWindow();
                        break;
                    case "Кафедра судебной медицины и правоведения":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                        mortuaryMarker.showInfoWindow();
                        break;
                    case "Кафедра военно - медицинской подготовки и экстремальной медицины":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.ARMY, 17));
                        army.showInfoWindow();
                        break;

                    case "Кафедра базисной и клинической фармакологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        mainCorp.showInfoWindow();
                        break;
                    case "Кафедра физики, математики, информатики и компьютерных технологий":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        mainCorp.showInfoWindow();
                        break;
                    case "Кафедра микробиологии, вирусологии и иммунологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        mainCorp.showInfoWindow();
                        break;
                    case "Кафедра фундаментальной и клинической физиологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        mainCorp.showInfoWindow();
                        break;
                    case "Кафедра фундаментальных дисциплин":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        mainCorp.showInfoWindow();
                        break;
                    case "Кафедра общественного здоровья и здравоохранения":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        mainCorp.showInfoWindow();
                        break;
                    case "Кафедра общей и клинической эпидемиологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        mainCorp.showInfoWindow();
                        break;
                    case "Кафедра физического воспитания":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        mainCorp.showInfoWindow();
                        break;


                    case "Кафедра гигиенических дисциплин":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        fourthCorp.showInfoWindow();
                        break;
                    case "Кафедра общей гигиены":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        fourthCorp.showInfoWindow();
                        break;
                    case "Кафедра иностранных и латинского языка":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        fourthCorp.showInfoWindow();
                        break;
                    case "Кафедра кыргызского и русского языков":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        fourthCorp.showInfoWindow();
                        break;
                    case "Кафедра клинической реабилитологии и физиотерапии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        fourthCorp.showInfoWindow();
                        break;
                    case "Кафедра философии и общественных наук":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        fourthCorp.showInfoWindow();
                        break;
                    /*case "Кафедра фармакогнозии и химии лекарственных средств":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        fourthCorp.showInfoWindow();
                        break;
                    case "Кафедра управления и экономики фармации, технологии лекарственных средств":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        fourthCorp.showInfoWindow();
                        break;*/


                    case "Кафедра акушерства и гинекологии №1":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.OBSTETRICS_AND_GYNECOLOGY_1_COORDINATES, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.OBSTETRICS_AND_GYNECOLOGY_1_COORDINATES).title(getString(R.string.obstetrics_and_gynecology1_title)));*/
                        obsGyn1.showInfoWindow();
                        break;
                    case "Кафедра акушерства и гинекологии №2":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.OBSTETRICS_AND_GYNECOLOGY_2_COORDINATES, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.OBSTETRICS_AND_GYNECOLOGY_2_COORDINATES).title(getString(R.string.obstetrics_and_gynecology2_title)));*/
                        obsGyn2.showInfoWindow();
                        break;
                    case "Кафедра анестезиологии, реанимации и интенсивной терапии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.ANESTHESIOLOGY_COORDINATES, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.ANESTHESIOLOGY_COORDINATES).title("Кафедра анестезиологии, реанимации и интенсивной терапии"));*/
                        anest.showInfoWindow();
                        break;
                    case "Кафедра оториноларингологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.OTORHINOLARYNGOLOGY_COORDINATES, 18));
                       /* mMap.addMarker(new MarkerOptions().position(Constants.OTORHINOLARYNGOLOGY_COORDINATES).title("Кафедра оториноларингологии"));*/
                        otorhin.showInfoWindow();
                        break;
                    case "Кафедра офтальмологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.OPHTHALMOLOGY_COORDINATES, 18));
                       /* mMap.addMarker(new MarkerOptions().position(Constants.OPHTHALMOLOGY_COORDINATES).title("Кафедра офтальмологии"));*/
                        ophtalm.showInfoWindow();
                        break;
                    case "Кафедра лучевой диагностики и терапии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.ROENTGENOLOGY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.ROENTGENOLOGY).title("Кафедра лучевой диагностики и терапии"));*/
                        roentg.showInfoWindow();
                        break;
                    case "Кафедра неврологии с курсом медицинской генетики":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.NEUROLOGY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.NEUROLOGY).title("Кафедра неврологии с курсом медицинской генетики"));*/
                        neurol.showInfoWindow();
                        break;
                    case "Кафедра пропедевтики внутренних болезней с курсом эндокринологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.ENDOCRINOLOGY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.ENDOCRINOLOGY).title("Кафедра пропедевтики внутренних болезней с курсом эндокринологии"));*/
                        edocr.showInfoWindow();
                        break;
                    case "Кафедра пропедевтики детских болезней":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PROPED_PEDIATRICS, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.PROPED_PEDIATRICS).title("Кафедра пропедевтики детских болезней"));*/
                        propPed.showInfoWindow();
                        break;
                    case "Кафедра психиатрии, психотерапии и наркологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PSYCHIATRY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.PSYCHIATRY).title("Кафедра психиатрии, психотерапии и наркологии"));*/
                        psych.showInfoWindow();
                        break;
                    case "Кафедра сестринского дела":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.NURSE, 18));
                        Marker marker = mMap.addMarker(new MarkerOptions().position(Constants.NURSE).title("Кафедра сестринского дела"));
                        marker.showInfoWindow();
                        break;
                    case "Кафедра ортопедической стоматологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.NURSE, 18));
                        Marker marker1 = mMap.addMarker(new MarkerOptions().position(Constants.NURSE).title("Кафедра ортопедической стоматологии"));
                        marker1.showInfoWindow();
                        break;
                    case "Кафедра детской стоматологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.NURSE, 18));
                        Marker marker2 = mMap.addMarker(new MarkerOptions().position(Constants.NURSE).title("Кафедра детской стоматологии"));
                        marker2.showInfoWindow();
                        break;
                    case "Кафедра терапевтической стоматологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.NURSE, 18));
                        Marker marker3 = mMap.addMarker(new MarkerOptions().position(Constants.NURSE).title("Кафедра терапевтической стоматологии"));
                        marker3.showInfoWindow();
                        break;
                    case "Кафедра хирургической стоматологии и челюстно - лицевой хирургии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.NURSE, 18));
                        Marker marker4 = mMap.addMarker(new MarkerOptions().position(Constants.NURSE).title("Кафедра хирургической стоматологии и челюстно - лицевой хирургии"));
                        marker4.showInfoWindow();
                        break;
                    case "Кафедра терапии общей практики с курсом семейной медицины":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FAMILY_MEDICINE, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.FAMILY_MEDICINE).title("Кафедра терапии общей практики с курсом семейной медицины"));*/
                        family.showInfoWindow();
                        break;
                    case "Кафедра травматологии, ортопедии и экстремальной хирургии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.TRAUMATOLOGY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.TRAUMATOLOGY).title("Кафедра травматологии, ортопедии и экстремальной хирургии"));*/
                        trauma.showInfoWindow();
                        break;
                    case "Кафедра урологии и андрологии до- и последипломного обучения":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.UROLOGY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.UROLOGY).title("Кафедра урологии и андрологии до- и последипломного обучения"));*/
                        urol.showInfoWindow();
                        break;
                    case "Кафедра госпитальной терапии, профпатологии с курсом гематологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.HOSPITAL_THERAPY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.HOSPITAL_THERAPY).title("Кафедра госпитальной терапии, профпатологии с курсом гематологии"));*/
                        hospThera.showInfoWindow();
                        break;
                    case "Центр гематологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.HAEMATOLOGY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.HAEMATOLOGY).title("Центр гематологии"));*/
                        haema.showInfoWindow();
                        break;
                    case "Кафедра госпитальной педиатрии с курсом неонатологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.HOSPITAL_PEDIATRICS_AND_NEONATOLOGY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.HOSPITAL_PEDIATRICS_AND_NEONATOLOGY).title("Кафедра госпитальной педиатрии с курсом неонатологии"));*/
                        neonat.showInfoWindow();
                        break;
                    case "Кафедра факультетской терапии (НЦКиТ)":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FACULTY_THERAPY, 17));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.FACULTY_THERAPY).title("Кафедра факультетской терапии (НЦКиТ)"));
                        mMap.addMarker(new MarkerOptions().position(Constants.FACULTY_THERAPY_POLICLINIC).title("Кафедра факультетской терапии (кардиология)"));*/
                        facThera.showInfoWindow();
                        break;
                    case "Кафедра пропедхирургии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PROPED_SURGERY, 18));
                        propSurg.showInfoWindow();
                        break;
                    case "Кафедра факультетской педиатрии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FACULTY_PEDIATRICS, 18));
                        facPed.showInfoWindow();
                        break;
                    case "Кафедра хирургии общей практики с курсом комбустиологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.SURGERY_COMBUST, 18));
                        surgComb.showInfoWindow();
                        break;
                    case "Кафедра фтизиатрии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.TB, 18));
                        tb.showInfoWindow();
                        break;
                    case "Кафедра госпитальной хирургии с курсом оперативной хирургии им. академика М.М. Мамакеева":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.MAMAKEEV, 18));
                        mamakeev.showInfoWindow();
                        break;
                    case "Кафедра инфекционных болезней":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.INFECTION, 18));
                        infect.showInfoWindow();
                        break;
                    case "Кафедра факультетской хирургии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FAC_SURGERY, 18));
                        facSurg.showInfoWindow();
                        break;
                    case "Кафедра нейрохирургии до дипломного и последипломного образования":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.NEUROSURGERY, 18));
                        neurosurg.showInfoWindow();
                        break;
                    case "Кафедра детских инфекционных болезней":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.INFECT_CHILD, 18));
                        infectChild.showInfoWindow();
                        break;
                    case "Кафедра детской хирургии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.CHILD_SURGERY, 18));
                        childSurgery.showInfoWindow();
                        break;
                    case "Кафедра дерматовенерологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.DERMATOVEN, 18));
                        dermatoVen.showInfoWindow();
                        break;
                    case "Кафедра онкологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.ONCOLOGY, 18));
                        oncology.showInfoWindow();

                        break;
                }
            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        autoCompleteTextView.addTextChangedListener(textWatcher);


    }


}
