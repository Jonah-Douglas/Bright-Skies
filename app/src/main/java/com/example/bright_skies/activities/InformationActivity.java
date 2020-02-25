package com.example.bright_skies.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bright_skies.BaseDrawerActivity;
import com.example.bright_skies.R;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class InformationActivity extends BaseDrawerActivity {

    Button Expand;
    ExpandableRelativeLayout expandView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_information, frameLayout);

        Expand = findViewById(R.id.Expand);

        setTitle("Information");
    }

    /** Called when the user taps the Discover Your Bright Skies button */
    public void goToNavigationOptions(View view) {
        Intent intent = new Intent(this, NavigationPageActivity.class);
        startActivity(intent);
    }

    public void showMyInformation(View view) {
        expandView = findViewById(R.id.expandview);
        expandView.toggle();
    }
}
