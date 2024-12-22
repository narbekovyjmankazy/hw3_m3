package com.example.recyclerviewjava;

import static com.example.recyclerviewjava.ContinentFragment.url;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.recyclerviewjava.databinding.FragmentCountryBinding;

import java.util.ArrayList;
import java.util.List;

public class CountryFragment extends Fragment {

    private FragmentCountryBinding binding;

    private static final String ARG_CONTINENT_NAME = "continent_name";

    public static CountryFragment newInstance(String continentName) {
        CountryFragment fragment = new CountryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CONTINENT_NAME, continentName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCountryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String continentName = getArguments() != null ? getArguments().getString(ARG_CONTINENT_NAME) : "";

        List<GeoItem> countries = new ArrayList<>();

        switch (continentName){
            case "Asia":
                countries.add(new GeoItem("Asia", url, null));
                countries.add(new GeoItem("Europe", url, null));
                countries.add(new GeoItem("America", url, null));
                countries.add(new GeoItem("Australia", url, null));

            case "Africa":
                countries.add(new GeoItem("Africa", url, null));
                countries.add(new GeoItem("Europe", url, null));
                countries.add(new GeoItem("America", url, null));
                countries.add(new GeoItem("Australia", url, null));
            case "America":
                countries.add(new GeoItem("Africa", url, null));
                countries.add(new GeoItem("Asia", url, null));
                countries.add(new GeoItem("Europe", url, null));
                countries.add(new GeoItem("America", url, null));
                countries.add(new GeoItem("Australia", url, null));
            case "Europe":
                countries.add(new GeoItem("Africa", url, null));
                countries.add(new GeoItem("Europe", url, null));
                countries.add(new GeoItem("Australia", url, null));
            case "Australia":
                countries.add(new GeoItem("Africa", url, null));
                countries.add(new GeoItem("Europe", url, null));
                countries.add(new GeoItem("Australia", url, null));
        }

        GeoAdapter adapter = new GeoAdapter(countries, item -> {
            CityFragment cityFragment = CityFragment.newInstance(item.getName());
            countries.clear();
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, cityFragment)
                    .addToBackStack(null)
                    .commit();
        });

        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
