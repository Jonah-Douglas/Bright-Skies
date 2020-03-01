package com.example.bright_skies.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.bright_skies.R;
import com.example.bright_skies.activities.MainActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;
//import com.google.android.gms.maps.MapFragment;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private MapView mMapView;
    private FusedLocationProviderClient mFusedLocationClient;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);
//        MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);
//        mapFragment.getMapAsync(this);
        Bundle mapViewBundle = null;
//        if (ContextCompat.checkSelfPermission(MainActivity, Manifest.permission.ACCESS_FINE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(MainActivity, Manifest.permission.ACCESS_FINE_LOCATIONS);
//        }

        mMapView = (MapView) root.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
        return root;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mFusedLocationClient = getFusedLocationProviderClient(Objects.requireNonNull(getContext()));
        mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful()) {
                    Log.d("TEST","entered successful");
                    Location location = task.getResult();
                    double lat = location.getLatitude();
                    double lon = location.getLongitude();
                    LatLng userLatLng = new LatLng(lat, lon);
                    Log.d("TEST", String.valueOf(lat));
                    Log.d("TEST", String.valueOf(lon));
                    CameraPosition cam_pos = new CameraPosition.Builder()
                            .target(userLatLng)
                            .zoom(18)
                            .tilt(45)
                            .build();
                    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cam_pos));
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 20));
                    googleMap.addMarker(new MarkerOptions().position(userLatLng).title("Your Location"));
                }
            }
        });
//        googleMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("Marker"));
        googleMap.setMyLocationEnabled(true);

    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.d("TEST", "entered onResume");
        super.onResume();
        mMapView.onResume();
        Log.d("TEST", "leaving onResume");
    }

}
