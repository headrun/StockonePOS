package com.headrun.pos.ui.pages.scratch_card;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.headrun.pos.R;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.model.ScratchModel;
import com.headrun.pos.utils.Constants;
import com.headrun.pos.utils.General;
import com.headrun.pos.ui.pages.base.BasePresenter;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScratchPresenter extends BasePresenter<ScratchView> {

    private Context context;

    protected ScratchPresenter(ScratchView view, Context context) {
        super(view);
        this.context = context;
    }

    public void onScratchButtonClicked(){

        if (view.validateLogin()){

            view.hideButtons();

            ScratchModel model = new ScratchModel();
            model.setCreated_at(System.currentTimeMillis());
            model.setFrom_date(view.getFromDate());
            model.setTo_date(view.getToDate());
            model.setDiscount_amt(view.getDiscountAmt());
            model.setUser_id(MyApplication.get().getmAuth().getCurrentUser().getUid());
            model.setCoupon_code("POS"+ General.randomString(3));

            MyApplication.get().getmFirebaseFirestore().collection(Constants.SCRATCH_CARD)
                    .document().set(model)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            ToastUtils.showLong("Coupon code generated successfully");

                            view.showButtons();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    ToastUtils.showLong("Oops something went wrong!");

                    view.showButtons();
                }
            });
        }
    }

    public void initializeCalendar(ScratchCard scratchCard){

        Date date = new Date(System.currentTimeMillis());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("dd");
        final String getYear = format.format(date);
        final String getDay = format1.format(date);

        int month = Calendar.getInstance().get(Calendar.MONTH);

        new SpinnerDatePickerDialogBuilder()
                .context(context)
                .callback(scratchCard)
                .spinnerTheme(R.style.NumberPickerStyle)
                .showTitle(true)
                .showDaySpinner(true)
                .defaultDate(Integer.parseInt(getYear), month, Integer.parseInt(getDay))
                .maxDate(2024, 0, 1)
                .minDate(2000, 0, 1)
                .build()
                .show();
    }
}
