package com.example.bright_skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    /** Called when the user taps the Discover Your Bright Skies button */
    public void enterApp(View view) {
        Intent intent = new Intent(this, MapPageActivity.class);
        startActivity(intent);
    }
}
