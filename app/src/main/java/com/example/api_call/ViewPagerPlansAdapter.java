package com.example.api_call;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerPlansAdapter extends FragmentStatePagerAdapter {

    Bundle bundle;
    int mNumOfTabs;

    public ViewPagerPlansAdapter(FragmentManager fm, int NumOfTabs, Bundle bundle) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.bundle = bundle;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FULLTALKTIMEFragment tab1 = new FULLTALKTIMEFragment();
                tab1.setArguments(this.bundle);
                return tab1;
            case 1:
                TopupFragment tab2 = new TopupFragment();
                tab2.setArguments(this.bundle);
                return tab2;
            case 2:
                ThreeGFragment tab3 = new ThreeGFragment();
                tab3.setArguments(this.bundle);
                return tab3;
            case 3:
                RateCuterFragment tab4 = new RateCuterFragment();
                tab4.setArguments(this.bundle);
                return tab4;
            case 4:
                TWOGFragment tab5 = new TWOGFragment();
                tab5.setArguments(this.bundle);
                return tab5;
            case 5:
                SMSFragment tab6 = new SMSFragment();
                tab6.setArguments(this.bundle);
                return tab6;
            case 6:
                COMBOFragment tab = new COMBOFragment();
                tab.setArguments(this.bundle);
                return tab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 0;
    }
}
