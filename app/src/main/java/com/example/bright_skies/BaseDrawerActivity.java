package com.example.bright_skies;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.bright_skies.activities.AboutUsActivity;
import com.example.bright_skies.activities.EnergyCalculatorActivity;
import com.example.bright_skies.activities.FAQsActivity;
import com.example.bright_skies.activities.InformationActivity;
import com.example.bright_skies.activities.LocationResultsActivity;
import com.example.bright_skies.activities.MapPageActivity;
import com.example.bright_skies.activities.SavedSearchesActivity;
import com.google.android.material.navigation.NavigationView;

public class BaseDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected FrameLayout frameLayout;
    protected DrawerLayout drawerLayout;
    protected Toolbar toolbar;
    protected NavigationView navView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        frameLayout = findViewById(R.id.content_frame);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.nav_app_bar_open_drawer_description,
                R.string.nav_app_bar_navigate_up_description);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        //to prevent current item select over and over
        if (item.isChecked()) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }

        Intent newIntent;

        if (id == R.id.nav_about_us) {
            newIntent = new Intent(getApplicationContext(), AboutUsActivity.class);
        } else if (id == R.id.nav_calculator) {
            newIntent = new Intent(getApplicationContext(), EnergyCalculatorActivity.class);
        } else if (id == R.id.nav_faq) {
            newIntent = new Intent(getApplicationContext(), FAQsActivity.class);
        } else if (id == R.id.nav_information) {
            newIntent = new Intent(getApplicationContext(), InformationActivity.class);
        } else if (id == R.id.nav_map) {
            newIntent = new Intent(getApplicationContext(), MapPageActivity.class);
        } else if (id == R.id.nav_saved_searches) {
            newIntent = new Intent(getApplicationContext(), SavedSearchesActivity.class);
        } else {
            newIntent = new Intent(getApplicationContext(), LocationResultsActivity.class);
        }

        startActivity(newIntent);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
