package com.headrun.pos.ui.pages.login;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.headrun.pos.R;
import com.headrun.pos.utils.ActivityManager;
import com.headrun.pos.ui.pages.base.BaseActivity;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.login_bg) ImageView bg;
    @BindView(R.id.et_login_email) EditText et_email;
    @BindView(R.id.et_login_password) EditText et_password;
    @BindView(R.id.avi_login) AVLoadingIndicatorView avi;
    @BindView(R.id.btn_login) Button btn_login;

    @NonNull
    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Glide.with(this).load(R.drawable.bg).into(bg);

        presenter.alreadyLoggedIn();


    }


    @OnClick(R.id.please_register)
    void onRegister(){

        ActivityManager.REGISTER(this);
        finish();
    }

    @OnClick(R.id.btn_login)
    void onLoginClicked(){

        presenter.onSignInButtonClicked();
    }

    @Override
    public String getUserid() {
        return et_email.getText().toString();
    }

    @Override
    public String getUserPwd() {
        return et_password.getText().toString();
    }

    @Override
    public void redirectPage() {

        ActivityManager.HOME(Login.this);
        finish();
    }

    @Override
    public void errorMsg(String msg) {

        ToastUtils.showLong(msg);

    }

    @Override
    public void showProgressBar() {

        btn_login.setVisibility(View.GONE);
        avi.setVisibility(View.VISIBLE);
        avi.show();
    }

    @Override
    public void hideProgressBar() {

        avi.hide();
        btn_login.setVisibility(View.VISIBLE);
        avi.setVisibility(View.GONE);
    }

    @Override
    public boolean validateLogin() {

        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        if (TextUtils.isEmpty(email)){
            ToastUtils.showLong("Please enter email id");
            avi.hide();
            btn_login.setVisibility(View.VISIBLE);
            avi.setVisibility(View.GONE);
            return false;
        }

        if (TextUtils.isEmpty(password)){
            ToastUtils.showLong("Please enter password");
            avi.hide();
            btn_login.setVisibility(View.VISIBLE);
            avi.setVisibility(View.GONE);
            return false;
        }
        return true;
    }
}
