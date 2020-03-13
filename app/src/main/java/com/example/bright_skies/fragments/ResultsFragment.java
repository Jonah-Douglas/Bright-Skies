package com.example.bright_skies.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bright_skies.R;

public class ResultsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_results, container, false);

        return root;
    }

    public String solarRecommendation(int dni) {
        String result;

        if (dni < 1000) {
            result = "Your location does not have the required solar irradiance to allow for solar panels to be viable";
        } else if (dni < 1500) {
            result = "Average";
        } else {
            result = "Good";
        }
    }

}
