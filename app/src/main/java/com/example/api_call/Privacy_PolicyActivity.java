package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Privacy_PolicyActivity extends AppCompatActivity {

    TextView user_namepolicy;
    TextView shopname_policy;
    TextView date;
    private List<agremment_class> agremmentClasses;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy2);
        user_namepolicy= findViewById(R.id.user_namepolicy);
        shopname_policy= findViewById(R.id.shopname_policy);
        date= findViewById(R.id.date);
        policymethod();

    }

    private void policymethod() {

        HashMap<String, String> body = new HashMap<>();
        String username = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
        String device = PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, "");
        String Token = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, "");
        body.put("UniqueCode", username);
        body.put("DeviceId", device);
        body.put("Token", Token);
        ApiInterface apiInterface = RetrofitHandler.getService2();
        Call<AggrementCertificateclass> call = apiInterface.AggrementCertificatemethod(body);
        call.enqueue(new Callback<AggrementCertificateclass>() {
            @Override
            public void onResponse(Call<AggrementCertificateclass> call, Response<AggrementCertificateclass> response) {
//                Toast.makeText(Privacy_PolicyActivity.this, "Response", Toast.LENGTH_SHORT).show();

                AggrementCertificateclass aggrementCertificateclass = response.body();
                if (aggrementCertificateclass.getResponseStatus() == 1){
                    List<agremment_class> agremmentClasses1 = response.body().getUserData();
                    aggrementCertificateclass.setUserData(agremmentClasses1);

                    if (!agremmentClasses1.isEmpty()){
                      agremment_class userData = agremmentClasses1.get(0);
                      user_namepolicy.setText("" +userData.getfName());
                      shopname_policy.setText("" +userData.getShopName());
                      date.setText("" +userData.getIncorporateDate());

                  }else {
                     ApplicationConstant.displayToastMessage(Privacy_PolicyActivity.this,"Error");

                  }

                }




//                if (response.body().getResponseStatus() ==1){
//                    List<agremment_class> jsonString = response.body().getUserData();
//                    try {
//                        JSONArray jsonArray = new JSONArray(jsonString);
//                        JSONObject jsonObject = jsonArray.getJSONObject(0);
//                        String Username = jsonObject.getString(ConstantClass.USERDETAILS.UserName);
//                        String ShopName = jsonObject.getString(ConstantClass.USERDETAILS.Shopname);
//
//                    if (Username.equals(null)){
//                        user_namepolicy.setText("" +Username);
//                        PrefUtils.saveToPrefs(Privacy_PolicyActivity.this, "Wallet_UserName", Username);
//                    }
//
//
//                    } catch (JSONException e) {
//                        throw new RuntimeException(e);
//                    }
//
//
//                }

//


            }

            @Override
            public void onFailure(Call<AggrementCertificateclass> call, Throwable t) {
                Toast.makeText(Privacy_PolicyActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }






}