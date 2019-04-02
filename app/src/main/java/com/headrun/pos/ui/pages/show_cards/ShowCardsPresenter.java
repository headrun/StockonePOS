package com.headrun.pos.ui.pages.show_cards;

import android.support.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.model.ScratchModel;
import com.headrun.pos.ui.pages.base.BasePresenter;
import com.headrun.pos.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ShowCardsPresenter extends BasePresenter<ShowCardsView> {


    List<ScratchModel> list;

    protected ShowCardsPresenter(ShowCardsView view) {
        super(view);
        list = new ArrayList<>();
    }

    public void getCouponCodes(){

        MyApplication.get().getmFirebaseFirestore()
                .collection(Constants.SCRATCH_CARD).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        list.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            ScratchModel model = new ScratchModel();
                            model.setCoupon_code(document.getString("coupon_code"));
                            model.setDiscount_amt(document.getString("discount_amt"));
                            model.setFrom_date(document.getString("from_date"));
                            model.setTo_date(document.getString("to_date"));
                            list.add(model);
                        }
                        view.hideProgressBar();
                        view.showCards(list);
                    } else {
                        LogUtils.e("Error getting documents: ", task.getException());
                        view.hideProgressBar();
                    }
                });
    }
}
