package com.headrun.pos.ui.pages.scratch_card;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.headrun.pos.R;
import com.headrun.pos.utils.ActivityManager;
import com.headrun.pos.utils.General;
import com.headrun.pos.ui.pages.base.BaseActivity;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScratchCard extends BaseActivity<ScratchPresenter> implements DatePickerDialog.OnDateSetListener, ScratchView {

    @BindView(R.id.toolbar_scratch_card) Toolbar toolbar;
    @BindView(R.id.scratch_bg) ImageView scratch_bg;
    @BindView(R.id.et_from_date) EditText et_from_date;
    @BindView(R.id.et_to_date) EditText et_to_date;
    @BindView(R.id.et_disc_amt) EditText et_disc_amt;
    @BindView(R.id.scratch_btn) Button scratch_btn;
    @BindView(R.id.hide_scratch_btn) Button hide_scratch_btn;

    boolean from_date;
    boolean to_date;

    @NonNull
    @Override
    protected ScratchPresenter createPresenter() {
        return new ScratchPresenter(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_card);
        ButterKnife.bind(this);
        General.setToolbar(this, toolbar);

        Glide.with(this).load(R.drawable.bg).into(scratch_bg);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.show_coupon)
    void onShowCoupon(){

        ActivityManager.SHOW_CARDS(this);

    }

    @OnClick(R.id.et_from_date)
    void onFromDate(){

        from_date = true;
        presenter.initializeCalendar(this);

    }

    @OnClick(R.id.et_to_date)
    void onToDate(){

        to_date = true;
        presenter.initializeCalendar(this);

    }

    @OnClick(R.id.scratch_btn)
    void onScratchBtnSubmit(){

        presenter.onScratchButtonClicked();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (from_date){
            et_from_date.setText(simpleDateFormat.format(calendar.getTime()));
            from_date = false;
        }else if (to_date){
            et_to_date.setText(simpleDateFormat.format(calendar.getTime()));
            to_date = false;
        }

    }

    @Override
    public boolean validateLogin() {
        String from_date = et_from_date.getText().toString();
        String to_date = et_to_date.getText().toString();
        String discount_amt = et_disc_amt.getText().toString();

        if (TextUtils.isEmpty(from_date)){
            ToastUtils.showLong("Please mention from date.");
            return false;
        }
        if (TextUtils.isEmpty(to_date)){
            ToastUtils.showLong("Please mention to date.");
            return false;
        }
        if (TextUtils.isEmpty(discount_amt)){
            ToastUtils.showLong("Please mention discount amount.");
            return false;
        }
        return true;
    }

    @Override
    public String getFromDate() {
        return et_from_date.getText().toString();
    }

    @Override
    public String getToDate() {
        return et_to_date.getText().toString();
    }

    @Override
    public String getDiscountAmt() {
        return et_disc_amt.getText().toString();
    }

    @Override
    public void hideButtons() {

        hide_scratch_btn.setVisibility(View.VISIBLE);
        scratch_btn.setVisibility(View.GONE);
    }

    @Override
    public void showButtons() {

        hide_scratch_btn.setVisibility(View.GONE);
        scratch_btn.setVisibility(View.VISIBLE);

        et_disc_amt.getText().clear();
        et_from_date.getText().clear();
        et_to_date.getText().clear();
    }
}
