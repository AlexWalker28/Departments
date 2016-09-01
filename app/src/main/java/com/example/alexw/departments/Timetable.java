package com.example.alexw.departments;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class Timetable extends AppCompatActivity {

   private Spinner facultySpinner;
   private Spinner courseSpinner;
   private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        facultySpinner = (Spinner)findViewById(R.id.faculty);
        courseSpinner = (Spinner)findViewById(R.id.course);
        imageView = (ImageView)findViewById(R.id.imageView2);


        String[] faculty = {"Лечебное дело №1", "Лечебное дело №2", "Педиатрия", "Стоматология", "Фармация", "МПД", "Сестринское дело"};
        String[] course = {"1 курс", "2 курс", "3 курс", "4 курс", "5 курс", "6 курс"};

        ArrayAdapter<String> facultyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, faculty);
        facultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, course);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        facultySpinner.setPrompt("Выберите факультет");
        courseSpinner.setPrompt("Выберите курс");

        facultySpinner.setAdapter(facultyAdapter);
        courseSpinner.setAdapter(courseAdapter);

        facultySpinner.setSelection(5);

        facultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (adapterView.getSelectedItem().toString()){
                    case "Лечебное дело №1":
                            imageView.setImageResource(R.drawable.kgma);
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //TODO
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.putExtra(Intent.EXTRA_STREAM, R.drawable.kgma);
                                    intent.setType("image/jpg");
                                    startActivity(intent);
                                }
                            });
                        break;
                    case "Лечебное дело №2":

                        break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });









    }
}
