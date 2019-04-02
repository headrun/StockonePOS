package com.headrun.pos.ui.pages.single_transactions;

import com.headrun.pos.model.TransactionModel;
import com.headrun.pos.ui.pages.base.BaseView;

import java.util.List;

public interface SingleTransactionView extends BaseView {

    void showTransactionDetails(List<TransactionModel> transactionModels);
    void hideProgress();
    void showDateTime(String date, String time);
    void showReceipt(String receipt);
    void paymentType(String payment_type);
    void total_price(String total_price);
    String getDate();
    String getReceipt();
    String getTime();
    String getPaymentType();
    String getTotalPrice();

}
