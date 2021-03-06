package com.example.bright_skies.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.Objects;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;
//import com.google.android.gms.maps.MapFragment;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private MapView mMapView;
    private GoogleMap mGoogleMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private EditText textInput;
    private Button searchEnter;
    private RequestQueue requestQueue;
    private final String PLACES_URL = "https:/maps.googleapis.com/maps/api/place/autocomplete/";
    private final String GEOCODE_URL = "https://maps.googleapis.com/maps/api/geocode/";
    private final String NREL_URL = "https://developer.nrel.gov/api/solar/solar_resource/v1.json?api_key=4eE4mdyQSmbhnkpcNjv9FIjen1ZgLXf0cGSxQReU";
    private double lat;
    private double lng;

    //WHY DOES SDK HATE THIS? IMMEDIATELY CLOSES APP
    /*FragmentManager fragmentManager = getChildFragmentManager();
    FragmentTransaction goToResults = fragmentManager.beginTransaction();*/

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         * Inflate View
         */
        View root = inflater.inflate(R.layout.fragment_map, container, false);

        Bundle mapViewBundle = null;

        searchEnter = (Button) root.findViewById(R.id.search_button);
        searchEnter.setEnabled(false);


        requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()).getApplicationContext());
        /**
         * Search Button and Text Input
         */
        textInput = (EditText) root.findViewById(R.id.location_input);
        textInput.setHint("Enter location");
        textInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enableSearch(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        searchEnter = (Button) root.findViewById(R.id.search_button);
        searchEnter.setEnabled(false);
        searchEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = textInput.getText().toString();
                input = input.replaceAll("\\s","+");
                new GetJSONTask().execute(input);

                String[] input2 = {Double.toString(lat), Double.toString(lng)};
                new GetSolarInfoTask().execute(input2);

                /*ResultsFragment results = new ResultsFragment();
                FragmentTransaction goToResults = getFragmentManager().beginTransaction();
                goToResults.replace(R.id.fragment_map, ResultsFragment);
                goToResults.commit();*/
                /*switch (v.getId()) {
                    case R.id.search_button:
                        ResultsFragment results = new ResultsFragment();
                        goToResults.replace(R.id.nav_map, results);
                        goToResults.addToBackStack(null);

                        goToResults.commit();
                    break;
                }*/
            }
        });

        /**
         * Map View
         */
        mMapView = (MapView) root.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
        return root;
    }

    public void enableSearch(CharSequence s) {
        boolean textPresent = (s.length() > 0);
        searchEnter.setEnabled(textPresent);
    }

    private void updateMapLatLng() {
        LatLng new_lat_lng = new LatLng(lat, lng);
        CameraPosition cam_pos = new CameraPosition.Builder()
                .target(new_lat_lng)
                .zoom(18)
                .tilt(45)
                .build();
        mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cam_pos));
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 20));
        mGoogleMap.addMarker(new MarkerOptions().position(new_lat_lng).title("Your Location"));
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mFusedLocationClient = getFusedLocationProviderClient(Objects.requireNonNull(getContext()));
        mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful()) {
                    Log.d("TEST","entered successful");
                    Location location = task.getResult();
                    lat = location.getLatitude();
                    lng = location.getLongitude();
                    LatLng userLatLng = new LatLng(lat, lng);
                    Log.d("TEST", "last known lat: " + String.valueOf(lat));
                    Log.d("TEST", "last known lng: " + String.valueOf(lng));
                    updateMapLatLng();
                }
            }
        });
//        googleMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("Marker"));
        googleMap.setMyLocationEnabled(true);

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
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }


    private class GetJSONTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String input = strings[0];
            Log.d("TEST", "searchLocation input: " + input);
            String request_url = GEOCODE_URL + "json?address=" + input + "&key=" + getResources().getString(R.string.google_api_key);
            Log.d("TEST", request_url);
            JsonObjectRequest jsonRequest = new JsonObjectRequest(request_url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("TEST", "Response: " + response.toString(2));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        JSONObject results = (JSONObject) response.getJSONArray("results").get(0);
                        JSONObject geometry = results.getJSONObject("geometry");
                        JSONObject location = geometry.getJSONObject("location");
                        Log.d("TEST", "Location: " + location.toString(2));
                        lat = Double.parseDouble(location.getString("lat").toString());
                        lng = Double.parseDouble(location.getString("lng").toString());
                        Log.d("TEST", "new lat:" + lat + "\tnew lng: " + lng);
                        updateMapLatLng();
                    } catch (JSONException e) {
                        Log.d("TEST", "failed to get geometry");
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("TEST", "JSON Error: " + error.toString());
                }
            });
            requestQueue.add(jsonRequest);
            return null;
        }
    }

    private class GetSolarInfoTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String latitude = strings[0];
            String longitude = strings[1];
            String request_url = NREL_URL + "&lat=" + latitude + "&lon=" + longitude;

            JsonObjectRequest jsonRequest = new JsonObjectRequest(request_url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("TEST2", "Response: " + response.toString(2));
                    } catch (JSONException e) {
                        Log.d("TEST2", "ah1");
                        e.printStackTrace();
                    }

                    try {
                        JSONObject outputs = response.getJSONObject("outputs");

                        JSONObject avgdni = outputs.getJSONObject("avg_dni");
                        double annual = Double.parseDouble(avgdni.getString("annual"));
                        Log.d("TEST3", "Annual avg dni: " + annual);

                        JSONObject avgghi = outputs.getJSONObject("avg_ghi");
                        double annual2 = Double.parseDouble(avgghi.getString("annual"));
                        Log.d("TEST3", "Annual avg ghi: " + annual2);

                        JSONObject avglattilt = outputs.getJSONObject("avg_lat_tilt");
                        double annual3 = Double.parseDouble(avglattilt.getString("annual"));
                        Log.d("TEST3", "Annual avg lat tilt: " + annual3);

                    } catch (JSONException e) {
                        Log.d("TEST3", "ah2");
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("TEST", "JSON Error: " + error.toString());
                }
            });

            requestQueue.add(jsonRequest);

            return null;
        }
    }

}
