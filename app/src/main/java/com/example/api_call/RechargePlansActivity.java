package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.google.android.material.tabs.TabLayout;

public class RechargePlansActivity extends AppCompatActivity {

    Intent intent;
    private Context mContext;
    RelativeLayout relativeLayout;
    TabLayout tabLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_plans);
        setTitle("Mobile Plan");
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        this.tabLayout = tabLayout;
        tabLayout.addTab(tabLayout.newTab().setText("FULLTT"));
        TabLayout tabLayout2 = this.tabLayout;
        tabLayout2.addTab(tabLayout2.newTab().setText("Top Up"));
        TabLayout tabLayout3 = this.tabLayout;
        tabLayout3.addTab(tabLayout3.newTab().setText("3G/4G"));
        TabLayout tabLayout4 = this.tabLayout;
        tabLayout4.addTab(tabLayout4.newTab().setText("RATE CUTTER"));
        TabLayout tabLayout5 = this.tabLayout;
        tabLayout5.addTab(tabLayout5.newTab().setText("2G"));
        TabLayout tabLayout6 = this.tabLayout;
        tabLayout6.addTab(tabLayout6.newTab().setText("SMS"));
        TabLayout tabLayout7 = this.tabLayout;
        tabLayout7.addTab(tabLayout7.newTab().setText("COMBO"));
        this.tabLayout.setTabGravity(0);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        this.relativeLayout = (RelativeLayout) findViewById(R.id.main_layout);
        this.intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString("circleName", this.intent.getStringExtra("circleName"));
        bundle.putString("operator", this.intent.getStringExtra("operatorName"));
        ViewPagerPlansAdapter adapter = new ViewPagerPlansAdapter(getSupportFragmentManager(), this.tabLayout.getTabCount(), bundle);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this.tabLayout));
        this.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.uvapay.activities.RechargePlansActivity.1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    @SuppressLint("MissingSuperCall")
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200) {
            setResult(resultCode, data);
            finish();
        }
    }
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }




}