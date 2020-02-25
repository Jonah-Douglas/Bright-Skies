package com.example.bright_skies.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bright_skies.BaseDrawerActivity;
import com.example.bright_skies.R;

public class FAQsActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_faqs, frameLayout);

        setTitle("FAQ");
    }

    protected void onResume() {
        super.onResume();
        // to check current activity in navigation drawer
        navView.getMenu().findItem(R.id.nav_faq).setChecked(true);
    }

}
