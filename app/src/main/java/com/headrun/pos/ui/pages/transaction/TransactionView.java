package com.headrun.pos.ui.pages.transaction;

import com.headrun.pos.model.TransactionModel;
import com.headrun.pos.ui.pages.base.BaseView;

import java.util.List;

public interface TransactionView extends BaseView {

    void showTransaction(List<TransactionModel> transactionModels);

    void hideProgress();

    void setPriceFromSales(String priceFromSales);

}
