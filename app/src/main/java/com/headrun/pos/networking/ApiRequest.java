package com.headrun.pos.networking;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;

import java.util.HashMap;
import java.util.Map;

public class ApiRequest {

    public static void GET(String url, Map<String, String> params, OnStringResponse onStringResponse ){
        if(params == null){
            params = new HashMap<>();
        }
        Log.e("url", url);
        AndroidNetworking.get(url)
                .addPathParameter(params)
                .build()
                .getAsString(new RequestListener(onStringResponse));
    }


    public static void POST(String url, Map<String,String> params, OnStringResponse onStringResponse ){
        if (params == null){
            params = new HashMap<>();
        }
        Log.e("url", url);
        AndroidNetworking.post(url)
                .addBodyParameter(params)
                .build()
                .getAsString(new RequestListener(onStringResponse));
    }
}
