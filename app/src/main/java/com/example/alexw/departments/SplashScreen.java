package com.example.alexw.departments;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.app.Activity;

public class SplashScreen extends Activity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mapsActivityIntent = new Intent(SplashScreen.this, MapsActivity.class);
                SplashScreen.this.startActivity(mapsActivityIntent);
                SplashScreen.this.finish();
            }
        }, Constants.SPLASH_ACTIVITY_LENGTH);




    }
}
