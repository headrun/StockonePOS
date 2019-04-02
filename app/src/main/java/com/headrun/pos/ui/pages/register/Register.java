package com.headrun.pos.ui.pages.register;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.headrun.pos.R;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.model.UserModel;
import com.headrun.pos.utils.ActivityManager;
import com.headrun.pos.utils.Constants;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Register extends AppCompatActivity {

    @BindView(R.id.signup_bg) ImageView bg;
    @BindView(R.id.et_email) EditText et_email;
    @BindView(R.id.et_password) EditText et_password;
    @BindView(R.id.et_full_name) EditText et_full_name;
    @BindView(R.id.et_phone_no) EditText et_phone_no;
    @BindView(R.id.avi_register) AVLoadingIndicatorView avi;
    @BindView(R.id.btn_register) Button btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        Glide.with(this).load(R.drawable.bg).into(bg);
    }

    @OnClick(R.id.please_login)
    void onLoginClick(){

        ActivityManager.LOGIN(this);
        finish();
    }

    @OnClick(R.id.btn_register)
    void onRegister(){

        final String email = et_email.getText().toString();
        final String password = et_password.getText().toString();
        final String full_name = et_full_name.getText().toString();
        final String phone_no = et_phone_no.getText().toString();

        if (TextUtils.isEmpty(email)){
            ToastUtils.showLong("Please enter email Id");
            return;
        }
        if (TextUtils.isEmpty(password)){
            ToastUtils.showLong("Please enter password");
            return;
        }
        if (TextUtils.isEmpty(full_name)){
            ToastUtils.showLong("Please enter store name");
            return;
        }
        if (TextUtils.isEmpty(phone_no)){
            ToastUtils.showLong("Please enter phone no");
            return;
        }

        btn_register.setVisibility(View.GONE);
        avi.setVisibility(View.VISIBLE);
        avi.show();


        MyApplication.get().getmAuth().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            LogUtils.e("createUserWithEmail:success");
                            FirebaseUser user = MyApplication.get().getmAuth().getCurrentUser();

                            UserModel model = new UserModel();
                            model.setFull_name(full_name);
                            model.setTime(System.currentTimeMillis());
                            model.setEmail_id(email);
                            model.setPassword(password);
                            model.setRole("Admin");
                            model.setPhone_no(phone_no);
                            if (user != null)
                            model.setUser_id(user.getUid());

                            MyApplication.get().getmFirebaseFirestore().collection(Constants.USER_DETAILS)
                                    .document(user.getUid())
                                    .set(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    ActivityManager.HOME(Register.this);
                                    finish();
                                    avi.hide();
                                    btn_register.setVisibility(View.VISIBLE);
                                    avi.setVisibility(View.GONE);
                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user.
                            LogUtils.e("createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            avi.hide();
                            btn_register.setVisibility(View.VISIBLE);
                            avi.setVisibility(View.GONE);
                        }
                    }
            });
    }
}
