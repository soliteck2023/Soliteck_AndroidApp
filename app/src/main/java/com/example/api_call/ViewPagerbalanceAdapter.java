package com.example.api_call;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerbalanceAdapter extends FragmentStatePagerAdapter {
    Context context;
    Bundle bundle;
//    int mNumOfTabs;


    public ViewPagerbalanceAdapter(@NonNull FragmentManager fm, Context context, Bundle bundle) {
        super(fm);
        this.context = context;
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
     switch (position){
         case 0:
             main_balance screen1 =new main_balance();
             screen1.setArguments(this.bundle);
             return screen1;
         case 1:
             cash_depository screen2 = new cash_depository();
             screen2.setArguments(this.bundle);
             return screen2;
         default:
             return null;
      }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
