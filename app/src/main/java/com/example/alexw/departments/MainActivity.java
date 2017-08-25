package com.example.alexw.departments;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;


public class MainActivity extends AppCompatActivity {

    private MenuItem hybridMapTypeMenuItem;
    private MenuItem reportMistakeMenuItem;
    private MenuItem goKGMAMenuItem;
    private MapView mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(getSupportFragmentManager());
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);

       mMap = (MapView)findViewById(R.id.mapView);


    }

    @Override
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
            /*case R.id.map_type_hybrid_menu_item:
                if(!item.isChecked() ){
                    item.setChecked(true);
                    mMap.
                } else {
                    item.setChecked(false);
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                break;*/
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
