package com.example.alexw.departments;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by alexwalker on 25.08.17.
 */

class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    private String [] pageTitlesArray;

    public CustomFragmentPagerAdapter(FragmentManager fm, String [] pageTitlesArray) {
        super(fm);
        this.pageTitlesArray = pageTitlesArray;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new MapsFragment();
        } else return new BooksFragment();
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
