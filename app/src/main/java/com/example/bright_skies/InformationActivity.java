package com.example.bright_skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class InformationActivity extends AppCompatActivity {

    Button Expand;
    ExpandableRelativeLayout expandView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Expand = findViewById(R.id.Expand);
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
