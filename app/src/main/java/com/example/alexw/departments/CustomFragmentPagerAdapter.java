package com.example.alexw.departments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by alexwalker on 25.08.17.
 */

class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    String [] pageTitlesArray = {"Кафедры", "Книги"};

    public CustomFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        /*if(position == 0){
            return new MapsActivity();
        } else*/ return new BooksFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitlesArray[position];
    }
}
