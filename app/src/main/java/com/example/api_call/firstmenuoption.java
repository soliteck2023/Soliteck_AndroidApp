package com.example.api_call;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link firstmenuoption#newInstance} factory method to
 * create an instance of this fragment.
 */
public class firstmenuoption extends Fragment {

    LinearLayout menuBank;
    LinearLayout linear_payment_transfer_report;
    LinearLayout Payment_request;
    LinearLayout Latest_Txn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public firstmenuoption() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment firstmenuoption.
     */
    // TODO: Rename and change types and number of parameters
    public static firstmenuoption newInstance(String param1, String param2) {
        firstmenuoption fragment = new firstmenuoption();
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

        View view = inflater.inflate(R.layout.fragment_firstmenuoption, container, false);


        menuBank = view.findViewById(R.id.first_linear);

        linear_payment_transfer_report =view.findViewById(R.id.linear_paytransfe_rreport);
        Payment_request= view.findViewById(R.id.pending_txn_report);
        Latest_Txn = view.findViewById(R.id.linear_latestReport);

        menuBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CompanyBankListActivity.class);
                startActivity(intent);

            }
        });

                linear_payment_transfer_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PaymentReceivedReportActivity.class);
                startActivity(intent);

            }
        });

                Latest_Txn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), LatestReportActivity.class);
                        startActivity(intent);

                    }
                });



        return view;
    }
}