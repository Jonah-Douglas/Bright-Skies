package com.example.bright_skies.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.bright_skies.R;

import static android.content.ContentValues.TAG;

public class CalculatorFragment extends Fragment {

    private double fridge;
    private double microwave;
    private double miles;
    private double bulb;
    private double coal;
    private double gas;
    private double ledbulb;
    private double kwh;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calculator, container, false);
        Button button = root.findViewById(R.id.calculate_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView();
            }
        });

        return root;
    }

    protected void updateView() {
        View v = getView();
        EditText editText = v.findViewById(R.id.energy_input);
        String input = editText.getText().toString();
        try {
            kwh = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            Log.e(TAG, "onCreateView: no number detected",null );
        }

        updateFridgeHours(v);
        updateMicrowaveHours(v);
        updateMiles(v);
        updateCoal(v);
        updateGas(v);
        updateLightbulb(v);
        updateLEDbulb(v);

    }

    private void updateLightbulb(View v) {
        TextView lightbulbView = v.findViewById(R.id.bulb_number);
        lightbulbView.setText(Double.toString(calculateLightBulb()));
    }

    private void updateLEDbulb(View v) {
        TextView lightbulbView = v.findViewById(R.id.bulb_number);
        lightbulbView.setText(Double.toString(calculateLEDBulb()));
    }

    private void updateGas(View v) {
        TextView gasView = v.findViewById(R.id.gas_number);
        gasView.setText(Double.toString(calculateGas()));
    }

    private void updateCoal(View v) {
        TextView coalView = v.findViewById(R.id.coal_number);
        coalView.setText(Double.toString(calculateCoal()));
    }

    private void updateMiles(View v) {
        TextView mileView = v.findViewById(R.id.walk_number);
        mileView.setText(Double.toString(calculateMiles()));
    }

    private void updateMicrowaveHours(View v) {
        TextView microwaveView = v.findViewById(R.id.microwave_number);
        microwaveView.setText(Double.toString(calculateMicrowaveHours()));
    }

    private void updateFridgeHours(View v) {
        TextView fridgeView = v.findViewById(R.id.fridge_number);
        fridgeView.setText(Double.toString(calculateFridgeHours()));
    }

    private double calculateFridgeHours() {
        fridge = kwh * .5;
        fridge = Math.round(fridge * 100.0) / 100.0;
        return fridge;
    }

    private double calculateMicrowaveHours() {
        microwave = kwh * .83;
        microwave = Math.round(microwave * 100.0) / 100.0;
        return microwave;
    }

    private double calculateMiles() {
        miles = kwh * 1.39;
        miles = Math.round(miles * 100.0) / 100.0;
        return miles;
    }

    private double calculateCoal() {
        coal = kwh;
        coal = Math.round(coal * 100.0) / 100.0;
        return coal;
    }

    private double calculateLightBulb() {
        bulb = kwh * 16.67;
        bulb = Math.round(bulb * 100.0) / 100.0;
        return bulb;
    }

    private double calculateGas() {
        gas = kwh * 3.412;
        gas = Math.round(gas * 100.0) / 100.0;
        return gas;
    }

    private double calculateLEDBulb() {
        ledbulb = kwh * 200;
        ledbulb = Math.round(ledbulb * 100.0) / 100.0;
        return ledbulb;
    }
}
