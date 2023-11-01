package com.example.appdevproject.Investment.Fragments.VolySingleton;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VollySingleton {
    private RequestQueue requestQueue;
    private static VollySingleton instace;

    private VollySingleton (Context context){
        requestQueue= Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VollySingleton getInstace(Context context){
        if(instace== null){
            instace= new VollySingleton(context);
        }
        return  instace;
    }
    public RequestQueue getRequestQueue(){return  requestQueue;}

}

