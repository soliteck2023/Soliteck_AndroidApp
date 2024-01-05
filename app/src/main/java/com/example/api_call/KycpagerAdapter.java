package com.example.api_call;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class KycpagerAdapter extends FragmentPagerAdapter {

    Context context;

    public KycpagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Personal_information();
            case 1:
                return new Address_Details();
            case 2:
                return new Documents();
            case 3:
                return new Confirmation();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
