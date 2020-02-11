package com.example.brightskies;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class NavigationDrawer extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dl = (DrawerLayout)findViewById(R.id.activity_main);
//        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close)
//        TODO: figure out what R.string.Open and R.string.Close should represent
        t = new ActionBarDrawerToggle(this, dl, 1, 1);


        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.locationsearch:
                        Toast.makeText(NavigationDrawer.this, "Location Search",Toast.LENGTH_SHORT).show();break;
                    case R.id.savedsearches:
                        Toast.makeText(NavigationDrawer.this, "Saved Searches",Toast.LENGTH_SHORT).show();break;
                    case R.id.energycalculator:
                        Toast.makeText(NavigationDrawer.this, "Energy Calculator",Toast.LENGTH_SHORT).show();break;
                    case R.id.information:
                        Toast.makeText(NavigationDrawer.this, "Information",Toast.LENGTH_SHORT).show();break;
                    case R.id.faqs:
                        Toast.makeText(NavigationDrawer.this, "FAQs",Toast.LENGTH_SHORT).show();break;
                    case R.id.aboutus:
                        Toast.makeText(NavigationDrawer.this, "About Us",Toast.LENGTH_SHORT).show();break;
                    default:
                        return true;
                }


                return true;

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
