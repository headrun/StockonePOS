package com.headrun.pos.ui.pages.transaction_details;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.headrun.pos.R;
import com.headrun.pos.adapter.TransactionDetailsAdapter;
import com.headrun.pos.model.TransactionModel;
import com.headrun.pos.utils.General;
import com.headrun.pos.ui.pages.base.BaseActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionDetails extends BaseActivity<TransactionDetailPresenter> implements TransactionDetailView {

    @BindView(R.id.toolbar_transaction_details) Toolbar toolbar;
    @BindView(R.id.rv_transaction_details) RecyclerView recyclerView;
    @BindView(R.id.avi_transaction_details) AVLoadingIndicatorView avi;
    @BindView(R.id.title) TextView title;
    TransactionDetailsAdapter adapter;
    String date;

    @NonNull
    @Override
    protected TransactionDetailPresenter createPresenter() {
        return new TransactionDetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);
        ButterKnife.bind(this);
        General.setToolbar(this, toolbar);

        init();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransactionDetailsAdapter(this);
        recyclerView.setAdapter(adapter);

        presenter.getAllTransaction();
    }

    private void init(){

        Intent extras = getIntent();
        Bundle bundle = extras.getExtras();
        if(bundle == null){
            ToastUtils.showLong("Some problem occurs.");
            finish();
            return;
        }
        date = bundle.getString("date");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showTransactionDetails(List<TransactionModel> transactionModels) {

        adapter.setList(transactionModels);
    }

    @Override
    public void hideProgress() {

        avi.hide();
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void showDate(String date) {

        title.setText(date);
    }
}
