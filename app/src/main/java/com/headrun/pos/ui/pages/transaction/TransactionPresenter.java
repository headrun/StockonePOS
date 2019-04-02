package com.headrun.pos.ui.pages.transaction;


import com.blankj.utilcode.util.LogUtils;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.model.TransactionModel;
import com.headrun.pos.utils.Constants;
import com.headrun.pos.ui.pages.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class TransactionPresenter extends BasePresenter<TransactionView> {

    private List<TransactionModel> list = new ArrayList<>();

    protected TransactionPresenter(TransactionView view) {
        super(view);
    }

    public void getAllTransaction() {
        getTransaction();
    }


    private void getTransaction(){

        MyApplication.get().getmFirebaseFirestore()
                .collection(Constants.TRANSACTIONS).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        list.clear();
                        int sales = 0;
                        double price = 0;
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            LogUtils.e(document.getId() + " => " + document.getData());
                            TransactionModel model = new TransactionModel();
                            model.setDate(document.getId());
                            model.setTransaction_total_price(document.getString("total_price"));
                            model.setTransaction_total_sales(document.getString("total_sales"));
                            list.add(model);
                            view.showTransaction(list);
                            try {
                                sales+=Integer.parseInt(document.getString("total_sales"));
                                price+= Double.parseDouble(document.getString("total_price"));
                            }catch (NumberFormatException e){

                            }
                        }
                        view.hideProgress();
                        view.setPriceFromSales("₹ "+price+ " from "+ sales+" sales");
                    } else {
                        LogUtils.e("Error getting documents: ", task.getException());
                        view.hideProgress();
                    }
                });
    }
}
