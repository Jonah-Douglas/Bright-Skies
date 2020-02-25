package com.example.bright_skies.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bright_skies.BaseDrawerActivity;
import com.example.bright_skies.R;

public class MapPageActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_map_page, frameLayout);

        setTitle("Map");
    }

    protected void onResume() {
        super.onResume();
        // to check current activity in navigation drawer
        navView.getMenu().findItem(R.id.nav_map).setChecked(true);
    }

    /** Called when the user taps the Discover Your Bright Skies button */
    public void goToNavigationOptions(View view) {
        Intent intent = new Intent(this, NavigationPageActivity.class);
        startActivity(intent);
    }

    public void goToLocationResults(View view) {
        Intent intent = new Intent(this, LocationResultsActivity.class);
        startActivity(intent);
    }
}
