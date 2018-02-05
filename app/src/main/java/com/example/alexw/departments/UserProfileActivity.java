package com.example.alexw.departments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class UserProfileActivity extends AppCompatActivity {

    private static final String TAG = "UserProfileActivity";
    private ImageView userPicImageView;
    private EditText userNameEditText;
    private Spinner facultySpinner;
    private Spinner yearSpinner;
    private EditText groupNumberEditText;
    private Button saveButton;
    private String userId;
    private String userName;
    private String userFaculty;
    private String userYear;
    private int userGroupNumber;
    private User user;
    private String [] facultyArray = {"Лечебное дело", "Педиатрия", "Стоматология", "Фармация", "МПД", "Сестринское дело"}; /*getResources().getStringArray(R.array.faculties);*/
    private String [] yearArray = {"1", "2", "3", "4", "5", "6"};

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference userNodeDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //TODO: better make it a fragment (read about it on android developers website)

        userPicImageView = (ImageView)findViewById(R.id.user_pic_image_view);
        userNameEditText = (EditText)findViewById(R.id.user_name_edit_text);
        facultySpinner = (Spinner)findViewById(R.id.faculty_spinner);
        yearSpinner = (Spinner)findViewById(R.id.year_spinner);
        groupNumberEditText = (EditText) findViewById(R.id.group_number_edit_text);
        saveButton = (Button)findViewById(R.id.save_user_info_button);
        firebaseDatabase = FirebaseDatabase.getInstance();
        user = new User();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        userId = intent.getStringExtra(Constants.USER_ID);
        userNodeDatabaseReference = firebaseDatabase.getReference().child("Users").child(userId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = userNameEditText.getText().toString();
                userFaculty = facultySpinner.getSelectedItem().toString();
                userYear = yearSpinner.getSelectedItem().toString();
                userGroupNumber = Integer.valueOf(groupNumberEditText.getText().toString());

                Map<String, Object> userUpdates = new HashMap<>();
                userUpdates.put("name", userName);
                userUpdates.put("faculty", userFaculty);
                userUpdates.put("year", userYear);
                userUpdates.put("groupNumber", userGroupNumber);
                userNodeDatabaseReference.updateChildren(userUpdates);
               /* user.setId(userId);
                user.setName(userName);
                user.setFaculty(userFaculty);
                user.setYear(userYear);
                user.setGroupNumber(userGroupNumber);
                userNodeDatabaseReference.setValue(user);*/
            }
        });
        ArrayAdapter<String> facultyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, facultyArray);
        facultySpinner.setAdapter(facultyAdapter);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, yearArray);
        yearSpinner.setAdapter(yearAdapter);

        userNodeDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    userNameEditText.setText(user.getName());
                    facultySpinner.setSelection(findFacultyIndex(user));
                    Log.v(TAG, "selected item position: " + facultySpinner.getSelectedItemPosition() + "\n" +
                            "method: " + findFacultyIndex(user)+ "\n" +
                            "should be: " + user.getFaculty());
                    yearSpinner.setSelection(findYearIndex(user));
                    groupNumberEditText.setText(String.valueOf(user.getGroupNumber()));
                } else {
                    Toast.makeText(getApplicationContext(), R.string.something_is_wrong, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private int findYearIndex(User user) {
        int index = 0;
        for(int i = 0; i < yearArray.length; i++){
            if(yearArray[i] == user.getYear()){
                Log.v(TAG, "user year: " + user.getYear());
                index = i;
                break;
            }
        }
        return index;
    }

    private int findFacultyIndex(User user) {
        int index = 0;
        for(int i = 0; i < facultyArray.length; i++){
            if(facultyArray[i] == user.getFaculty()){
                Log.v(TAG, "user faculty: " + user.getFaculty() + "\n" +
                        "faculty in array: " + facultyArray[i]);
                index = i;
                break;
            }
        }
        return index;
    }

}
