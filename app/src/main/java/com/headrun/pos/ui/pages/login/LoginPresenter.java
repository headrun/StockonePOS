package com.headrun.pos.ui.pages.login;

import android.content.Context;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.utils.DbUtils;
import com.headrun.pos.ui.pages.base.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView view) {
        super(view);
    }

    public void alreadyLoggedIn(){

        if (MyApplication.get().getmAuth().getCurrentUser() != null){
            if (view != null)
            view.redirectPage();
        }
    }


    public void onSignInButtonClicked() {

        if (view != null) {

            view.showProgressBar();

            if (view.validateLogin()) {

                MyApplication.get().getmAuth().signInWithEmailAndPassword(view.getUserid(), view.getUserPwd())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    LogUtils.e("signInWithEmail:success");

                                    DbUtils.storeUserSessionTime(System.currentTimeMillis());

                                    view.redirectPage();
                                    view.hideProgressBar();


                                } else {
                                    // If sign in fails, display a message to the user.
                                    LogUtils.e("signInWithEmail:failure", task.getException());
                                    view.hideProgressBar();
                                    view.errorMsg("Email Id does not exist.");
                                }
                            }
                        });
            }
        }
    }


}
