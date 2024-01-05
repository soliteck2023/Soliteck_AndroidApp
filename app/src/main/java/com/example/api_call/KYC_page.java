package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class KYC_page extends AppCompatActivity {
    ViewPager kycpager;
//    TabLayout tab;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_page);
        kycpager = findViewById(R.id.pager2);
//        tab = findViewById(R.id.tab2);
        TabLayout tabLayout = findViewById(R.id.tab2);

        TabLayout.Tab tab = tabLayout.newTab();
        tabLayout.addTab(tab);

        KycpagerAdapter adapter = new KycpagerAdapter(getSupportFragmentManager(), KYC_page.this);
        kycpager.setAdapter(adapter);
        kycpager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(kycpager);

        tabLayout.getTabAt(0).setText("Personal info");
        tabLayout.getTabAt(1).setText("Address Details");
        tabLayout.getTabAt(2).setText("Documents");
        tabLayout.getTabAt(3).setText("Confirmation");


//        tabLayout.setBackgroundColor(R.color.colorPrimary);
//        tabLayout.setTabTextColors(ColorStateList.valueOf(R.color.white));
//


    }
}