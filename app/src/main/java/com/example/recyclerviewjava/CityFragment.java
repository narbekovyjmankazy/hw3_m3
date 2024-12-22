package com.example.recyclerviewjava;

import static com.example.recyclerviewjava.ContinentFragment.url;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.recyclerviewjava.databinding.FragmentCityBinding;
import com.example.recyclerviewjava.databinding.FragmentCountryBinding;

import java.util.ArrayList;
import java.util.List;

public class CityFragment extends Fragment {

    private FragmentCityBinding binding;
    String desc = "Описание абвгд.........";

    private static final String ARG_COUNTRY_NAME = "country_name";

    public static CityFragment newInstance(String countryName) {
        CityFragment fragment = new CityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_COUNTRY_NAME, countryName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCityBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String countryName = getArguments() != null ? getArguments().getString(ARG_COUNTRY_NAME) : "";

        List<GeoItem> cities = new ArrayList<>();
        switch (countryName){
            case "Asia":
                cities.add(new GeoItem("Africa", url, desc));
                cities.add(new GeoItem("Asia", url, desc));
                cities.add(new GeoItem("Europe", url, desc));
                cities.add(new GeoItem("Australia", url, desc));

            case "Africa":
                cities.add(new GeoItem("Africa", url, desc));
                cities.add(new GeoItem("Asia", url, desc));
                cities.add(new GeoItem("Europe", url, desc));
                cities.add(new GeoItem("America", url, desc));
                cities.add(new GeoItem("Australia", url, desc));
            case "America":
                cities.add(new GeoItem("Asia", url, desc));
                cities.add(new GeoItem("Europe", url, desc));
                cities.add(new GeoItem("America", url, desc));
                cities.add(new GeoItem("Australia", url, desc));
            case "Europe":
                cities.add(new GeoItem("Africa", url, desc));
                cities.add(new GeoItem("Asia", url, desc));
                cities.add(new GeoItem("Europe", url, desc));
                cities.add(new GeoItem("Australia", url, desc));
            case "Australia":
                cities.add(new GeoItem("Africa", url, desc));
                cities.add(new GeoItem("Europe", url, desc));
                cities.add(new GeoItem("America", url, desc));
                cities.add(new GeoItem("Australia", url, desc));
        }

        GeoAdapter adapter = new GeoAdapter(cities, item -> {
            CityDetailFragment detailFragment = CityDetailFragment.newInstance(item.getName(), item.getImageUrl(), item.getDescription());
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, detailFragment)
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
