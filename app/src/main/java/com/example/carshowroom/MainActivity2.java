package com.example.carshowroom;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.carshowroom.ui.main.SectionsPagerAdapter;
import com.example.carshowroom.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;

    private SharedPreferences sharedPreferences;
    private static final String LANGUAGE_PREF = "language_pref";
    private static final String LANGUAGE_KEY = "language_key";
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences(LANGUAGE_PREF, MODE_PRIVATE);
        // Get the current language setting
        boolean isArabic = sharedPreferences.getBoolean(LANGUAGE_KEY, false);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),isArabic);

        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        // Setting icons for each tab
        tabs.getTabAt(0).setIcon(R.drawable.home); // Set your own drawable icons here
        tabs.getTabAt(1).setIcon(R.drawable.star);
        tabs.getTabAt(2).setIcon(R.drawable.settings);
// Update tab titles based on the current language setting
        updateTabTitles(isArabic);


    }

    private void updateTabTitles(boolean isArabic) {
        if (isArabic) {
            tabs.getTabAt(0).setText("الرئيسية");
            tabs.getTabAt(1).setText("المفضلة");
            tabs.getTabAt(2).setText("الإعدادات");
        } else {
            tabs.getTabAt(0).setText("Home");
            tabs.getTabAt(1).setText("Favourite");
            tabs.getTabAt(2).setText("Settings");
        }
    }
}