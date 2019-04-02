package com.headrun.pos.networking;

import java.util.HashMap;
import java.util.Map;

public class UserApi {

    public static void pay(String mId, String orderId, String custId,
                                String channelId,String txnAmount,String website,
                           String callbackUrl, String industryTypeId, OnStringResponse onStringResponse){
        Map<String, String> params = new HashMap<>();
        params.put("MID", mId);
        params.put("ORDER_ID", orderId);
        params.put("CUST_ID", custId);
        params.put("CHANNEL_ID", channelId);
        params.put("TXN_AMOUNT", txnAmount);
        params.put("WEBSITE", website);
        params.put("CALLBACK_URL", callbackUrl);
        params.put("INDUSTRY_TYPE_ID", industryTypeId);

        ApiRequest.POST(Apis.BASE_URL, params, onStringResponse);
    }
}
