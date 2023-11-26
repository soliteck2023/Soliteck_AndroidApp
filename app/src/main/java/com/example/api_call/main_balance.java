package com.example.api_call;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link main_balance#newInstance} factory method to
 * create an instance of this fragment.
 */
public class main_balance extends Fragment {

    LinearLayout linear_request;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main_balance, container, false);

        linear_request= view.findViewById(R.id.reuest_balance);
        linear_request.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.DashboardActivity.22
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {


                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                Fragment fragment= new balance_reuest_fragment();
                Bundle bundle =new Bundle();
                fragment.setArguments(bundle);
                transaction.add(R.id.dash_freme, fragment);
                transaction.commit();


            }
        });

        return view;

    }
}