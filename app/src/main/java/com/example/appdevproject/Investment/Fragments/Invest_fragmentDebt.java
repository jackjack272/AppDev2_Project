package com.example.appdevproject.Investment.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdevproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Invest_fragmentDebt#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Invest_fragmentDebt extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Invest_fragmentDebt() {
        // Required empty public constructor
    }

    public static Invest_fragmentDebt newInstance(String param1, String param2) {
        Invest_fragmentDebt fragment = new Invest_fragmentDebt();
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
        return inflater.inflate(R.layout.fragment_invest__debt, container, false);
    }

//my code

    TextView output;
    Button btn;
    EditText editText;


    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState){


//        makeAssocications();
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "fragment 1 ", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void makeAssocications(){
        output= getView().findViewById(R.id.invest_debt_heading);
        btn= getView().findViewById(R.id.invest_debt_btn);
        editText=getView().findViewById(R.id.invest_debt_inputField);
    }




}