package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class New_content_dashboard extends AppCompatActivity {
    Intent intent;
    RelativeLayout relativeLayout;
    private TextView textaepsBalance;
    private TextView textbalance;
    ViewPager Pager;
    Bundle bundle;
    ViewPagerbalanceAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_content_dashboard);

        this.relativeLayout = (RelativeLayout)findViewById(R.id.linear_request);
        this.textbalance = (TextView) findViewById(R.id.view_balance);
        this.textaepsBalance = (TextView) findViewById(R.id.textaepsBalance);
        Pager = findViewById(R.id.viewpager);
        this.intent = getIntent();
        
        getuserbalance();
        
//        Bundle bundle = new Bundle();
//        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//        bundle.putString("Mainbalance", this.intent.getStringExtra("Mainbalance"));
//        bundle.putString("Cashoutbalance", this.intent.getStringExtra("Cashoutbalance"));
//        ViewPagerbalanceAdapter adapter = new ViewPagerbalanceAdapter(getSupportFragmentManager(), bundle);
//        viewPager.setAdapter(adapter);
////        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this.tabLayout));
//


    }

    private void getuserbalance() {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        String username = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
        String password = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, "");
        String device = PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, "");
        String Token = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, "");
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, username);
        body.put("Password", password);
        body.put("DeviceId", device);
        body.put("Token", Token);
        ApiInterface apiInterface2 = RetrofitHandler.getService();
        Call<GetBalance> objbanklist = apiInterface2.getUserBalance(body);
        objbanklist.enqueue(new Callback<GetBalance>() { // from class: com.uvapay.activities.DashboardActivity.32
            @Override // retrofit2.Callback
            public void onResponse(Call<GetBalance> call, Response<GetBalance> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body().getResponseStatus().equals(ConstantClass.MOBILESERVICEID)) {
                    String jsonString = response.body().getData();
                    try {
                        JSONArray jsonArray = new JSONArray(jsonString);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String Main = jsonObject.getString(ConstantClass.USERDETAILS.MainBalance);
                        String Aeps = jsonObject.getString(ConstantClass.USERDETAILS.AEPSBalance);
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("textbalance",Main);
                        bundle1.putString("textaepsBalance",Aeps);
                        ViewPagerbalanceAdapter adapter = new ViewPagerbalanceAdapter(getSupportFragmentManager(),New_content_dashboard.this,bundle1);
                        Pager.setAdapter(adapter);


//                        if (Aeps.equals("null")) {
//                            textbalance.setText("M: " + Main);
//                            textaepsBalance.setText("A: 0.00");
//                            PrefUtils.saveToPrefs(New_content_dashboard.this, "Wallet_Main_Balance", Main);
//                            PrefUtils.saveToPrefs(New_content_dashboard.this, ConstantClass.USERDETAILS.AEPSBalance, "0.00");
//                        } else if (Main.equals("null")) {
//                            textbalance.setText("M: 0.00");
//                            textaepsBalance.setText("A: " + Aeps);
//                            PrefUtils.saveToPrefs(New_content_dashboard.this, "Wallet_Main_Balance", "0.00");
//                            PrefUtils.saveToPrefs(New_content_dashboard.this, ConstantClass.USERDETAILS.AEPSBalance, Aeps);
////                        } else {
//                            textbalance.setText("M: " + Main);
//                            textaepsBalance.setText("A: " + Aeps);
//                            PrefUtils.saveToPrefs(New_content_dashboard.this, "Wallet_Main_Balance", Main);
//                            PrefUtils.saveToPrefs(New_content_dashboard.this, ConstantClass.USERDETAILS.AEPSBalance, Aeps);
//                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<GetBalance> call, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                New_content_dashboard dashboardActivity = New_content_dashboard.this;
                ConstantClass.displayMessageDialog(dashboardActivity, dashboardActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });
    }
}