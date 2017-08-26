package com.example.alexw.departments;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MenuItem reportMistakeMenuItem;
    private MenuItem goKGMAMenuItem;
    private DrawerLayout drawerLayout;
    private ArrayList<String> drawerDataArrayList;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ViewPager viewPager;
    private String [] tabTitlesArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabTitlesArray = getResources().getStringArray(R.array.tab_titles);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), tabTitlesArray);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupDrawerContent(navigationView);

        drawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(drawerToggle);

        handleMapTypeSwitch();

    }

    private void handleMapTypeSwitch() {
        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.map_type_switch_menu_item);
        SwitchCompat switchCompat = (SwitchCompat) MenuItemCompat.getActionView(menuItem).findViewById(R.id.switch_compat);
        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isActivated()){
                    MapsFragment.getMap().setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    v.setActivated(false);
                } else {
                    MapsFragment.getMap().setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    v.setActivated(true);
                }
                drawerLayout.closeDrawers();
            }
        });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectDrawerItem(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_first_fragment:
                viewPager.setCurrentItem(0, true);
                break;
            case R.id.nav_second_fragment:
                viewPager.setCurrentItem(1, true);
                break;

        }
        drawerLayout.closeDrawers();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
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
            case R.id.kgma_item:
                goKGMA();
        }
        return true;
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

}
