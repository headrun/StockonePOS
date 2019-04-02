package com.headrun.pos.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.headrun.pos.R;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.callback.OnCouponClicked;
import com.headrun.pos.utils.Constants;

public class CouponDialog {

    private Context context;
    private Dialog dialog;
    private ImageView img_cross;
    private EditText et_enter_coupon;
    private Button btn_apply_coupon;
    private OnCouponClicked onCouponClicked;


    public CouponDialog(Context context, OnCouponClicked onCouponClicked) {
        this.context = context;
        this.onCouponClicked = onCouponClicked;
        init();
    }

    private void init(){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.coupon_dialog);
        dialog.setCancelable(false);

        img_cross = dialog.findViewById(R.id.img_cross);
        et_enter_coupon = dialog.findViewById(R.id.et_enter_coupon);
        btn_apply_coupon = dialog.findViewById(R.id.btn_apply_coupon);


        dialog.setCanceledOnTouchOutside(true);

        if(dialog.getWindow() != null) {
//            dialog.getWindow().getAttributes().windowAnimations = R.style.Slide_Up_Down;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });

        btn_apply_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String coupon_code = et_enter_coupon.getText().toString();

                if (TextUtils.isEmpty(coupon_code)){

                    ToastUtils.showLong("Please enter code.");
                    return;
                }

                getCouponCode(coupon_code);

            }
        });
    }


    public void show(){
        try {
            dialog.show();
        } catch (Exception e){

        }
    }

    public void close(){
        dialog.hide();
    }

    private void getCouponCode(final String coupon_code){

        MyApplication.get().getmFirebaseFirestore()
                .collection(Constants.SCRATCH_CARD).whereEqualTo("coupon_code", coupon_code)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.getResult().isEmpty()){
                            ToastUtils.showLong("Invalid coupon code.");
                        }else {
                            String discount_amt = null;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                discount_amt = document.getString("discount_amt");
                            }
                            ToastUtils.showLong("Successfully applied.");
                            onCouponClicked.onCouponPrice(Double.parseDouble(discount_amt), coupon_code);
                            dialog.hide();

                        }
                    }
                }      );
    }
}
