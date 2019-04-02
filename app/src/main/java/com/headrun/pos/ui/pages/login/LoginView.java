package com.headrun.pos.ui.pages.login;

import com.headrun.pos.ui.pages.base.BaseView;

public interface LoginView extends BaseView {

    String getUserid();
    String getUserPwd();
    void redirectPage();
    void errorMsg(String msg);
    void showProgressBar();
    void hideProgressBar();
    boolean validateLogin();

}
