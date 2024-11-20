package com.example.carshowroom.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.carshowroom.FavouriteFragment;
import com.example.carshowroom.HomeFragment;
import com.example.carshowroom.R;
import com.example.carshowroom.SettingsFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private final boolean isArabic;
//    String[] titles={"Home","Favourite","Settings"};
//    Fragment[]  fragments={new HomeFragment(),new FavouriteFragment(),new SettingsFragment()};
//
//    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm, boolean isArabic) {
        super(fm);
//        mContext = context;
        this.isArabic = isArabic;
    }

    @Override
    public Fragment getItem(int position) {

//        return fragments[position];
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new FavouriteFragment();
            case 2:
                return new SettingsFragment();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

//        return titles[position];
        if (isArabic) {
            switch (position) {
                case 0:
                    return "الرئيسية"; // Arabic for Home
                case 1:
                    return "المفضلة"; // Arabic for Favourite
                case 2:
                    return "الإعدادات"; // Arabic for Settings
                default:
                    return null;
            }
        } else {
            switch (position) {
                case 0:
                    return "Home";
                case 1:
                    return "Favourite";
                case 2:
                    return "Settings";
                default:
                    return null;
            }
        }
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3 ;
    }
}