package com.example.bright_skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NavigationPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_page);
    }

    /** Called when the user taps the Discover Your Bright Skies button */
    public void goToLocationSearch(View view) {
        Intent intent = new Intent(this, MapPageActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Discover Your Bright Skies button */
    public void goToSavedSearches(View view) {
        Intent intent = new Intent(this, SavedSearchesActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Discover Your Bright Skies button */
    public void goToEnergyCalculator(View view) {
        Intent intent = new Intent(this, EnergyCalculatorActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Discover Your Bright Skies button */
    public void goToInformation(View view) {
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Discover Your Bright Skies button */
    public void goToFAQs(View view) {
        Intent intent = new Intent(this, FAQsActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Discover Your Bright Skies button */
    public void goToAboutUs(View view) {
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
    }

}
