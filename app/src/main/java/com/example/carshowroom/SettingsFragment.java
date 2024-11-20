package com.example.carshowroom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


public class SettingsFragment extends Fragment {
    public static final String LANGUAGE_PREF = "language_pref";
    public static final String LANGUAGE_KEY = "language_key";
    private SharedPreferences sharedPreferences;
    Button buttonInstructions;
    TextView switchLanguage;

    Button buttonCall;


    public SettingsFragment() {
        super(R.layout.fragment_settings);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        buttonInstructions =view.findViewById(R.id.buttonInstructions);
        switchLanguage=view.findViewById(R.id.switchLanguage);

        buttonCall = view.findViewById(R.id.buttonCall);

        buttonCall.setOnClickListener(v -> {
            String phoneNumber = "tel:01120206369"; // Replace with the desired phone number
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse(phoneNumber));
            startActivity(callIntent);
        });

        buttonInstructions.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InstructionsActivity.class);
            startActivity(intent);
        });

//        sharedPreferences = requireActivity().getSharedPreferences("favourites", requireContext().MODE_PRIVATE);
        sharedPreferences = requireActivity().getSharedPreferences(LANGUAGE_PREF, requireContext().MODE_PRIVATE);

        Switch switchLanguage = view.findViewById(R.id.switchLanguage);

        // Set the switch based on saved preference
        boolean isArabic = sharedPreferences.getBoolean(LANGUAGE_KEY, false);
        switchLanguage.setChecked(isArabic);

        // Adjust layout direction based on language
        if (isArabic) {
            buttonInstructions.setText(" تعليمات التطبيق ");
            buttonCall.setText(" الدعم الفني ");
            switchLanguage.setText(" تغيير اللغة ");
        } else {
            buttonInstructions.setText(" App Instructions ");
            buttonCall.setText(" Call Support ");
            switchLanguage.setText(" Change Language ");
        }

        switchLanguage.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveLanguagePreference(isChecked);

            // Restart activity to update the UI language
            if (getActivity() != null) {
                getActivity().recreate();
            }
        });

        return view;
    }

    private void saveLanguagePreference(boolean isArabic) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LANGUAGE_KEY, isArabic);
        editor.apply();
    }


}