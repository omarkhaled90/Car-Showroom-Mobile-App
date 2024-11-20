package com.example.carshowroom;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;


public class FavouriteFragment extends Fragment {
    private List<car_type> favouriteCarsList = new ArrayList<>();
    private CarAdapter adapter;

    private SharedPreferences favouritesPreferences; // Use separate SharedPreferences for favourites

    RecyclerView recyclerView;

    public FavouriteFragment()
    {
        super(R.layout.fragment_favourite);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourite, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get saved favourites from SharedPreferences
         favouritesPreferences = getContext().getSharedPreferences("favourites_data", getContext().MODE_PRIVATE);
        // Load favourites from SharedPreferences
        loadFavourites();

        // Set up adapter for the RecyclerView
        adapter = new CarAdapter(favouriteCarsList, favouritesPreferences);
        recyclerView.setAdapter(adapter);
        ImageButton buttonResetFavourites = rootView.findViewById(R.id.buttonResetFavourites);
        buttonResetFavourites.setOnClickListener(v -> {
            // Your reset action logic
            resetFavourites();
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Reload the data from SharedPreferences
        loadFavourites();
    }

    private void loadFavourites() {
        favouriteCarsList.clear();
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("favourites", getContext().MODE_PRIVATE);

        String savedData = favouritesPreferences.getString("favourites_data", "");

        if (!savedData.isEmpty()) {
            String[] carsData = savedData.split(";");
            for (String carData : carsData) {
                String[] carInfo = carData.split(",");
                if (carInfo.length == 5) {
                    try {
                        favouriteCarsList.add(new car_type(
                                carInfo[0], // CompanyName
                                carInfo[1], // carName
                                Integer.parseInt(carInfo[2]), // model
                                carInfo[3], // maker
                                Integer.parseInt(carInfo[4]) // carLogo
                        ));
                    } catch (NumberFormatException e) {
                        e.printStackTrace(); // Log parsing errors
                    }
                }
            }
        }
        // Notify the adapter about data changes if it is initialized
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

    }

    // Reset SharedPreferences and clear the list
    private void resetFavourites() {
        SharedPreferences.Editor editor = favouritesPreferences.edit();
        editor.clear();  // Clear all saved favourites
        editor.apply();  // Apply changes

        // Clear the list and notify the adapter
        favouriteCarsList.clear();
        adapter.notifyDataSetChanged();

    }
}