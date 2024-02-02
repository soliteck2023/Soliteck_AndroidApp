package com.example.api_call;

import android.os.Build;
import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class balance_reuest_fragment extends Fragment {

    private String request_type = "";
    private String UserName = "";
    private String AuthKey = "";
    private String MerchantId = "";
    private String AgentId = "";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public balance_reuest_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment balance_reuest_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static balance_reuest_fragment newInstance(String param1, String param2) {
        balance_reuest_fragment fragment = new balance_reuest_fragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_balance_reuest_fragment, container, false);
        TextView mText_remark = (TextView) view.findViewById(R.id.text_remark);
        final LinearLayout type_history = (LinearLayout) view.findViewById(R.id.type_history);
        final TextView text_history = (TextView) view.findViewById(R.id.text_history);
        final LinearLayout type_pending = (LinearLayout) view.findViewById(R.id.type_pending);
        final TextView text_pending = (TextView) view.findViewById(R.id.text_pending);
        Button mBtn_request = (Button) view.findViewById(R.id.btn_request);
        mText_remark.setText("Payment Request");
        text_pending.setText("Request Form");
        final BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(view);
        dialog.show();


        type_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>=16){
                    type_history.setBackground(getActivity().getResources().getDrawable(R.drawable.violet_button_background));
                    text_history.setTextColor(-1);
                    type_pending.setBackground(getActivity().getResources().getDrawable(R.drawable.text_view_border));
                    text_pending.setTextColor(ViewCompat.MEASURED_STATE_MASK);
//                    getActivity().request_type = "history";
                }


            }
        });

        type_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>=16){
                    type_pending.setBackground(getActivity().getResources().getDrawable(R.drawable.violet_button_background));
                    text_pending.setTextColor(-1);
                    type_history.setBackground(getActivity().getResources().getDrawable(R.drawable.text_view_border));
                    text_history.setTextColor(ViewCompat.MEASURED_STATE_MASK);
//                    getActivity().request_type = "history";
                }
            }
        });





    return view;

    }
}