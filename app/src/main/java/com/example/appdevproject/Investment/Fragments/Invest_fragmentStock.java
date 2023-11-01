package com.example.appdevproject.Investment.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.appdevproject.Investment.Fragments.VolySingleton.VollySingleton;
import com.example.appdevproject.Investment.Models.Invest_Stock;
import com.example.appdevproject.R;
import com.example.appdevproject.Utility.ProjectDb;

import org.json.JSONArray;
import org.json.JSONObject;

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
     *      this call will give me div, need to find out the avg close open for the month.
     *
     *  for canadian stocks need TSX:symbol
     */

    private static String TAG=Invest_fragmentStock.class.getSimpleName();


    EditText stockTicker, stockQuanitiy;
    Button save;
    ProjectDb myDb;
    RequestQueue requestQueue;


    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState){
        makeAssociations();
        presetValues("xbb"); //assumption of Toronto Stock Exchange.

        requestQueue= VollySingleton.getInstace(getContext()).getRequestQueue();



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ticker= stockTicker.getText().toString();
                String url=makeAPIUrl(ticker,"AXGKAKRXD6N4OHYY");

                Invest_Stock stock= makeApiCall(url);
                if(stock==null){
                    return;
                }
//                myDb.stock_makeOne(stock);

            }
        });




    }

    protected Invest_Stock makeApiCall(String url){
        Invest_Stock myStock=null;

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Log.e(TAG, response.toString());

//                    JSONArray myArray=response.getJSONArray("Weekly Adjusted Time Series");
//                    Log.e(TAG,"inside api call");
//                    Log.e(TAG, myArray.toString());

                    JSONArray timeSeries= response.getJSONArray("Monthly Adjusted Time Series");
                    Log.e(TAG,timeSeries.toString());









                }catch (Exception e){
                    e.printStackTrace();
                    Log.e(TAG,"dev, bad call");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "is this stock on TSX?", Toast.LENGTH_SHORT).show();

                Log.e(TAG, error.toString());
            }
        });

        requestQueue.add(jsonObjectRequest);
        return myStock;
    }

    private String makeAPIUrl(String ticker, String apiKey){
        String url="https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY_ADJUSTED" +
                "&symbol=TSX:"+ticker+"" +
                "&apikey="+apiKey;

        return url;
    }


    private void presetValues(String ticker){
        stockTicker.setText(ticker);
        stockQuanitiy.setText("22");
    }

    private void makeAssociations(){
        stockTicker= getView().findViewById(R.id.invest_stock_ticker);
        stockQuanitiy= getView().findViewById(R.id.invest_stock_quantity);

        save=getView().findViewById(R.id.invest_stock_save);
        myDb= new ProjectDb(getContext());
    }



}