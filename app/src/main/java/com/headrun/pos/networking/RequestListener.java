package com.headrun.pos.networking;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.gson.Gson;
import com.headrun.pos.model.RequestHeader;

public class RequestListener implements StringRequestListener {

    private OnStringResponse onStringResponse;

    public RequestListener(OnStringResponse onStringResponse) {
        this.onStringResponse = onStringResponse;
    }

    @Override
    public void onResponse(String response) {

        Log.e("NETWORK ", "-> " + response);
        try {

            RequestHeader requestHeader = new Gson().fromJson(response, RequestHeader.class);
            if (requestHeader.isSuc()) {
                onStringResponse.onSuc(response);
                Log.e("NETWORK SUC", "-> " + response);
            } else {
                onStringResponse.onFail(requestHeader.getCode(), requestHeader.getMsg());
                Log.e("NETWORK FAIL", "-> " + response);
            }
        } catch (Exception e) {
            onStringResponse.onErr(response);
            Log.e("NETWORK ERR", "-> " + e.getMessage());
        }
    }

    @Override
    public void onError(ANError anError) {

        Log.e("NTWRK ERR", "-> " + anError.getErrorBody() + ", " + anError.getErrorDetail());
        onStringResponse.onErr(anError.getErrorBody());
    }
}
