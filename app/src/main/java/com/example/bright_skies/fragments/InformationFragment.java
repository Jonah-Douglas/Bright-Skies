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

        groups = new String[] { "Current Research", "Legal Information", "Solar World News", "Tax Information" };

        children = new String [][] {
                { "There are now several exciting new solar panel technologies either in the pipeline or already on the market. These promising technologies will revolutionize the way we think about not just solar, but energy production in general. Solar no longer requires large parcels of land or roof space, nor does it need to look boring. Recent research includes floating solar farms, building-integrated photovoltaics, solar skins (solar panels that belnd in with your roof), solar fabrics (solar filaments can be embedded into your t-shirts, winter coats, or any other clothing to help you keep warmer, power your phone, and provide energy for other needs while you’re on the go), and photovoltaic solar noise barriers (merging noise abatement with sustainable power generation)."  },
                {"There are bills that support, and even demand, the use of solar state-by-state. "},
                {"According to the Department of Energy, in the United States, solar energy is “more affordable, accessible and prevalent than ever before.” According to the Department, since 2008, U.S. installations have increased seventeen-fold from 1.2 GW to nearly 30. This is enough energy to power approximately 5.7 million American homes. SEIA also reports that the average cost of solar PV panels has dropped over 60% and the cost of an entire solar electric system is about half of what it was in 2010."},
                { " Even though the price of solar panels and the cost of installing a solar array have never been lower, you may still qualify for extra rebates or incentives to make the total investment as low as possible for your home or business. Almost every state now offers tax credits as an incentive for citizens to switch to solar. Depending on where you live, you may be able to subsidize a large portion of your solar installation using tax credits."}
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
