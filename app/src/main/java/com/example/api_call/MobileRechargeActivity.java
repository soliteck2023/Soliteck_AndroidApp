package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileRechargeActivity extends AppCompatActivity  implements View.OnClickListener{
    private static final int PLANPLANREQUESTCODE = 110;
    private static final int PLANREQUESTCODE = 101;
    private Button btn_mobile_Recharge;
    private EditText edit_amount;
    private EditText edit_mobile;
    private EditText edit_operator;
    private GetOperatorByNum getOperatorByNum;
    private RelativeLayout layout_plans;
    TextView mTxtMobilePlan;
    TextView mTxtROffer;
    private EditText medit_tpin;
    Spinner stateSpinner;
    private String NUMBER = "";
    private String AMT = "";
    private String CALL = "";
    String opCode = "";
    private ProgressDialog progressDialog;

    private String ourcode = "";
    List<OperatorResponseData> MobileoperatorList = new ArrayList();

    List<String> CircleList = new ArrayList();

    List<String> operatorlist;
    List<Operater> operatorDataArrayList = new ArrayList();
    List<Operater> sorted_operatorList = new ArrayList();

    private  String OPTID ="";
    private String Call = "";
    private String Operator = "";

    RecyclerView mobilerecylerview;
    MyofferAdapter myofferAdapter;
    private Timer timer;
    private int currentPage = 0;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_recharge);
//       mobilerecylerview = findViewById(R.id.recycler_mobile);

//        mobilerecylerview.setLayoutManager(new LinearLayoutManager(MobileRechargeActivity.this,RecyclerView.HORIZONTAL,false));
//        List<add> list =new ArrayList<>();
//        list.add(new add(R.drawable.mobile_recharge));
//        list.add(new add(R.drawable.mobile_recharge2));
//        list.add(new add(R.drawable.mobile_recharge));
//        myofferAdapter = new MyofferAdapter(list);  //recyclerview_adpter
//        mobilerecylerview.setAdapter(myofferAdapter);
//        startAutoScroll();


        initComponents();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//       getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Mobile Recharge");

        Spinner stateSpinner = findViewById(R.id.spinnerCircleListList);
        String[] states = getResources().getStringArray(R.array.circle);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(adapter);
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle the selected state here
                String selectedState = states[position];
//                Toast.makeText(MobileRechargeActivity.this, "Selected State: " + selectedState, Toast.LENGTH_SHORT).show();
                // Do something with the selected state
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });


        this.mTxtROffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(MobileRechargeActivity.this.edit_operator.getText().toString().trim())) {
                    MobileRechargeActivity.this.edit_operator.setError("Select Operator");
                    return;
                }
                MobileRechargeActivity.this.edit_operator.setError(null);
                if (TextUtils.isEmpty(MobileRechargeActivity.this.edit_mobile.getText().toString().trim())) {
                    MobileRechargeActivity.this.edit_mobile.setError("Enter number");
                    return;
                }
                MobileRechargeActivity.this.edit_mobile.setError(null);
                Intent intent1 = new Intent(MobileRechargeActivity.this, MobileROfferActivity.class);
                intent1.putExtra("operatorName1", MobileRechargeActivity.this.opCode);
                intent1.putExtra("number", MobileRechargeActivity.this.edit_mobile.getText().toString().trim());
                intent1.putExtra("Call", ConstantClass.MOBILESERVICEID);
                startActivityForResult(intent1, 101);


            }
        });

        this.mTxtMobilePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MobileRechargeActivity.this, RechargePlansActivity.class);
//                intent.putExtra("circleName", MobileRechargeActivity.this.mspinnerCircleListList.getSelectedItem().toString().trim());
                intent.putExtra("operatorName", MobileRechargeActivity.this.edit_operator.getText().toString().trim());
                startActivityForResult(intent, 110);


//                if (TextUtils.isEmpty(MobileRechargeActivity.this.edit_operator.getText().toString().trim())) {
//                    MobileRechargeActivity.this.edit_operator.setError("Select Operator");
//                    return;
//               }
//                MobileRechargeActivity.this.edit_operator.setError(null);
//                if (TextUtils.isEmpty(MobileRechargeActivity.this.mspinnerCircleListList.getSelectedItem().toString())) {
//                    MobileRechargeActivity.this.edit_mobile.setError("Enter Circle");
//                    return;
//                }
//                MobileRechargeActivity.this.edit_mobile.setError(null);
//                Intent intent = new Intent(MobileRechargeActivity.this, RechargePlansActivity.class);
//                intent.putExtra("circleName", MobileRechargeActivity.this.mspinnerCircleListList.getSelectedItem().toString().trim());
//                intent.putExtra("operatorName", MobileRechargeActivity.this.edit_operator.getText().toString().trim());
//                startActivityForResult(intent, 110);

            }
        });

        this.layout_plans.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.MobileRechargeActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MobileRechargeActivity.this.edit_mobile.getText().toString().trim().isEmpty()) {
                    MobileRechargeActivity.this.edit_mobile.setError("Enter number");
                    MobileRechargeActivity.this.edit_mobile.requestFocus();
                } else if (MobileRechargeActivity.this.edit_operator.getText().toString().trim().isEmpty()) {
                    MobileRechargeActivity.this.edit_mobile.setError(null);
                    MobileRechargeActivity.this.edit_operator.setError("Select operator");
                    MobileRechargeActivity.this.edit_operator.requestFocus();
                } else {
                    MobileRechargeActivity.this.edit_operator.setError(null);
                    Intent intent = new Intent(MobileRechargeActivity.this, MobileRechargePlansActivity.class);
                    intent.putExtra("MOBILE", MobileRechargeActivity.this.edit_mobile.getText().toString());
                    intent.putExtra("OPERATOR", MobileRechargeActivity.this.edit_operator.getText().toString());
                    intent.putExtra("AMT", MobileRechargeActivity.this.edit_amount.getText().toString());
                    intent.putExtra("CALL", "MOBILE");
                    MobileRechargeActivity.this.startActivity(intent);
                }
            }
        });

        this.btn_mobile_Recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MobileRechargeActivity.this.edit_mobile.getText().toString().trim().isEmpty()) {
                    MobileRechargeActivity.this.edit_mobile.setError("Enter number");
                    MobileRechargeActivity.this.edit_mobile.requestFocus();
                } else if (MobileRechargeActivity.this.edit_amount.getText().toString().trim().isEmpty()) {
                    MobileRechargeActivity.this.edit_amount.setError("Enter amount");
                    MobileRechargeActivity.this.edit_amount.requestFocus();
                    return;
                }
                MobileRechargeActivity.this.edit_amount.setError(null);
                if (TextUtils.isEmpty(MobileRechargeActivity.this.medit_tpin.getText().toString().trim())) {
                    MobileRechargeActivity.this.medit_tpin.setError("Enter TPIN");
                    return;
                }
                MobileRechargeActivity.this.medit_tpin.setError(null);
                MobileRechargeActivity mobileRechargeActivity = MobileRechargeActivity.this;
                mobileRechargeActivity.getMobileConformation(mobileRechargeActivity.edit_amount.getText().toString(), MobileRechargeActivity.this.edit_mobile.getText().toString(), MobileRechargeActivity.this.edit_operator.getText().toString());

            }
        });
        this.edit_operator.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.MobileRechargeActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent(MobileRechargeActivity.this, OperatorsActivity.class);
                intent.putExtra("NUMBER", MobileRechargeActivity.this.edit_mobile.getText().toString());
                intent.putExtra("AMT", MobileRechargeActivity.this.edit_amount.getText().toString());
                intent.putExtra("CALL", "MOBILE");
                MobileRechargeActivity.this.startActivityForResult(intent, 1);


            }
        });


    }

    private void startAutoScroll() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
//                if (!(currentPage != MyofferAdapter.ImageViewHolder==-1)) {
//                    currentPage = 0;
//                }
                mobilerecylerview.smoothScrollToPosition(++currentPage);
            }
        };

        // Set up a timer to scroll the RecyclerView at regular intervals
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 3000, 3000); // Adjust the timing as needed (e.g., every 2000 milliseconds)
    }{
    }


    private void initComponents() {
        this.medit_tpin = (EditText) findViewById(R.id.edit_tpin);
        this.edit_mobile = (EditText) findViewById(R.id.edit_mobile);
        this.edit_amount = (EditText) findViewById(R.id.edit_amount);
        this.edit_operator = (EditText) findViewById(R.id.edit_operator);
        this.btn_mobile_Recharge = (Button) findViewById(R.id.btn_mobile_Recharge);
        this.layout_plans = (RelativeLayout) findViewById(R.id.layout_plans);
        this.mTxtROffer = (TextView) findViewById(R.id.TxtROffer);
        this.mTxtMobilePlan = (TextView) findViewById(R.id.TxtMobilePlan);
        this.stateSpinner  = (Spinner) findViewById(R.id.spinnerCircleListList);
        this.edit_mobile.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.activities.MobileRechargeActivity.4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (MobileRechargeActivity.this.edit_mobile.getText().toString().trim().length() == 10) {
                    MobileRechargeActivity mobileRechargeActivity = MobileRechargeActivity.this;
                    mobileRechargeActivity.callOperator(mobileRechargeActivity.edit_mobile.getText().toString().trim());
                }
            }
        });
        getAssetsCircle();
        callCircleAdapter();

    }

    private void getMobileConformation(String edit_amount, String edit_mobile,String edit_operator) {   // String edit_operator
        Intent intent = new Intent(this, RechargeConfirmationActivity.class);
        intent.putExtra("MOBILE_NO", edit_mobile);
        intent.putExtra("AMOUNT", edit_amount);
        intent.putExtra("CALL", "MOBILE");
        intent.putExtra("OPERATOR", edit_operator);
        String message = this.medit_tpin.getText().toString().trim();
        String EncodedTPIN = ApplicationConstant.EncodeStringToHMACSHA256(message);
        intent.putExtra("TPIN", EncodedTPIN);
        startActivity(intent);
    }

    private void getAssetsCircle(){
        BufferedReader reader = null;
        try {
            try {
                reader = new BufferedReader(new InputStreamReader(getAssets().open("circle.txt"), Key.STRING_CHARSET_NAME));
                while (true) {
                    String mLine = reader.readLine();
                    if (mLine != null) {
                        this.CircleList.add(mLine);
                    } else {
                        reader.close();
                        return;
                    }
                }
            } catch (IOException e) {
            }
        } catch (Throwable th) {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e2) {
                }
            }
            throw th;
        }
    }

    @SuppressLint("ResourceType")
    private void callCircleAdapter() {
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, (int) R.layout.spinner_item, this.CircleList);
        adapter1.setDropDownViewResource(17367049);
       this.stateSpinner.setAdapter((SpinnerAdapter) adapter1);
    }

    private void callOperator(String number) {
        String username = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
        PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, "");
        String device = PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, "");
        String Token = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, "");
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, username);
        body.put("MobileNumber", number);
        body.put("DeviceId", device);
        body.put("Token", Token);
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<FetchOperator> rechargeress = apiservice.getMobileOPT(body);
        rechargeress.enqueue(new Callback<FetchOperator>() { // from class: com.uvapay.activities.MobileRechargeActivity.5
            @Override // retrofit2.Callback
            public void onResponse(Call<FetchOperator> call, Response<FetchOperator> response) {
                try {
                    if (response.body().getStatusCode().intValue() == 1) {
                        try {
//                            MobileRechargeActivity.this.edit_operator.setText(response.body().getMessage());
                        } catch (Exception e) {
                            ApplicationConstant.DisplayMessageDialog(MobileRechargeActivity.this, "Response", e.getMessage());
                        }
                        return;
                    }
                    ApplicationConstant.DisplayMessageDialog(MobileRechargeActivity.this, "Response", response.body().getMessage());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<FetchOperator> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(MobileRechargeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
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

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == -1) {
                try {
                    if (data.getExtras() != null) {
                        this.NUMBER = data.getExtras().getString("NUMBER");
                        this.AMT = data.getExtras().getString("AMT");
                        this.CALL = data.getExtras().getString("CALL");
                        this.edit_amount.setText(this.AMT);
                        this.edit_mobile.setText(this.NUMBER);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (ConstantClass.datum != null && ConstantClass.datum.getServiceName().equals("MOBILE")) {
                        this.edit_operator.setText(ConstantClass.datum.getName());
                        this.opCode = ConstantClass.datum.getId();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                Toast.makeText(this, "Result Not Ok", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 101 && resultCode == -1) {
            this.edit_amount.setText(String.valueOf(data.getStringExtra("ROFFERAMT")));
        }
        if (requestCode == 110 && resultCode == -1) {
            this.edit_amount.setText(String.valueOf(data.getStringExtra("MAMOUNT")));
        }
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {





//        int txtROffer = R.id.TxtROffer;
//        int txtMobilePlanId = R.id.TxtMobilePlan;
//        if (txtMobilePlanId == txtMobilePlanId) {
//            if (TextUtils.isEmpty(this.edit_operator.getText().toString().trim())) {
//                this.edit_operator.setError("Select Operator");
//                return;
//            }
//            this.edit_operator.setError(null);
//            if (TextUtils.isEmpty(this.mspinnerCircleListList.getSelectedItem().toString())) {
//                    this.edit_mobile.setError("Enter Circle");
//                    return;
//                }
//            this.edit_mobile.setError(null);
//                Intent intent = new Intent(this, RechargePlansActivity.class);
//                intent.putExtra("circleName", this.mspinnerCircleListList.getSelectedItem().toString().trim());
//                intent.putExtra("operatorName", this.edit_operator.getText().toString().trim());
//                startActivityForResult(intent, 110);
//                return;
//
//        } else if (txtROffer == txtROffer){
//            if (TextUtils.isEmpty(this.edit_operator.getText().toString().trim())) {
//                    this.edit_operator.setError("Select Operator");
//                    return;
//                }
//                this.edit_operator.setError(null);
//                if (TextUtils.isEmpty(this.edit_mobile.getText().toString().trim())) {
//                    this.edit_mobile.setError("Enter number");
//                    return;
//                }
//                this.edit_mobile.setError(null);
//                Intent intent1 = new Intent(this, MobileROfferActivity.class);
//                intent1.putExtra("operatorName1", this.opCode);
//                intent1.putExtra("number", this.edit_mobile.getText().toString().trim());
//                intent1.putExtra("Call", ConstantClass.MOBILESERVICEID);
//                startActivityForResult(intent1, 101);
//                return;
//
//        }else {
//            Toast.makeText(this, "Update correct details", Toast.LENGTH_SHORT).show();
//        }







//        switch (v.getId()) {
//            case R.id.TxtMobilePlan /* 2131361872 */:
//                if (TextUtils.isEmpty(this.edit_operator.getText().toString().trim())) {
//                    this.edit_operator.setError("Select Operator");
//                    return;
//                }
//                this.edit_operator.setError(null);
//                if (TextUtils.isEmpty(this.mspinnerCircleListList.getSelectedItem().toString())) {
//                    this.edit_mobile.setError("Enter Circle");
//                    return;
//                }
//                this.edit_mobile.setError(null);
//                Intent intent = new Intent(this, RechargePlansActivity.class);
//                intent.putExtra("circleName", this.mspinnerCircleListList.getSelectedItem().toString().trim());
//                intent.putExtra("operatorName", this.edit_operator.getText().toString().trim());
//                startActivityForResult(intent, 110);
//                return;
//            case R.id.TxtROffer /* 2131361879 */:
//                if (TextUtils.isEmpty(this.edit_operator.getText().toString().trim())) {
//                    this.edit_operator.setError("Select Operator");
//                    return;
//                }
//                this.edit_operator.setError(null);
//                if (TextUtils.isEmpty(this.edit_mobile.getText().toString().trim())) {
//                    this.edit_mobile.setError("Enter number");
//                    return;
//                }
//                this.edit_mobile.setError(null);
//                Intent intent1 = new Intent(this, MobileROfferActivity.class);
//                intent1.putExtra("operatorName1", this.opCode);
//                intent1.putExtra("number", this.edit_mobile.getText().toString().trim());
//                intent1.putExtra("Call", ConstantClass.MOBILESERVICEID);
//                startActivityForResult(intent1, 101);
//                return;
//            default:
//                return;
//        }
    }

}



