package com.example.api_call;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.EnquiryForm;
import com.bumptech.glide.Glide;
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
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {  //implements NavigationView.OnNavigationItemSelectedListener
    private final String UserName = "";
    private final String AuthKey = "";
    private final String MerchantId = "";
    private final String AgentId = "";
    private final int currentPage = 0;
    private final List<CommissionData> list_margin = new ArrayList();
    private final boolean isBalanceVisible = false;
    LinearLayout menuBank;
    Intent intent;
    MyofferAdapter myofferAdapter;
    TextView continuetext;
    ImageView mainbalance, cashoutbalance;
    LinearLayout linermain_blance, linearcashout_balance, compaint_rpt;
    AlertDialog alertDialog;
    Bitmap bitmap;
    private TextView TextName;
    private EditText amountEditText;
    private EditText tpinEditText;
    private CheckBox show_password;
    private Integer Tpin;
    private ImageView image_dth;
    private ImageView image_mobile;
    private ImageView image_postpaid;
    private ImageView image_profile;
    private ImageView image_refresh;
    private LinearLayout linear_Ledger;
    private LinearLayout linear_aeps;
    private LinearLayout linear_bbps;
    private LinearLayout linear_earning;
    private LinearLayout linear_latestReport;
    private LinearLayout linear_payment_received_report;
    private LinearLayout linear_pending_txn_report;
    private LinearLayout linear_cashout_txn_report;
    private LinearLayout linear_payment_transfer_report;
    //    ViewPagerbalanceAdapter ViewPagerbalanceAdapter;
    private LinearLayout linear_paymentgateway;
    private LinearLayout linear_profile;
    private LinearLayout pay_history;
    private LinearLayout linear_transactions;
    private ProgressDialog progressDialog;
    private TextView text_mobile;
    private TextView text_name;
    private TextView textaepsBalance;
    private TextView textbalance;
    //    private ViewCommissionAdapter viewCommissionAdapter;
//    private List<CommissionData> list_margin = new ArrayList();
    private String request_type = "";
    private CheckView checkView;
    private Timer timer;
    private ViewCommissionAdapter viewCommissionAdapter;
    //   ImageView advertiseing;
    GifImageView advertiseing;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mainbalance = findViewById(R.id.hideimge1);
        cashoutbalance = findViewById(R.id.hideimge2);
        textbalance = findViewById(R.id.textbalance);
        textaepsBalance = findViewById(R.id.textaepsBalance);

        linermain_blance = findViewById(R.id.main_liner);
        linearcashout_balance = findViewById(R.id.cashout_liner);
        PackageManager packageManager = getPackageManager();


        Spinner menuspinner = (Spinner) findViewById(R.id.menuspinner);

//menuspinner.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        List<String> policy = new ArrayList<String>();
//        policy.add("User Agrement");
//        policy.add("Certificate");
//        policy.add("Saftey & Sercurity");
//        policy.add("Privacy Policy");
//        policy.add("Terms & Condition");
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(DashboardActivity.this, 17367043, 16_908_308, policy);
//        final Dialog dialog = new Dialog(DashboardActivity.this);
//        dialog.setContentView(R.layout.material_list);
//        dialog.setCancelable(false);
//        dialog.setTitle("Select Payment Mode");
//        final ListView listView = (ListView) dialog.findViewById(R.id.list);
//        dialog.show();
//        listView.setAdapter((ListAdapter) adapter);
//
//    }
//});

//        menuspinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                List<String> policy = new ArrayList<String>();
//                policy.add("User Agrement");
//                policy.add("Certificate");
//                policy.add("Saftey & Sercurity");
//                policy.add("Privacy Policy");
//                policy.add("Terms & Condition");
//                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(DashboardActivity.this, android.R.layout.simple_spinner_item, policy);
//                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                menuspinner.setAdapter(dataAdapter);
//            }
//        });
        linermain_blance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainbalance.getVisibility() == View.VISIBLE) {
                    mainbalance.setVisibility(View.GONE);
                    textbalance.setVisibility(View.VISIBLE);
                } else if (textbalance.getVisibility() == View.VISIBLE) {
                    // If textbalance is visible, hide it and show mainbalance
                    textbalance.setVisibility(View.GONE);
                    mainbalance.setVisibility(View.VISIBLE);
                } else {
                    mainbalance.setVisibility(View.VISIBLE);
                    textbalance.setVisibility(View.GONE);
                }

            }
        });

        linearcashout_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    if (cashoutbalance.getVisibility() == View.VISIBLE) {
                        cashoutbalance.setVisibility(View.GONE);
                        textaepsBalance.setVisibility(View.VISIBLE);
                    } else if (textaepsBalance.getVisibility() == View.VISIBLE) {
                        // If textbalance is visible, hide it and show mainbalance
                        textaepsBalance.setVisibility(View.GONE);
                        cashoutbalance.setVisibility(View.VISIBLE);
                    } else {
                        cashoutbalance.setVisibility(View.VISIBLE);
                        textaepsBalance.setVisibility(View.GONE);
                    }

                }

            }
        });

        continuetext = findViewById(R.id.text_news);
        Animation animation = new TranslateAnimation(800, -1000, 0, 0);
        animation.setDuration(9500);
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(Animation.INFINITE);
        continuetext.setAnimation(animation);
//        Pager =  findViewById(R.id.reuqst_logout);    //viewpager reuestbutton
//        adapter1 = new MyPagerAdapter(getSupportFragmentManager(),DashboardActivity.this);
//        Pager.setAdapter(adapter1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);
        this.linear_profile = (LinearLayout) headerLayout.findViewById(R.id.linear_profile);
        this.image_profile = (ImageView) headerLayout.findViewById(R.id.image_profile);
        this.text_name = (TextView) headerLayout.findViewById(R.id.text_name);
        this.text_mobile = (TextView) headerLayout.findViewById(R.id.text_mobile);
//        this.textbalance = (TextView) findViewById(R.id.textbalance);
//        this.textaepsBalance = (TextView) findViewById(R.id.textaepsBalance);
//        this.image_refresh = (ImageView) findViewById(R.id.image_refresh);
        this.linear_aeps = (LinearLayout) findViewById(R.id.linear_aeps);
//        this.linear_settelment = (LinearLayout) findViewById(R.id.linear_settelment);

        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) DashboardActivity.this);
        navigationView.setItemIconTintList(null);
        advertiseing = findViewById(R.id.recyclerAd);  //recyclerview
//        advertiseing.setAdapter(new CustomAdpter(this));
//       advertiseing.setLayoutManager(new LinearLayoutManager(DashboardActivity.this, RecyclerView.HORIZONTAL, false));
//        List<add> list = new ArrayList<>();
////        Glide.with(this).load(R.drawable.world_braille_day).into(advertiseing);
//        list.add(new add(R.drawable.banner2));
//        list.add(new add(R.drawable.mobile_offeradd));
//        list.add(new add(R.drawable.pravasi_divas_9th_jan));
//        list.add(new add(R.drawable.world_braille_day));
//        list.add(new add(R.drawable.mobile_offeradd));
//        list.add(new add(R.drawable.banner2));
//        CustomAdpter adpter = new CustomAdpter(this);
//               myofferAdapter = new MyofferAdapter(list);  //recyclerview_adpter
//        advertiseing.setAdapter(myofferAdapter);
//        startAutoScroll();

        //>>>>>>>>>>>>>>>>>>>>>> declaration of Main menu >>>>>>>>>>>>>>>>>>>>
        LinearLayout linear_request = (LinearLayout) findViewById(R.id.first_linear);
        menuBank = findViewById(R.id.second_linier);
        this.linear_payment_received_report = (LinearLayout) findViewById(R.id.third_linear);
        LinearLayout walletSettelement_Req = (LinearLayout) findViewById(R.id.fourth);


        //>>>>>>>>>>>>>>> declaration of Payment And Recharge >>>>>>>>>>>>>>>>>>>>>
        LinearLayout linear_mobile = (LinearLayout) findViewById(R.id.linear_mobile);
        LinearLayout linear_dth = (LinearLayout) findViewById(R.id.linear_dth);
        LinearLayout linear_postpaid = (LinearLayout) findViewById(R.id.linear_postpaid);
        LinearLayout linear_transfer = (LinearLayout) findViewById(R.id.linear_transferdmt);
        this.linear_bbps = (LinearLayout) findViewById(R.id.linear_bbps);
        this.linear_aeps = (LinearLayout) findViewById(R.id.linear_aeps);


        //>>>>>>>>>>>>>>>declaration of Reoprt And Other >>>>>>>>>>>>>>>>>>>>>>>
        this.linear_transactions = (LinearLayout) findViewById(R.id.linear_txn);
        this.linear_pending_txn_report = (LinearLayout) findViewById(R.id.linear_pending);
        this.linear_earning = (LinearLayout) findViewById(R.id.linear_earn);
        this.linear_latestReport = (LinearLayout) findViewById(R.id.latest_rpt);

        this.linear_Ledger = (LinearLayout) findViewById(R.id.linear_latetledger);
        LinearLayout linear_cashout_txn_report = (LinearLayout) findViewById(R.id.linear_cashout_txn);
        LinearLayout Cashout_ledger_transaction = (LinearLayout) findViewById(R.id.linear_cashoutledger_txn);
        LinearLayout commisstion_rpt = (LinearLayout) findViewById(R.id.linear_commisstion);
        LinearLayout compaint_rpt = (LinearLayout) findViewById(R.id.linear_cmplaint2);
        LinearLayout pay_history = (LinearLayout) findViewById(R.id.pay_history);


        FloatingActionButton linear_help = (FloatingActionButton) findViewById(R.id.linear_help);
        this.image_mobile = (ImageView) findViewById(R.id.image_mobile);
        this.image_dth = (ImageView) findViewById(R.id.image_dth);
        this.TextName = (TextView) findViewById(R.id.TextName1);

        this.textaepsBalance = (TextView) findViewById(R.id.textaepsBalance);
        this.image_refresh = (ImageView) findViewById(R.id.image_refresh1);
        LinearLayout linear_settelment = (LinearLayout) findViewById(R.id.linear_compliant);


//        this.linear_profile.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.20
//            @Override // android.view.View.OnClickListener
//            public void onClick(View v) {
//                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
//                DashboardActivity.this.startActivity(intent);
//            }
//        });
        this.image_refresh.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                getUserBalance(textbalance, DashboardActivity.this.textaepsBalance);
                continuetestmethod();
            }
        });


        linear_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, EnquiryForm.class));
            }
        });

        //>>>>>>>>>>>>>>>>>>>>> Main Menu Function >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        //Paymet Request
        linear_request.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
//                DashboardActivity.this.viewPaymentRequest();

                DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, PaymentRequestActivity.class));


            }
        });

        //BankList
        menuBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, CompanyBankListActivity.class));

            }
        });


        //Internal Load
        linear_payment_received_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, PaymentReceivedReportActivity.class));

            }
        });

        //AEPS settlement
        walletSettelement_Req.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DashboardActivity.this.viewSettelmentFragment();
            }
        });


        //>>>>>>>>>>>>>>>>>>>>>>>>>>>> Payment And Recharge >>>>>>>>>>>>>>>>>>>>>>

        linear_mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MobileRechargeActivity.class);
                DashboardActivity.this.startActivity(intent);

            }
        });

        linear_dth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, DTHRechargeActivity.class);  //DTHRechargeActivity
                DashboardActivity.this.startActivity(intent);

            }
        });

        linear_postpaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, PostpaidRechargeActivity.class);
                DashboardActivity.this.startActivity(intent);

            }
        });

        linear_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MoneyTransferActivity.class);
                DashboardActivity.this.startActivity(intent);
//                DashboardActivity.this.viewdmtRequest();

            }
        });

//        linear_bbps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        linear_aeps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //click on this if condition will set
                //if{ASPE IS NOT INSTALL---- DIRECTLY goes on PLAY STORE .
                //else{AEPS is installed then Token will genrate with the help of AEPS API and and once token will genrate then that token will pass to install app(ex.when clcik on button redirectly to next activity with data this functionality use) and that token will pass to install app.


//                int keyCode= 0;
//                if (keyCode == KeyEvent.KEYCODE_ENTER) {
//                    // Toast.makeText(this, "Application Name", Toast.LENGTH_LONG).show();
//                    Intent i = new Intent();
//                    i.setAction(Intent.ACTION_VIEW);
//                    i.setClassName("com.example.cpi_call",
//                            "https://play.google.com/store/search?q=SmartSDK&c=appse");
//
//                    startActivity(i);
//
//                }
//                String packageName = "https://play.google.com/store/search?q=SmartSDK&c=apps"; // replace with the actual package name of your app
//                PackageManager packageManager = getPackageManager();
//
//
//                try {
//                    PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
//
//                    // App is installed, launch the app
//                    Intent launchIntent = packageManager.getLaunchIntentForPackage(packageName);
//                    if (launchIntent != null) {
//                        startActivity(launchIntent);
//                    } else {
//                        // Handle the case where the launch intent is null
//                    }
//                } catch (PackageManager.NameNotFoundException e) {
//                    // App is not installed, open the Play Store link
//                    String playStoreLink = "https://play.google.com/store/search?q=SmartSDK&c=apps";
//                    Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(playStoreLink));
//
//                    // Check if there's an app that can handle the intent
//                    if (playStoreIntent.resolveActivity(getPackageManager()) != null) {
//                        startActivity(playStoreIntent);
//                    } else {
//                        // Handle the case where there is no app to handle the intent
//                    }
//                }
            }
        });


//>>>>>>>>>>>>>>>>>>>>>>>>>Report And Other >>>>>>>>>>>>>>>>>>>>>>>>
        //Transaction Report
        linear_transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, TransactionsReportActivity.class));
            }
        });

        //Pending Transaction Report

        linear_pending_txn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, pendingReceivedReportActivity.class));

            }
        });

        //Myearning Report

        linear_earning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, MyEarningReportActivity2.class));

            }
        });

        //Latest Report
        linear_latestReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, LatestReportActivity.class));

            }
        });
        //Ledger Report

        linear_Ledger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, LedgerReportActivity.class));
            }
        });

        //Cashout Transaction Report

        linear_cashout_txn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, Ledger_cashouttxn_ReportActivity.class));

            }
        });

        //Cashout Ledger Report

        Cashout_ledger_transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, Ledger_cashoutLedger_ReportActivity.class));

            }
        });

        //Commission Report

        commisstion_rpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, viewCommissionActivity.class));


            }
        });

        //Complaint Raise

        compaint_rpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, user_raisecompliant.class)); ///  viewCompaintActivity
            }
        });

        ///  payment history
        pay_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, PayRequestHistoryActivity.class)); ///  payment history

            }
        });

    }

    private void aepsmethod() {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(DashboardActivity.this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        String defaultMerchantID = "8286171818";
        String merchantID = PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.MerchantID, "");
        //  body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("UserName", "8286171818");
        body.put("AuthKey", "83e90e4c-5801-47ac-934f-7818f020b94a");
        if ("9372879094".equals("MerchantID")) {
            merchantID = "8286171818";
        } else {
            merchantID = defaultMerchantID;
        }
        body.put("MerchantID", merchantID);
        body.put("UniqueAgentID", "soliteck");
        body.put("ServiceID", "151");
        ApiInterface apiInterface = RetrofitHandler.getService3();
        Call<TokenResponse> result = apiInterface.getSDGenerateAuthToken(body);
        result.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (!response.body().getStatusCode().equals(ConstantClass.MOBILESERVICEID)) {
                        ApplicationConstant.DisplayMessageDialog(DashboardActivity.this, "Response", response.body().getMessage());
                        return;
                    }
                    return;
                }
                try {
                    ApplicationConstant.DisplayMessageDialog(DashboardActivity.this, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                ApplicationConstant.DisplayMessageDialog(DashboardActivity.this, "", t.getMessage());
            }
        });


    }

    private void goturl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }


    private void filter(String s) {
        List<CommissionData> listnew_Banks = new ArrayList<>();
        for (CommissionData allBankResponse : this.list_margin) {
//            if (allBankResponse.getOperatorName().toLowerCase().contains(s.toLowerCase())) {
//                listnew_Banks.add(allBankResponse);
//            }
        }
        this.viewCommissionAdapter.filter(listnew_Banks);
    }


    private void startAutoScroll() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
//                if (!(currentPage != MyofferAdapter.ImageViewHolder==-1)) {
//                    currentPage = 0;
//                }
//                advertiseing.smoothScrollToPosition(++currentPage);
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
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint("MissingInflatedId")
    private void viewdmtRequest() {
        View view_types = getLayoutInflater().inflate(R.layout.layout_viewdmt_requests, (ViewGroup) null);
        TextView mText_remark = (TextView) view_types.findViewById(R.id.text_remark1);
        final LinearLayout type_dmt = (LinearLayout) view_types.findViewById(R.id.linear_transferdmt);
        final TextView text_history = (TextView) view_types.findViewById(R.id.text_history);
        final ImageView image_dmt = (ImageView) view_types.findViewById(R.id.dmt1);
        final LinearLayout type_dmt1 = (LinearLayout) view_types.findViewById(R.id.linear_transferdmt1);
        final ImageView image_dmt1 = (ImageView) view_types.findViewById(R.id.dmt2);
        final TextView text_pending = (TextView) view_types.findViewById(R.id.text_pending);
        mText_remark.setText("Data Money Transfer");
        text_pending.setText("DMT1");
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view_types);
        dialog.show();
        image_dmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 16) {
                    image_dmt.setBackground(DashboardActivity.this.getResources().getDrawable(R.drawable.text_view_border));
                    text_history.setTextColor(-1);
                    image_dmt.setBackground(DashboardActivity.this.getResources().getDrawable(R.drawable.text_view_border));
                    image_dmt1.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                    DashboardActivity.this.request_type = "dmt";
                    Intent intent = new Intent(DashboardActivity.this, MoneyTransferActivity.class);
                    DashboardActivity.this.startActivity(intent);
                }
            }
        });

        image_dmt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 16) {
                    image_dmt1.setBackground(DashboardActivity.this.getResources().getDrawable(R.drawable.text_view_border));
                    text_history.setTextColor(-1);
                    image_dmt.setBackground(DashboardActivity.this.getResources().getDrawable(R.drawable.text_view_border));
//                    image_dmt.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                    DashboardActivity.this.request_type = "dmt";
                    Intent intent = new Intent(DashboardActivity.this, MoneyTransferActivity.class);
                    DashboardActivity.this.startActivity(intent);
                }
            }
        });
    }


    private void viewPaymentRequest() {
        View view_types = getLayoutInflater().inflate(R.layout.layout_view_requests, (ViewGroup) null);
        TextView mText_remark = (TextView) view_types.findViewById(R.id.text_remark);
        final LinearLayout type_history = (LinearLayout) view_types.findViewById(R.id.type_history);
        final TextView text_history = (TextView) view_types.findViewById(R.id.text_history);
        final LinearLayout type_pending = (LinearLayout) view_types.findViewById(R.id.type_pending);
        final TextView text_pending = (TextView) view_types.findViewById(R.id.text_pending);
//        Button mBtn_request = (Button) view_types.findViewById(R.id.btn_request);
        mText_remark.setText("Payment Request");
        text_pending.setText("Request Form");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setView(view_types);
        alertDialog.show();
        //        final BottomSheetDialog dialog = new BottomSheetDialog(this);
//        dialog.setContentView(view_types);
//        dialog.show();
        type_history.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 16) {
                    type_history.setBackground(DashboardActivity.this.getResources().getDrawable(R.drawable.violet_button_background));
//                    text_history.setTextColor(-1);
//                    type_pending.setBackground(DashboardActivity.this.getResources().getDrawable(R.drawable.text_view_border));
//                    text_pending.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    DashboardActivity.this.request_type = "history";
                    Intent intent = new Intent(DashboardActivity.this, PayRequestHistoryActivity.class);
                    DashboardActivity.this.startActivity(intent);
                }
            }
        });
        type_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 16) {

                    type_pending.setBackground(DashboardActivity.this.getDrawable(R.drawable.violet_button_background));
//                    text_pending.setTextColor(-1);
//                    type_history.setBackground(DashboardActivity.this.getDrawable(R.drawable.text_view_border));
//                    text_history.setTextColor(View.MEASURED_STATE_MASK);
                    DashboardActivity.this.request_type = "form";
                    Intent intent = new Intent(DashboardActivity.this, PaymentRequestActivity.class);
                    DashboardActivity.this.startActivity(intent);

                }
            }
        });

    }


    private void viewSettelmentFragment() {
        View view_types = getLayoutInflater().inflate(R.layout.layout_walletsettelment_fragment, (ViewGroup) null);
        TextView mText_remark = (TextView) view_types.findViewById(R.id.text_remark);
        final TextView text_submit = (TextView) view_types.findViewById(R.id.textView);
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view_types);
        dialog.show();
        DashboardActivity.this.amountEditText = view_types.findViewById(R.id.Amount);
        DashboardActivity.this.tpinEditText = view_types.findViewById(R.id.Tpin);
        DashboardActivity.this.show_password = view_types.findViewById(R.id.show_password);
        tpinEditText.setTransformationMethod(new PasswordTransformationMethod());
        show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.uvapay.activities.LoginActivity.4
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {

                    tpinEditText.setTypeface(Typeface.DEFAULT);
                    tpinEditText.setTransformationMethod(new HideReturnsTransformationMethod());
                } else {

                    DashboardActivity.this.tpinEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        text_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Amount = null;
                String Tpin = null;
                String Encodetpin = null;
                if (amountEditText != null && tpinEditText != null) {
                    Amount = String.valueOf(amountEditText.getText());
                    Tpin = String.valueOf(tpinEditText.getText());
                    String tpin = tpinEditText.getText().toString().trim();
                    Encodetpin = ApplicationConstant.EncodeStringToHMACSHA256(tpin);
                }

                if (Amount != null && !Amount.equals("") && Tpin != null && !Tpin.equals("")) {
                    final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(DashboardActivity.this);
                    progressDialog.show();
                    HashMap<String, String> body = new HashMap<>();
                    body.put("DeviceId", PrefUtils.getFromPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
                    body.put("UniqueCode", PrefUtils.getFromPrefs(DashboardActivity.this, ConstantClass.USERDETAILS.UserName, ""));
                    body.put("Token", PrefUtils.getFromPrefs(DashboardActivity.this, ConstantClass.USERDETAILS.Token, ""));
                    body.put("Amount", Amount);
                    body.put("Tpin", Encodetpin);
                    ApiInterface apiservice = RetrofitHandler.getService2();
                    Call<SettlementBase> result = apiservice.AEPSWalletSettelement(body);
                    result.enqueue(new Callback<SettlementBase>() {
                        @Override
                        public void onResponse(Call<SettlementBase> call, Response<SettlementBase> response) {
                            ProgressDialog progressDialog2 = progressDialog;
                            if (progressDialog2 != null && progressDialog2.isShowing()) {
                                progressDialog.dismiss();
                            }
                            if (response.body().getResponseStatus().intValue() == 1) {
                                ApplicationConstant.DisplayMessageDialog(DashboardActivity.this, "Success", response.body().getRemarks());
                            } else {
                                ApplicationConstant.DisplayMessageDialog(DashboardActivity.this,"Error" ,response.body().getRemarks());
                            }
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            if (alertDialog != null) {
                                alertDialog.dismiss();
                            }

                        }

                        @Override
                        public void onFailure(Call<SettlementBase> call, Throwable t) {
                            ProgressDialog progressDialog2 = progressDialog;
                            if (progressDialog2 != null && progressDialog2.isShowing()) {
                                progressDialog.dismiss();
                            }
                        }
                    });
                } else {
                    mText_remark.setText("Please enter the details !!");
                    mText_remark.setTextColor(Color.RED);
                }
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        continuetestmethod();
        getUserBalance(this.textbalance, this.textaepsBalance);

//        String news = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.News, "");
        String name = PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.Name, "");
        String mobile = PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.MobileNo, "");

        this.TextName.setText(name);
        this.text_name.setText(name);
//        this.text_mobile.setText(mobile);  /// HERE NEED TO SHOW MOBILE NO
    }

    private void continuetestmethod() {
        String username = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
        String device = PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, "");
        String Token = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, "");
        HashMap<String, String> body = new HashMap<>();
        body.put("UniqueCode", username);
        body.put("DeviceId", device);
        body.put("Token", Token);
        ApiInterface apiservice = RetrofitHandler.getService2();
        Call<NewsResponse> call = apiservice.getnews(body);
        call.enqueue(new Callback<NewsResponse>() {
            @SuppressLint({"CheckResult", "ResourceType"})
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse newsResponse = response.body();
                if (newsResponse.getResponseStatus() == 1) {
                    List<DataItem> dataItems = response.body().getData();
                    newsResponse.setData(dataItems);
                    if (dataItems != null && !dataItems.isEmpty()) {
                        for (DataItem dataItem : dataItems) {
                            String type = dataItem.getType();
                            String description = (String) dataItem.getDiscription();
                            String Notification = (ConstantClass.USERDETAILS.Notification);
                            String Banner = ConstantClass.USERDETAILS.Banner;
                            String newsPath = dataItem.getNewsPath();

                            if (type.equals("Notification")){

                                if (Notification.equals(type)) {
                                    continuetext.setText("" + description);
                                    PrefUtils.saveToPrefs(DashboardActivity.this, "Continue Text description", description);
                                } else {
                                    continuetext.setText("Welcome to Soliteck application");
                                    PrefUtils.saveToPrefs(DashboardActivity.this, "Continue Text description", description);
                                }
                            } else if (type.equals("Banner")) {
                                //gif wcall code succeesfully
                                if (newsPath != null) {
//                                    for (int i =0;i<imageList.size();i++){
//                                        byte[] decodedBytes = Base64.decode(newsPath, Base64.DEFAULT);
//                                        Bitmap bitmap1 = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
//                                        horizontalScrollView.addView(advertiseing);
//                                        advertiseing.setImageBitmap(bitmap1);
//                                    }
                                    byte[] decodedBytes = Base64.decode(newsPath, Base64.DEFAULT);
                                    Bitmap bitmap1 = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                                    advertiseing.setImageBitmap(bitmap1);
                                    Glide.with(DashboardActivity.this).load(decodedBytes).into(advertiseing);

                                } else {
                                    List<add> list = new ArrayList<>();
                                    list.add(new add(R.drawable.banner_7thfeb));
                                }
                            }

                        }

                    }
                }


            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(DashboardActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });


    }


    private void getUserBalance(final TextView textbalance, final TextView textaepsBalance) {
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
            @Override
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
                        JSONObject userData = jsonArray.getJSONObject(0);
                        String Main = jsonObject.getString(ConstantClass.USERDETAILS.MainBalance);
                        String Aeps = jsonObject.getString(ConstantClass.USERDETAILS.AEPSBalance);
                        double mainBalance = userData.getDouble("MainBalance");  // Format the "MainBalance" with two decimal places
                        double aepsBalance = userData.getDouble("AEPSBalance");
                        String formattedMainBalance = String.format("%.2f", mainBalance);
                        String formattedAEPSBalance = String.format("%.2f", aepsBalance);
                        if (Aeps.equals("null")) {
                            textbalance.setText("" + formattedMainBalance);
                            textaepsBalance.setText("0.00");
                            PrefUtils.saveToPrefs(DashboardActivity.this, "Wallet_Main_Balance", Main);
                            PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.USERDETAILS.AEPSBalance, "0.00");
                        } else if (Main.equals("null")) {
                            textbalance.setText("0.00");
                            textaepsBalance.setText(" " + formattedAEPSBalance);
                            PrefUtils.saveToPrefs(DashboardActivity.this, "Wallet_Main_Balance", "0.00");
                            PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.USERDETAILS.AEPSBalance, Aeps);
                        } else {
                            textbalance.setText("" + formattedMainBalance);
                            textaepsBalance.setText("" + Aeps);
                            PrefUtils.saveToPrefs(DashboardActivity.this, "Wallet_Main_Balance", Main);
                            PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.USERDETAILS.AEPSBalance, Aeps);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetBalance> call, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                DashboardActivity dashboardActivity = DashboardActivity.this;
                ConstantClass.displayMessageDialog(dashboardActivity, dashboardActivity.getString(R.string.server_problem), t.getMessage());
            }
        });
    }

    private void getProfileDetails() {
        String username = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
        String password = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, "");
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, username);
        body.put("Password", password);
        ProfileApiService apiServiceGenerator = (ProfileApiService) RetrofitHandler.getService();
        Call<GetUserProfileDetails> objbanklist = apiServiceGenerator.getUserProfileDetails(body);
        objbanklist.enqueue(new Callback<GetUserProfileDetails>() { // from class: com.uvapay.activities.DashboardActivity.33
            @Override // retrofit2.Callback
            public void onResponse(Call<GetUserProfileDetails> call, Response<GetUserProfileDetails> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                GetUserProfileDetails profileresponse = response.body();
                try {
                    DashboardActivity.this.text_name.setText(profileresponse.getFirstName());
                    DashboardActivity.this.text_mobile.setText(profileresponse.getContactNo());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.Name, response.body().getFirstName());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.Middle, response.body().getMiddleName());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.Last, response.body().getLastName());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.StateName, response.body().getStatesName());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.CityName, response.body().getCityName());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.ShopAddress, response.body().getShopAddress());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.PermanentAddress, response.body().getPermanentAddress());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.PinCode, response.body().getPinCode());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.zipcode, response.body().getZipCode());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.MobileNo, response.body().getContactNo());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.EmailId, response.body().getEmailID());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.PanCard, response.body().getPanCardNo());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.Aadhar, response.body().getAadharNo());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.BanksName, response.body().getBanksName());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.AccountNo, response.body().getAccountNo());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.IFSCCode, response.body().getIFSCCode());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.RefrenceNumber, response.body().getRefrenceNumber());
                    PrefUtils.saveToPrefs(DashboardActivity.this, ConstantClass.PROFILEDETAILS.WhiteUser, response.body().getWhiteUser());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetUserProfileDetails> call, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });


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
        ApiInterface apiInterface = RetrofitHandler.getService();
        Call<ChangePasswordResponse> call = apiInterface.ChangeTpinResponse(body);

//        ProfileApiService apiServiceGenerator3 = (ProfileApiService) RetrofitHandler.getService();
//        Call<ChangePasswordResponse> call = apiServiceGenerator3.ChangeTpinResponse(body);
        call.enqueue(new Callback<ChangePasswordResponse>() { // from class: com.uvapay.activities.DashboardActivity.31
            @Override // retrofit2.Callback
            public void onResponse(Call<ChangePasswordResponse> call2, Response<ChangePasswordResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getStatusCode().intValue() == 1) {
//                        ConstantClass.displayMessageDialog(DashboardActivity.this, "Response", response.body().getMessage());
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


            @Override
            public void onFailure(Call<ChangePasswordResponse> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                DashboardActivity dashboardActivity = DashboardActivity.this;
                ConstantClass.displayMessageDialog(dashboardActivity, dashboardActivity.getString(R.string.server_problem), t.getMessage());
            }
        });
    }

//    private void getForgotTpin(final TextView mTxtTitle, AlertDialog alertDialog) {
//        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
//        this.progressDialog = dialogue;
//        dialogue.show();
//        String username = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
//        ProfileApiService apiServiceGenerator2 = (ProfileApiService) RetrofitHandler.getService();
//        Call<OtpSentResponse> call = apiServiceGenerator2.getForgotTpin("Authentication/ForgotTPin?UserName=" + username);
//        call.enqueue(new Callback<OtpSentResponse>() { // from class: com.uvapay.activities.DashboardActivity.30
//            @Override // retrofit2.Callback
//            public void onResponse(Call<OtpSentResponse> call2, Response<OtpSentResponse> response) {
//                if (response != null) {
//                    if (response.body().getStatusCode().equals("0")) {
//                        if (DashboardActivity.this.progressDialog != null && DashboardActivity.this.progressDialog.isShowing()) {
//                            DashboardActivity.this.progressDialog.dismiss();
//                        }
//                        ConstantClass.displayMessageDialog(DashboardActivity.this, "Response", response.body().getMessage());
//                        return;
//                    }
//                    if (DashboardActivity.this.progressDialog != null && DashboardActivity.this.progressDialog.isShowing()) {
//                        DashboardActivity.this.progressDialog.dismiss();
//                    }
////                    check.setVisibility(View.VISIBLE);
////                    mTxtTitle.setText(response.body().getMessage());
////                    check.check();
//                    ConstantClass.displayToastMessage(DashboardActivity.this, response.body().getMessage());
//                } else if (DashboardActivity.this.progressDialog != null && DashboardActivity.this.progressDialog.isShowing()) {
//                    DashboardActivity.this.progressDialog.dismiss();
//                }
//            }
//
//            @Override // retrofit2.Callback
//            public void onFailure(Call<OtpSentResponse> call2, Throwable t) {
//                if (DashboardActivity.this.progressDialog != null && DashboardActivity.this.progressDialog.isShowing()) {
//                    DashboardActivity.this.progressDialog.dismiss();
//                }
//                DashboardActivity dashboardActivity = DashboardActivity.this;
//                ConstantClass.displayMessageDialog(dashboardActivity, dashboardActivity.getString(R.string.server_problem), t.getMessage());
//            }
//        });
//    }

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
        ApiInterface apiInterface = RetrofitHandler.getService();
        Call<ChangePasswordResponse> call = apiInterface.ChangePasswordResponse(body);

        call.enqueue(new Callback<ChangePasswordResponse>() {
            @Override // retrofit2.Callback
            public void onResponse(Call<ChangePasswordResponse> call2, Response<ChangePasswordResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getStatusCode().intValue() == 1) {
//                        ConstantClass.displayMessageDialog(DashboardActivity.this, "Response", response.body().getMessage());
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

            public void onFailure(Call<ChangePasswordResponse> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                DashboardActivity dashboardActivity = DashboardActivity.this;
                ConstantClass.displayMessageDialog(dashboardActivity, dashboardActivity.getString(R.string.server_problem), t.getMessage());
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_mobile) {
            Intent intent = new Intent(this, MobileRechargeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_dth) {
            Intent intent2 = new Intent(this, DTHRechargeActivity.class);
            startActivity(intent2);
        } else if (id == R.id.nav_contactus) {
            Intent intent3 = new Intent(this, contact_activity.class);
            startActivity(intent3);
        } else if (id == R.id.nav_transfer) {
            Intent intent4 = new Intent(this, MoneyTransferActivity.class);
            startActivity(intent4);
        } else if (id == R.id.nav_policy) {
            showPolicyOptionsDialog(item);
            return true;

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
//            CheckView checkView = (CheckView) view.findViewById(R.id.check);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog alertDialog = builder.create();
            alertDialog.setView(view);
            AlertDialog finalAlertDialog = alertDialog;
            image_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.25
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    finalAlertDialog.dismiss();
                }
            });
            AlertDialog finalAlertDialog1 = alertDialog;
            btn_change_password.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.26
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    forgot_tpin.setVisibility(View.GONE);
                    if (edit_old_password.getText().toString().isEmpty()) {
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
                        DashboardActivity.this.getChangePassword(edit_old_password.getText().toString(), edit_new_password.getText().toString(), edit_confirm_password.getText().toString(), finalAlertDialog1);
                    }
                }
            });

//            if (Build.VERSION.SDK_INT >= 21) {
//                if (!DashboardActivity.this.isFinishing()) {
//                    alertDialog = new AlertDialog.Builder(DashboardActivity.this)
//                            .setTitle("Your Title")
//                            .setMessage("Your Message")
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // Handle positive button click
//                                }
//                            })
//                            .create();  // Create the AlertDialog instance
//                    alertDialog.show();  // Show the AlertDialog
//                }
//            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (!DashboardActivity.this.isFinishing()) {
                    alertDialog.create();
                    alertDialog.show();
                }
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
                } else if (id != R.id.nav_send) {
                    if (id == R.id.nav_logout) {
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else if (id == R.id.nav_chnagetpin) {
                        View view2 = getLayoutInflater().inflate(R.layout.layout_change_password, (ViewGroup) null);
                        TextView forgot_tpin2 = (TextView) view2.findViewById(R.id.forgot_tpin);
                        forgot_tpin2.setVisibility(View.GONE);
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
//                        final CheckView check = (CheckView) view2.findViewById(R.id.check);
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                        final AlertDialog alertDialog2 = builder2.create();
                        alertDialog2.setView(view2);
                        image_cancel2.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.27
                            @Override // android.view.View.OnClickListener
                            public void onClick(View v) {
                                alertDialog2.dismiss();
                            }
                        });
//                        forgot_tpin2.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.28
//                            @Override // android.view.View.OnClickListener
//                            public void onClick(View v) {
//                                DashboardActivity.this.getForgotTpin(check, mTxtTitle, alertDialog2);
//                                layout_hide.setVisibility(View.GONE);
//                                image_cancel2.setVisibility(View.GONE);
//                            }
//                        });
                        btn_change_password2.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.29
                            @Override // android.view.View.OnClickListener
                            public void onClick(View v) {

                                if (edit_old_password2.getText().toString().isEmpty()) {
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

    private void showPolicyOptionsDialog(MenuItem menuItem) {
        View actionView = menuItem.getActionView();
        if (actionView != null) {
            Spinner spinner = actionView.findViewById(R.id.menuspinner);

            // Set a click listener for the Spinner
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // Handle the selection as needed
                    String selectedOption = (String) parent.getItemAtPosition(position);
                    launchPolicyActivity(selectedOption);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Handle the case when nothing is selected
                }
            });

            // Populate the Spinner with your data
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.drwerpolicy, // Replace with your array resource or data source
                    android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);


        }
    }


    private void launchPolicyActivity(String selectedOption) {
        if ("User Agrement".equals(selectedOption)) {
            Intent intent = new Intent(this, User_AgrementActivity.class);
            intent.putExtra("selectedOption", selectedOption);
            startActivity(intent);
        } else if ("Privacy Policy".equals(selectedOption)) {
            Intent intent = new Intent(this, Privacy_PolicyActivity.class);
            intent.putExtra("selectedOption", selectedOption);
            startActivity(intent);

        } else if ("Terms and Condition".equals(selectedOption)) {
            Intent intent = new Intent(this, Privacy_PolicyActivity.class);
            intent.putExtra("selectedOption", selectedOption);
            startActivity(intent);
        }

        //another parameters are added here


    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }

    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    protected void onDestroy() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        super.onDestroy();
    }
}
