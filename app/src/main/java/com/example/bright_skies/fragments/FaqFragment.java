package com.example.bright_skies.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import androidx.fragment.app.Fragment;

import com.example.bright_skies.R;
import com.example.bright_skies.managers.ExpandableListAdapter;

public class FaqFragment extends Fragment {

    private String[] groups;
    private String[][] children;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        groups = new String[] { "What Are Solar Panels?", "Why Do I Have to Give My Location?", "What is a Kilowatt Hour?", "Why Should I Consider Solar?" };

        children = new String [][] {
                { "Solar panels are comprised of many smaller units called photovoltaic cells, which convert sunlight to energy. They allow photons, or particles of light, to knock electrons free from atoms, generating a flow of electricity" },
                { "We need your location in order to generate and return solar potential results. Your information is never saved or used for purposes outside of providing you solar energy information." },
                { "The kilowatt-hour (symbolized kWh) is a unit of energy equivalent to one kilowatt (1 kW) of power expended for one hour (1 h) of time. Check out our energy calculator to see what that means in daily-use terms!" },
                { "Switching to solar energy, for most people, means great monetary benefits in the long run. Also, solar produces no harmful emissions that hurt the environment. It's a clean, renewable process that uses the most natural of all resources: the sun." }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.information_expandable, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ExpandableListView expandableListView = view.findViewById(R.id.expandableListView);
        expandableListView.setAdapter(new ExpandableListAdapter(this, groups, children));
        expandableListView.setGroupIndicator(null);

    }

}
