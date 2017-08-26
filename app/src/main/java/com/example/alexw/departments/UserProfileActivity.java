package com.example.alexw.departments;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;


public class UserProfileActivity extends AppCompatActivity {

    private static final String TAG = "UserProfileActivity";
    ImageView userPicImageView;
    EditText userNameEditText;
    Spinner facultySpinner;
    Spinner yearSpinner;
    EditText groupNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userPicImageView = (ImageView)findViewById(R.id.user_pic_image_view);
        userNameEditText = (EditText)findViewById(R.id.user_name_edit_text);
        facultySpinner = (Spinner)findViewById(R.id.faculty_spinner);
        yearSpinner = (Spinner)findViewById(R.id.year_spinner);
        groupNumberEditText = (EditText) findViewById(R.id.group_number_edit_text);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home: {
                Log.v(TAG, "pressed");
                NavUtils.navigateUpFromSameTask(this);
                return true;
            }
            default : return super.onOptionsItemSelected(item);
        }


    }
}
