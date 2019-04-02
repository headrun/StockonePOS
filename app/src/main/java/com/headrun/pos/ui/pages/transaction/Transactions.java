package com.headrun.pos.ui.pages.transaction;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.headrun.pos.R;
import com.headrun.pos.adapter.TransactionAdapter;
import com.headrun.pos.model.TransactionModel;
import com.headrun.pos.utils.General;
import com.headrun.pos.ui.pages.base.BaseActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Transactions extends BaseActivity<TransactionPresenter> implements TransactionView {

    @BindView(R.id.toolbar_transaction) Toolbar toolbar;
    @BindView(R.id.rv_transaction) RecyclerView recyclerView;
    @BindView(R.id.transaction_sales_total) TextView sales_total;
    @BindView(R.id.avi_transaction) AVLoadingIndicatorView avi;
    TransactionAdapter adapter;

    @NonNull
    @Override
    protected TransactionPresenter createPresenter() {
        return new TransactionPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        ButterKnife.bind(this);
        General.setToolbar(this, toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransactionAdapter(this);
        recyclerView.setAdapter(adapter);

        presenter.getAllTransaction();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showTransaction(List<TransactionModel> transactionModels) {
        adapter.setList(transactionModels);
    }

    @Override
    public void hideProgress() {
        avi.hide();
    }

    @Override
    public void setPriceFromSales(String priceFromSales) {

        sales_total.setText(priceFromSales);
    }
}
