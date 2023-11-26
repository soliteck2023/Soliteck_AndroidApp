package com.example.api_call;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private TextView TextName;

    private ImageView btn_aeps;
    private Button btn_bankDetails;
    private Button btn_bankSettlement;
    private ImageView image_datacard;
    private ImageView image_dth;
    private ImageView image_mobile;
    private ImageView image_postpaid;
    private ImageView image_profile;
    private ImageView image_refresh;
    private ImageView image_request;
    private ImageView image_transfer;
    private LinearLayout linear_Bank;
    private LinearLayout linear_mobile;
    private LinearLayout linear_Ledger;
    private LinearLayout linear_aeps;
    private LinearLayout linear_bbps;
    private LinearLayout linear_earning;
    private LinearLayout linear_latestReport;
    private LinearLayout linear_payment_received_report;
    private LinearLayout linear_payment_transfer_report;
    private LinearLayout linear_paymentgateway;
    private LinearLayout linear_profile;
    private LinearLayout linear_settelment;
    private LinearLayout linear_transactions;
//    PDFView pdf_view;
    private ProgressDialog progressDialog;
    private TextView text_mobile;
    private TextView text_name;
    private TextView text_news;
    private TextView textaepsBalance;
    private TextView textbalance;
//    private ViewCommissionAdapter viewCommissionAdapter;
//    private List<CommissionData> list_margin = new ArrayList();
    private String request_type = "";
    private String UserName = "";
    private String AuthKey = "";
    private String MerchantId = "";
    private String AgentId = "";
//    MyPagerAdapter adapter;
//    ViewPager pager;
    LinearLayout reuest_balance;

    private CheckView checkView;


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//        pager =  findViewById(R.id.viewpager2);    //viewpager reuestbutton

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        LinearLayout linear_mobile = (LinearLayout) findViewById(R.id.linear_mobile);
        LinearLayout linear_dth = (LinearLayout) findViewById(R.id.linear_dth);
        LinearLayout linear_transfer = (LinearLayout) findViewById(R.id.linear_transferdmt);
        LinearLayout linear_postpaid = (LinearLayout) findViewById(R.id.linear_postpaid);
        FloatingActionButton linear_help = (FloatingActionButton) findViewById(R.id.linear_help);
//        LinearLayout linear_paymentgateway = (LinearLayout) findViewById(R.id.linear_paymentgateway);
//        LinearLayout linear_matm = (LinearLayout) findViewById(R.id.linear_matm);
//        LinearLayout linear_request = (LinearLayout) findViewById(R.id.linear_request);
//        LinearLayout linear_statements = (LinearLayout) findViewById(R.id.linear_statements);
        this.linear_Bank = (LinearLayout) findViewById(R.id.linear_banks);
        this.linear_transactions = (LinearLayout) findViewById(R.id.linear_txn);
//        this.linear_payment_received_report = (LinearLayout) findViewById(R.id.linear_payment_received_report);
        LinearLayout linear_request = (LinearLayout) findViewById(R.id.reuest_balance);

        this.linear_payment_transfer_report = (LinearLayout) findViewById(R.id.linear_transferreport);
        this.linear_Ledger = (LinearLayout) findViewById(R.id.linear_leger);
        this.linear_earning = (LinearLayout) findViewById(R.id.linear_earn);
        this.linear_mobile = (LinearLayout) findViewById(R.id.linear_mobile);
//        this.linear_latestReport = (LinearLayout) findViewById(R.id.linear_latestReport);
        this.linear_bbps = (LinearLayout) findViewById(R.id.linear_bbps);
        this.image_mobile = (ImageView) findViewById(R.id.image_mobile);
        this.image_dth = (ImageView) findViewById(R.id.image_dth);
        this.image_transfer = (ImageView) findViewById(R.id.image_transfer);
        this.image_postpaid = (ImageView) findViewById(R.id.image_postpaid);
//        this.image_request = (ImageView) findViewById(R.id.image_request);
//        this.image_datacard = (ImageView) findViewById(R.id.image_datacard);
        this.text_news = (TextView) findViewById(R.id.text_news);
        this.TextName = (TextView) findViewById(R.id.TextName);
//        this.btn_aeps = (ImageView) findViewById(R.id.btn_aeps);
//        this.btn_bankDetails = (Button) findViewById(R.id.btn_bankDetails);
//        this.btn_bankSettlement = (Button) findViewById(R.id.btn_bankSettlement);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dashboard");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);
//        adapter = new MyPagerAdapter(getSupportFragmentManager(),DashboardActivity.this);
//        pager.setAdapter(adapter);

        this.linear_profile = (LinearLayout) headerLayout.findViewById(R.id.linear_profile);
//        this.image_profile = (ImageView) headerLayout.findViewById(R.id.image_profile);
        this.text_name = (TextView) headerLayout.findViewById(R.id.text_name);
          this.text_mobile = (TextView) headerLayout.findViewById(R.id.text_mobile);
           this.textbalance = (TextView) findViewById(R.id.view_balance);
           this.textaepsBalance = (TextView) findViewById(R.id.textaepsBalance);
//        this.image_refresh = (ImageView) findViewById(R.id.image_refresh);
//        this.linear_aeps = (LinearLayout) findViewById(R.id.linear_aeps);
//        this.linear_settelment = (LinearLayout) findViewById(R.id.linear_settelment);
//        navigationView.setNavigationItemSelectedListener(this);
//        navigationView.setItemIconTintList(null);


        linear_request.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.22
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DashboardActivity.this.viewPaymentRequest();
            }
        });



//        linear_mobile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        linear_dth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        linear_postpaid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        linear_transfer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        linear_bbps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

//        linear_aeps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

//        linear_transactions.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        linear_Ledger.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        linear_earning.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        linear_Bank.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    private void viewPaymentRequest() {
        View view_types = getLayoutInflater().inflate(R.layout.layout_view_requests, (ViewGroup) null);
        TextView mText_remark = (TextView) view_types.findViewById(R.id.text_remark);
        final LinearLayout type_history = (LinearLayout) view_types.findViewById(R.id.type_history);
        final TextView text_history = (TextView) view_types.findViewById(R.id.text_history);
        final LinearLayout type_pending = (LinearLayout) view_types.findViewById(R.id.type_pending);
        final TextView text_pending = (TextView) view_types.findViewById(R.id.text_pending);
        Button mBtn_request = (Button) view_types.findViewById(R.id.btn_request);
        mText_remark.setText("Payment Request");
        text_pending.setText("Request Form");
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view_types);
        dialog.show();
        type_history.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.38
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 16) {
                    type_history.setBackground(DashboardActivity.this.getResources().getDrawable(R.drawable.violet_button_background));
                    text_history.setTextColor(-1);
                    type_pending.setBackground(DashboardActivity.this.getResources().getDrawable(R.drawable.text_view_border));
                    text_pending.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    DashboardActivity.this.request_type = "history";
                }
            }
        });
        type_pending.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.39
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 16) {
                    type_pending.setBackground(DashboardActivity.this.getResources().getDrawable(R.drawable.violet_button_background));
                    text_pending.setTextColor(-1);
                    type_history.setBackground(DashboardActivity.this.getResources().getDrawable(R.drawable.text_view_border));
                    text_history.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    DashboardActivity.this.request_type = "form";
                }
            }
        });
        mBtn_request.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.40
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DashboardActivity.this.request_type.equals("form")) {
                    dialog.dismiss();
                    Intent intent = new Intent(DashboardActivity.this, PaymentRequestActivity.class);
                    DashboardActivity.this.startActivity(intent);
                } else if (DashboardActivity.this.request_type.equals("history")) {
                    dialog.dismiss();
                    Intent intent2 = new Intent(DashboardActivity.this, PayRequestHistoryActivity.class);
                    DashboardActivity.this.startActivity(intent2);
                }
            }
        });
    }

//    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
//        if (!ConstantClass.isNetworkAvailable(this)) {
//            ConstantClass.displayMessageDialog(this, "No Internet Connection", "Please enable internet connection first to proceed");
//            return;
//        }
        getUserBalance(this.textbalance, this.textaepsBalance);
        String news = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.News, "");
        String name = PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.Name, "");
        String mobile = PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.MobileNo, "");
        if (news.equals("null")) {
            this.text_news.setText("Welcome to Soliteck application");
            this.text_news.setSelected(true);
        } else {
            this.text_news.setText(news);
            this.text_news.setSelected(true);
        }
        this.TextName.setText(name);
        this.TextName.setSelected(true);
       this.text_name.setText(name);
       this.text_mobile.setText(mobile);
    }

    private void getUserBalance(TextView textbalance, TextView textaepsBalance) {
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
                        if (Aeps.equals("null")) {
                            textbalance.setText("M: " + Main);
                            textaepsBalance.setText("A: 0.00");
                            PrefUtils.saveToPrefs(DashboardActivity.this, "Wallet_Main_Balance", Main);
                            PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.USERDETAILS.AEPSBalance, "0.00");
                        } else if (Main.equals("null")) {
                            textbalance.setText("M: 0.00");
                            textaepsBalance.setText("A: " + Aeps);
                            PrefUtils.saveToPrefs(DashboardActivity.this, "Wallet_Main_Balance", "0.00");
                            PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.USERDETAILS.AEPSBalance, Aeps);
                        } else {
                            textbalance.setText("M: " + Main);
                            textaepsBalance.setText("A: " + Aeps);
                            PrefUtils.saveToPrefs(DashboardActivity.this, "Wallet_Main_Balance", Main);
                            PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.USERDETAILS.AEPSBalance, Aeps);
                        }
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
                DashboardActivity dashboardActivity = DashboardActivity.this;
                ConstantClass.displayMessageDialog(dashboardActivity, dashboardActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });
    }

//    private void getProfileDetails() {
//        String username = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
//        String password = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, "");
//        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
//        progressDialog.show();
//        HashMap<String, String> body = new HashMap<>();
//        body.put(ConstantClass.PROFILEDETAILS.UserName_, username);
//        body.put("Password", password);
//        ProfileApiService apiServiceGenerator = (ProfileApiService) RetrofitHandler.getService();
//        Call<GetUserProfileDetails> objbanklist = apiServiceGenerator.getUserProfileDetails(body);
//        objbanklist.enqueue(new Callback<GetUserProfileDetails>() { // from class: com.uvapay.activities.DashboardActivity.33
//            @Override // retrofit2.Callback
//            public void onResponse(Call<GetUserProfileDetails> call, Response<GetUserProfileDetails> response) {
//                ProgressDialog progressDialog2 = progressDialog;
//                if (progressDialog2 != null && progressDialog2.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                GetUserProfileDetails profileresponse = response.body();
//                try {
//                    DashboardActivity.this.text_name.setText(profileresponse.getFirstName());
//                    DashboardActivity.this.text_mobile.setText(profileresponse.getContactNo());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.Name, response.body().getFirstName());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.Middle, response.body().getMiddleName());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.Last, response.body().getLastName());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.StateName, response.body().getStatesName());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.CityName, response.body().getCityName());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.ShopAddress, response.body().getShopAddress());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.PermanentAddress, response.body().getPermanentAddress());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.PinCode, response.body().getPinCode());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.zipcode, response.body().getZipCode());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.MobileNo, response.body().getContactNo());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.EmailId, response.body().getEmailID());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.PanCard, response.body().getPanCardNo());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.Aadhar, response.body().getAadharNo());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.BanksName, response.body().getBanksName());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.AccountNo, response.body().getAccountNo());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.IFSCCode, response.body().getIFSCCode());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.RefrenceNumber, response.body().getRefrenceNumber());
//                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.WhiteUser, response.body().getWhiteUser());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override // retrofit2.Callback
//            public void onFailure(Call<GetUserProfileDetails> call, Throwable t) {
//                ProgressDialog progressDialog2 = progressDialog;
//                if (progressDialog2 != null && progressDialog2.isShowing()) {
//                    progressDialog.dismiss();
//                }
//            }
//        });
//    }


    @Override // com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_orders) {
            startActivity(new Intent(this, HistoryTransactionsActivity.class));
        } else if (id == R.id.nav_mobile) {
            Intent intent = new Intent(this, MobileRechargeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_dth) {
            Intent intent2 = new Intent(this, DTHRechargeActivity.class);
            startActivity(intent2);
        } else if (id == R.id.nav_contactus) {
            Intent intent3 = new Intent(this, ContactUsActivity.class);
            startActivity(intent3);
        } else if (id == R.id.nav_transfer) {
            Intent intent4 = new Intent(this, MoneyTransferActivity.class);
            startActivity(intent4);
        } else if (id == R.id.nav_password) {
            View view = getLayoutInflater().inflate(R.layout.layout_change_password, (ViewGroup) null);
            ImageView image_cancel = (ImageView) view.findViewById(R.id.image_cancel);
            final EditText edit_old_password = (EditText) view.findViewById(R.id.edit_old_password);
            final TextView forgot_tpin = (TextView) view.findViewById(R.id.forgot_tpin);
            final EditText edit_new_password = (EditText) view.findViewById(R.id.edit_new_password);
            final EditText edit_confirm_password = (EditText) view.findViewById(R.id.edit_confirm_password);
            TextView textView = (TextView) view.findViewById(R.id.text_pass_change);
            Button btn_change_password = (Button) view.findViewById(R.id.btn_change_password);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.layout_hide);
//            CheckView checkView = view.findViewById(R.id.check);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final AlertDialog alertDialog = builder.create();
            alertDialog.setView(view);
            image_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.25
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
            btn_change_password.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.26
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    forgot_tpin.setVisibility(View.VISIBLE);
                    if (!ConstantClass.isNetworkAvailable(DashboardActivity.this)) {
                        ConstantClass.displayMessageDialog(DashboardActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
                    } else if (edit_old_password.getText().toString().isEmpty()) {
                        edit_old_password.setError("Enter Old Password");
                        edit_old_password.requestFocus();
                    } else if (edit_new_password.getText().toString().isEmpty()) {
                        edit_old_password.setError(null);
                        edit_new_password.setError("Enter New Password");
                        edit_new_password.requestFocus();
                    } else if (edit_confirm_password.getText().toString().isEmpty()) {
                        edit_new_password.setError(null);
                        edit_confirm_password.setError("Enter Confirm Password");
                        edit_confirm_password.requestFocus();
                    } else if (!edit_confirm_password.getText().toString().trim().equals(edit_new_password.getText().toString().trim())) {
                        edit_confirm_password.setError("New Password & Confirm should be same");
                        edit_confirm_password.requestFocus();
                    } else {
                        DashboardActivity.this.getChangePassword(edit_old_password.getText().toString(), edit_new_password.getText().toString(), edit_confirm_password.getText().toString(), alertDialog);
                    }
                }
            });
            if (Build.VERSION.SDK_INT >= 21) {
                alertDialog.create();
                alertDialog.show();
            }
        } else if (id == R.id.nav_postpaid) {
            Intent intent5 = new Intent(this, PostpaidRechargeActivity.class);
            startActivity(intent5);
        } else if (id != R.id.nav_gateway) {
            if (id == R.id.nav_datacard) {
                Intent intent6 = new Intent(this, DataCardRechargeActivity.class);
                startActivity(intent6);
            } else if (id != R.id.nav_share) {
                if (id == R.id.nav_payment_request) {
                    startActivity(new Intent(this, PaymentRequestActivity.class));
                } else if (id == R.id.nav_statements) {
                    startActivity(new Intent(this, StatementReportActivity.class));
                } else if (id != R.id.nav_send) {
                    if (id == R.id.nav_logout) {
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else if (id == R.id.nav_chnagetpin) {
                        View view2 = getLayoutInflater().inflate(R.layout.layout_change_password, (ViewGroup) null);
                        TextView forgot_tpin2 = (TextView) view2.findViewById(R.id.forgot_tpin);
                        forgot_tpin2.setVisibility(View.VISIBLE);
                        final ImageView image_cancel2 = (ImageView) view2.findViewById(R.id.image_cancel);
                        final TextView mTxtTitle = (TextView) view2.findViewById(R.id.TxtTitle);
                        mTxtTitle.setText("Change TPIN");
                        final EditText edit_old_password2 = (EditText) view2.findViewById(R.id.edit_old_password);
                        edit_old_password2.setHint("Enter Old TPIN");
                        final EditText edit_new_password2 = (EditText) view2.findViewById(R.id.edit_new_password);
                        ApplicationConstant.setEditTextMaxLength(edit_new_password2, 4);
                        edit_new_password2.setHint("Enter New TPIN");
                        final EditText edit_confirm_password2 = (EditText) view2.findViewById(R.id.edit_confirm_password);
                        edit_confirm_password2.setHint("Confirm New TPIN");
                        ApplicationConstant.setEditTextMaxLength(edit_confirm_password2, 4);
                        TextView textView2 = (TextView) view2.findViewById(R.id.text_pass_change);
                        Button btn_change_password2 = (Button) view2.findViewById(R.id.btn_change_password);
                        final LinearLayout layout_hide = (LinearLayout) view2.findViewById(R.id.layout_hide);
//                        final CheckView check = (CheckView) view2.findViewById(R.id.checkbox_remember);
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                        final AlertDialog alertDialog2 = builder2.create();
                        alertDialog2.setView(view2);
                        image_cancel2.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.27
                            @Override // android.view.View.OnClickListener
                            public void onClick(View v) {
                                alertDialog2.dismiss();
                            }
                        });
                        forgot_tpin2.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.28
                            @Override // android.view.View.OnClickListener
                            public void onClick(View v) {
                                DashboardActivity.this.getForgotTpin(checkView, mTxtTitle, alertDialog2);
                                layout_hide.setVisibility(View.VISIBLE);
                                image_cancel2.setVisibility(View.VISIBLE);
                            }
                       });
                        btn_change_password2.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.29
                            @Override // android.view.View.OnClickListener
                            public void onClick(View v) {
                                if (!ConstantClass.isNetworkAvailable(DashboardActivity.this)) {
                                    ConstantClass.displayMessageDialog(DashboardActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
                                } else if (edit_old_password2.getText().toString().isEmpty()) {
                                    edit_old_password2.setError("Enter Old Tpin");
                                    edit_old_password2.requestFocus();
                                } else if (edit_new_password2.getText().toString().isEmpty()) {
                                    edit_old_password2.setError(null);
                                    edit_new_password2.setError("Enter New Tpin");
                                    edit_new_password2.requestFocus();
                                } else if (edit_confirm_password2.getText().toString().isEmpty()) {
                                    edit_new_password2.setError(null);
                                    edit_confirm_password2.setError("Enter Confirm Tpin");
                                    edit_confirm_password2.requestFocus();
                                } else if (!edit_confirm_password2.getText().toString().trim().equals(edit_new_password2.getText().toString().trim())) {
                                    edit_confirm_password2.setError("New Tpin & Confirm should be same");
                                    edit_confirm_password2.requestFocus();
                                } else {
                                    DashboardActivity.this.getChangeTpin(edit_old_password2.getText().toString(), edit_new_password2.getText().toString(), edit_confirm_password2.getText().toString(), alertDialog2);
                                }
                            }
                        });
                        if (Build.VERSION.SDK_INT >= 21) {
                            alertDialog2.create();
                            alertDialog2.show();
                        }
                    }
                }
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getChangeTpin(String oldTpin, String newTpin, String confirm, final AlertDialog alertDialog) {
        String username = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
        PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, "");
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, username);
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("Password", newTpin);
        body.put("ConfirmPassword", confirm);
        body.put("OldPassword", oldTpin);
        body.put("PasswordType", "2");
        ProfileApiService apiServiceGenerator3 = (ProfileApiService) RetrofitHandler.getService();
        Call<ChangePasswordResponse> call = apiServiceGenerator3.ChangeTpinResponse(body);
        call.enqueue(new Callback<ChangePasswordResponse>() { // from class: com.uvapay.activities.DashboardActivity.31
            @Override // retrofit2.Callback
            public void onResponse(Call<ChangePasswordResponse> call2, Response<ChangePasswordResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getStatusCode().intValue() == 1) {
                        ApplicationConstant.displayToastMessage(DashboardActivity.this, response.body().getMessage());
                        return;
                    }
                    ApplicationConstant.DisplayMessageDialog(DashboardActivity.this, "Response", response.body().getMessage());
                    alertDialog.dismiss();
                    return;
                }
                ProgressDialog progressDialog3 = progressDialog;
                if (progressDialog3 != null && progressDialog3.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(DashboardActivity.this, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



            @Override // retrofit2.Callback
            public void onFailure(Call<ChangePasswordResponse> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                DashboardActivity dashboardActivity = DashboardActivity.this;
                ConstantClass.displayMessageDialog(dashboardActivity, dashboardActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });
    }

    private void getForgotTpin(final CheckView check, final TextView mTxtTitle, AlertDialog alertDialog) {
        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
        this.progressDialog = dialogue;
        dialogue.show();
        String username = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
        ProfileApiService apiServiceGenerator2 = (ProfileApiService) RetrofitHandler.getService();
        Call<OtpSentResponse> call = apiServiceGenerator2.getForgotTpin("Authentication/ForgotTPin?UserName=" + username);
        call.enqueue(new Callback<OtpSentResponse>() { // from class: com.uvapay.activities.DashboardActivity.30
            @Override // retrofit2.Callback
            public void onResponse(Call<OtpSentResponse> call2, Response<OtpSentResponse> response) {
                if (response != null) {
                    if (response.body().getStatusCode().equals("0")) {
                        if (DashboardActivity.this.progressDialog != null && DashboardActivity.this.progressDialog.isShowing()) {
                            DashboardActivity.this.progressDialog.dismiss();
                        }
                        ConstantClass.displayMessageDialog(DashboardActivity.this, "Response", response.body().getMessage());
                        return;
                    }
                    if (DashboardActivity.this.progressDialog != null && DashboardActivity.this.progressDialog.isShowing()) {
                        DashboardActivity.this.progressDialog.dismiss();
                    }
                    check.setVisibility(View.VISIBLE);
                    mTxtTitle.setText(response.body().getMessage());
                    check.check();
                    ConstantClass.displayToastMessage(DashboardActivity.this, response.body().getMessage());
                } else if (DashboardActivity.this.progressDialog != null && DashboardActivity.this.progressDialog.isShowing()) {
                    DashboardActivity.this.progressDialog.dismiss();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<OtpSentResponse> call2, Throwable t) {
                if (DashboardActivity.this.progressDialog != null && DashboardActivity.this.progressDialog.isShowing()) {
                    DashboardActivity.this.progressDialog.dismiss();
                }
                DashboardActivity dashboardActivity = DashboardActivity.this;
                ConstantClass.displayMessageDialog(dashboardActivity, dashboardActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });
    }

    private void getChangePassword(String old_password, String new_password, String confirm, final AlertDialog alertDialog) {
        String username = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
        PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, "");
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, username);
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("Password", new_password);
        body.put("ConfirmPassword", confirm);
        body.put("OldPassword", old_password);
        body.put("PasswordType", ConstantClass.MOBILESERVICEID);
        ProfileApiService apiServiceGenerator = (ProfileApiService) RetrofitHandler.getService();
        Call<ChangePasswordResponse> call = apiServiceGenerator.ChangePasswordResponse(body);
        call.enqueue(new Callback<ChangePasswordResponse>() { // from class: com.uvapay.activities.DashboardActivity.34
            @Override // retrofit2.Callback
            public void onResponse(Call<ChangePasswordResponse> call2, Response<ChangePasswordResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getStatusCode().intValue() == 1) {
                        ApplicationConstant.displayToastMessage(DashboardActivity.this, response.body().getMessage());
                        DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, MainActivity.class));
                        DashboardActivity.this.finish();
                        return;
                    }
                    ApplicationConstant.DisplayMessageDialog(DashboardActivity.this, "Response", response.body().getMessage());
                    alertDialog.dismiss();
                    return;
                }
                ProgressDialog progressDialog3 = progressDialog;
                if (progressDialog3 != null && progressDialog3.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(DashboardActivity.this, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ChangePasswordResponse> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                DashboardActivity dashboardActivity = DashboardActivity.this;
                ConstantClass.displayMessageDialog(dashboardActivity, dashboardActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });
    }


}
