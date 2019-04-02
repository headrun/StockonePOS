package com.headrun.pos.ui.pages.scan_barcode;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.ui.pages.base.BasePresenter;
import com.headrun.pos.utils.Constants;

public class ScanBarcodePresenter extends BasePresenter<ScanBarcodeView> {


    protected ScanBarcodePresenter(ScanBarcodeView view) {
        super(view);
    }

    public void checkProducts(String product_id){

        MyApplication.get().getmFirebaseFirestore()
                .collection(Constants.PRODUCTS).document(MyApplication.get().getmAuth().getCurrentUser().getUid())
                .collection(Constants.CREATED_PRODUCTS).whereEqualTo("product_id", product_id)
                .get()
                .addOnCompleteListener(task -> {

                    LogUtils.e(task.getResult().isEmpty());

                    if (task.getResult().isEmpty()){
                        view.ifTaskEmpty();
                    }else {
                        view.ifTaskNotEmpty(product_id);
                    }
                });


    }

    public void initializeBarcode(){

        view.initializeBarcode();
    }
}
