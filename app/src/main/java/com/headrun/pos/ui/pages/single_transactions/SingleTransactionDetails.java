package com.headrun.pos.ui.pages.single_transactions;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.headrun.pos.R;
import com.headrun.pos.adapter.SingleTransactionAdapter;
import com.headrun.pos.model.TransactionModel;
import com.headrun.pos.utils.General;
import com.headrun.pos.ui.pages.base.BaseActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleTransactionDetails extends BaseActivity <SingleTransactionPresenter> implements SingleTransactionView {

    @BindView(R.id.toolbar_single_trans) Toolbar toolbar;
    @BindView(R.id.rv_single_transaction) RecyclerView recyclerView;
    @BindView(R.id.payment_type) TextView payment_tp;
    @BindView(R.id.receipt_number) TextView receipt_number;
    @BindView(R.id.single_trans_amt) TextView single_trans_amt;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.avi) AVLoadingIndicatorView avi;
    @BindView(R.id.img_receipt) ImageView img_receipt;
    SingleTransactionAdapter adapter;
    private List<TransactionModel> list = new ArrayList<>();

    String date, time, payment_type, receipt_no, total_price;

    @NonNull
    @Override
    protected SingleTransactionPresenter createPresenter() {
        return new SingleTransactionPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_transaction_details);
        ButterKnife.bind(this);
        General.setToolbar(this, toolbar);

        init();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SingleTransactionAdapter(this);
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
        time = bundle.getString("time");
        payment_type = bundle.getString("payment_type");
        receipt_no = bundle.getString("receipt_no");
        total_price = bundle.getString("total_price");

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
    public void showDateTime(String date1, String time) {

        Date date = new Date(Long.parseLong(time));
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("dd MMMM,yy");
        String getTime = format.format(date);
        String getDate = format1.format(date);
        title.setText(getDate+" at "+ getTime);
    }

    @Override
    public void showReceipt(String receipt) {

        receipt_number.setText("Receipt: "+receipt);
    }

    @Override
    public void paymentType(String payment_type) {

        payment_tp.setText(payment_type);
    }

    @Override
    public void total_price(String total_price) {

        single_trans_amt.setText("₹ "+total_price);
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public String getReceipt() {
        return receipt_no;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public String getPaymentType() {
        return payment_type;
    }

    @Override
    public String getTotalPrice() {
        return total_price;
    }

//    private void getReceipt(){
//
//        MyApplication.get().getmFirebaseFirestore()
//                .collection(Constants.RECEIPT).document(receipt_no).get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//
//                        img_receipt.setVisibility(View.VISIBLE);
//                        String image_url = documentSnapshot.getString("receipt");
//                        Glide.with(SingleTransactionDetails.this).load(image_url).into(img_receipt);
//
//                    }
//                });
//    }

    @OnClick(R.id.receipt_btn)
    void onTransReceiptBtn(){

    }
}
