package com.example.bright_skies.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bright_skies.BaseDrawerActivity;
import com.example.bright_skies.R;

public class SavedSearchesActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_saved_searches, frameLayout);

        setTitle("Saved Searches");
    }

    protected void onResume() {
        super.onResume();
        // to check current activity in navigation drawer
        navView.getMenu().findItem(R.id.nav_saved_searches).setChecked(true);
    }

    /** Called when the user taps the Discover Your Bright Skies button */
    public void goToNavigationOptions(View view) {
        Intent intent = new Intent(this, NavigationPageActivity.class);
        startActivity(intent);
    }
}
