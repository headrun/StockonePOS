package com.headrun.pos.ui.pages.user_details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.headrun.pos.R;
import com.headrun.pos.ui.dialog.UserDialog;
import com.headrun.pos.utils.General;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserDetails extends AppCompatActivity {

    @BindView(R.id.toolbar_user_details) Toolbar toolbar;
    @BindView(R.id.et_company_name) EditText et_company_name;
    @BindView(R.id.et_address_one) EditText et_address_one;
    @BindView(R.id.et_gst_no) EditText et_gst_no;
    @BindView(R.id.avi_user_details) AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);
        General.setToolbar(this, toolbar);

        avi.hide();


    }

    @OnClick(R.id.btn_enter_store)
    void onEnterStore(){

        String company_name = et_company_name.getText().toString();
        String address_one = et_address_one.getText().toString();
        String gst_no = et_gst_no.getText().toString();

        if (TextUtils.isEmpty(company_name)){
            ToastUtils.showLong("Please enter company name.");
            return;
        }
        if (TextUtils.isEmpty(address_one)){
            ToastUtils.showLong("Please enter address.");
            return;
        }
        if (TextUtils.isEmpty(gst_no)){
            ToastUtils.showLong("Please enter GST no.");
            return;
        }

        avi.show();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_company_users)
    void onCompanyUsers(){

        UserDialog userDialog = new UserDialog(this);
        userDialog.show();
    }
}
