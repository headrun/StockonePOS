package com.headrun.pos.ui.pages.single_transactions;

import android.support.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.model.TransactionModel;
import com.headrun.pos.utils.Constants;
import com.headrun.pos.ui.pages.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class SingleTransactionPresenter extends BasePresenter<SingleTransactionView> {

    private List<TransactionModel> list = new ArrayList<>();

    protected SingleTransactionPresenter(SingleTransactionView view) {
        super(view);
    }

    public void getAllTransaction() {
        getTransaction();
    }

    private void getTransaction(){

        view.showDateTime(view.getDate(), view.getTime());
        view.showReceipt(view.getReceipt());
        view.paymentType(view.getPaymentType());
        view.total_price(view.getTotalPrice());

        MyApplication.get().getmFirebaseFirestore()
                .collection(Constants.TRANSACTIONS).document(view.getDate()).collection(Constants.RECEIPT)
                .document(view.getReceipt()).collection(Constants.SOLDITEMS).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            list.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                LogUtils.e(document.getId() + " => " + document.getData());
                                TransactionModel model = new TransactionModel();

                                model.setItem_qty(document.getString("item_quantity"));
                                model.setItem_name(document.getString("item_name"));
                                model.setItem_price(document.getString("item_price"));
                                list.add(model);

                            }
                            view.hideProgress();
                            view.showTransactionDetails(list);
                        } else {
                            LogUtils.e("Error getting documents: ", task.getException());
                            view.hideProgress();
                        }
                    }
                });
    }

}
