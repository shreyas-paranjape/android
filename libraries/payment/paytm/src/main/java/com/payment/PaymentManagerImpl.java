package com.payment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.paytm.pgsdk.PaytmMerchant;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PaymentManagerImpl implements PaymentManager {

    private final Context context;
    private PaytmPGService service = null;

    private static final String checksumGenerationUrl =
            "https://pguat.paytm.com/merchant-chksum/ChecksumGenerator";
    private static final String checksumVerificationUrl =
            "https://pguat.paytm.com/merchant-chksum/ValidateChksum";

    private int randomInt = 0;

    public PaymentManagerImpl(Context context) {
        this.context = context;
        service = PaytmPGService.getStagingService();
    }


    @Override
    public void pay() {
        PaytmMerchant Merchant = new PaytmMerchant(checksumGenerationUrl, checksumVerificationUrl);
        PaytmOrder Order = new PaytmOrder(getRequestParamMap());
        service.initialize(Order, Merchant, null);
        service.startPaymentTransaction(context, false, false, getInPaymentTransactionCallback());
    }

    @NonNull
    private PaytmPaymentTransactionCallback getInPaymentTransactionCallback() {
        return new PaytmPaymentTransactionCallback() {

            @Override
            public void onTransactionSuccess(Bundle inResponse) {

            }

            @Override
            public void onTransactionFailure(String inErrorMessage, Bundle inResponse) {
                Log.i("Error", "onTransactionFailure :" + inErrorMessage);
            }

            @Override
            public void networkNotAvailable() {

            }


            @Override
            public void clientAuthenticationFailed(String inErrorMessage) {
                Log.i("Error", "clientAuthenticationFailed :" + inErrorMessage);
            }

            @Override
            public void someUIErrorOccurred(String s) {

            }

            @Override
            public void onErrorLoadingWebPage(int i, String s, String s1) {

            }


        };
    }

    @NonNull
    private Map<String, String> getRequestParamMap() {
        Map<String, String> paramMap = new HashMap<>();
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(1000);

        //these are mandatory parameters
        paramMap.put("REQUEST_TYPE", "DEFAULT");
        paramMap.put("ORDER_ID", String.valueOf(randomInt));
        paramMap.put("MID", "klbGlV59135347348753");
        paramMap.put("CUST_ID", "CUST123");
        paramMap.put("CHANNEL_ID", "WAP");
        paramMap.put("INDUSTRY_TYPE_ID", "Retail");
        paramMap.put("WEBSITE", "paytm");
        paramMap.put("TXN_AMOUNT", "1");
        paramMap.put("THEME", "merchant");
        return paramMap;
    }


}
