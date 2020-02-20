package com.example.bright_skies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

public class WelcomePage1 extends AppCompatActivity {
    TabLayout mTabLayout;
    ViewPager mViewPager;
    FragmentTabAdapter mFragmentTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_1);

        mTabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.view_pager);
        mFragmentTabAdapter = new FragmentTabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mFragmentTabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
