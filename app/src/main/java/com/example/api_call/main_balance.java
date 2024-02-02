package com.example.api_call;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link main_balance#newInstance} factory method to
 * create an instance of this fragment.
 */
public class main_balance extends Fragment {

    LinearLayout linear_request;
    TextView textbalance;
    TextView textaepsBalance;
    private ProgressDialog progressDialog;

    ImageView image_refresh;

    String request_type = "";



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public main_balance() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment main_balance.
     */
    // TODO: Rename and change types and number of parameters
    public static main_balance newInstance(String param1, String param2) {
        main_balance fragment = new main_balance();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main_balance, container, false);

        linear_request= view.findViewById(R.id.linear_request);
        textbalance = view.findViewById(R.id.view_balance);
        textaepsBalance = view.findViewById(R.id.textaepsBalance);



        return view;
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
        final BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(view_types);
        dialog.show();
        type_history.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.38
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 16) {
                    type_history.setBackground(getContext().getResources().getDrawable(R.drawable.violet_button_background));
                    text_history.setTextColor(-1);
                    type_pending.setBackground(getContext().getResources().getDrawable(R.drawable.text_view_border));
                    text_pending.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    request_type = "history";
                    Intent intent2 = new Intent(getContext(), PayRequestHistoryActivity.class);
                    getContext().startActivity(intent2);
                }
            }
        });
        type_pending.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.39
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 16) {
                    type_pending.setBackground(getContext().getResources().getDrawable(R.drawable.violet_button_background));
                    text_pending.setTextColor(-1);
                    type_history.setBackground(getContext().getResources().getDrawable(R.drawable.text_view_border));
                    text_history.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    request_type = "form";
                    Intent intent = new Intent(getContext(), PaymentRequestActivity.class);
                    getContext().startActivity(intent);
                }
            }
        });
//        mBtn_request.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.40
//            @Override // android.view.View.OnClickListener
//            public void onClick(View view) {
//                if (request_type.equals("form")) {
//                    dialog.dismiss();
//                    Intent intent = new Intent(getContext(), PaymentRequestActivity.class);
//                    getContext().startActivity(intent);
//                } else if (request_type.equals("history")) {
//                    dialog.dismiss();
//                    Intent intent2 = new Intent(getContext(), PayRequestHistoryActivity.class);
//                    getContext().startActivity(intent2);
//                }
//            }
//        });
    }

    private void getUserBalance(TextView textbalance,TextView textaepsBalance) {
//        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(getContext().toString());
//        progressDialog.show();
        String username = PrefUtils.getFromPrefs(getContext(), ConstantClass.USERDETAILS.UserName, "");
        String password = PrefUtils.getFromPrefs(getContext(), ConstantClass.USERDETAILS.UserPassword, "");
        String device = PrefUtils.getFromPrefs(getContext(), ConstantClass.PROFILEDETAILS.DeviceId, "");
        String Token = PrefUtils.getFromPrefs(getContext(), ConstantClass.USERDETAILS.Token, "");
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
                            textbalance.setText("" + Main); //M: replace with empty
                            textaepsBalance.setText("A: 0.00");
                            PrefUtils.saveToPrefs(getContext(), "Wallet_Main_Balance", Main);
                            PrefUtils.saveToPrefs(getContext(), ConstantClass.USERDETAILS.AEPSBalance, "0.00");
                        } else if (Main.equals("null")) {
                            textbalance.setText(" 0.00"); //M: replace with empty
                            textaepsBalance.setText("" + Aeps); //A: replace with empty
                            PrefUtils.saveToPrefs(getContext(), "Wallet_Main_Balance", "0.00");
                            PrefUtils.saveToPrefs(getContext(), ConstantClass.USERDETAILS.AEPSBalance, Aeps);
                        } else {
                            textbalance.setText("" + Main); //M: replace with empty
                            textaepsBalance.setText("" + Aeps); //A: replace with empty
                            PrefUtils.saveToPrefs(getContext(), "Wallet_Main_Balance", Main);
                            PrefUtils.saveToPrefs(getContext(), ConstantClass.USERDETAILS.AEPSBalance, Aeps);
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

//                DashboardActivity dashboardActivity = DashboardActivity.this;
//                ConstantClass.displayMessageDialog(dashboardActivity, dashboardActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });
    }
}