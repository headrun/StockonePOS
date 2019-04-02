package com.headrun.pos.ui.pages.transaction_details;

import com.blankj.utilcode.util.LogUtils;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.model.TransactionModel;
import com.headrun.pos.utils.Constants;
import com.headrun.pos.ui.pages.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class TransactionDetailPresenter extends BasePresenter<TransactionDetailView> {

    private List<TransactionModel> list = new ArrayList<>();

    protected TransactionDetailPresenter(TransactionDetailView view) {
        super(view);
    }

    public void getAllTransaction() {

        getTransaction();
    }

    private void getTransaction(){

        view.showDate(view.getDate());

        MyApplication.get().getmFirebaseFirestore()
                .collection(Constants.TRANSACTIONS).document(view.getDate()).collection(Constants.RECEIPT).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        list.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            LogUtils.e(document.getId() + " => " + document.getData());
                            TransactionModel model = new TransactionModel();
                            model.setTotal_price(document.getString("total_price"));
                            model.setTotal_items(document.getString("total_items"));
                            model.setReceipt_no(document.getString("receipt_no"));
                            model.setTime(document.getString("time"));
                            model.setDate(view.getDate());
                            model.setPayment_type(document.getString("payment_type"));
                            list.add(model);
                        }
                        view.hideProgress();
                        view.showTransactionDetails(list);
                    } else {
                        LogUtils.e("Error getting documents: ", task.getException());
                        view.hideProgress();
                    }
                });
    }
}
