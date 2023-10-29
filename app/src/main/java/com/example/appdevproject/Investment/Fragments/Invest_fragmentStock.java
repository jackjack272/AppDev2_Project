package com.example.appdevproject.Investment.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appdevproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Invest_fragmentStock#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Invest_fragmentStock extends Fragment {
//boiler plate - auto generation
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public Invest_fragmentStock() {
        // Required empty public constructor
    }
    public static Invest_fragmentStock newInstance(String param1, String param2) {
        Invest_fragmentStock fragment = new Invest_fragmentStock();
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
        return inflater.inflate(R.layout.fragment_invest_stock, container, false);
    }

// -------------coding area

    /**
     * api: https://www.alphavantage.co/documentation/
     * key: AXGKAKRXD6N4OHYY
     *
     *
     * https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY_ADJUSTED&symbol=TSX:XDIV&apikey=AXGKAKRXD6N4OHYY
     *  for canadian stocks need TSX:symbol
     *
     */


    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState){


    }

    protected void makeApiCall(){



    }



}