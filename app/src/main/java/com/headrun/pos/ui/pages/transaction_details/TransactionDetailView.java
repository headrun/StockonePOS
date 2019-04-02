package com.headrun.pos.ui.pages.transaction_details;

import com.headrun.pos.model.TransactionModel;
import com.headrun.pos.ui.pages.base.BaseView;

import java.util.List;

public interface TransactionDetailView  extends BaseView {

    void showTransactionDetails(List<TransactionModel> transactionModels);

    void hideProgress();

    String getDate();

    void showDate(String date);
}
