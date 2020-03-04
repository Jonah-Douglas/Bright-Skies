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

        groups = new String[] { "What Are Solar Panels?", "Why Do I Have to Give My Location?", "What is a Kilowatt Hour?", "Why Should I Consider Solar?", "How Do Solar Panels Work?", "Will My Solar Panels Generate Electricity During Cloudy, Rainy, or Snowy Days?", " Do I Have to Go Off the Grid When I Switch to Solar Energy?", "Are Solar Panels Difficult to Maintain?", "Will I Ever Need to Replace My Solar Panels?" };

        children = new String [][] {
                { "Solar panels are comprised of many smaller units called photovoltaic cells, which convert sunlight to energy. They allow photons, or particles of light, to knock electrons free from atoms, generating a flow of electricity" },
                { "We need your location in order to generate and return solar potential results. Your information is never saved or used for purposes outside of providing you solar energy information." },
                { "The kilowatt-hour (symbolized kWh) is a unit of energy equivalent to one kilowatt (1 kW) of power expended for one hour (1 h) of time. Check out our energy calculator to see what that means in daily-use terms!" },
                { "Switching to solar energy, for most people, means great monetary benefits in the long run. Also, solar produces no harmful emissions that hurt the environment. It's a clean, renewable process that uses the most natural of all resources: the sun." },
                { " Solar panels are made of highly excitable, conductive materials. When the sun’s rays hit the solar panels, the reaction creates direct current (DC) electricity."},
                { " Your solar panels don’t need sunshine, per se, to generate electricity as much as they need direct, unobstructed access to the sun’s UV rays. " +
                        "\n" +
                        "Similar to how your skin still tans when it’s overcast outside, your solar panels will still generate electricity during cloudy, rainy, or snowy days — they just won’t produce as much energy as they do during clear days." },
                { " In reality, you probably won’t want to go off grid. The practice is known as “islanding”, or when you connect your solar array to batteries so you’ll be able to power your home or business entirely on solar energy instead of using the utility company for power.\n" +
                        "\n" +
                        "The thing is, this practice is incredibly expensive and inefficient for most people.\n" +
                        "\n" +
                        "Batteries are not only expensive, they’re not as technically developed as solar panels (read: they clunk up the operation). They also need to be maintained and replaced routinely.\n" +
                        "\n" +
                        "Most solar adopters choose to stay on the grid out of convenience and money-saving."},
                { " Solar panels are easier to maintain than your property’s HVAC, appliances, and maybe even your cell phone. " +
                        "\n" +
                        "That’s because solar panels have zero moving parts to break. The most you’ll have to do to maintain your solar panels is make sure they’re free of dust, pollen, leaves, and other debris. Whatever’s obstructing your panels will make for less efficient energy production."},
                { " Reputable solar providers now offer solar panels with manufacturer’s warranties that last between 20–30 years. " +
                        "\n" +
                        "Newer models have expected lifespans of 50+ years."}
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
