package com.example.bright_skies;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class FragmentTabAdapter extends FragmentPagerAdapter {

    public FragmentTabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0 :
                return new WelcomeFragment1();
            case 1:
                return new WelcomeFragment2();
            case 2:
                return new WelcomeFragment3();
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
    }

    @Override
    public int getCount() {
    }
}