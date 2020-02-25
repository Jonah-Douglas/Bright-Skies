package com.example.bright_skies.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bright_skies.BaseDrawerActivity;
import com.example.bright_skies.R;

public class LocationResultsActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_location_results, frameLayout);

        setTitle("Results");
    }
    protected void onResume() {
        super.onResume();
        // to check current activity in navigation drawer
        navView.getMenu().findItem(R.id.nav_results).setChecked(true);
    }

    /** Called when the user taps the navigation button */
    public void goToNavigationOptions(View view) {
        Intent intent = new Intent(this, NavigationPageActivity.class);
        startActivity(intent);
    }
}
