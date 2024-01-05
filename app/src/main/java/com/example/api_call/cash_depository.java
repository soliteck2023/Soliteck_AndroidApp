package com.example.api_call;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cash_depository#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cash_depository extends Fragment {

    TextView textaepsBalance;
    private ProgressDialog progressDialog;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public cash_depository() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cash_depository.
     */
    // TODO: Rename and change types and number of parameters
    public static cash_depository newInstance(String param1, String param2) {
        cash_depository fragment = new cash_depository();
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
        View view= inflater.inflate(R.layout.fragment_cash_depository, container, false);

        textaepsBalance = view.findViewById(R.id.textaepsBalance);
        textaepsBalance.setText(getArguments().getString("textbalance"));
        getUserBalance(textaepsBalance);
        return view;

    }

    private void getUserBalance(TextView textaepsBalance) {
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
//                            textbalance.setText("M: " + Main);
                            textaepsBalance.setText("A: 0.00");
                            PrefUtils.saveToPrefs(getContext(), "Wallet_Main_Balance", Main);
                            PrefUtils.saveToPrefs(getContext(), ConstantClass.USERDETAILS.AEPSBalance, "0.00");
                        } else if (Main.equals("null")) {
//                            textbalance.setText("M: 0.00");
                           textaepsBalance.setText("A: " + Aeps);
                            PrefUtils.saveToPrefs(getContext(), "Wallet_Main_Balance", "0.00");
                            PrefUtils.saveToPrefs(getContext(), ConstantClass.USERDETAILS.AEPSBalance, Aeps);
                        } else {
//                            textbalance.setText("M: " + Main);
                            textaepsBalance.setText("A: " + Aeps);
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