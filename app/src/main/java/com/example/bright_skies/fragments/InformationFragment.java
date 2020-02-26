package com.example.bright_skies.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import androidx.fragment.app.Fragment;

import com.example.bright_skies.R;
import com.example.bright_skies.managers.ExpandableListAdapter;

public class InformationFragment extends Fragment {

    private String[] groups;
    private String[][] children;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        groups = new String[] { "Current Research", "Legal Information", "Solar World News" };

        children = new String [][] {
                { "..." },
                {"..."},
                {"..."}
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
